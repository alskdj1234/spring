package com.kh.spring09.dto;
import lombok.Data;
//lombok에서 제공하는 어노테이션으로 필드를 제외한 나머지 기본요소를 생성
//이 라이브러리는 이클립스를 속여야 하는 라이브러리(추가 설치 작업이 필요)
//프로젝트에만 적용하면 원하는 효과를 보기 어려움
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Setter//세터 메소드 생성
//@Getter//게터 메소드 생성
//@NoArgsConstructor//기본 생성자 생성
//@ToString//투스트링 생성 ()로 옵션도 가능

@Data//위에 4개 다 합침
public class CountryDto {
	
private int countryNo;
private String countryRegion;
private String countryName;
private String countryCapital;
private long countryPopulation;



}
