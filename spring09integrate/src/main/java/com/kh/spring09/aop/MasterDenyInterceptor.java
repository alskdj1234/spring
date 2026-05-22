package com.kh.spring09.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring09.dao.MemberDao;
import com.kh.spring09.dto.MemberDto;
import com.kh.spring09.exception.GetOutException;
import com.kh.spring09.exception.TargetNotfoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//memberId라는 파라미터로 전달되는 아이디가 관리자면 차단시키는 인터셉터
@Service
public class MasterDenyInterceptor implements HandlerInterceptor{
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//컨트롤러에서는 파라미터를 @RequestParam이라고 불러온다 (Java EE의 명령이 아님)
		//인터셉터에서는 사용자 정보(request)에서 파라미터를 추출(getParameter())한다
		String memberId = request.getParameter("memberId");
		
		//memberId가 null이면 = 달라는 정보를 주지 않았다면
		if(memberId == null) {
			throw new IllegalArgumentException("잘못된 형식의 요청");
		}
		
		//존재하지 않는 회원이면
		MemberDto memberDto = memberDao.selectOne(memberId);
		if(memberDto == null) {
			throw new TargetNotfoundException("존재하지 않는 회원");
		}
		
		//관리자라면
		if(memberDto.getMemberLevel().equals("마스터")) {
			throw new GetOutException();
		}
		
		return true;//통과
	}
}






