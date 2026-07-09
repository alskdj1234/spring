package com.kh.spring11.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ListVO {
	private List list;//데이터 목록
	private boolean last;//마지막인지
}
