package com.kh.spring09.aop;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//비회원의 접근을 차단하는 인터셉터
//비회원이란 HttpSession에 loginId와 loginLevel이 존재하지 않는 사용자
@Service
public class MemberOnlyInterceptor implements HandlerInterceptor {
 @Override
 	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	 //세션이 사용자정보(서브렛 리퀘스트)에 숨어 있는데 스프링에선 선언만 하면 이용할 수 있도록 컨트롤러에 특혜를 줌
	
	 HttpSession session = request.getSession();
	 String loginId = (String) session.getAttribute("loginId");
	 String loginLevel= (String) session.getAttribute("loginLevel");
	 
	 
	 if(loginId==null||loginLevel==null)return false;
	 
	 
	 return true;
	 
 }
}
