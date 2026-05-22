package com.kh.spring09.aop;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring09.exception.WhoAreYouException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//비회원의 접근을 차단하는 인터셉터
//비회원이란? HttpSession에 loginId와 loginLevel이 존재하지 않는 사용자
@Service
public class MemberOnlyInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//세션이 어딨지? 컨트롤러에선 매개변수에 선언만 하면 주던데...
		//세션은 사실 사용자 정보(HttpServletRequest)에 숨어있기 때문에 꺼내서 사용해야 한다
		//스프링에선 자주 쓸거같은 HttpSession을 선언만 하면 이용할 수 있도록 컨트롤러에게 특혜를 줬다
		HttpSession session = request.getSession();
		
		String loginId = (String) session.getAttribute("loginId");//아이디 추출
		String loginLevel = (String) session.getAttribute("loginLevel");//등급 추출
		
		//회원이 아니면
		if(loginId == null || loginLevel == null) {
			//차단만 하면 사용자에게는 아무런 화면도 나오지 않으므로 플랜 B를 알려주고 차단시켜야 한다
			
			//1. 다른 매핑으로 리다이렉트 (ex : 로그인 페이지)
			//return "redirect:/member/login";//컨트롤러였다면 사용했을 코드
			//response.sendRedirect("/member/login");//정석적인 Java EE의 코드
			//return false;//차단!
			
			//2. HTTP 상태메세지를 발송
			//response.sendError(401);//미인증 상태코드 발송
			//return false;//차단!
			
			//3. 예외로 처리
			throw new WhoAreYouException();
		}

		return true;//통과!
	}
}



