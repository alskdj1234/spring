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
=======
import jakarta.servlet.http.HttpSession;

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
	            + "member_address2, member_message, member_level"
	            + ") values ( "
	            + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, default" // ?는 10개, 마지막은 고정값 default
	            + ")";
	            
	    // params 배열에서 memberDto.getMemberLevel()를 삭제 (10개만 유지)
	    Object[] params = {
	        memberDto.getMemberId(),       // 1
	        memberDto.getMemberEmail(),    // 2
	        memberDto.getMemberPassword(), // 3
	        memberDto.getMemberNickname(), // 4
	        memberDto.getMemberBirth(),    // 5
	        memberDto.getMemberContact(),  // 6
	        memberDto.getMemberPost(),     // 7
	        memberDto.getMemberAddress1(), // 8
	        memberDto.getMemberAddress2(), // 9
	        memberDto.getMemberMessage()   // 10
	    };
	
		jdbcTemplate.update(sql, params);
	
	}
	
	public MemberDto selectOne(String memberId) {
		String sql = " select member.* from member where member_id = ?";
		Object[] params = {memberId};
		List<MemberDto> success = jdbcTemplate.query(sql, memberMapper,params );
		return success.isEmpty()?null : success.get(0);
		
	}
	
	public boolean updateMemberLogin(String memberId) {
		String sql = "update member set member_login=systimestamp where member_id=?";
		Object[] params = {memberId};
		return jdbcTemplate.update(sql, params) > 0;
	}
	
	public boolean changePassword(MemberDto memberDto) {
		
		String sql ="update member set member_password=?"
				+ ", member_change=systimestamp where member_id=?";
		Object[] params = {memberDto.getMemberPassword(),memberDto.getMemberId()};
		return jdbcTemplate.update(sql,params)>0;
		
		
		
	}
	

	
}
