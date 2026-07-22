package com.kh.spring11.token;

import java.time.Instant;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test01토큰생성 {
	
	@Test
	public void test() {
		//목표 : JWT(Json Web Token)을 생성해보자!
		//[1] 암호화를 위한 알고리즘과 키(key)를 설정한다
		//[2] 토큰 본체를 생성 (원하는 정보를 추가)
		//[3] 최종 토큰 생성
		
		//[1]
		//암호화/복호화에 사용할 열쇠(외부노출금지) - 32byte 이상을 권장
		String secret = "12345678901234567890123456789012";
		//사용할 알고리즘을 지정하여 실제 암호화용 키로 변환
		SecretKey key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
		
		//[2]
		Instant current = Instant.now();
		JwtClaimsSet claims = JwtClaimsSet.builder()
					//표준 데이터
					.issuer("https://www.khacademy.co.kr/")//발행자(홈페이지)
					.issuedAt(current)//발급시각
					.expiresAt(current.plusSeconds(60L))//만료시각(테스트로 60초)
					.subject("testuser1")//토큰의 소유자(유일한 항목)
					//커스텀 데이터
					.claim("accountId", "testuser1")
					.claim("accountLevel", "브론즈")
					.claim("accountNickname", "테스트유저1")
				.build();
		
		//[3]
		//JWT 토큰 생성기를 만든다
		JwtEncoder jwtEncoder = 
				new NimbusJwtEncoder(new ImmutableSecret<>(key));
		
		//JWT 헤더 생성
		JwsHeader jwsHeader = JwsHeader
								.with(MacAlgorithm.HS256)
								.build();
		
		//최종 생성
		String jwtToken = jwtEncoder
			.encode(JwtEncoderParameters.from(jwsHeader, claims))
			.getTokenValue();
		log.debug("jwt token = {}", jwtToken);
	}
	
}


