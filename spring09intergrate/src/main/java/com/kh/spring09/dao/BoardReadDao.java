package com.kh.spring09.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//게시글 조회 이력만 관리하는 디에이오
@Repository
public class BoardReadDao {
 @Autowired
  private JdbcTemplate jdbcTemplate;

 //등록
 public void insert(String memberId, long boardNo) {
	 String sql = "insert into board_read(member_id, board_no) values(?, ?)";
	 Object [] params = {memberId,boardNo};
	 jdbcTemplate.update(sql, params);
 }
 
 //카운트
 public int count(String memberId, long boardNo) {
	 String sql = "select count(*) from board_read where member_id=? and board_no=?";
	 Object [] params = {memberId,boardNo};
	 return jdbcTemplate.queryForObject(sql,int.class,params);
 }
}
