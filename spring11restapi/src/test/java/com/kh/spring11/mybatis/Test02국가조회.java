package com.kh.spring11.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.dto.CountryDto;

@SpringBootTest
public class Test02국가조회 {
	@Autowired
	private SqlSession sqlSession;

	@Test
	public void test() {
		List<CountryDto> list = sqlSession.selectList("mapper.country.list");
		System.out.println(list.size());
		for(CountryDto countryDto : list) {
			System.out.println(countryDto);
		}
	}
}
