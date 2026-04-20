package com.kh.spring09.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring09.dao.BoardDao;
import com.kh.spring09.dto.BoardDto;
import com.kh.spring09.exception.GetOutException;
import com.kh.spring09.exception.TargetNotfoundException;
import com.kh.spring09.exception.WhoAreYouException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//본인 소유의 글일 경우만 통과시키기

@Service
public class BoardOwnerInterceptor implements HandlerInterceptor {
	@Autowired
	private BoardDao boardDao;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		//1. 파라미터에 boardNo가 없으면 차단
		String boardNoStr = request.getParameter("boardNo");
		if(boardNoStr == null) {
			throw new TargetNotfoundException("존재 하지 않는 글");
		}
		//2. 로그인된 사용자가 아니면 차단
		HttpSession session = request.getSession();
		String loginId = (String)session.getAttribute("loginId");
		if(loginId == null) {
			throw new WhoAreYouException();
		}
		
		//3. 존재하지 않는 글 차단
		long boardNo = Long.parseLong(boardNoStr);
		BoardDto boardDto = boardDao.selectOne(boardNo);
		
		if(boardDto==null) {
			throw new TargetNotfoundException("존재 하지 않는 글");
		}
		//4.작성자가 탈퇴했다면 차단
		if(boardDto.getBoardWriter() == null) {
			throw new GetOutException();
		}
		
		//5. 소유자가 아니면 차단
		if(!loginId.equals(boardDto.getBoardWriter())) {
			throw new GetOutException();
		}
		
		
		return true;
	}
}
