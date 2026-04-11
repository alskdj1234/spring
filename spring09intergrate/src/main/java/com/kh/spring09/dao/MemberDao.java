package com.kh.spring09.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring09.dto.MemberDto;
import com.kh.spring09.mapper.MemberMapper;

@Repository
public class MemberDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private MemberMapper memberMapper;

	
	
	public void insert(MemberDto memberDto) {
		String sql = "insert into member ( "
		        + "member_id, member_email, member_password, member_nickname, "
		        + "member_birth, member_contact, member_post, member_address1, "
		        + "member_address2, member_message, member_point "
		        + ") values ( "
		        + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?" 
		        + " )";
		Object[] params= {memberDto.getMemberId(),memberDto.getMemberEmail(),memberDto.getMemberPassword(),
				memberDto.getMemberNickname(),memberDto.getMemberBirth(),memberDto.getMemberContact(),memberDto.getMemberPost(),
				memberDto.getMemberAddress1(),memberDto.getMemberAddress2(),memberDto.getMemberMessage(),memberDto.getMemberPoint()
		};
	
		jdbcTemplate.update(sql, params);
	
	}
	
	public boolean login(MemberDto memberDto) {
		String sql = " select member.* from member where member_id = ? and member_password = ?";
		Object[] params = {memberDto.getMemberId(),memberDto.getMemberPassword()};
		List<MemberDto> success = jdbcTemplate.query(sql, memberMapper,params );
		return !success.isEmpty()?true:false;
		
	}
	
}
