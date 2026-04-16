package com.kh.spring09.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring09.dao.MemberDao;
import com.kh.spring09.dto.MemberDto;
import com.kh.spring09.exception.GetOutException;
import com.kh.spring09.exception.TargetNotfoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//아이디 파라미터로 전달 되는 아이디가 관리자면 차단
public class MasterDenyInterceptor implements HandlerInterceptor {
	@Autowired
	private MemberDao memberDao;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 컨트롤러에서는 파라미터를 @RequestParam이라고 불러오는데 여긴 안됨
		
		//아이디가 널이면 == 달라는 정보를 주지 않았다면
		
		
		
		if (request.getParameter("memberId") == null)
			throw new IllegalArgumentException("유효 하지 않은 요청");
		
		MemberDto memberDto = memberDao.selectOne(request.getParameter("memberId"));
		
		if(memberDto==null)
			throw new TargetNotfoundException("존재 하지 않는 회원");
		
		if (memberDto.getMemberLevel().equals("마스터"))
			throw new GetOutException();

		return true;
	}
}
