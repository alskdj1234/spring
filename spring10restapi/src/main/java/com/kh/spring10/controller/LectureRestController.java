package com.kh.spring10.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring10.dao.LectureDao;
import com.kh.spring10.dto.LectureDto;
//기존 key 1=value 1 & key 2= value 2 & ....(form-data)
//get/post 위치만 다르고 데이터 형식은 같음
//모델 어트리뷰트 리퀘스트 파람

//리액트 등록(레스트 컨트롤러)
// 데이터가 ajax방식으로 전송(application/json)
// {"key1" : "value1" , "key2" : "value2", ...}
// get일 때 불가능하고 post처럼 body가 존재하는 방식에서만 가능
@CrossOrigin
@RestController
@RequestMapping("/api/lecture")
public class LectureRestController {
	private LectureDao lectureDao;

	@PostMapping("/insert")
	public void insert(@RequestBody LectureDto lectureDto) {
		int lectureNo = lectureDao.sequence();
		lectureDto.setLectureNo(lectureNo);
		lectureDao.insert(lectureDto);
	}
	
	@GetMapping("/list")
	public List<LectureDto> list(){
		return lectureDao.selectList(1, 10000);
	}
}
