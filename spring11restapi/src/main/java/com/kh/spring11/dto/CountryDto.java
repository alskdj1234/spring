package com.kh.spring11.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//lombok에서 제공하는 애노테이션으로 필드를 제외한 나머지 기본요소를 생성
//- 이 라이브러리는 이클립스를 속여야 하는 라이브러리 (+추가 설치 작업이 필요)
//- 프로젝트에만 적용하면 원하는 효과를 보기 어렵다
//- @Setter : 세터메소드 생성
//- @Getter : 게터메소드 생성
//- @NoArgsConstructor : 기본생성자 생성
//- @ToString : toString() 메소드 생성
//- @Data : 세터 + 게터 + toString()
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CountryDto {
	private int countryNo;
	private String countryRegion;
	private String countryName;
	private String countryCapital;
	private long countryPopulation;
}




