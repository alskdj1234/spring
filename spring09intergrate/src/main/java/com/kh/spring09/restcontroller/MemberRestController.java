package com.kh.spring09.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring09.dao.MemberDao;
import com.kh.spring09.dao.MemberLikeDao;
import com.kh.spring09.dto.MemberDto;
import com.kh.spring09.vo.LikeVO;

import jakarta.servlet.http.HttpSession;

@CrossOrigin // 외부 접근을 허용 (CORS 허용)
@RestController
@RequestMapping("/rest/member")
public class MemberRestController {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberLikeDao memberLikeDao;

	// 아이디 중복검사(없으면 = 사용가능 = true반환
	@RequestMapping("/validId")
	public boolean validId(@RequestParam String memberId) {
		MemberDto memberDto = memberDao.selectOne(memberId);
		return memberDto == null;
	}

	@PostMapping("/validNickname")
	public boolean validNickname(@RequestParam String memberNickname) {
		MemberDto memberDto = memberDao.selectOnebyNickname(memberNickname);
		return memberDto == null;
	}

	@PostMapping("/validEmail")
	public boolean validEmail(@RequestParam String memberEmail) {
		MemberDto memberDto = memberDao.selectOnebyEmail(memberEmail);
		return memberDto == null;
	}

	@PostMapping("/like-check")
	public LikeVO likeCheck(@RequestParam String memberTarget, HttpSession session) {
		String loginId = (String) session.getAttribute("loginId");
		boolean action = memberLikeDao.check(loginId, memberTarget);

		int count = memberLikeDao.count(memberTarget);

		return LikeVO.builder().action(action).count(count).build();

	}

	@PostMapping("/like-action")
	public LikeVO likeAction(@RequestParam String memberTarget, HttpSession session) {
		String loginId = (String) session.getAttribute("loginId");
		boolean current = memberLikeDao.check(loginId, memberTarget);

		if (current) {
			memberLikeDao.delete(loginId, memberTarget);
		}

		else {
			memberLikeDao.insert(loginId, memberTarget);
		}
		int count = memberLikeDao.count(memberTarget);

		return LikeVO.builder().action(!current).count(count).build();
	}

}
