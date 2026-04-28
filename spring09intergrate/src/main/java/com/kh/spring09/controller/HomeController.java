package com.kh.spring09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	//메인 페이지를 가장 짧은 주소로 만들려면?
	@RequestMapping("/")
	public String home() {
		return "home";
	}
}
