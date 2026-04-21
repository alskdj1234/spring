package com.kh.spring09.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring09.dao.BoardDao;
import com.kh.spring09.dto.BoardDto;
import com.kh.spring09.exception.TargetNotfoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardReadInterceptor implements HandlerInterceptor {
@Autowired
private BoardDao boardDao;
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
	
	 //3 조회수 증가처리
	 boardDao.updateBoardReadcount(boardNo);
	 
	 
	 //조회수가 올라가든 안 올라가든 무조건 통과
	return true;
}
}
