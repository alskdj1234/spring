package com.kh.spring06.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring06.mapper.CourseMapper;
import com.kh.spring06.dto.CourseDto;

@Repository // 파일 또는 dbms 제어도구
public class CourseDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CourseMapper courseMapper;

	public void insert(CourseDto courseDto) {
		String sql = "insert into course" + "(course_no, course_name, category, " + "lecture_time, fee, class_type "
				+ ")values(seq_course.nextval,?,?,?,?,?)";

		Object[] params = { courseDto.getCourseName(), courseDto.getCategory(), courseDto.getLectureTime(),
				courseDto.getFee(), courseDto.getClassType() };
		jdbcTemplate.update(sql, params);

	}

	public boolean update(CourseDto courseDto) {
		String sql = "update set course"
				+ "course_no=?, course_name=?, category=?, lecture_time=?, fee=?, class_type=?";
		Object[] params = { courseDto.getCourseNo(), courseDto.getCourseName(), courseDto.getCategory(),
				courseDto.getLectureTime(), courseDto.getFee(), courseDto.getClassType() };
		return jdbcTemplate.update(sql, params) > 0;
	}

	public boolean delete(int courseNo) {
		String sql = "delete from course where course_no=?";
		Object[] params = {courseNo};
		return jdbcTemplate.update(sql, params) > 0;

	}
	
	public List<CourseDto> selectList(){
		String sql = "select * from course order by course_no asc";

		return jdbcTemplate.query(sql, courseMapper);
	}


	public List<CourseDto> selectList(String column, String keyword) {
		if (column == null || keyword == null)
			return selectList();

		Set<String> allowList = Set.of("course_name", "category", "class_type");
		if (allowList.contains(column) == false)
			return List.of();

		String sql = "select * from course where instr(" + column + ",?)>0 order by course_no asc";

		Object[] params = { keyword };

		return jdbcTemplate.query(sql, courseMapper, params);
	}
	
	public CourseDto selectOne(int courseNo) {
		
		String sql = "select * from course where course_no = ? ";
		
		Object[] params = {courseNo};
		List<CourseDto> list =jdbcTemplate.query(sql, courseMapper,params);
		return list.isEmpty()?null:list.get(0);
	}
	
	
}
