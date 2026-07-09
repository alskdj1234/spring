package com.kh.spring11.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.dto.CountryDto;

//jdbc의 종류
//1.자바 기본 패키지(java.sql)
//2. Spring jdbc
//3.orm framework
//(1) mybatis 구문만큼은 내가 씀 
//(2) spring data jpa (hibernate) 구문도 안 쓰고 코드로 후려침
@SpringBootTest
public class Test01국가등록 {
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		sqlSession.insert(
				"mapper.country.add",
				CountryDto.builder()
						.countryNo(1233)
						.countryName("살려줘")
						.countryCapital("수도")
						.countryRegion("대륙")
						.countryPopulation(1000000L)
						.build());
	};
}
