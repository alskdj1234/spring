package com.kh.spring09.aop;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring09.exception.GetOutException;
import com.kh.spring09.exception.WhoAreYouException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//관리자(마스터 등급)의 접근만 허용하는 인터셉터
@Service
public class MasterOnlyInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//[1] 세션 획득
		HttpSession session = request.getSession();
		
		//[2] 로그인 관련 정보를 획득
		String loginLevel = (String) session.getAttribute("loginLevel");
		
		//[3] 비회원
		if(loginLevel == null) {
			throw new WhoAreYouException();
		}
		
		//[4] 권한부족 여부 체크
		if(!loginLevel.equals("마스터")) {
			throw new GetOutException();
		}
		
		//다 통과했다면 지나가세요!
		return true;
	}
}






