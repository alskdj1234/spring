package com.kh.spring11.websocket.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

//사용자가 보내는 데이터
@Data @JsonIgnoreProperties(ignoreUnknown = true)
public class WebSocketV3RequestVO {
	private String content;
}
