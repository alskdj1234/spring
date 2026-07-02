package com.kh.spring09.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring09.dto.CertDto;
import com.kh.spring09.mapper.CertMapper;

@Repository
public class CertDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private CertMapper certMapper;

	// 필요 기능 : 등록, 수정, 삭제, 상세
	public void insert(CertDto certDto) {
		String sql = "insert into cert(cert_email, cert_number) values(?, ?)";
		Object[] params = { certDto.getCertEmail(), certDto.getCertNumber() };
		jdbcTemplate.update(sql, params);
	}

	public boolean update(CertDto certDto) {
		String sql = "update cert " + "set cert_number=?, cert_time=systimestamp " + "where cert_email=?";
		Object[] params = { certDto.getCertNumber(), certDto.getCertEmail() };
		return jdbcTemplate.update(sql, params) > 0;
	}

	public CertDto selectOne(String certEmail) {
		String sql = "select * from cert where cert_email=?";
		Object[] params = { certEmail };
		List<CertDto> list = jdbcTemplate.query(sql, certMapper, params);
		return list.isEmpty() ? null : list.get(0);
	}

	public boolean delete(String certEmail) {
		String sql = "delete cert where cert_email=?";
		Object[] params = { certEmail };
		return jdbcTemplate.update(sql, params) > 0;
	}

	public boolean update(String certEmail) {
		String sql = "update cert set cert_yn = 'Y' where cert_email=?";
		Object[] params = { certEmail };
		return jdbcTemplate.update(sql, params) > 0;
	}

	// 청소 메소드 -nTime(N의 소멸시간), yTime(Y의 소멸 시간)
	public boolean clear(int nTime, int yTime) {
		String sql = "DELETE cert WHERE "
				+ "(cert_yn='N' AND systimestmap -cert_time > interval ? MINUTE>)"
				+ "and "
				+ "(cert_yn='Y' AND systimestmap - cert_time > interval ? MINUTE)";
	
		Object[] params ={
			String.valueOf(nTime),//따옴표 생기도록 변환
			String.valueOf(yTime)//따옴표 생기도록 변환
		};
		
		return jdbcTemplate.update(sql,params)>0;
	}
}
