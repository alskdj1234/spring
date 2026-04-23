package com.kh.spring09.dto;

import lombok.Data;

@Data
public class AttachDto {
	private int attachNo;
	private String attachName;
	private String attachType;
	private long attachSize;
	
	//파일 유형 알려줄 메소드
	// 유형 알 수 없을때에는 null 대신 application/octet-stream을 반환
	
	public String getAttachTypeString() {
		if(attachType == null) return "application/octet-stream";
		return attachType;
	}
}
