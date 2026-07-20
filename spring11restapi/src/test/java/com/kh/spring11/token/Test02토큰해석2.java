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
		
		//1
		String token ="";

		Jwt jwt = jwtDecoder.decode(token);//토큰 변조 만료 다 체크 됨(예외발생)
	
		//기본 정보 출력:iss,iat,exp,sub
		log.debug("iss ={)",jwt.getIssuer());
		log.debug("iat ={}", jwt.getIssuedAt());
		log.debug("sub ={)",jwt.getSubject());
		log.debug("exp ={)",jwt.getExpiresAt());
		//커스텀 정보 출력 : accountId, accountNickname, accountLevel
		log.debug("accountId ={}",jwt.getClaimAsString("accountId"));
		log.debug("accountNickname ={}",jwt.getClaimAsString("accountNickname"));
		log.debug("accountLevel ={}",jwt.getClaimAsString("accountLevel"));
	}
}
