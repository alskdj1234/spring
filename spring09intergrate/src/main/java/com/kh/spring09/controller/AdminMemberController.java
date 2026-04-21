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
import com.kh.spring09.vo.PageVO;


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
	public String list(Model model,@ModelAttribute PageVO pageVO) {
		
		int totalCount = memberDao.count();
		model.addAttribute("totalCount",totalCount);
		List<MemberDto> list = memberDao.selectList(pageVO);
		model.addAttribute("list", list);
		
		//페이징을 위해 추가로 전달할 값이 있다면 전달해야 한다
			int count = memberDao.count(pageVO);
				pageVO.setCount(count);//데이터 개수 설정
				model.addAttribute("pageVO", pageVO);
				
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
