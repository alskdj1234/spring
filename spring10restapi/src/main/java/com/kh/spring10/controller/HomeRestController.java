package com.kh.spring10.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController//컨트롤러와 리스폰스바디
public class HomeRestController {
	@RequestMapping("/")
	public String home() {
		return ".";
	}
}
