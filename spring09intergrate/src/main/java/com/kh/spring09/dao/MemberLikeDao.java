package com.kh.spring09.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberLikeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//구현해야하는 기능 : 등록, 삭제, 검사, 개수확인
	public void insert(String memberId, String memberTarget) {
		String sql = "insert into member_like(member_id, member_target) values(?, ?)";
		Object[] params = { memberId, memberTarget };
		jdbcTemplate.update(sql, params);
	}
	public boolean delete(String memberId, String memberTarget) {
		String sql = "delete member_like where member_id=? and member_target=?";
		Object[] params = { memberId, memberTarget };
		return jdbcTemplate.update(sql, params) > 0;
	}
	public boolean check(String memberId, String memberTarget) {
		String sql = "select count(*) from member_like where member_id=? and member_target=?";
		Object[] params = { memberId, memberTarget };
		return jdbcTemplate.queryForObject(sql, int.class, params) > 0;
	}
	public int count(String memberTarget) {
		String sql = "select count(*) from member_like where member_target=?";
		Object[] params = { memberTarget };
		return jdbcTemplate.queryForObject(sql, int.class, params);
	}
}