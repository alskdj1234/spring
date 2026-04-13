package com.kh.spring09.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		MemberDto memberDto = new MemberDto();
		memberDao.login(memberDto);
		return"/WEB-INF/views/member/login.jsp";
	}
	
	@PostMapping("/login")
	public String loginComplete(boolean success) {
		if(success)
		return"redirect:./loginComplete";
		else
		return"redirect:./loginFail";
	}
	
=======
import jakarta.servlet.http.HttpSession;

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
	public String join(@ModelAttribute MemberDto memberDto) {
		memberDao.insert(memberDto);
		return "redirect:./joinComplete";
	}

	@RequestMapping("/joinComplete")
	public String joinComplete() {
		return "/WEB-INF/views/member/joinComplete.jsp";
	}

	@GetMapping("/login")
	public String login() {

		return "/WEB-INF/views/member/login.jsp";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute MemberDto memberDto, HttpSession session) {// 세션 사용 요청
		// 아이디 비교
		MemberDto findMemberDto = memberDao.selectOne(memberDto.getMemberId());
		if (findMemberDto == null) {
			return "redirect:./login?error";// 리다이렉트는 겟으로만 감
		}
		boolean isPasswordValid = memberDto.getMemberPassword().equals(findMemberDto.getMemberPassword());
		if (!isPasswordValid) {
			return "redirect:./login?error";
		}

		// 차단 여부

		boolean blockValid = findMemberDto.getMemberBlock().equals("Y");
		if (blockValid)
			return "redirect:./block";

		// 성공 시
		// 로그인 시간만 갱신
		memberDao.updateMemberLogin(findMemberDto.getMemberId());

		// 세션(httpSession)에 로그인 되었음을 표시
		// 최소한의 정보만 저장
		session.setAttribute("loginId", findMemberDto.getMemberId());

		session.setAttribute("loginLevel", findMemberDto.getMemberLevel());

		return "redirect:/";
	}

	// 로그인은 세션에 이름과 값들을 넣는 것
	// 로그아웃은 세션에 이름과 값들을 빼는 것
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("loginId");
		session.removeAttribute("loginLevel");
		return "redirect:/";
	}

	@RequestMapping("/block")
	public String block() {
		return "/WEB-INF/views/member/block.jsp";
	}

	// 마이페이지
	// 세션에 들어있는 아이디를 이용해 현재 회원의 모든 정보를 화면에 전달한다.
	@RequestMapping("/mypage")
	public String mypage(HttpSession session, Model model) {
		// 세션에 존재하는 현재 사용자 영역에 저장된 loginId란 값을 불러와 줘
		String loginId = (String) session.getAttribute("loginId");
		// (String) 다운 캐스팅
		MemberDto memberDto = memberDao.selectOne(loginId);
		model.addAttribute("memberDto", memberDto);
		return "/WEB-INF/views/member/mypage.jsp";
	}

	@GetMapping("/password")
	public String password() {

		return "/WEB-INF/views/member/password.jsp";
	}

	@PostMapping("/password")
	public String password(HttpSession session, @RequestParam String originPw, @RequestParam String changePw) {
		if(originPw.equals(changePw)) return "redirect:./password?error";
		String loginId = (String)session.getAttribute("loginId");
		MemberDto memberDto = memberDao.selectOne(loginId);
		if(!memberDto.getMemberPassword().equals(originPw)) {
			return"redirect:./password?error";
		}
		memberDto.setMemberPassword(changePw);
		memberDao.changePassword(memberDto);
		return"redirect:./passwordFinish";
		
		
		
	}
	@RequestMapping("/passwordFinish")
	public String passwordFinish() {
		return"WEB-INF/views/member/passwordFinish.jsp";
	}

