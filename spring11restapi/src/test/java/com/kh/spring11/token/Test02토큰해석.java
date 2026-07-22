package com.kh.spring11.token;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test02토큰해석 {
	
	@Test
	public void test() {
		//목표 : 이미 만들어진 JWT Token의 정보를 불러와 검증 후 해석 결과 출력
		//[1] 만들어진 토큰을 하나 가져온다
		//[2] 토큰을 해석할 수 있는 도구를 생성한다
		//[3] 토큰 해석 및 결과를 확인한다
		
		//[1] 테스트1번에서 생성한 토큰을 복사해서 넣으면 된다
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0dXNlcjEiLCJhY2NvdW50SWQiOiJ0ZXN0dXNlcjEiLCJpc3MiOiJodHRwczovL3d3dy5raGFjYWRlbXkuY28ua3IvIiwiYWNjb3VudE5pY2tuYW1lIjoi7YWM7Iqk7Yq47Jyg7KCAMSIsImV4cCI6MTc4NDUzMzgyMywiYWNjb3VudExldmVsIjoi67iM66Gg7KaIIiwiaWF0IjoxNzg0NTMzNzYzfQ.f6JKZvDdg0LVRPkVeiiEr28gbYm3pu-Tr5VFC8dGR3Q";
		
		//[2]
		//- 해석에 사용할 Secret Key를 생성
		String secret = "12345678901234567890123456789012";
		SecretKey key = new SecretKeySpec(secret.getBytes(),"HmacSHA256");
		
		//- Secret Key를 이용해서 해석 도구(JwtDecoder) 생성
		JwtDecoder jwtDecoder = NimbusJwtDecoder
								.withSecretKey(key)
								.macAlgorithm(MacAlgorithm.HS256)
								.build();
		
		//[3]
		//- 이 한줄로 토큰변조, 만료 다 체크됨(예외발생)
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
