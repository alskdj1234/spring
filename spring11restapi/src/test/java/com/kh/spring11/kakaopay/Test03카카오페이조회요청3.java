package com.kh.spring11.kakaopay;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import com.kh.spring11.configuration.KakaopayProperties;
import com.kh.spring11.service.KakaopayService;
import com.kh.spring11.vo.kakaopay.KakaopayOrderRequestVO;
import com.kh.spring11.vo.kakaopay.KakaopayOrderResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test03카카오페이조회요청3 {
	
	@Autowired
	private KakaopayService kakaopayService;
	
	@Test
	public void test() {
		KakaopayOrderResponseVO payResponse = kakaopayService.order(
			KakaopayOrderRequestVO.builder()
					.tid("Ta73d58961000ca9ea68")
				.build()
		);
		
		log.debug("response = {}", payResponse);
	}
	
}




