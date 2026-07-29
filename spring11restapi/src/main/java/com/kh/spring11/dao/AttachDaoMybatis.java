package com.kh.spring11.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring11.dto.AttachDto;

@Repository
public class AttachDaoMybatis implements AttachDao {
 @Autowired
 private SqlSession sqlSession;

 @Override
 public int sequence() {
	return sqlSession.selectOne("mapper.attach.sequence");
 }

 @Override
 public void insert(AttachDto attachDto) {
	sqlSession.insert("mapper.attach.add", attachDto);
	
 }

 @Override
 public AttachDto selectOne(int attachNo) {
	return sqlSession.selectOne("mapper.attach.find", attachNo);
 }

 @Override
 public boolean delete(int attachNo) {
	// TODO Auto-generated method stub
	return sqlSession.delete("mapper.attach.delete", attachNo) >0;
 }


}
