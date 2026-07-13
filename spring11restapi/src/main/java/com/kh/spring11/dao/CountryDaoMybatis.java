package com.kh.spring11.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring11.dto.CountryDto;
import com.kh.spring11.vo.CountryComplexRequestVO;

@Repository
public class CountryDaoMybatis implements CountryDao{
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int sequence() {
		return sqlSession.selectOne("mapper.country.sequence");
	}

	@Override
	public void insert(CountryDto countryDto) {
		sqlSession.insert("mapper.country.add", countryDto);
	}

	@Override
	public boolean update(CountryDto countryDto) {
		return sqlSession.update("mapper.country.updateUnit", countryDto) > 0;
	}

	@Override
	public boolean delete(int countryNo) {
		return sqlSession.delete("mapper.country.delete", countryNo) > 0;
	}

	@Override
	public CountryDto selectOne(int countryNo) {
		return sqlSession.selectOne("mapper.country.find", countryNo);
	}

	@Override
	public List<CountryDto> selectList(Integer lastCountryNo, int size) {
		//mybatis는 구문 뒤에 데이터를 딱 하나만 보낼 수 있다
		//return sqlSession.selectList("mapper.country.listMore", lastCountryNo, size);
		
		Map<String, Object> params = new HashMap<>();
		params.put("lastCountryNo", lastCountryNo);
		params.put("size", size);
		return sqlSession.selectList("mapper.country.listMore", params);
	}
	@Override
	public int count(Integer lastCountryNo) {
		return sqlSession.selectOne("mapper.country.countMore", lastCountryNo);
	}

	@Override
	public List<CountryDto> searchByCountryName(String keyword) {
		return sqlSession.selectList("mapper.country.searchByKeyword", keyword);
	}

	@Override
	public List<CountryDto> complexSearch(CountryComplexRequestVO vo) {
		return sqlSession.selectList("mapper.country.complexSearch", vo);
	}

	@Override
	public int complexSearchCount(CountryComplexRequestVO vo) {
		return sqlSession.selectOne("mapper.country.complexSearchCount", vo);
	}
	
}