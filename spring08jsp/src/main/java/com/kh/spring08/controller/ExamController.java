package com.kh.spring08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam")
public class ExamController {
	@RequestMapping("/test01")
	public String test01() {
		return "/WEB-INF/views/exam/test01.jsp";
	}
	@RequestMapping("/test02")
	public String test02() {
		return "/WEB-INF/views/exam/test02.jsp";
	}
	@RequestMapping("/test03")
	public String test03() {
		return "/WEB-INF/views/exam/test03.jsp";
	}

	@RequestMapping("/test04")
	public String test04() {
		return "/WEB-INF/views/exam/test04.jsp";
	}
	
	@RequestMapping("/test05")
	public String test05() {
		return "/WEB-INF/views/exam/test05.jsp";
	}
	
	@RequestMapping("/test06")
	public String test06() {
		return "/WEB-INF/views/exam/test06.jsp";
	}
	
	@RequestMapping("/test07")
	public String test07() {
		return "/WEB-INF/views/exam/test07.jsp";
	}
	
	@RequestMapping("/test08")
	public String test08() {
		return "/WEB-INF/views/exam/test08.jsp";
	}
	@RequestMapping("/test09")
	public String test() {
		
		return"/WEB-INF/views/exam/test09.jsp";
	}
}




