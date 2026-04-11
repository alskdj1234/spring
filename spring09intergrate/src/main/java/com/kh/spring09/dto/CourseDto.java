package com.kh.spring09.dto;

import lombok.Data;

@Data
public class CourseDto {
	private int courseNo;
	private String courseName;
	private String category;
	private int lectureTime;
	private long fee;
	private String classType;
	
	
}
