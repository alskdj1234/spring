package com.kh.spring09.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring09.dto.LectureDto;
import com.kh.spring09.mapper.LectureMapper;
import com.kh.spring09.vo.PageVO;

@Repository//파일 또는 DBMS 제어도구
public class LectureDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private LectureMapper lectureMapper;
	
	//등록 메소드
	public int sequence() {
		String sql = "select lecture_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	public void insert(LectureDto lectureDto) {
		String sql = "insert into lecture("
						+ "lecture_no, lecture_title, lecture_category,"
						+ "lecture_duration, lecture_price, lecture_type"
					+ ") values(?, ?, ?, ?, ?, ?)";
		Object[] params = {
			lectureDto.getLectureNo(),
			lectureDto.getLectureTitle(), lectureDto.getLectureCategory(),
			lectureDto.getLectureDuration(), lectureDto.getLecturePrice(),
			lectureDto.getLectureType()
		};
		jdbcTemplate.update(sql, params);
	}
	
	//수정
	public boolean update(LectureDto lectureDto) {
		String sql = "update lecture "
						+ "set lecture_title=?, lecture_category=?, "
						+ "lecture_duration=?, lecture_price=?, lecture_type=? "
						+ "where lecture_no=?";
		Object[] params = {
			lectureDto.getLectureTitle(), lectureDto.getLectureCategory(),
			lectureDto.getLectureDuration(), lectureDto.getLecturePrice(),
			lectureDto.getLectureType(), lectureDto.getLectureNo()
		};
		//return jdbcTemplate.update(sql, params) > 0;
		int rows = jdbcTemplate.update(sql, params);
		return rows > 0;
	}
	//삭제
	public boolean delete(int lectureNo) {
		String sql = "delete lecture where lecture_no = ?";
		Object[] params = { lectureNo };
		return jdbcTemplate.update(sql, params) > 0; 
	}
	//목록
	public List<LectureDto> selectList(int beginRownum, int endRownum) {
		String sql = "select * from ("
						+ "select rownum rn, TMP.* from ("
							+ "select * from lecture order by lecture_no asc"
						+ ")TMP"
					+ ") where rn between ? and ?";
		Object[] params = { beginRownum, endRownum };
		return jdbcTemplate.query(sql, lectureMapper, params);
	}
	//검색 (내부적으로 목록을 사용)
	public List<LectureDto> selectList(PageVO pageVO) {
		if(pageVO.isList()) //목록의 결과를 반환해~ (또는 return List.of())
			return selectList(pageVO.getBeginRownum(), pageVO.getEndRownum());
		
		Set<String> allowList = Set.of("lecture_category", "lecture_title", "lecture_type");
		if(!allowList.contains(pageVO.getColumn())) 
			return selectList(pageVO.getBeginRownum(), pageVO.getEndRownum());
		
		String sql = "select * from ("
						+ "select rownum rn, TMP.* from ("
							+ "select * from lecture "
							+ "where instr("+pageVO.getColumn()+", ?) > 0 "
							+ "order by lecture_no asc"
						+ ")TMP"
					+ ") where rn between ? and ?";
		Object[] params = { 
			pageVO.getKeyword(), pageVO.getBeginRownum(), pageVO.getEndRownum() 
		};
		return jdbcTemplate.query(sql, lectureMapper, params);
	}
	
	//상세
	public LectureDto selectOne(int lectureNo) {
		String sql = "select * from lecture where lecture_no = ?";
		Object[] params = { lectureNo };
		List<LectureDto> list = jdbcTemplate.query(sql, lectureMapper, params);
		return list.isEmpty() ? null : list.get(0);
	}
	
	public int count() {
		String sql = "select count(*) from lecture";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	public int count(PageVO pageVO) {
		if(pageVO.isList()) return count();
		
		String sql = "select count(*) from lecture "
					+ "where instr("+pageVO.getColumn()+", ?) > 0";
		Object[] params = { pageVO.getKeyword() };
		return jdbcTemplate.queryForObject(sql, int.class, params);
	}
	
	//연결
	public void connect(int lectureNo, int attachNo) {
		String sql = "insert into lecture_image(lecture_no, attach_no) values(?, ?)";
		Object[] params = { lectureNo, attachNo };
		jdbcTemplate.update(sql, params);
	}
	
	//미리보기 이미지 찾기
	public List<Integer> searchImage(int lectureNo) {
		String sql = "select attach_no from lecture_image where lecture_no = ?";
		Object[] params = { lectureNo };
		return jdbcTemplate.queryForList(sql, int.class, params);
	}
}






