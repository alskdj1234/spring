package com.kh.spring08.dto;

import lombok.Data;

@Data
public class AttachDto {
	private int attachNo;
	private String attachName;
	private String attachType;
	private long attachSize;
}
