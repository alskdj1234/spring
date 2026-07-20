package com.kh.spring11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring11.annotation.CommonsApiResponse;
import com.kh.spring11.service.AuthService;
import com.kh.spring11.vo.auth.AuthLoginRequestVO;
import com.kh.spring11.vo.auth.AuthLoginResponseVO;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "인증 처리 서비스", description = "stateless 서버의 인증 처리 로직 구현")
@CommonsApiResponse

@CrossOrigin
@RestController
@RequestMapping("/service/auth")
public class AuthRestController {
	@Autowired
	private AuthService authService;
	
	@ApiResponse(responseCode = "200", description = "로그인 성공")
	@PostMapping(value = "/login", produces = "application/json")
	public AuthLoginResponseVO login(
			@RequestBody AuthLoginRequestVO request) {
		return authService.login(request);
	}
}







