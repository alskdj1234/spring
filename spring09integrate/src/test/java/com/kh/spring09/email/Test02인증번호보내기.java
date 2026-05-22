package com.kh.spring09.email;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring09.service.EmailService;

//목표 : 지정된 이메일로 인증번호 6자리를 발송하도록 만든 EmailService 기능 작동 테스트
@SpringBootTest
public class Test02인증번호보내기 {
	@Autowired
	private EmailService emailService;
	
	@Test
	public void test() {
		emailService.sendCertNumber("hiphop5782@naver.com");
	}
}
