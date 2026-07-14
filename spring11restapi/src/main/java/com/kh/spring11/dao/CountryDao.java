package com.kh.spring11.dao;

import java.util.List;

import com.kh.spring11.dto.CountryDto;
import com.kh.spring11.vo.CountryComplexRequestVO;

public interface CountryDao {
	int sequence();//등록
	void insert(CountryDto countryDto);//등록
	boolean update(CountryDto countryDto);//전체수정
	boolean delete(int countryNo);//삭제
	CountryDto selectOne(int countryNo);//상세
	List<CountryDto> selectList(Integer lastCountryNo, int size);//목록
	int count(Integer lastCountryNo);//개수
	List<CountryDto> searchByCountryName(String keyword);
	List<CountryDto> complexSearch(CountryComplexRequestVO vo);
}
