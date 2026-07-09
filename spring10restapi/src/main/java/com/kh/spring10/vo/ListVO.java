package com.kh.spring10.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ListVO {
	private boolean last;//마지막이냐??
	private List list;//어떤 형태든 담는...
}
