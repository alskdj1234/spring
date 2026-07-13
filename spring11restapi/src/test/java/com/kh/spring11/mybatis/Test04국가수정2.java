package com.kh.spring11.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.dto.CountryDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test04국가수정2 {
	@Autowired
	private SqlSession sqlSession;
	
	//@Slf4j를 사용하면 생기는 숨겨진 객체
	//private static final Logger log = 
	//			LoggerFactory.getLogger(Test04국가수정.class);
	
	@Test
	public void test() {
		int rows = sqlSession.update(
			"mapper.country.updateUnit",
			CountryDto.builder()
				.countryNo(1235)
//				.countryName("변경한국가")
				.countryRegion("아시아")
				.countryCapital("변경한수도")
//				.countryPopulation(5000000L)
			.build()
		);
		//log.debug("rows = " + rows);
		log.debug("rows = {}", rows);
		
	}
}







