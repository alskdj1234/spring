package com.kh.spring11.vo.room;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "특정 사용자 시점에서 조회한 방 정보")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class RoomListVO {
	private int roomNo;
	private String roomName;
	private String roomOwner;
	private Integer roomLimit;//null 가능(제한 없음)
	private Timestamp roomCtime;
	private int cnt;
	private String enter;
}
