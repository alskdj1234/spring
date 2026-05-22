package com.kh.spring09.dto;

import lombok.Data;

@Data
public class LectureDto {
	private int lectureNo;
	private String lectureTitle;
	private String lectureCategory;
	private int lectureDuration;
	private int lecturePrice;
	private String lectureType;
}
