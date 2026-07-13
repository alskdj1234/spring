package com.kh.spring11.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.dto.CountryDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j//롬복이 제공하는 로그 도구 생성 구문
@SpringBootTest
public class Test03국가상세 {
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		int countryNo = 1234;
		CountryDto countryDto = sqlSession.selectOne(
									"mapper.country.find", countryNo);
		//System.out.println(countryDto);
		log.debug("countryDto = {}", countryDto);
	}
}




