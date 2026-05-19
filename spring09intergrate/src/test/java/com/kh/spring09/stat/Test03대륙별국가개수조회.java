package com.kh.spring09.stat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring09.dao.StatDao;
import com.kh.spring09.vo.StatVO;

@SpringBootTest
public class Test03대륙별국가개수조회 {
	@Autowired
	private StatDao statDao;
	
	@Test
	public void test() {
		List<StatVO> list = statDao.countryByRegion();
		for(StatVO statVO : list) {
			System.out.println(statVO);
		}
	}
}