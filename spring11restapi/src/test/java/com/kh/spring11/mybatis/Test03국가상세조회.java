package com.kh.spring11.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.dto.CountryDto;

import lombok.extern.slf4j.Slf4j;
@Slf4j//롬복 제공 로그 도구
@SpringBootTest
public class Test03국가상세조회 {
	@Autowired
	private SqlSession sqlSession;

	@Test
	public void test() {
		int countryNo = 5;
		CountryDto findCountryDto = sqlSession.selectOne("mapper.country.find", countryNo);
//		System.out.println(findCountryDto);
		log.debug("countryDto={)", findCountryDto);
	}
}
