package com.kh.spring08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController//기존의 등록(화면 없이 데이터를 전달해주겠다)
@Controller//새로운 등록(화면을 줄게!)
public class HomeController {
	@RequestMapping("/welcome")
	public String welcome() {
		//webapp 뒤의 경로를 슬래시부터 작성 (슬.래.시.부.터!)
		return "/WEB-INF/views/welcome.jsp";
	}
}
