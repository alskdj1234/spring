package com.kh.spring11.configuration;

import com.kh.spring11.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class KakaopayConfiguration {
	@Autowired
	private KakaopayProperties kakaopayProperties;

	//이러한 WebClient는 여러 개가 존재할 수 있기 때문에 구분이 필요하다
	//@Bean 생성 시 닉네임을 부여해서 @Qualifier로 선택하여 사용하는것을 권장
	@Bean("kakaopayClient")
	public WebClient webClient() {
		return WebClient.builder()
			.baseUrl("https://open-api.kakaopay.com")
			.defaultHeader("Authorization", "SECRET_KEY " + kakaopayProperties.getSecretKey())
			.defaultHeader("Content-Type", "application/json")
		.build();
	}
}
