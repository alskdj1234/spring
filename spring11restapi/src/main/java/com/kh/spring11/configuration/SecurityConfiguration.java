package com.kh.spring11.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//보안을 위해 필요한 도구 및 설정을 작성 (향후 스프링 시큐리티 설정도 이곳에 작성)
@Configuration
public class SecurityConfiguration {
	//단방향 암호화를 위한 BCryptPasswordEncoder를 등록
	@Bean
	public PasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}