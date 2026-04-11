package com.kh.spring06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.kh.spring06.dao.CourseDao;

import com.kh.spring06.dto.CourseDto;

@RestController
@RequestMapping("/course") // 공용주소
public class CourseController {
	@Autowired
	CourseDao courseDao;

	@RequestMapping("/insert") // 개별주소 (공용주소와 합쳐짐)
	public String courseInsert(@ModelAttribute CourseDto courseDto) {
		courseDao.insert(courseDto);
		return "등록 완";
	}

	@RequestMapping("/update")
	public String courseUpdate(@ModelAttribute CourseDto courseDto) {
		boolean success = courseDao.update(courseDto);
		if (success) {
			return "수정 완";
		} else {
			return "존재 x";
		}
	}

	@RequestMapping("/delete")
	public String courseDelete(@RequestParam int courseNo) {
		boolean success = courseDao.delete(courseNo);
		if (success) {
			return "삭제 완";
		} else {
			return "존재 x";
		}
	}

	@RequestMapping("/list")
	public String list(@RequestParam(required = false) String column, @RequestParam(required = false) String keyword) {

		// 검색어가 있든 없든 상관없이 검색을 수행(내부적으로 구분)
		List<CourseDto> list = courseDao.selectList(column, keyword);
		// StringBuffer를 이용해서 문자열로 만들어서 반환
		// 화면으로 바뀌는 부분
		StringBuffer buffer = new StringBuffer();
		buffer.append("결과 수 : " + list.size());
		buffer.append("<br>");
		for (CourseDto courseDto : list) {
			buffer.append(courseDto);
			buffer.append("<br>");
		}
		return buffer.toString();

	}
	
	@RequestMapping("/detail")
	public String detail(@RequestParam int courseNo) {
		CourseDto courseDto =courseDao.selectOne(courseNo);
		
		if(courseDto==null) {
			return "존재 x";
		}
		else {
			StringBuffer buffer = new StringBuffer();
			buffer.append(courseDto.getCourseNo()+"<br>");
			buffer.append("이름: " + courseDto.getCourseName()+"<br>");
			buffer.append("카테고리: "+courseDto.getCategory()+"<br>");
			buffer.append("수강료: "+courseDto.getFee()+"<br>");
			buffer.append("시간: "+courseDto.getLectureTime()+"<br>");
			buffer.append("수업 타입: "+courseDto.getClassType()+"<br>");
		
			return buffer.toString();
		
		}
	
	}

}
