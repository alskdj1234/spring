package com.kh.spring11.websocket.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//사용자에게 보내줄 데이터
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class WebSocketV3ChatVO {
	@Builder.Default
	private String type = "chat";
	private String senderId, senderLevel, senderNickname;
	private String content;
	private LocalDateTime time;
}



