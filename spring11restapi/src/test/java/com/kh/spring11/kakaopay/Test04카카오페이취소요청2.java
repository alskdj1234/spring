package com.kh.spring11.kakaopay;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import com.kh.spring11.configuration.KakaopayProperties;
import com.kh.spring11.vo.kakaopay.KakaopayCancelRequestVO;
import com.kh.spring11.vo.kakaopay.KakaopayCancelResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test04카카오페이취소요청2 {

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
		KakaopayCancelRequestVO payRequest = KakaopayCancelRequestVO.builder()
					.cid(kakaopayProperties.getCid())
					.tid("Ta72f50826bf7fb4c2cc")
					.cancelAmount(10)
				.build();
		
//		요청 발송 및 응답 수신
		KakaopayCancelResponseVO payResponse = webClient.post()//POST요청
				.uri(url)//상세주소
				.bodyValue(payRequest)//첨부데이터
			.retrieve()//응답 수신 허용
				.bodyToMono(KakaopayCancelResponseVO.class)//일시불(Mono)로 수신 (↔ 할부는 Flux)
				.block();//동기방식으로 수신
		
//		결과 확인
		log.debug("payResponse = {}", payResponse);
	}
	
}
