package com.kh.spring11.dao;

import com.kh.spring11.dto.CertDto;

public interface CertDao {
	void add(CertDto certDto);
	boolean change(CertDto certDto);
	CertDto find(String certEmail);
	boolean delete(String certEmail);
	boolean use(String certEmail);
}
