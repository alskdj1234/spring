package com.kh.spring11.vo.message;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data @EqualsAndHashCode(callSuper = true)
public class ChatMessageVO extends MessageVO {
	private int no;
	private String senderId;
	private String senderNickname;
	private String senderLevel;
}
