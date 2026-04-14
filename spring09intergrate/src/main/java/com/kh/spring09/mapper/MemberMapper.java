package com.kh.spring09.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.spring09.dto.MemberDto;

@Component
public class MemberMapper implements RowMapper<MemberDto>{
	@Override
	public MemberDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberDto memberDto = new MemberDto();
		//16개의 정보를 옮겨담는다
		memberDto.setMemberId(rs.getString("member_id"));
		memberDto.setMemberEmail(rs.getString("member_email"));
		memberDto.setMemberPassword(rs.getString("member_password"));
		memberDto.setMemberNickname(rs.getString("member_nickname"));
		memberDto.setMemberBirth(rs.getString("member_birth"));
		memberDto.setMemberContact(rs.getString("member_contact"));
		memberDto.setMemberPost(rs.getString("member_post"));
		memberDto.setMemberAddress1(rs.getString("member_address1"));
		memberDto.setMemberAddress2(rs.getString("member_address2"));
		memberDto.setMemberLevel(rs.getString("member_level"));
		memberDto.setMemberMessage(rs.getString("member_message"));
		memberDto.setMemberJoin(rs.getTimestamp("member_join"));
		memberDto.setMemberLogin(rs.getTimestamp("member_login"));
		memberDto.setMemberChange(rs.getTimestamp("member_change"));
		memberDto.setMemberBlock(rs.getString("member_block"));
		memberDto.setMemberPoint(rs.getInt("member_point"));
		return memberDto;
	}
}