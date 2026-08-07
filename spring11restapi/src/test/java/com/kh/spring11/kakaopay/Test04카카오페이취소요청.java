package com.kh.spring11.kakaopay;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import com.kh.spring11.configuration.KakaopayProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test04카카오페이취소요청 {

	@Qualifier("kakaopayClient")
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private KakaopayProperties kakaopayProperties;
	
	@Test
	public void test() {
//		상세 주소 설정
		String url = "/online/v1/payment/cancel";
		
//		보낼 데이터(Body) 준비
		Map<String, String> body = new HashMap<>();
		body.put("cid", kakaopayProperties.getCid());
		body.put("tid", "Ta72f50826bf7fb4c2cc");
		body.put("cancel_amount", "10");
		body.put("cancel_tax_free_amount", "0");
		
//		요청 발송 및 응답 수신
		Map response = webClient.post()//POST요청
				.uri(url)//상세주소
				.bodyValue(body)//첨부데이터
			.retrieve()//응답 수신 허용
				.bodyToMono(Map.class)//일시불(Mono)로 수신 (↔ 할부는 Flux)
				.block();//동기방식으로 수신
		
//		결과 확인
		for(Object key : response.keySet()) {
			Object value = response.get(key);
			log.debug("{} = {}", key, value);
		}
	}
	
}
