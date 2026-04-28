package com.kh.spring09.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.spring09.dto.MemberHistoryDto;

@Component
public class MemberHistoryMapper implements RowMapper<MemberHistoryDto>{
	@Override
	public MemberHistoryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberHistoryDto memberHistoryDto = new MemberHistoryDto();
		memberHistoryDto.setMemberHistoryNo(rs.getInt("member_history_no"));
		memberHistoryDto.setMemberHistoryTime(rs.getTimestamp("member_history_time"));
		memberHistoryDto.setMemberHistoryOrigin(rs.getString("member_history_origin"));
		memberHistoryDto.setMemberHistoryAddress(rs.getString("member_history_address"));
		memberHistoryDto.setMemberHistoryAgent(rs.getString("member_history_agent"));
		return memberHistoryDto;
	}
}







