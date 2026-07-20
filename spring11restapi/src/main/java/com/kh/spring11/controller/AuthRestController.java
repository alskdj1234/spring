package com.kh.spring11.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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

@CrossOrigin(
		origins = "http://localhost:5173",
		allowCredentials = "true")
@RestController
@RequestMapping("/service/auth")
public class AuthRestController {
	@Autowired
	private AuthService authService;
	
	@ApiResponse(responseCode = "200", description = "로그인 성공")
	@PostMapping(value = "/login", produces = "application/json")
	//AuthLoginResponseVO login => 데이터만 반환
	public ResponseEntity<AuthLoginResponseVO> login(//데이터 + 헤더 + 쿠키 반환
			@RequestBody AuthLoginRequestVO request) {
		//로그인 처리를 수행하고 결과를 얻어낸다
		AuthLoginResponseVO response = authService.login(request);
		
		ResponseCookie postIt = ResponseCookie
				.from("loginId",response.getAccountId())
				//각종 설정들
				.maxAge(Duration.ofMinutes(30L))//30분으로 유효시간 설정
				.path("/")//적용 범위(어디든 쓰겠다는 뜻)
				.httpOnly(false)//true : 서버전용(등 뒤에) false : 클라이언트 겸용(이마)
				.secure(false)//https 사용 여부 배포시 트루로
				.sameSite("Lax")//허용 범위 (NONE:자유 Lax:유연, Strict:엄격)
				.build();
		//결과 반환
		return ResponseEntity.ok()
				//쿠키를 추가 하는 설정
				.header(HttpHeaders.SET_COOKIE,postIt.toString())
				.body(response);
	}
	
	//로그아웃 매핑
	// 서버에서 사용자의 로그아웃 작업 중 핵심은 쿠키 삭제
	// 쿠키는 지우는 명령이 없고 제한시간 설정해서 만드는것밖에 없음=>0초뒤에 삭제(만료)해라
	@DeleteMapping("/logout")
	public ResponseEntity<Void> logout(@CookieValue(name="loginId", required=false) String accountId){
//		if(쿠기 o) {
//			있으면 함
//		}
		
		//삭제 위한 쿠키 생성(생성시와 똑같은데 만료시간이 0초임)
		ResponseCookie detachIt = ResponseCookie
				.from("loginId",accountId)
				//각종 설정들
				.maxAge(Duration.ofMinutes(0L))
				//.maxAge(Duration.ZERO)//0분으로 유효시간 설정
				.path("/")//적용 범위(어디든 쓰겠다는 뜻)
				.httpOnly(false)//true : 서버전용(등 뒤에) false : 클라이언트 겸용(이마)
				.secure(false)//https 사용 여부 배포시 트루로
				.sameSite("Lax")//허용 범위 (NONE:자유 Lax:유연, Strict:엄격)
				.build();
		//응답 생성
		return ResponseEntity.noContent()
				.header(HttpHeaders.SET_COOKIE, detachIt.toString())
				.build();
	}
}







