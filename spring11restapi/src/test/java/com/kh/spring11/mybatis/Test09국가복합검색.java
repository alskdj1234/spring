package com.kh.spring11.mybatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.dto.CountryDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test09국가복합검색 {
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		//선택적으로 구문에 데이터를 추가
		Map<String, Object> params = new HashMap<>();
//		params.put("countryRegions", List.of("아시아", "유럽", "아프리카"));
		
//		params.put("countryName", "대한");
//		params.put("countryCapital", "티아");
//		params.put("minCountryPopulation", 10000);
//		params.put("maxCountryPopulation", 99999);
		
//		params.put("orders", List.of(
//			"country_population desc", 
//			"country_name asc" 
//		));
		
//		params.put("lastCountryNo", 34);
//		params.put("size", 10);
		
		List<CountryDto> list = sqlSession.selectList(
				"mapper.country.complexSearch", params);
		log.debug("결과 수 : {}개", list.size());
		for(CountryDto countryDto : list) {
			log.debug(countryDto.toString());
		}
	}
}
