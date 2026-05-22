package com.kh.spring09.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//FE에서 차트데이터를 요청할 때 전달할 객체
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ChartDataVO {
	@Builder.Default
	private String type = "bar";//차트유형
	private List<String> titles;//차트x축제목
	private List<Double> values;//차트데이터
}
