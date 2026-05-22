package com.kh.spring09.email;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
public class Test01이메일보내기2 {

	@Autowired
	private JavaMailSender sender;
	
	@Test
	public void test() {
		//2. 메세지 생성
		SimpleMailMessage message = new SimpleMailMessage();
		
		//+ 메세지 내용 작성
		message.setFrom("hwangtest15@gmail.com");
		message.setTo("hiphop5782@naver.com");
		//message.setCc("참조이메일주소");
		//message.setBcc("숨은참조이메일주소");
		message.setSubject("테스트 메일 발송");
		message.setText("이메일 발송 테스트");
		
		//3. 전송
		sender.send(message);
	}
	
}






