package com.kh.spring09.aop;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring09.dao.BoardDao;
import com.kh.spring09.dao.BoardReadDao;
import com.kh.spring09.dto.BoardDto;
import com.kh.spring09.exception.TargetNotfoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//목표 : 디비를 이용해 조회 이력 저장하고 중복증가를 차단
// 아이디 기반이라 세션이 달라도 차단이된다.
@Service
public class BoardReadInterceptor4 implements HandlerInterceptor {
@Autowired
private BoardDao boardDao;
@Autowired
private BoardReadDao boardReadDao;
	@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
	 //파라미터에 있는 boardNo를 찾아서 해당 글의 조회수를 증가
	 
	 //1.boardNo 널 제거
	 String boardNoStr = request.getParameter("boardNo");
	 if(boardNoStr == null) throw new TargetNotfoundException("존재 하지 않는 게시글");
			 
	 //2.boardNo가 유효하지 않은 번호인 경우 제거
	 long boardNo = Long.parseLong(boardNoStr);
	 BoardDto boardDto=boardDao.selectOne(boardNo);
	 if(boardDto==null)throw new TargetNotfoundException("존재 하지 않는 게시글");
	
	 //3. 비회원인 경우 제거
	 HttpSession session = request.getSession();
	 String loginId = (String)session.getAttribute("loginId");
	 if(loginId==null)return true;
	 
	 //4.db에 조회이력이 있으면 제거
	 int count = boardReadDao.count(loginId, boardNo);
	 if(count>0)return true;//기록이 있으면 그냥 가라
	 
	 //db에 조회 이력이 없으면
	 boardReadDao.insert(loginId, boardNo);
	 
	 
	 // 조회수 증가처리
	 boardDao.updateBoardReadcount(boardNo);
	 
	 
	 //조회수가 올라가든 안 올라가든 무조건 통과
	return true;
}
}
