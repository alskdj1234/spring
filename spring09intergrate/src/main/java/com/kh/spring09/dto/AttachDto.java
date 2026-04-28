package com.kh.spring09.dto;

import lombok.Data;

@Data
public class AttachDto {
	private int attachNo;
	private String attachName;
	private String attachType;
	private long attachSize;
	
<<<<<<< HEAD
	//파일 유형(MIME TYPE)을 알려주기 위한 메소드
	//- 만약 유형을 알 수 없으면 null 대신 application/octet-stream 을 반환한다
	public String getAttachTypeString() {
		if(attachType == null) { 
			return "application/octet-stream";
		}
=======
	//파일 유형 알려줄 메소드
	// 유형 알 수 없을때에는 null 대신 application/octet-stream을 반환
	
	public String getAttachTypeString() {
		if(attachType == null) return "application/octet-stream";
>>>>>>> branch 'main' of https://github.com/alskdj1234/spring.git
		return attachType;
	}
}

