package com.kh.spring09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring09.dao.MemberDao;
import com.kh.spring09.dao.MemberExitDao;
import com.kh.spring09.dao.MemberHistoryDao;
import com.kh.spring09.dto.MemberDto;
import com.kh.spring09.dto.MemberExitDto;
import com.kh.spring09.dto.MemberHistoryDto;
import com.kh.spring09.exception.GetOutException;
import com.kh.spring09.exception.TargetNotfoundException;

@Controller
@RequestMapping("/admin/member")
public class AdminMemberController {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberExitDao memberExitDao;
	@Autowired
	private MemberHistoryDao memberHistoryDao;
	
	@RequestMapping("/list")
	public String list(Model model, 
			@RequestParam(required = false) String column,
			@RequestParam(required = false) String keyword) {
		
		List<MemberDto> list = memberDao.selectList(column, keyword);
		model.addAttribute("list", list);
		
		return "admin/member/list";
	}
	//@RequestMapping("/detail/{memberId}")
	//public String detail(@PathVariable String memberId, Model model) {
	@RequestMapping("/detail")
	public String detail(@RequestParam String memberId, Model model) {
		MemberExitDto memberDto = memberExitDao.selectOne(memberId);
		model.addAttribute("memberDto", memberDto);
		
		List<MemberHistoryDto> loginHistory = memberHistoryDao.selectList(memberId, 1, 10);
		model.addAttribute("loginHistory", loginHistory);
		
		return "admin/member/detail";
	}
	
	@RequestMapping("/block")
	public String block(@RequestParam String memberId) {
		MemberDto memberDto = memberDao.selectOne(memberId);
		
		String current = memberDto.getMemberBlock();//현재 상태를 불러온다
		String future = current.equals("Y") ? "N" : "Y";
		memberDto.setMemberBlock(future);
		memberDao.updateMemberBlock(memberDto);//객체로 아이디와 차단상태를 전달
		//memberDao.updateMemberBlock(memberId, future);//문자열 두개로 아이디와 차단상태를 전달
		
		return "redirect:./detail?memberId="+memberId; 
	}
	
	//정보 변경
	@GetMapping("/edit")
	public String edit(@RequestParam String memberId, Model model) {
		MemberDto memberDto = memberDao.selectOne(memberId);
		model.addAttribute("memberDto", memberDto);
		return "admin/member/edit";
	}
	
	@PostMapping("/edit")
	public String edit(@ModelAttribute MemberDto memberDto) {
		//memberDao.update(memberDto);//쓰면 안됨(등급과 포인트가 수정되지 않음)
		memberDao.updateByMaster(memberDto);
		return "redirect:./detail?memberId="+memberDto.getMemberId();
	}
}









