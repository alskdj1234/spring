package com.kh.spring11.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.dto.CountryDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test07국가명검색 {
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		String keyword = "이";
		List<CountryDto> list = sqlSession.selectList(
				"mapper.country.searchByKeyword", keyword);
		log.debug("결과 수 = {}", list.size());
		for(CountryDto countryDto : list) {
			log.debug(countryDto.toString());
		}
	}
}
