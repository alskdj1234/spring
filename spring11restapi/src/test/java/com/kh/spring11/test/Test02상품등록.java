package com.kh.spring11.test;

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
public class Test02상품등록 {
	@Autowired
	private SaleService saleService;
	
	@Test
	public void test() throws IllegalStateException, IOException {
		SaleAddResponseVO response = saleService.add(
				
				SaleAddRequestVO.builder()
					.saleName("반포 자이")
					.saleCategory("투기")
					.saleOriginalPrice(10)
					.saleStock(1)
					.saleContent("설명 없음")
					.build()
				);
		log.debug("response ={}" , response);
	}
}
