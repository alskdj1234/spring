package com.kh.spring09.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring09.dao.BoardDao;
import com.kh.spring09.dao.MemberDao;
import com.kh.spring09.dao.MemberExitDao;
import com.kh.spring09.dao.MemberHistoryDao;
import com.kh.spring09.dto.BoardDto;
import com.kh.spring09.dto.MemberDto;
import com.kh.spring09.dto.MemberExitDto;
import com.kh.spring09.dto.MemberHistoryDto;
import com.kh.spring09.exception.TargetNotfoundException;
import com.kh.spring09.service.AttachService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberExitDao memberExitDao;
	@Autowired
	private MemberHistoryDao memberHistoryDao;
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private AttachService attachService;
	
	//가입에 필요한 매핑들
	@GetMapping("/join")
	public String join() {
		return "member/join";
	}
	@PostMapping("/join")
	public String join(@ModelAttribute MemberDto memberDto,
						@RequestParam MultipartFile attach) throws IllegalStateException, IOException {
		//가입은 프로필과 관계없이 일단 진행하고
		memberDao.insert(memberDto);
		
		//프로필이 있으면 추가 등록 및 연결
		if(!attach.isEmpty()) {
			int attachNo = attachService.save(attach);
			memberDao.connect(memberDto.getMemberId(), attachNo);
		}
		
		return "redirect:./joinFinish";
		//return "redirect:/member/joinFinish";
	}
	@RequestMapping("/joinFinish")
	public String joinFinish() {
		return "member/joinFinish";
	}
	
	//로그인 매핑
	@GetMapping("/login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute MemberDto memberDto,//아이디, 비밀번호가 존재 
						HttpSession session,//세션을 사용하겠다고 요청
						//@RequestHeader("User-Agent") String userAgent,//헤더값 읽기
						HttpServletRequest request//요청 정보를 모두 가져오기
						) {
		//[1] 사용자가 입력한 아이디를 이용하여 DB에 대상이 존재하는지 조회
		//MemberDto findMemberDto = memberDao.selectOne(memberDto.getMemberId());
		MemberExitDto findMemberDto = memberExitDao.selectOne(memberDto.getMemberId());
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
		
		//[4] 탈퇴 예정인 회원이라면 로그인을 취소하고 안내페이지로 이동
		if(findMemberDto.isWaitForDelete()) {
			return "redirect:./waiting";//삭제예정 안내 페이지로 이동
		}
		
		//[5] 로그인 성공
		//- 현재시간을 생성 (완벽하게 동일한 시간으로 설정해야 할 경우 자바에서 시간을 생성해서 양측에 추가)
		//Timestamp now = Timestamp.valueOf(LocalDateTime.now());
		//- 로그인시간을 갱신
		memberDao.updateMemberLogin(findMemberDto.getMemberId());
		//- 로그인 이력 생성
		MemberHistoryDto memberHistoryDto = new MemberHistoryDto();
		memberHistoryDto.setMemberHistoryOrigin(findMemberDto.getMemberId());//아이디
		memberHistoryDto.setMemberHistoryAddress(request.getRemoteAddr());//IP
		memberHistoryDto.setMemberHistoryAgent(request.getHeader("User-Agent"));//Agent
		memberHistoryDao.insert(memberHistoryDto);
		
		//- 세션(HttpSession)에 로그인 되었음을 표시
		session.setAttribute("loginId", findMemberDto.getMemberId());
		session.setAttribute("loginLevel", findMemberDto.getMemberLevel());
		
		//[6] 비밀번호 변경한 시간을 비교해서 일정기간 이상이면 비밀번호 변경 안내 페이지로 리다이렉트
		Timestamp last = findMemberDto.getMemberChange();//언제 비밀번호 바꿨니?
		if(last == null) {//바꾼적 없어?
			last = findMemberDto.getMemberJoin();//그럼 가입일로 하자!
		}
		LocalDateTime lastChange = last.toLocalDateTime();//위해서 계산한 시간과
		LocalDateTime current = LocalDateTime.now();//현재시각과의
		Duration duration = Duration.between(lastChange, current);//차이를 구해라!
		//if(duration.toHours() >= 1) {//비밀번호 변경한지 일정시간(ex : 1시간)이 지났다면
		if(duration.toDays() >= 30) {//비밀번호 변경한지 일정시간(ex : 30일)이 지났다면
			return "redirect:./notice";//비밀번호 변경 알림 페이지로 보내라
		}
		
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
		return "member/block";
	}
	@RequestMapping("/waiting")
	public String waiting() {
		return "member/waiting";
	}
	
	//마이페이지(회원 전용 기능)
	//- 세션에 들어있는 아이디를 이용해서 현재 회원의 모든 정보를 화면에 전달
	@RequestMapping("/mypage")
	public String mypage(HttpSession session, Model model) {
		//session에 존재하는 현재 사용자 영역에 저장된 loginId라는 이름의 값을 불러오세요!
		String loginId = (String) session.getAttribute("loginId");
		
		//개인정보 조회 후 첨부
		MemberDto memberDto = memberDao.selectOne(loginId);
		model.addAttribute("memberDto", memberDto);
		
		//로그인이력 조회 후 첨부
		List<MemberHistoryDto> loginHistory = 
								memberHistoryDao.selectList(loginId, 1, 10);
		model.addAttribute("loginHistory", loginHistory);
		
		return "member/mypage";
	}
	
	//비밀번호 변경(회원 전용 기능)
	@GetMapping("/password")
	public String password() {
		return "member/password";
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
		return "member/passwordFinish";
	}
	
	//개인정보 변경 매핑(회원 전용 기능)
	@GetMapping("/edit")
	public String edit(Model model, HttpSession session) {
		String loginId = (String) session.getAttribute("loginId");
		MemberDto memberDto = memberDao.selectOne(loginId);
		model.addAttribute("memberDto", memberDto);
		return "member/edit";
	}
	
	@PostMapping("/edit")
	public String edit(HttpSession session, @ModelAttribute MemberDto memberDto,
						@RequestParam MultipartFile attach) throws IllegalStateException, IOException {
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
		
		//프로필 교체 작업
		if(!attach.isEmpty()) {
			//삭제
			try {
				int attachNo = memberDao.searchProfile(memberDto.getMemberId());
				attachService.delete(attachNo);//지워
			} catch(Exception e) {}
			
			//등록
			int attachNo = attachService.save(attach);//새로 저장해!
			memberDao.connect(memberDto.getMemberId(), attachNo);
		} 
		
		return "redirect:./mypage";
	}
	
	//회원 탈퇴 매핑(회원 전용 기능)
	@GetMapping("/goodbye")
	public String goodbye() {
		return "member/goodbye";
	}
	@PostMapping("/goodbye")
	public String goodbye(@RequestParam String memberPassword, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		MemberDto memberDto = memberDao.selectOne(loginId);
		
		boolean valid = memberDto.getMemberPassword().equals(memberPassword);
		if(!valid) {//비밀번호가 맞지 않는다면
			return "redirect:./goodbye?error";//비밀번호 입력페이지로 되돌린다
		}
		
		//memberDao.delete(loginId);//회원과 연결된 모든데이터가 다 사라지는 일이 발생 (복구불가)
		memberExitDao.insert(loginId);//삭제대기 테이블에 등록하는 것으로 대체
		
		//로그아웃 처리
		//session.invalidate();//세션 파괴 명령
		session.removeAttribute("loginId");
		session.removeAttribute("loginLevel");
		
		return "redirect:./goodbyeFinish";
	}
	
	@RequestMapping("/goodbyeFinish")
	public String goodbyeFinish() {
		return "member/goodbyeFinish";
	}
	
	//마이페이지에서 로그인 이력 더보기를 누르면 나올 페이지 매핑
	@RequestMapping("/history")
	public String history(HttpSession session, Model model,
				@RequestParam(required = false) String beginDate,
				@RequestParam(required = false) String endDate,
				@RequestParam(required = false, defaultValue = "1") int page,
				@RequestParam(required = false, defaultValue = "20") int size) {
		String loginId = (String)session.getAttribute("loginId");
		
		int endRow = page * size;
		//int beginRow = endRow - (size-1);
		int beginRow = (page-1) * size + 1;
		
		List<MemberHistoryDto> loginHistory = 
			memberHistoryDao.selectList(loginId, beginDate, endDate, beginRow, endRow);
		model.addAttribute("loginHistory", loginHistory);
		
		return "member/history";
	}
	
	@RequestMapping("/notice")
	public String notice() {
		return "member/notice";
	}
	
	@RequestMapping("/later")
	public String later(HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");//로그인 아이디를 찾고
		MemberDto memberDto = memberDao.selectOne(loginId);//회원정보를 불러와서
		memberDao.updateMemberPassword(memberDto);//그대로 업데이트(시간만 바뀜)
		return "redirect:/";//메인페이지로 리다이렉트
	}
	
	//다른사람의 정보를 보기 위한 매핑
	@RequestMapping("/detail")
	public String detail(@RequestParam String memberId, Model model) {
		MemberDto memberDto = memberDao.selectOne(memberId);
		if(memberDto == null) throw new TargetNotfoundException("존재하지 않는 회원");
		
		//만약 관리자는 못본다면 memberDto의 등급을 확인해서 차단(GetOutException 처리)
		
		model.addAttribute("memberDto", memberDto);
		//List<BoardDto> boardList = boardDao.selectList("board_writer", memberId);//비추천
		List<BoardDto> boardList = boardDao.selectListByBoardWriter(memberId);
		model.addAttribute("boardList", boardList);
		
		return "member/detail";
	}
	
	//프로필 매핑
	@RequestMapping("/profile")
	public String profile(@RequestParam String memberId) {
		try {
			int attachNo = memberDao.searchProfile(memberId);
			return "redirect:/download/modern?attachNo="+attachNo;
		}
		catch(Exception e) {
			return "redirect:/images/no_image.png";
		}
	}
		
}








