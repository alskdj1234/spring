package com.kh.spring11.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.stereotype.Service;

import com.kh.spring11.configuration.JwtProperties;
import com.kh.spring11.vo.jwt.TokenCreateRequestVO;
import com.kh.spring11.vo.jwt.TokenParseResponseVO;

@Service
public class JwtService {
	
	@Autowired
	private JwtEncoder jwtEncoder;
	@Autowired
	private JwsHeader jwsHeader;
	@Autowired
	private JwtProperties jwtProperties;
	@Autowired
	private JwtDecoder jwtDecoder;
	
	//액세스 토큰 생성 메소드
	public String createAccessToken(TokenCreateRequestVO request) {
		//토큰 발생시각을 객체로 생성
		Instant current = Instant.now();
		
		//JWT에 추가할 데이터 본문을 생성
		JwtClaimsSet claims = JwtClaimsSet.builder()
					//표준데이터 - iss, iat, exp, sub
					.issuer(jwtProperties.getIssuer())
					.issuedAt(current)
					.expiresAt(current.plusSeconds(jwtProperties.getAccessTokenValidity()))
					.subject(request.getAccountId())
					//커스텀데이터 - 우리 마음대로
					.claim("accountId", request.getAccountId())
					.claim("accountLevel", request.getAccountLevel())
					.claim("accountNickname", request.getAccountNickname())
					//Spring Security 검사를 위한 항목을 추가
					//- 이름은 authorities 고정 → hasAuthority()로 검사
					//- 이름은 roles로 설정하면 → hasRoles()로 검사 (ROLE_접두사 필요)
					.claim("authorities", List.of(
						request.getAccountLevel()
					))
				.build();
		
		//토큰 최종 생성 및 결과 반환
		return jwtEncoder
				.encode(JwtEncoderParameters.from(jwsHeader, claims))
				.getTokenValue();
	}
	
	//액세스 토큰 해석 메소드
	public TokenParseResponseVO parseAccessToken(String token) throws JwtValidationException {
		//오류검사 후 정보추출 (문제가 생기면 JwtValidationException 발생)
		Jwt jwt = jwtDecoder.decode(token);
		return TokenParseResponseVO.builder()
				.accountId(jwt.getClaimAsString("accountId"))
				.accountNickname(jwt.getClaimAsString("accountNickname"))
				.accountLevel(jwt.getClaimAsString("accountLevel"))
			.build();
	}
	//액세스 토큰 해석 메소드
	public TokenParseResponseVO parseAccessToken(Jwt jwt) {
		return TokenParseResponseVO.builder()
				.accountId(jwt.getClaimAsString("accountId"))
				.accountNickname(jwt.getClaimAsString("accountNickname"))
				.accountLevel(jwt.getClaimAsString("accountLevel"))
			.build();
	}
	
	//리프레시 토큰 생성 메소드
	public String createRefreshToken(String accountId) {
		//토큰 발생시각을 객체로 생성
		Instant current = Instant.now();
		
		//JWT에 추가할 데이터 본문을 생성
		JwtClaimsSet claims = JwtClaimsSet.builder()
					//표준데이터 - iss, iat, exp, sub
					.issuer(jwtProperties.getIssuer())
					.issuedAt(current)
					.expiresAt(current.plusSeconds(
						jwtProperties.getRefreshTokenValidity()
					))
					.subject(accountId)
				.build();
		
		//토큰 최종 생성 및 DB 저장 + 결과 반환
		return jwtEncoder
				.encode(JwtEncoderParameters.from(jwsHeader, claims))
				.getTokenValue();
	}
	
	//리프레시 토큰 해석 메소드
	public String parseRefreshToken(String token) {
		//오류검사 후 정보추출 (문제가 생기면 JwtValidationException 발생)
		Jwt jwt = jwtDecoder.decode(token);
		return jwt.getSubject();
	}
}