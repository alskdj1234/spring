package com.kh.spring09.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring09.dto.AttachDto;
import com.kh.spring09.mapper.AttachMapper;

@Repository
public class AttachDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private AttachMapper attachMapper;

	public int sequence() {
		String sql = "select attach_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, int.class);
	}

	public void insert(AttachDto attachDto) {
		String sql ="insert into attach(attach_no, attach_name, attach_type, attach_type)"
				+ " values(?,?,?,?)";
	
		Object[] params = {
				attachDto.getAttachNo(), attachDto.getAttachName(),
				attachDto.getAttachType(), attachDto.getAttachSize()
				
		};
		jdbcTemplate.update(sql,params);
	}
	//상세
	public AttachDto selectOne(int attachNo) {
		String sql = "select * from attach where attach_no = ?";
		Object [] params = {attachNo};
		List<AttachDto> list = jdbcTemplate.query(sql, attachMapper, params);
		return list.isEmpty() ? null : list.get(0);

	}
}
