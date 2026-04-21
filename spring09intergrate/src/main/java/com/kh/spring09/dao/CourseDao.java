package com.kh.spring09.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring09.dto.CourseDto;
import com.kh.spring09.mapper.CourseMapper;
import com.kh.spring09.vo.PageVO;

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
		Object[] params = { courseNo };
		return jdbcTemplate.update(sql, params) > 0;

	}

	public List<CourseDto> selectList(int page, int size) {
		String sql = "select * from ("
				+ "select rownum rn, TMP.* from ("
					+ "select * from course order by course_no desc"
				+ ") TMP"
			+ ") where rn between ? and ?";
int beginRow = page * size - (size-1);
int endRow = page * size;
Object[] params = { beginRow , endRow };		
return jdbcTemplate.query(sql, courseMapper, params);
	}

	public List<CourseDto> selectList(PageVO pageVO) {
		if (pageVO.isList())
			return selectList(pageVO.getPage(),pageVO.getSize());

		Set<String> allowList = Set.of("course_name", "category", "class_type");
		if (allowList.contains(pageVO.getColumn()) == false)
			return List.of();

		String sql = "select * from (" + "select rownum rn, TMP.* from (" + "select * from course " + "where instr("
				+ pageVO.getColumn() + ", ?) > 0 " + "order by course_no desc" + ") TMP" + ") where rn between ? and ?";
		Object[] params = { pageVO.getKeyword(), pageVO.getBeginRownum(), pageVO.getEndRownum() };

		return jdbcTemplate.query(sql, courseMapper, params);
	}

	public CourseDto selectOne(int courseNo) {

		String sql = "select * from course where course_no = ? ";

		Object[] params = { courseNo };
		List<CourseDto> list = jdbcTemplate.query(sql, courseMapper, params);
		return list.isEmpty() ? null : list.get(0);
	}

	
	public int count() {
		String sql = "select count(*) from course";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	public int count(PageVO pageVO) {
		if(pageVO.isList()) return count();
		
		Set<String> allowList = Set.of("course_name", "category", "class_type");
		if (allowList.contains(pageVO.getColumn()) == false)
			return 0;//결과 없음
		
		String sql = "select count(*) from course "
					+ "where instr("+pageVO.getColumn()+", ?) > 0";
		Object[] params = { pageVO.getKeyword() };
		return jdbcTemplate.queryForObject(sql, int.class, params);
	}
}
