package com.kh.spring11.mybatis;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.dao.CountryDao;
import com.kh.spring11.dto.CountryDto;
import com.kh.spring11.service.KoreanService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test08한글가지고놀기3 {
	
	@Autowired
	private KoreanService koreanService;
	
	@Autowired
	private CountryDao countryDao;
	
	@Test
	public void test() {
		String keyword = "잊";
		
		List<CountryDto> list = countryDao.selectList(null, Integer.MAX_VALUE);
		
		List<CountryDto> result = list.stream()
				.filter(country->koreanService.isMatch(country.getCountryName(), keyword))
				.toList();
		
		log.debug("검색 결과 : {}", result.size());
		for(CountryDto countryDto : result) {
			log.debug("{} -> {}", countryDto.getCountryName(), countryDto);
		}
	}
	
}
