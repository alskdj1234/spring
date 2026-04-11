
package com.kh.spring06.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.spring06.dto.CourseDto;
@Component//외부 도움없이(오토와이어 없이) 스스로 작업을 해내는 도구
public class CourseMapper implements RowMapper<CourseDto>{
	
	public CourseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		CourseDto courseDto = new CourseDto();
		courseDto.setCourseNo(rs.getInt("course_no"));
		courseDto.setCourseName(rs.getString("course_name"));
		courseDto.setCategory(rs.getString("category"));
		courseDto.setLectureTime(rs.getInt("lecture_time"));
		courseDto.setFee(rs.getLong("fee"));
		courseDto.setClassType(rs.getString("class_type"));
		
		return courseDto;
	}
}