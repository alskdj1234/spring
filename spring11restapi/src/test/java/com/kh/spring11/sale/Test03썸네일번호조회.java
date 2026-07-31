package com.kh.spring11.sale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.dao.SaleDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test03썸네일번호조회 {
	@Autowired
	private SaleDao saleDao;
	
	@Test
	public void test() {
		Integer attachNo = saleDao.findAttach(18);
		log.debug("attachNo = {}", attachNo);
	}
}
