package com.kh.spring09.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.spring09.dto.MemberExitDto;

@Component
public class MemberExitMapper implements RowMapper<MemberExitDto>{
	@Override
	public MemberExitDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberExitDto memberExitDto = new MemberExitDto();
		//16+1개(탈퇴시각)의 정보를 옮겨담는다
		memberExitDto.setMemberId(rs.getString("member_id"));
		memberExitDto.setMemberEmail(rs.getString("member_email"));
		memberExitDto.setMemberPassword(rs.getString("member_password"));
		memberExitDto.setMemberNickname(rs.getString("member_nickname"));
		memberExitDto.setMemberBirth(rs.getString("member_birth"));
		memberExitDto.setMemberContact(rs.getString("member_contact"));
		memberExitDto.setMemberPost(rs.getString("member_post"));
		memberExitDto.setMemberAddress1(rs.getString("member_address1"));
		memberExitDto.setMemberAddress2(rs.getString("member_address2"));
		memberExitDto.setMemberLevel(rs.getString("member_level"));
		memberExitDto.setMemberMessage(rs.getString("member_message"));
		memberExitDto.setMemberJoin(rs.getTimestamp("member_join"));
		memberExitDto.setMemberLogin(rs.getTimestamp("member_login"));
		memberExitDto.setMemberChange(rs.getTimestamp("member_change"));
		memberExitDto.setMemberBlock(rs.getString("member_block"));
		memberExitDto.setMemberPoint(rs.getInt("member_point"));
		memberExitDto.setMemberExitTime(rs.getTimestamp("member_exit_time"));
		return memberExitDto;
	}
}
