package com.kh.spring09.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring09.mapper.StatMapper;
import com.kh.spring09.vo.StatVO;

@Repository
public class StatDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private StatMapper statMapper;
	
	public List<StatVO> countryByRegion() {
		String sql = "select country_region title, count(*) value "
					+ "from country group by country_region "
					+ "order by value desc, title asc";
		return jdbcTemplate.query(sql, statMapper);
	}
}