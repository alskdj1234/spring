package com.kh.spring11.dao;

import com.kh.spring11.dto.AttachDto;

public interface AttachDao {
int sequence();
  void insert(AttachDto attachDto);
  AttachDto selectOne(int attachNo);
  boolean delete (int attachNo);
 
}
