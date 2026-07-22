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
import com.kh.spring11.service.JwtService;
import com.kh.spring11.vo.jwt.TokenCreateRequestVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test01토큰생성3 {
	
	@Autowired
	private JwtService jwtService;
	
	@Test
	public void test() {
		String jwtToken = jwtService.createAccessToken(
			TokenCreateRequestVO.builder()
				.accountId("testuser1")
				.accountNickname("테스트유저1")
				.accountLevel("브론즈")
			.build()
		);
		log.debug("jwt token = {}", jwtToken);
	}
	
}


