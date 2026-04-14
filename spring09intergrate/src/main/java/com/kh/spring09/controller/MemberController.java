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

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberDao memberDao;
	
	//가입에 필요한 매핑들
	@GetMapping("/join")
	public String join() {
		return "/WEB-INF/views/member/join.jsp";
	}
	@PostMapping("/join")
	public String join(@ModelAttribute MemberDto memberDto) {
		memberDao.insert(memberDto);
		return "redirect:./joinFinish";
		//return "redirect:/member/joinFinish";
	}
	@RequestMapping("/joinFinish")
	public String joinFinish() {
		return "/WEB-INF/views/member/joinFinish.jsp";
	}
	
	//로그인 매핑
	@GetMapping("/login")
	public String login() {
		return "/WEB-INF/views/member/login.jsp";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute MemberDto memberDto,//아이디, 비밀번호가 존재 
							HttpSession session) {//세션을 사용하겠다고 요청
		//[1] 사용자가 입력한 아이디를 이용하여 DB에 대상이 존재하는지 조회
		MemberDto findMemberDto = memberDao.selectOne(memberDto.getMemberId());
		if(findMemberDto == null) {
			return "redirect:./login?error";//아이디 없음 (되돌려보내기, redirect는 GET만 가능)
		}
		
		//[2] 비밀번호를 비교
		//boolean isPasswordValid = memberDto의 비밀번호 == findMemberDto의 비밀번호;
		boolean isPasswordValid = memberDto.getMemberPassword()
									.equals(findMemberDto.getMemberPassword());
		if(!isPasswordValid) {
			return "redirect:./login?error";//비밀번호 불일치 (되돌려보내기, redirect는 GET만 가능)
		}
		
		//[3] 이 회원의 member_block 상태가 Y라면 차단
		if(findMemberDto.getMemberBlock().equals("Y")) {
			//return "redirect:./login?error";//현재 상황에는 맞지 않음
			return "redirect:./block";//차단 페이지로 이동
		}
		
		//[4] 차단되지 않았다면 로그인 성공
		//- 로그인시간을 갱신
		memberDao.updateMemberLogin(findMemberDto.getMemberId());
		
		//- 세션(HttpSession)에 로그인 되었음을 표시
		session.setAttribute("loginId", findMemberDto.getMemberId());
		session.setAttribute("loginLevel", findMemberDto.getMemberLevel());
		
		return "redirect:/";//첫페이지로 이동
	}
	
	//로그아웃(회원 전용 기능)
	//- 로그인 시 세션에 저장한 정보를 제거하는 작업
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
	
	
	//마이페이지(회원 전용 기능)
	//- 세션에 들어있는 아이디를 이용해서 현재 회원의 모든 정보를 화면에 전달
	@RequestMapping("/mypage")
	public String mypage(HttpSession session, Model model) {
		//session에 존재하는 현재 사용자 영역에 저장된 loginId라는 이름의 값을 불러오세요!
		String loginId = (String) session.getAttribute("loginId");
		MemberDto memberDto = memberDao.selectOne(loginId);
		model.addAttribute("memberDto", memberDto);
		return "/WEB-INF/views/member/mypage.jsp";
	}
	
	//비밀번호 변경(회원 전용 기능)
	@GetMapping("/password")
	public String password() {
		return "/WEB-INF/views/member/password.jsp";
	}
	@PostMapping("/password")
	public String password(HttpSession session,
			@RequestParam String originPw, @RequestParam String changePw) {
		//[1] 동일한 비밀번호로 변경을 시도하는 경우는 차단
		if(originPw.equals(changePw)) {
			return "redirect:./password?error";
		}

		String loginId = (String) session.getAttribute("loginId");
		MemberDto memberDto = memberDao.selectOne(loginId);
		
		//[2] 기존 비밀번호가 일치하지 않는 경우는 차단
		if(!memberDto.getMemberPassword().equals(originPw)) {
			return "redirect:./password?error";
		}
		
		//[3] 1, 2번을 통과했다면 비밀번호 변경 처리를 수행
		//memberDto.setMemberPassword(changePw);//기존 정보에서 비밀번호만 바꾸고
		//memberDao.updateMemberPassword(memberDto);//변경을 요청한다!
		MemberDto newMemberDto = new MemberDto();//신규 객체를 만들어
		newMemberDto.setMemberId(loginId);//아이디를 설정하고
		newMemberDto.setMemberPassword(changePw);//변경할 비밀번호를 설정하고
		memberDao.updateMemberPassword(newMemberDto);//변경을 요청한다!
		
		return "redirect:./passwordFinish";
	}
	@RequestMapping("/passwordFinish")
	public String passwordFinish() {
		return "/WEB-INF/views/member/passwordFinish.jsp";
	}
	
	//개인정보 변경 매핑(회원 전용 기능)
	@GetMapping("/edit")
	public String edit(Model model, HttpSession session) {
		String loginId = (String) session.getAttribute("loginId");
		MemberDto memberDto = memberDao.selectOne(loginId);
		model.addAttribute("memberDto", memberDto);
		return "/WEB-INF/views/member/edit.jsp";
	}
	
	@PostMapping("/edit")
	public String edit(HttpSession session, @ModelAttribute MemberDto memberDto) {
		//현재 memberDto에는 아이디가 없는 상태이므로 정상적인 수정 처리가 불가능
		String loginId = (String)session.getAttribute("loginId");
		
		//비밀번호 검사 후 차단 코드
		MemberDto findMemberDto = memberDao.selectOne(loginId);
		boolean valid = findMemberDto.getMemberPassword()
								.equals(memberDto.getMemberPassword());
		if(!valid) {//비밀번호가 일치하지 않으면
			return "redirect:./edit?error";
		}
		
		//개인정보 변경 처리
		memberDto.setMemberId(loginId);
		memberDao.update(memberDto);
		return "redirect:./mypage";
	}
	
	//회원 탈퇴 매핑(회원 전용 기능)
	@GetMapping("/goodbye")
	public String goodbye() {
		return "/WEB-INF/views/member/goodbye.jsp";
	}
	@PostMapping("/goodbye")
	public String goodbye(@RequestParam String memberPassword, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		MemberDto memberDto = memberDao.selectOne(loginId);
		
		boolean valid = memberDto.getMemberPassword().equals(memberPassword);
		if(!valid) {//비밀번호가 맞지 않는다면
			return "redirect:./goodbye?error";//비밀번호 입력페이지로 되돌린다
		}
		
		memberDao.delete(loginId);
		
		//로그아웃 처리
		//session.invalidate();//세션 파괴 명령
		session.removeAttribute("loginId");
		session.removeAttribute("loginLevel");
		
		return "redirect:./goodbyeFinish";
	}
	
	@RequestMapping("/goodbyeFinish")
	public String goodbyeFinish() {
		return "/WEB-INF/views/member/goodbyeFinish.jsp";
	}
	
}









