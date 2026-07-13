package com.kh.spring11.mybatis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.dao.LectureDao;
import com.kh.spring11.dto.LectureDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test06강좌더보기조회 {
	@Autowired
	private LectureDao lectureDao;
	
	@Test
	public void test() {
		Integer lastLectureNo = null;
		int size = 10;
		List<LectureDto> list = lectureDao.selectList(lastLectureNo, size);
		//log.debug("list size = {}", list.size());
		
		//Assertions.assertEquals(10, list.size());
		//assertEquals(10, list.size());//list.size()가 10이어야 통과
		
		//Assertions.assertTrue(size >= list.size());
		assertTrue(size >= list.size());
	}
}






