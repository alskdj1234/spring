package com.kh.spring11.kakaopay;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import com.kh.spring11.configuration.KakaopayProperties;
import com.kh.spring11.vo.kakaopay.KakaopayReadyRequestVO;
import com.kh.spring11.vo.kakaopay.KakaopayReadyResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test01카카오페이준비요청3 {
	
	//@Autowired만 적으면 동일한 요소가 애플리케이션에 2개 이상이 있을 경우 오류가 발생
	//추가로 @Qualifier를 적어서 아이디를 지정하여 주입한다
	@Qualifier("kakaopayClient")
	@Autowired
	private WebClient webClient;
	
	@Autowired
	private KakaopayProperties kakaopayProperties;
	
	@Test
	public void test() {
//		상세 주소 설정
		String url = "/online/v1/payment/ready";
		
//		보낼 데이터(Body) 준비
		KakaopayReadyRequestVO request = KakaopayReadyRequestVO.builder()
					.cid(kakaopayProperties.getCid())
					.partnerOrderId(UUID.randomUUID().toString())
					.partnerUserId("testuser1")
					.itemName("이모티콘 10종세트 외 2건")
					.totalAmount(3000)
					.approvalUrl("http://localhost:8080/success")
					.cancelUrl("http://localhost:8080/cancel")
					.failUrl("http://localhost:8080/fail")
				.build();
		
//		요청 발송 및 응답 수신
		KakaopayReadyResponseVO response = webClient.post()//POST요청
				.uri(url)//상세주소
				.bodyValue(request)//첨부데이터
			.retrieve()//응답 수신 허용
				.bodyToMono(KakaopayReadyResponseVO.class)//일시불(Mono)로 수신 (↔ 할부는 Flux)
				.block();//동기방식으로 수신
		
//		결과 확인
		log.debug("partner_order_id = {}", request.getPartnerOrderId());
		log.debug("partner_user_id = {}", request.getPartnerUserId());
		log.debug("tid = {}", response.getTid());
		log.debug("url = {}", response.getNextRedirectPcUrl());
		log.debug("time = {}", response.getCreatedAt());
	}
}









