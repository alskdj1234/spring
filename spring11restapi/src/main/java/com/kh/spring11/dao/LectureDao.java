package com.kh.spring11.dao;

import java.util.List;

import com.kh.spring11.dto.LectureDto;

public interface LectureDao {
	//등록 메소드
	int sequence();
	void insert(LectureDto lectureDto);
	
	//수정
	boolean update(LectureDto lectureDto);
	boolean updateUnit(LectureDto lectureDto);
	
	//삭제
	boolean delete(int lectureNo);
	//상세
	LectureDto selectOne(int lectureNo);
	
	List<LectureDto> selectList(Integer lastLectureNo, int size);
	int count(Integer lastLectureNo);
}






