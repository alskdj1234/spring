package com.kh.spring11.dao;

import java.util.List;

import com.kh.spring11.dto.AttachDto;

public interface AttachDao {
	int sequence();
	void insert(AttachDto attachDto);
	
	AttachDto selectOne(int attachNo);
	AttachDto selectOne(Integer attachNo);
	
	boolean delete(int attachNo);
	List<AttachDto> selectList(List<Integer> attachNumbers);
}



