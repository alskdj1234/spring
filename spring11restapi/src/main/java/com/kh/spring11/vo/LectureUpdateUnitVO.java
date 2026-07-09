package com.kh.spring11.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "강좌 부분 수정 정보")
public class LectureUpdateUnitVO {
	@Schema(description = "강좌 제목", example = "정보처리기사 필기")
	private String lectureTitle;
	@Schema(description = "강좌 분류", examples = {"이론", "실습", "시험"})
	private String lectureCategory;
	@Schema(description = "강의 시간", examples = {"30", "60", "90"})
	private Integer lectureDuration;
	@Schema(description = "수강료", examples = {"100000", "200000", "300000"})
	private Integer lecturePrice;
	@Schema(description = "강의 방법", examples = {"온라인", "오프라인", "혼합"})
	private String lectureType;
}





