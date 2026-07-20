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
		//이미 만들어진 JWT Token의 정보를 불러와 쓸만한지 검증 후 해석 결과 출력
		//1.만들어진 토큰 가져오기
		//2.토큰을 해석할 수 있는 도구를 생성한다.
		//3.토큰 해석 및 결과를 확인한다.
		
		//1
		String token ="";
		
		//2
		String secret ="12345678901234567890123456789012";
				SecretKey key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
	
		//시크릿 키를 이용해 해석 도구 생성
				JwtDecoder jwtDecoder = NimbusJwtDecoder
						.withSecretKey(key)
						.macAlgorithm(MacAlgorithm.HS256)
						.build();
				
		//3
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
