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
	public List<StatVO> lectureByCategory() {
		String sql = "select lecture_category title, count(*) value "
					+ "from lecture group by lecture_category "
					+ "order by value desc, title asc";
		return jdbcTemplate.query(sql, statMapper);
	}
	public List<StatVO> lectureByType() {
		String sql = "select lecture_type title, count(*) value "
					+ "from lecture group by lecture_type "
					+ "order by value desc, title asc";
		return jdbcTemplate.query(sql, statMapper);
	}
	public List<StatVO> bookByGenre() {
		String sql = "select book_genre title, count(*) value "
					+ "from book group by book_genre "
					+ "order by value desc, title asc";
		return jdbcTemplate.query(sql, statMapper);
	}
	public List<StatVO> memberByLevel() {
		String sql = "select member_level title, count(*) value "
					+ "from member group by member_level "
					+ "order by value desc, title asc";
		return jdbcTemplate.query(sql, statMapper);
	}
	public List<StatVO> boardByHead() {
		String sql = "select nvl(board_head, '없음') title, count(*) value "
					+ "from board group by board_head "
					+ "order by value desc, title asc";
		return jdbcTemplate.query(sql, statMapper);
	}
}








