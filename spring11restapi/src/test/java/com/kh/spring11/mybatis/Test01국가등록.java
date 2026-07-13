package com.kh.spring11.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.dto.CountryDto;

//JDBC의 종류
//1.자바 기본 패키지(java.sql 패키지)
//2.Spring JDBC
//3.ORM Framework
//	(1) myBatis - 구문만큼은 내가 쓰고 자바처럼 생각할 수 있게 해줄게!
//	(2) Spring Data JPA (Hibernate) - 구문도 쓰지말고 코드로 다 해치워!
@SpringBootTest
public class Test01국가등록 {
	//mybatis 실행도구를 가져오도록 설정(자동으로 생성됨)
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		//실행법 : sqlSession.insert(구문정보, 데이터);
		sqlSession.insert(
			"mapper.country.add", 
			CountryDto.builder()
				.countryNo(1236)
				.countryName("자바나라")
				.countryCapital("오라클")
				.countryRegion("유럽")
				.countryPopulation(1000000L)
			.build()
		);
	}
}




