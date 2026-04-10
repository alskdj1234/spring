package com.kh.spring09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/olympic")
public class OlympicController {
	
	@RequestMapping("/table")
	public String table() {
		
		return"/WEB-INF/views/olympic/olympic.jsp";
	}
}
