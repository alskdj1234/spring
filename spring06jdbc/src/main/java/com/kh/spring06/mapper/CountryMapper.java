package com.kh.spring06.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.spring06.dto.CountryDto;
@Component//부품 역할을 하는 작은 기능을 처리하는 도구(단위 기능)
public class CountryMapper implements RowMapper<CountryDto> {
	@Override
	public CountryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		//rs를 컨트리디티오로 변환하는 코드
		
		CountryDto countryDto = new CountryDto();
		countryDto.setCountryNo(rs.getInt("country_no"));
		countryDto.setCountryRegion(rs.getString("country_region"));
		countryDto.setCountryName(rs.getString("country_name"));
		countryDto.setCountryCapital(rs.getString("country_capital"));
		countryDto.setCountryPopulation(rs.getLong("country_population"));
	
		return countryDto;
	}
}
