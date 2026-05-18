package com.kh.spring09.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardLikeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

//구현 기능 : 등록 ,삭제 , 검사 ,개수 확인

	public void insert(String memberId, int boardNo) {
		String sql = "insert into board_like(member_id, board_no) values(?,?)";
		Object[] params = { memberId, boardNo };
		jdbcTemplate.update(sql, params);
	}
	
	public boolean delete(String memberId, int boardNo) {
		String sql = "delete board_like where memeber_id=? and board_no=?";
		Object[] params = { memberId, boardNo };
		jdbcTemplate.update(sql, params);
		return jdbcTemplate.update(sql,params)>0;
	}
	
	public boolean check(String memberId, int boardNo) {
		String sql = "select count(*) from board_like where member_id=? and board_no=?";
		Object[] params = {memberId, boardNo};
		return jdbcTemplate.queryForObject(sql, int.class,params)>0;
	}
	
	public int count(int boardNo) {
		String sql = "select count(*) from board_like where board_no=?";
		Object[] params = { boardNo};
		return jdbcTemplate.queryForObject(sql, int.class,params);
	}
	
	
}
