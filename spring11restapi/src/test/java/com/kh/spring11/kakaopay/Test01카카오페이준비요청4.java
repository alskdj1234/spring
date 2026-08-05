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
import com.kh.spring11.service.KakaopayService;
import com.kh.spring11.vo.kakaopay.KakaopayReadyRequestVO;
import com.kh.spring11.vo.kakaopay.KakaopayReadyResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test01카카오페이준비요청4 {
	
	@Autowired
	private KakaopayService kakaopayService;
	
	@Autowired
	private KakaopayProperties kakaopayProperties;
	
	@Test
	public void test() {
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
		KakaopayReadyResponseVO response = kakaopayService.ready(request);
		
//		결과 확인
		log.debug("partner_order_id = {}", request.getPartnerOrderId());
		log.debug("partner_user_id = {}", request.getPartnerUserId());
		log.debug("tid = {}", response.getTid());
		log.debug("url = {}", response.getNextRedirectPcUrl());
		log.debug("time = {}", response.getCreatedAt());
	}
}









