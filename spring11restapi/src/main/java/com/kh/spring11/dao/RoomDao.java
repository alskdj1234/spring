package com.kh.spring11.dao;

import java.util.List;

import com.kh.spring11.dto.RoomDto;
import com.kh.spring11.vo.room.RoomListVO;
import com.kh.spring11.vo.room.RoomUserVO;

public interface RoomDao {
	int sequence();
	void insert(RoomDto roomDto);
	boolean delete(int roomNo);
	RoomDto selectOne(int roomNo);

	List<RoomListVO> selectList();
	List<RoomListVO> selectList(String accountId);
	
	void enter(int roomNo, String accountId);
	void leave(int roomNo, String accountId);
	List<String> getMembers(int roomNo);
	List<RoomUserVO> getMemberInfo(int roomNo);
}
