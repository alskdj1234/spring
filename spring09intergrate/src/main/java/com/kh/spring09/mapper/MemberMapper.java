package com.kh.spring09.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.spring09.dto.MemberDto;

@Component
public class MemberMapper implements RowMapper<MemberDto> {

	@Override
	public MemberDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberDto memberDto = new MemberDto();
		memberDto.setMemberId(rs.getString("member_id"));
		memberDto.setMemberEmail(rs.getString("member_email"));
		memberDto.setMemberPassword(rs.getString("member_password"));
		memberDto.setMemberNickname(rs.getString("member_nickname"));
		memberDto.setMemberBirth(rs.getString("member_birth"));
		memberDto.setMemberContact(rs.getString("member_contact"));
		memberDto.setMemberPost(rs.getString("member_post"));
		memberDto.setMemberAddress1(rs.getString("member_address1"));
		memberDto.setMemberAddress2(rs.getString("member_Address2"));
		memberDto.setMemberLevel(rs.getString("member_level"));
		memberDto.setMemberMessage(rs.getString("member_message"));
		memberDto.setMemberBlock(rs.getString("member_block"));
		memberDto.setMemberJoin(rs.getObject("member_join", LocalDateTime.class));
		memberDto.setMemberLogin(rs.getObject("member_Login", LocalDateTime.class)==null ? null : rs.getObject("member_Login", LocalDateTime.class));
		memberDto.setMemberChange(rs.getObject("member_change", LocalDateTime.class)==null ? null : rs.getObject("member_change", LocalDateTime.class));
		return memberDto;
	}
	
	
}
