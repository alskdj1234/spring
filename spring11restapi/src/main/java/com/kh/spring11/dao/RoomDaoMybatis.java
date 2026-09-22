package com.kh.spring11.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring11.dto.RoomDto;
import com.kh.spring11.vo.room.RoomListVO;
import com.kh.spring11.vo.room.RoomUserVO;

@Repository
public class RoomDaoMybatis implements RoomDao {
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int sequence() {
		return sqlSession.selectOne("mapper.room.sequence");
	}
	@Override
	public void insert(RoomDto roomDto) {
		sqlSession.insert("mapper.room.create", roomDto);
	}
	@Override
	public boolean delete(int roomNo) {
		return sqlSession.delete("mapper.room.delete", roomNo) > 0;
	}
	@Override
	public RoomDto selectOne(int roomNo) {
		return sqlSession.selectOne("mapper.room.find", roomNo);
	}
	@Override
	public List<RoomListVO> selectList() {
		return sqlSession.selectList("mapper.room.list");
	}
	@Override
	public List<RoomListVO> selectList(String accountId) {
		return sqlSession.selectList("mapper.room.listUpgrade", accountId);
	}
	
	@Override
	public void enter(int roomNo, String accountId) {
		Map<String, Object> params = new HashMap<>();
		params.put("roomNo", roomNo);
		params.put("accountId", accountId);
		sqlSession.insert("mapper.room.enter", params);
	}
	@Override
	public void leave(int roomNo, String accountId) {
		Map<String, Object> params = new HashMap<>();
		params.put("roomNo", roomNo);
		params.put("accountId", accountId);
		sqlSession.delete("mapper.room.leave", params);
	}
	@Override
	public List<String> getMembers(int roomNo) {
		return sqlSession.selectList("mapper.room.member", roomNo);
	}
	@Override
	public List<RoomUserVO> getMemberInfo(int roomNo) {
		return sqlSession.selectList("mapper.room.memberInfo", roomNo);
	}
}
