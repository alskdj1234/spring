package com.kh.spring09.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.spring09.dto.ReplyDto;

@Component
public class ReplyMapper implements RowMapper<ReplyDto> {


 @Override
 public ReplyDto mapRow(ResultSet rs, int rowNum) throws SQLException {
	 ReplyDto replyDto = new ReplyDto();
	 replyDto.setReplyContent(rs.getString("reply_content"));
	 replyDto.setReplyWriter(rs.getString("reply_writer"));
	 replyDto.setReplyOrigin(rs.getLong("reply_origin"));
	 replyDto.setReplyNo(rs.getLong("reply_no"));
	 replyDto.setReplyWtime(rs.getTimestamp("reply_wtime"));
	 replyDto.setReplyEtime(rs.getTimestamp("reply_etime"));

	 return replyDto;
//	 return ReplyDto.builder()
//			 .replyNo(rs.getLong("reply_no"));
	 	
	
 }
}
