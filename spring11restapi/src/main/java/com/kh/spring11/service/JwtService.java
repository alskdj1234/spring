package com.kh.spring11.service;

import org.springframework.stereotype.Service;

import com.kh.spring11.dto.AccountDto;

@Service
public class JwtService {
	//토큰 생성 메소드
	public String createToken(TokenCreateRequestVO request) {
		return null;
	}
	
	public TokenParseResponseVO parseToken(String token) {
		return null;
	}
}
