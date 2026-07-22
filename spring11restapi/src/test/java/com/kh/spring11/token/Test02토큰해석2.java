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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test02토큰해석2 {
	
	@Autowired
	private JwtDecoder jwtDecoder;
	
	@Test
	public void test() {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlcjEiLCJhY2NvdW50SWQiOiJ0ZXN0dXNlcjEiLCJpc3MiOiJodHRwczovL3d3dy5raGFjYWRlbXkuY28ua3IvIiwiYWNjb3VudE5pY2tuYW1lIjoi7YWM7Iqk7Yq47Jyg7KCAMSIsImV4cCI6MTc4NDUzMzgyMywiYWNjb3VudExldmVsIjoi67iM66Gg7KaIIiwiaWF0IjoxNzg0NTMzNzYzfQ.f6JKZvDdg0LVRPkVeiiEr28gbYm3pu-Tr5VFC8dGR3Q";
		
		Jwt jwt = jwtDecoder.decode(token);
		
		//- 기본 정보 출력 : iss, iat, exp, sub
		log.debug("iss = {}", jwt.getIssuer());
		log.debug("iat = {}", jwt.getIssuedAt());
		log.debug("exp = {}", jwt.getExpiresAt());
		log.debug("sub = {}", jwt.getSubject());
		//- 커스텀 정보 출력 : accountId, accountNickname, accountLevel
		log.debug("accountId = {}", jwt.getClaimAsString("accountId"));
		log.debug("accountNickname = {}", jwt.getClaimAsString("accountNickname"));
		log.debug("accountLevel = {}", jwt.getClaimAsString("accountLevel"));
	}
}
