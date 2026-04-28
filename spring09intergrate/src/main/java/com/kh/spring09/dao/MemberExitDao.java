package com.kh.spring09.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring09.dto.MemberExitDto;
import com.kh.spring09.mapper.MemberExitMapper;

@Repository
public class MemberExitDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private MemberExitMapper memberExitMapper;
	
	//등록 - 회원 탈퇴를 눌렀을 때 실행될 기능
	public void insert(String memberExitId) {
		String sql = "insert into member_exit(member_exit_id) values(?)";
		Object[] params = { memberExitId };
		jdbcTemplate.update(sql, params);
	}
	
	//상세조회
	public MemberExitDto selectOne(String memberExitId) {
		String sql = "select * from member_with_exit where member_id=?";
		Object[] params = { memberExitId };
		List<MemberExitDto> list = jdbcTemplate.query(sql, memberExitMapper, params);
		return list.isEmpty() ? null : list.get(0);
	}
}






