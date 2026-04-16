package com.kh.spring09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kh.spring09.exception.GetOutException;
import com.kh.spring09.exception.TargetNotfoundException;
import com.kh.spring09.exception.WhoAreYouException;

//예외만 전담하여 처리하는 도구
//- ControllerAdvice로 등록해서 Controller에게 간섭할 수 있도록 처리
//- 컨트롤러가 일하기 전과 후에 추가할 코드를 구현할 수 있다
@ControllerAdvice(annotations = {Controller.class})
//@ControllerAdvice(basePackages = {"com.kh.spring09.controller"})
public class ErrorController {
	
	//컨트롤러에서 예외가 생기면 그 예외에 대한 처리를 수행하는 매핑
	//- 코드는 컨트롤러와 동일하게 작성 가능
	//- 예외 객체를 제공받을 수 있음
	@ExceptionHandler(Exception.class)
	public String error(Exception e, Model model) {
		e.printStackTrace();//오류 로그를 서버에 출력하고
		model.addAttribute("message", e.getMessage());//메세지 화면에 전달하고
		return "error/500";//오류 페이지 연결
	}
	
	@ExceptionHandler(TargetNotfoundException.class)
	public String notFound(Exception e, Model model) {
		model.addAttribute("message", e.getMessage());//메세지 화면에 전달하고
		return "error/404";//오류 페이지 연결
	}
	@ExceptionHandler(WhoAreYouException.class)
	public String unauthorize(Exception e, Model model) {
		model.addAttribute("message", e.getMessage());//메세지 화면에 전달하고
		return "error/401";//오류 페이지 연결
	}
	@ExceptionHandler(GetOutException.class)
	public String forbidden(Exception e, Model model) {
		model.addAttribute("message", e.getMessage());//메세지 화면에 전달하고
		return "error/403";//오류 페이지 연결
	}
	
}