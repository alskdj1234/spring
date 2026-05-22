package com.kh.spring09.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring09.dao.MemberDao;
import com.kh.spring09.dto.MemberDto;

//원래 RestController의 역할은 화면이 아닌 데이터를 사용하는 역할
// - AJAX 요청을 처리하기 위한 도구로 활용
// - 공용주소를 /rest로 시작하도록 처리
@CrossOrigin//다른 출처에서 AJAX요청을 보내는 것을 허락하겠다! (옵션을 통해 디테일한 대상 지정이 가능)
@RestController
@RequestMapping("/rest/test")
public class TestRestController {
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hello AJAX!";
	}
	
	//데이터를 받아서 계산하여 반환
	@RequestMapping("/plus")
	public int plus(@RequestParam int a, @RequestParam int b) {
		return a + b;
	}
	
	@Autowired
	private MemberDao memberDao;
	
	@RequestMapping("/check")
	public boolean check(@RequestParam String memberId) {
		MemberDto memberDto = memberDao.selectOne(memberId);
		return memberDto != null;//있으면 true, 없으면 false
	}
	
}









