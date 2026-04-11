package com.kh.spring06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring06.dao.CountryDao;
import com.kh.spring06.dto.CountryDto;

@RestController // 등록 (제어 반전)
@RequestMapping("/country")
public class CountryController {
	@Autowired // 주세요 해봐 (의존성 주입)
	private CountryDao countryDao;

	@RequestMapping("/insert")
	public String insert(@ModelAttribute CountryDto countryDto) {

		countryDao.insert(countryDto);
		return " 등록 완 ";
	}

	@RequestMapping("/update")
	public String update(@ModelAttribute CountryDto countryDto) {
		boolean success = countryDao.update(countryDto);

		if (success) {
			return "국가 정보 변경이 완료됨";
		} else {
			return "국가 존재 x";
		}

	}

	@RequestMapping("/delete") // 마지막 /<-엔드포인트
	public String delete(@RequestParam int countryNo) {
		boolean success = countryDao.delete(countryNo);
		if (success) {
			return "국가 정보 삭제가 완료됨";
		} else {
			return "국가 존재 x";
		}
	}

	@RequestMapping("/list")
	public String list(@RequestParam(required = false) String column, @RequestParam(required = false) String keyword) {

		// 검색어가 있든 없든 상관없이 검색을 수행(내부적으로 구분)
		List<CountryDto> list = countryDao.selectList(column, keyword);
		// StringBuffer를 이용해서 문자열로 만들어서 반환
		// 화면으로 바뀌는 부분
		StringBuffer buffer = new StringBuffer();
		buffer.append("결과 수 : " + list.size());
		buffer.append("<br>");
		for (CountryDto countryDto : list) {
			buffer.append(countryDto);
			buffer.append("<br>");
		}
		return buffer.toString();

	}

	@RequestMapping("/detail")
	public String detail(@RequestParam int countryNo) {
		CountryDto countryDto =countryDao.selectOne(countryNo);
		
		if(countryDto==null) {
			return "국가 존재 x";
		}
		else {
			StringBuffer buffer = new StringBuffer();
			buffer.append(countryDto.getCountryNo()+"<br>");
			buffer.append("대륙: " + countryDto.getCountryRegion()+"<br>");
			buffer.append("이름: "+countryDto.getCountryName()+"<br>");
			buffer.append("수도: "+countryDto.getCountryCapital()+"<br>");
			buffer.append("인구 수: "+countryDto.getCountryPopulation()+"<br>");
		
			return buffer.toString();
		
		}
	
	}
	
}
