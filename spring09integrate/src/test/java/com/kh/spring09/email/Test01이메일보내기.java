package com.kh.spring09.email;

import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@SpringBootTest
public class Test01이메일보내기 {

	/*
	구글 SMTP 서버 ─────────────────────> 다른 SMTP 서버(naver,daum,gmail,..)
	     ↑                                   ↓
	 우리의 웹서버                           다른 사용자
	(Spring Boot)
	
	<필요한 도구>
	- JavaMailSender/JavaMailSenderImpl : 구글SMTP서버로 메일을 전송하는 도구
	- SimpleMailMessage : text만 전송 가능한 메세지 규격 (= 일반 편지)
	- MimeMessage : text와 html, 미디어(이미지 등)을 전송할 수 있는 규격 (= 택배)
	*/
	
	@Test
	public void test() {
		//1. 메일 전송 도구 생성
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		
		//+ 메일 전송 도구의 정보 설정
		sender.setHost("smtp.gmail.com");//이용할 업체의 호스트 정보
		sender.setPort(587);//이용할 업체의 포트 번호
		sender.setUsername("hwangtest15");//이용자의 계정이름
		sender.setPassword("byvyvlorbylbgsaz");//이용자의 앱비밀번호(개인x)
		
		Properties props = new Properties();//Map<String, String> 형태
		props.setProperty("mail.smtp.auth", "true");//인증 사용
		props.setProperty("mail.smtp.debug", "true");//에러 시 통신내역 출력(운영단계에선 false)
		props.setProperty("mail.smtp.starttls.enable", "true");//보안 프로토콜 사용
		props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");//보안 프로토콜 버전을 최신으로 지정
		props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");//신뢰할 수 있는 업체 목록에 추가
		
		sender.setJavaMailProperties(props);//상세 옵션 설정
		
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






