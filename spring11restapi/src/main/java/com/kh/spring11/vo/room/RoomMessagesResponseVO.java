package com.kh.spring11.vo.room;

import java.util.List;

import com.kh.spring11.vo.message.MessageVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name ="채팅방 메시지 데이터")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class RoomMessagesResponseVO {
	private List<MessageVO> messages;
}
