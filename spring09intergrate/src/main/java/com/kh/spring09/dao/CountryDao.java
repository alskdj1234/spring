package com.kh.spring09.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring09.dto.CountryDto;
import com.kh.spring09.mapper.CountryMapper;

@Repository // db나 파일을 제어하기 우히나 도구 (영속성을 가진 대상 제어 도구)
public class CountryDao {
	@Autowired // 주세요 해봐 (의존성 주입)
	private JdbcTemplate jdbcTemplate;
	@Autowired // 주세요 해봐 (의존성 주입)
	private CountryMapper countryMapper;

	public void insert(CountryDto countryDto) {
		String sql = "insert into country" + "(country_no, country_region, country_name, "
				+ "country_capital, country_population" + ") values(country_seq.nextval,? ,? ,? ,?)";
		Object[] params = { countryDto.getCountryRegion(), countryDto.getCountryName(), countryDto.getCountryCapital(),
				countryDto.getCountryPopulation() };
		jdbcTemplate.update(sql, params);

	}

	// 수정처리
	public boolean update(CountryDto countryDto) {

		String sql = "update country " + "set country_region=?, country_name=?, "
				+ "country_capital=?, country_population=? " + "where country_no=?";

		Object[] params = { countryDto.getCountryRegion(), countryDto.getCountryName(), countryDto.getCountryCapital(),
				countryDto.getCountryPopulation(), countryDto.getCountryNo() };
		int rows = jdbcTemplate.update(sql, params);
		return rows > 0;// rows가 0보다 큰지 작은지 판단해서 반환
	}

	public boolean delete(int countryNo) {

		String sql = " delete country where country_no=?";
		Object[] params = { countryNo };
		return jdbcTemplate.update(sql, params) > 0;
	}

	public List<CountryDto> selectList() {

		String sql = "select * from country order by country_no asc";

		return jdbcTemplate.query(sql, countryMapper);
	}

	public List<CountryDto> selectList(String column, String keyword) {
		if (column == null || keyword == null)
			return selectList();

		Set<String> allowList = Set.of("country_region", "country_name", "country_capital");
		if (allowList.contains(column) == false)
			return List.of();

		String sql = "select * from country where instr(" + column + ",?)>0 order by country_no asc";

		Object[] params = { keyword };

		return jdbcTemplate.query(sql, countryMapper, params);
	}
	
	public CountryDto selectOne(int countryNo) {
		
		String sql = "select * from country where country_no = ? ";
		Object[] params = {countryNo};
		
		List<CountryDto> list = jdbcTemplate.query(sql, countryMapper,params);
		return list.isEmpty()?null:list.get(0);
		
	}
}
