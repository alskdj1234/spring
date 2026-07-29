package com.kh.spring11.dao;

import com.kh.spring11.dto.SaleDto;

public interface SaleDao {
	int sequence();
	void insert(SaleDto saleDto);
	SaleDto selectOne(int saleNo);
	void connect(int saleNo, int attachNo);
	void connectDetailImage(int saleNo, int attachNo);
}