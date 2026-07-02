package com.kh.spring09.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.spring09.dto.CertDto;

@Component
public class CertMapper implements RowMapper<CertDto>{
	@Override
	public CertDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		return CertDto.builder()
					.certEmail(rs.getString("cert_email"))
					.certNumber(rs.getString("cert_number"))
					.certTime(rs.getTimestamp("cert_time"))
					.certYn(rs.getString("cert_yn"))
					.build();
	}
}



