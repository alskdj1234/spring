package com.kh.spring11.vo.room;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class RoomUserVO {
	private String accountId;
	private String accountNickname;
	private String accountLevel;
}
