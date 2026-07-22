package com.kh.spring11.configuration;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.http.Cookie;

//보안을 위해 필요한 도구 및 설정을 작성 (향후 스프링 시큐리티 설정도 이곳에 작성)
@Configuration
public class SecurityConfiguration {
	//단방향 암호화를 위한 BCryptPasswordEncoder를 등록
	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	//Spring Security 시스템의 구조를 객체화하여 등록
	//→ SecurityFilterChain
	@Bean
	public SecurityFilterChain securityFilterChain(
		HttpSecurity http,//Spring Security가 제공하는 http 설정 객체
		BearerTokenResolver bearerTokenResolver,
		JwtAuthenticationConverter jwtAuthenticationConverter//직접 만든 권한 변환기
			) throws Exception {
		//http에 홈페이지 운영 규칙을 모두 설정하고 Build해서 반환
		http  
			//csrf 비활성화
			.csrf(csrf ->csrf.disable() )
			//cors 설정 : 별도로 등록한 CorsConfigurationSource 의 설정을 따르겠다(없으면 기본값)
			.cors(Customizer.withDefaults())
			//session 설정 : 무상태(STATELESS)로 설정
			.sessionManagement(
				session -> session.sessionCreationPolicy(
					SessionCreationPolicy.STATELESS
				)
			)
			//security의 기본 제공되는 로그인화면과 인증시스템을 비활성화
			.formLogin(form->form.disable())
			.httpBasic(basic->basic.disable())
			.logout(logout->logout.disable())
			//.logout(AbstractHttpConfigurer::disable)//Java Method Reference
		
			//HTTP 요청에 대한 처리계획
			//.requestMatchers("적용시킬 주소or패턴")
			//	.permitAll() - 모두 수락(접속 허용)
			//	.denyAll() - 모두 거절(접속 차단)
			//	.authenticated() - 인증 필요 (인증 방식에 대해서는 따로 정의)
			//	.hasRole() - Spring Security의 기본 역할 (`ROLE_` 로 시작)
			//	.hasAuthority() - 사용자가 임의로 지정한 역할
			.authorizeHttpRequests(
				auth -> auth	
					//무조건 허용할 기본 페이지들
					.requestMatchers(
						"/active"//생존 확인용 페이지
						,"/swagger-ui/**"//springdoc ui
						,"/v3/api-docs/**"//springdoc json
						
					).permitAll()
					
					.requestMatchers("/service/auth/login"
							,"/service/auth/logout"
							,"/service/auth/refresh").permitAll()
					
					.requestMatchers("/service/cert/**").permitAll()
					
					.requestMatchers("/api/country/**").permitAll()
					
					.requestMatchers(HttpMethod.POST,"/api/lecture/").authenticated()
					.requestMatchers(HttpMethod.PUT,"/api/lecture/**").authenticated()
					.requestMatchers(HttpMethod.PATCH,"/api/lecture/**").authenticated()
					.requestMatchers(HttpMethod.DELETE,"/api/lecture/**").authenticated()
					
					//조건부 허용(내가 만든 요소들)
					.requestMatchers(
						"/api/account/me"//내정보
					).authenticated()//인증 필요
					//관리자 기능 : Jwt의 authorities 클레임에 "마스터"가 포함되어 있어야함
					.requestMatchers("/api/admin/**").hasAuthority("마스터")
					//나머지 모두 거절
					.anyRequest().permitAll()
			)
			//JWT를 어떻게 검증할 것인지 설정 (JwtDecoder가 반드시 필요)

			//, BearerTokenResolver :AccessToken을 꺼내서 Jwt를 뽑아내는 도구
		    //JwtAuthenticationConverter :Jwt의 authority를 spring security 용으로 변환
			.oauth2ResourceServer(
					oauth2 -> oauth2
					//하단 @Bean으로 만든 해석 도구를 oauth2의 표준 해석기로 설정
						.bearerTokenResolver(bearerTokenResolver)
					//하단 @Bean으로 만든 JWT 권한 해석 및 변환기를 설정
						.jwt(
								jwt -> jwt.jwtAuthenticationConverter(
										jwtAuthenticationConverter//diy한 도구
										)
								)
					
					)
			//예외 상황 처리 설정
			//→ 인증되지 않은 경우는 401 , 권한이 부족한 경우는 403으로 반환하도록 설정
			.exceptionHandling(
					exception -> exception
					//인증 되지 않은 경우
					.authenticationEntryPoint(
								(req, res, exp) -> res.setStatus(401)
								)
					//접근 거부 당한 경우
						.accessDeniedHandler(
								(req, res, exp) -> res.setStatus(403)
								)
					)
		;
		
		return http.build();
	}
	
	//CorsConfigurationSource 생성 (Security의 기본값으로 자동 지정)
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		
		//cors 설정
		//1 허용되는 접근 대상을 지정 (allow origins or pattern)
		config.setAllowedOrigins(List.of(
				"http://localhost:5173"
				
				));
		
		//2. 허용할 HTTP 메소드 설정
		config.setAllowedMethods(List.of(
				"GET","POST","PUT","PATCH","DELETE",
				"OPTIONS"//오리진이 다른데 겟(또는 헤드)이 아닌 요청을 보내는 상황일 때 보내는 사전 답사용 요청
				,"HEAD"//GET과 같지만 응답 본문을 가져오지 않음.
				));
		
		//3. 허용할 http 헤더 설정
		// 특정 헤더를 반드시 포함 하는 경우 존재 (카카오 쪽 api..)
		// 보안 강화시 CSRF 헤더만 허용하는 경우가 있다(사이트간 요청 위조 방지 헤더)
		config.setAllowedHeaders(List.of("*"));
		
		//4.인증 쿠키 사용 설정
		config.setAllowCredentials(true);
		
		//5.preflight 시간 설정(캐싱 유지시간)
		config.setMaxAge(Duration.ofHours(1L));
		
		//적용시킬 주소까지 포함한 설정 객체로 확장
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		
		//완성된 객체 변환
		return source;
	}
	
	//BearerTokenResolver
	//Bearer는 토큰의 한 종류(인증을 통해 뭔가 얻어 내는 의미의 토큰)
	//원래 토큰은 표준이 없어서 JWT앞에 어떤 접두사를 붙여도 된다.
	//헤더 방식인 경우 "Authorization : Bearer [ 토큰 값 ]"과 같은 형태로 전달
	//카카오는 KAKAOAK 라는 자체 이름을 만들어서 토큰에 적용함
	//인증용 토큰을 해석하는 도구(accessToken 쿠키)
	@Bean
	public BearerTokenResolver bearerTokenResolver() {
		return request -> { 
			//request는 요청정보고 이 내부에 쿠키가 들어있어서 accessToken을 찾아 반환 하면됨
		
			//만약 accessToken이 만료되어도 상관이 없는 주소라면 통과 시킨다.
			Set<String> allowPaths = Set.of(
					"/service/auth/login",
					"/service/auth/logout"
					,"/sevice/auth/refresh"
					,"/service/cert/send"
					,"/sevice/cert/check"
			);
			
			if(allowPaths.contains(request.getServletPath())) {
				return null;
			}
			
			//엑세스토큰이 필요한 주소만 남아 검색을 통해 찾아 반환
			Cookie[] cookies = request.getCookies();
			if(cookies == null) {//options 같은 상황에서 null 가능성
				return null;
			}
			
//			//클래식 자바 버전으로 쿠키 찾기
//			String target = null;
//			for(Cookie cookie : cookies) {
//				if(cookie.getName().equals("accessToken")) {
//				  String token = cookie.getValue();//저장된 토큰을 꺼낸다
//				  //토큰이 없으면 넘기고
//				  if(token == null || token.isBlank()) continue;
//				  //토큰이 있으면 저장
//				  target = token;
//				}
//			}
//			return target;

			//모던 자바 (Stream API)버전으로 쿠키 찾기
			return Arrays.stream(cookies)
				.filter(cookie -> cookie.getName().equals("accessToken"))
				.map(cookie -> cookie.getValue())
				.filter(value->value!=null && !value.isBlank())
				.findFirst()
				.orElse(null);
			
		
		};
	}
	
	//JwtAuthenticationConverter
	// Jwt의 authorities 항목을 spring security authority로 변환
	@Bean
	public JwtAuthenticationConverter jwtAuthenticationConverter() {
		//권한 정보 변환 도구 생성
		JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
	
		//jwt에서 authorities와 관련된 claim 이름을 설정
		converter.setAuthoritiesClaimName("authorities");
		
		//기본 접두사 (ROLE_, SCOPE_ ...)모두 제거
		converter.setAuthorityPrefix("");
		
		//최종 JWT 변환 도구
		JwtAuthenticationConverter result = new JwtAuthenticationConverter();
		
		//앞에서 만든 도구를 끼고
		result.setJwtGrantedAuthoritiesConverter(converter);
		
		//반환
		return result;
	}
	
}