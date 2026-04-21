package com.kh.spring09.aop;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring09.dao.BoardDao;
import com.kh.spring09.dto.BoardDto;
import com.kh.spring09.exception.TargetNotfoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
//목표 : 세션을 이용해 읽은 글의 번호를 관리
// 1. 세션에 메모리라는 이름의 저장소가 있다고 가정한다 (저장소는 해쉬셋임)
// 2. 세션에서 메모리 저장소를 꺼낸다.
// 3. 2번에서 저장소가 없으면 신규 생성한다.
// 4. 현재 읽으려는 글 번호가 메모리 저장소에 존재하는지 확인
// 5. 만약 존재한다면 리턴 트루(조회수 증가 처리 없이 통과)
// 6. 만약 존재하지 않는다면 저장소에 번호를 등록 후 조회수 증가 후 통과
@Service
public class BoardReadInterceptor3 implements HandlerInterceptor {
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
	
	//3.세션의 메모리 항목을 조사하여 조회수 증가/유지 여부를 판정
	 HttpSession session = request.getSession();
	 Set<Long> memory = (Set<Long>) session.getAttribute("memory");
	 if(memory == null) {//없으면
		 memory = new HashSet<>();//신규 생성
	 }
	 
	 if(memory.contains(boardNo)) {//저장소 존재 번호라면(이미 읽었다면)
		 return true;//조회수 증가 없이 통과
	 }
	 
	 //세션 갱신
	 memory.add(boardNo);//번호 추가하고
	 session.setAttribute("memory",memory);//세션의 저장소를 갱신
	 
	 // 조회수 증가처리
	 boardDao.updateBoardReadcount(boardNo);
	 
	 
	 //조회수가 올라가든 안 올라가든 무조건 통과
	return true;
}
}
