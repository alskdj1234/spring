package com.kh.spring09.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberHistoryDto {
	private int memberHistoryNo;
	private Timestamp memberHistoryTime;
	private String memberHistoryOrigin;
	private String memberHistoryAddress;
	private String memberHistoryAgent;
}
