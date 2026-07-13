package com.kh.spring11.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CountryComplexRequestVO {
	private List<String> countryRegions;
	private String countryName;
	private String countryCapital;
	private Long minCountryPopulation;
	private Long maxCountryPopulation;
	private List<String> orders;
	private Integer lastCountryNo;
	private Integer size;
}






