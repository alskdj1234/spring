package com.kh.spring11.kakaopay;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test02카카오페이승인요청 {
	
	@Test
	public void test() {
//		카카오페이 요청사항
//		POST /online/v1/payment/approve HTTP/1.1
//		Host: open-api.kakaopay.com
//		Authorization: SECRET_KEY ${SECRET_KEY}
//		Content-Type: application/json
		
		WebClient webClient = WebClient.builder()
				.baseUrl("https://open-api.kakaopay.com")
				.defaultHeader("Authorization", "SECRET_KEY DEVE1A07FE9B8E01B5FAEEEE5A43A21CC289B781")
				.defaultHeader("Content-Type", "application/json")
			.build();
		
		String url = "/online/v1/payment/approve";
		
		Map<String, String> body = new HashMap<>();
		body.put("cid", "TC0ONETIME");//가맹점 코드
		body.put("partner_order_id", "9ee36fa7-8715-475d-b3ec-931621eeef8f");
		body.put("partner_user_id", "testuser1");
		body.put("tid", "Ta714bca617301447e6b");
		body.put("pg_token", "b45670f608cf1f98b069");
		
		Map response = webClient.post()//POST요청
				.uri(url)//상세주소
				.bodyValue(body)//첨부데이터
			.retrieve()//응답 수신 허용
				.bodyToMono(Map.class)//일시불(Mono)로 수신 (↔ 할부는 Flux)
				.block();//동기방식으로 수신
		
		for(Object key : response.keySet()) {
			Object value = response.get(key);
			log.debug("{} = {}", key, value);
		}
	}
}






