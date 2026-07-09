package com.kh.spring10.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.spring10.dto.LectureDto;

@Component//외부 도움 없이 스스로 작업을 해내는 도구
public class LectureMapper implements RowMapper<LectureDto>{
	@Override
	public LectureDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		LectureDto lectureDto = new LectureDto();
		lectureDto.setLectureNo(rs.getInt("lecture_no"));
		lectureDto.setLectureTitle(rs.getString("lecture_title"));
		lectureDto.setLectureCategory(rs.getString("lecture_category"));
		lectureDto.setLectureDuration(rs.getInt("lecture_duration"));
		lectureDto.setLecturePrice(rs.getInt("lecture_price"));
		lectureDto.setLectureType(rs.getString("lecture_type"));
		return lectureDto;
	}
}





