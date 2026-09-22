package com.kh.spring11.websocket.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//사용자에게 보내줄 데이터
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class WebSocketV3DmVO {
	@Builder.Default
	private String type = "dm";
	private String senderId, senderLevel, senderNickname;
	private String receiverId, receiverLevel, receiverNickname;
	private String content;
	private LocalDateTime time;
}



