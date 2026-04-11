package com.kh.spring09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring09.dao.MemberDao;
import com.kh.spring09.dto.MemberDto;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberDao memberDao;
	
	@GetMapping("/join")
	public String join() {
		return "/WEB-INF/views/member/join.jsp";
	}
	@PostMapping("/join")
	public String join (@ModelAttribute MemberDto memberDto) {
		 memberDao.insert(memberDto);
		 return "redirect:./joinComplete";
	}
	
	@RequestMapping("/joinComplete")
 	public String joinComplete() {
 		return"/WEB-INF/views/member/joinComplete.jsp";
 	}
	
	@GetMapping("/login")
	public String login() {
		
		return"/WEB-INF/views/member/login.jsp";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute MemberDto memberDto) {
		
		boolean success = memberDao.login(memberDto);
		if(success)
			return"redirect:./loginComplete";
			else
			return"redirect:./loginFail";
	}
	
	@RequestMapping("/loginComplete")
	public String loginComplete() {
		
		return"/WEB-INF/views/member/loginComplete.jsp";
		
	
	}
	
	@RequestMapping("/loginFail")
	public String loginFail() {
		
		return"/WEB-INF/views/member/loginFail.jsp";
		
	
	}
	
	
}
