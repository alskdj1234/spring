package com.kh.spring11.dao;

import java.util.List;

import com.kh.spring11.vo.message.MessageVO;
import com.kh.spring11.vo.room.RoomChatMessageVO;
import com.kh.spring11.vo.room.RoomSystemMessageVO;

public interface MessageDao {
	int sequence();
	void insertChat(RoomChatMessageVO message);
	void insertSystem(RoomSystemMessageVO message);
	List<MessageVO> selectList(int messageRoom);
}
