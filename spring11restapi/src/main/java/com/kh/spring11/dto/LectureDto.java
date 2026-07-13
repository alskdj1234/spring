package com.kh.spring11.dto;

import lombok.Data;

@Data
public class LectureDto {
	private int lectureNo;
	private String lectureTitle;
	private String lectureCategory;
	private Integer lectureDuration;
	private Integer lecturePrice;
	private String lectureType;
}
