package com.kh.spring11.token;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import com.kh.spring11.configuration.JwtProperties;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@SpringBootTest
public class Test01토큰생성2 {
	@Autowired
	private JwtEncoder jwtEncoder;
	@Autowired
	private JwsHeader jwsHeader;
	@Autowired
	private JwtProperties jwtProperties;
	
	@Test
	public void test() {
		
		
		//2.
		Instant current = Instant.now();
		JwtClaimsSet claims = JwtClaimsSet.builder()
				//표준 데이터
				.issuer(jwtProperties.getIssuer())//발행자(홈페이지)
				.issuedAt(current)//발급시각
				.expiresAt(current.plusSeconds(jwtProperties.getTokenValidity()))//만료시각
				.subject("testuser1")//토큰의 소유자(유일한 항목)
				//커스텀데이터
					.claim("accountId", "testuser1")
					.claim("accountLevel", "브론즈")
					.claim("accountNickname","테스트유저1")
				.build();
	
	
		//최종 생성
		String jwtToken = jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims))
				.getTokenValue();//jwt인코더파라미더스 => 헤더랑 jwtclaimsset을 합쳐줌
		log.debug("jwt token ={)" , jwtToken);
	}
}
