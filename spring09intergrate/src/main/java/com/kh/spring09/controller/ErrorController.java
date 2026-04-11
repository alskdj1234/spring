package com.kh.spring09.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = {Controller.class})
//컨트롤러에게 간섭할 수 있도록 처리
//컨트롤러가 일하기 전과 후에 추가 코드를 구현 할 수 있다.
public class ErrorController {
	//컨트롤러에서 예외가 생기면 그 예외에 대한 처리를 수행하는 매핑
	//코드는 컨트롤러와 동일하게 작성 가능
	//예외 객체를 제공 받을 수 있음
	@ExceptionHandler(Exception.class)
	public String error(Exception e, Model model) {
		e.printStackTrace();//오류 로그 서버에 출력
		model.addAttribute("message", e.getMessage());//메시지를 화면에 전달하고
		return "/WEB-INF/views/error/500.jsp";
	}
	
	
}
