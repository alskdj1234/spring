package com.kh.spring11.vo;

import lombok.Data;

@Data
public class ListRequestVO {
	private Integer lastNo;
	private int size = 10;
}
