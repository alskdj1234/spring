package com.kh.spring11.token;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import com.kh.spring11.service.JwtService;
import com.kh.spring11.vo.jwt.TokenParseResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test02토큰해석3 {
	
	@Autowired
	private JwtService jwtService;
	
	@Test
	public void test() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlcjEiLCJhY2NvdW50SWQiOiJ0ZXN0dXNlcjEiLCJpc3MiOiJodHRwczovL3d3dy5raGFjYWRlbXkuY28ua3IvIiwiYWNjb3VudE5pY2tuYW1lIjoi7YWM7Iqk7Yq47Jyg7KCAMSIsImV4cCI6MTc4NDU5NDU3OSwiYWNjb3VudExldmVsIjoi67iM66Gg7KaIIiwiaWF0IjoxNzg0NTk0NTE5fQ.kQ9vhBhTF4sg9Qkvlt-nG8oXudujXKiI0D9-M3v5_PU";
		
		TokenParseResponseVO response = jwtService.parseAccessToken(token);
		
		log.debug("accountId = {}", response.getAccountId());
		log.debug("accountNickname = {}", response.getAccountNickname());
		log.debug("accountLevel = {}", response.getAccountLevel());
	}
}
