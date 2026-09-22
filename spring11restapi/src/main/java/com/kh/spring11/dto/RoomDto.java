package com.kh.spring11.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class RoomDto {
	private int roomNo;
	private String roomName;
	private String roomOwner;
	private Integer roomLimit;//null 가능(제한 없음)
	private Timestamp roomCtime;
}
