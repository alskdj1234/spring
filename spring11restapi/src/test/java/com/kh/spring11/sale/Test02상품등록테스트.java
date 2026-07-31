package com.kh.spring11.sale;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.service.SaleService;
import com.kh.spring11.vo.sale.SaleAddRequestVO;
import com.kh.spring11.vo.sale.SaleAddResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test02상품등록테스트 {
	
	@Autowired
	private SaleService saleService;
	
	@Test
	public void test() throws IllegalStateException, IOException {
		SaleAddResponseVO response = saleService.add(
			SaleAddRequestVO.builder()
				.saleName("강남 아파트1채")
				.saleCategory("주택")
				.saleOriginalPrice(1000000)
				.saleStock(1)
				.saleContent("설명 없음")
			.build()
		);
		log.debug("Response = {}", response);
	}
	
}
