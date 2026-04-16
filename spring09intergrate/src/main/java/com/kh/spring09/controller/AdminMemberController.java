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
	public String list(Model model,@RequestParam(required = false) String column, @RequestParam(required = false) String keyword) {
		List<MemberDto> list = memberDao.selectList(column, keyword);
		model.addAttribute("list", list);
		return "admin/list";
	}

	@RequestMapping("/detail")
	public String detail(Model model,String memberId) {
	
	List<MemberHistoryDto> personalHistory = memberHistoryDao.selectList(memberId,1,10);
	
	model.addAttribute("personalHistory", personalHistory);
	
	MemberExitDto everyInfo = memberExitDao.selectOne(memberId);
	
	
	model.addAttribute("everyInfo", everyInfo);
	
	return"admin/detail";
		
	}
	
	@RequestMapping("/block")
	public String block(@RequestParam String memberId) {
		MemberDto memberDto = memberDao.selectOne(memberId);
	
		
		String current = memberDto.getMemberBlock();
		
		String future = current.equals("Y")?"N":"Y";
		
		memberDto.setMemberBlock(future);
		memberDao.updateMemberBlock(memberDto);
		
		return "redirect:./detail?memberId="+memberId;
	}
	
	@GetMapping("edit")
	public String edit(@RequestParam String memberId, Model model ) {
		MemberDto memberDto = memberDao.selectOne(memberId);
	
		model.addAttribute("memberDto", memberDto);
		return"/admin/edit";
	}
	
	@PostMapping("edit")
	public String edit(@ModelAttribute MemberDto memberDto) {
		
		
		memberDao.updateByMaster(memberDto);
		return "redirect:./detail?memberId="+memberDto.getMemberId();
	}
}
