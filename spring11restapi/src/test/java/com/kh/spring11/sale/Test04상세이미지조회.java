package com.kh.spring11.sale;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.dao.SaleDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test04상세이미지조회 {
	@Autowired
	private SaleDao saleDao;
	
	@Test
	public void test() {
		List<Integer> details = saleDao.findDetails(20);
		log.debug("size = {}", details.size());
		
		for(int attachNo : details) {
			log.debug("attachNo = {}", attachNo);
		}
	}
}
