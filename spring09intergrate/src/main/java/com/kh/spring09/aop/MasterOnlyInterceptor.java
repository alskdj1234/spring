package com.kh.spring09.aop;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kh.spring09.exception.GetOutException;
import com.kh.spring09.exception.WhoAreYouException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class MasterOnlyInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler) throws Exception{
	  HttpSession session = request.getSession();
	  String loginLevel = (String) session.getAttribute("loginLevel");
	 
	  if(loginLevel == null) {
		  throw new WhoAreYouException();
	  }
	 if(!loginLevel.equals("마스터")) {
		 throw new GetOutException();
	 }
  
	 return true;
  }
}
