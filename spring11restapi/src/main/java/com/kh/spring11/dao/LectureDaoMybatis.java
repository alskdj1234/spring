package com.kh.spring11.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring11.dto.LectureDto;

@Repository
public class LectureDaoMybatis implements LectureDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int sequence() {
		return sqlSession.selectOne("mapper.lecture.sequence");
	}
	@Override
	public void insert(LectureDto lectureDto) {
		sqlSession.insert("mapper.lecture.add", lectureDto);
	}

	@Override
	public boolean update(LectureDto lectureDto) {
		return sqlSession.update("mapper.lecture.update", lectureDto) > 0;
	}
	@Override
	public boolean updateUnit(LectureDto lectureDto) {
		return sqlSession.update("mapper.lecture.updateUnit", lectureDto) > 0;
	}

	@Override
	public boolean delete(int lectureNo) {
		return sqlSession.delete("mapper.lecture.delete", lectureNo) > 0;
	}

	@Override
	public LectureDto selectOne(int lectureNo) {
		Map<String, Object> params = new HashMap<>();
		params.put("lectureNo", lectureNo);
		return sqlSession.selectOne("mapper.lecture.find", params);
	}
	@Override
	public List<LectureDto> selectList(Integer lastLectureNo, int size) {
		Map<String, Object> params = new HashMap<>();
		params.put("lastLectureNo", lastLectureNo);
		params.put("size", size);
		return sqlSession.selectList("mapper.lecture.list", params);
	}

	@Override
	public int count(Integer lastLectureNo) {
		Map<String, Object> params = new HashMap<>();
		params.put("lastLectureNo", lastLectureNo);
		return sqlSession.selectOne("mapper.lecture.count", params);
	}
	
	
}
