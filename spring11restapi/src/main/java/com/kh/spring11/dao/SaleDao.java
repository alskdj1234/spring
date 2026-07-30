package com.kh.spring11.dao;

import java.util.List;

import com.kh.spring11.dto.AttachDto;
import com.kh.spring11.dto.SaleDto;
import com.kh.spring11.vo.sale.SaleDetailRequestVO;
import com.kh.spring11.vo.sale.SaleListItemVO;
import com.kh.spring11.vo.sale.SaleListRequestVO;

public interface SaleDao {
	int sequence();
	void insert(SaleDto saleDto);
	SaleDto selectOne(int saleNo);
	void connect(int saleNo, int attachNo);
	void connectDetailImage(int saleNo, int attachNo);
	List<SaleListItemVO> selectList(SaleListRequestVO request);
	AttachDto selectThumbnail(int saleNo);
	List<AttachDto> selectDetails(int saleNo);
}