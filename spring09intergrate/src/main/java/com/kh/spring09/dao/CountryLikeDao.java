package com.kh.spring09.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CountryLikeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insert(String memberId, int countryNo) {
		String sql = "insert into country_like(member_id, country_no) values(?,?)";
		Object[] params = {memberId,countryNo};
		jdbcTemplate.update(sql,params);
	}
	
	public boolean delete(String memberId,int countryNo) {
		String sql = "delete country_like where member_id=? and country_no=?";
		Object[] params = {memberId, countryNo};
		jdbcTemplate.update(sql,params);
		return jdbcTemplate.update(sql,params)>0;
	}

	public boolean check(String memberId, int countryNo) {
		String sql ="select count(*) from country_like where member_id=? and country_no=?";
		Object[] params = {memberId, countryNo};
		return jdbcTemplate.queryForObject(sql, int.class,params)>0;
	}
	
	public int count(int countryNo) {
		String sql ="select count(*) from country_like where country_no=?";
		Object[] params = {countryNo};
		return jdbcTemplate.queryForObject(sql, int.class,params);
	}
}
