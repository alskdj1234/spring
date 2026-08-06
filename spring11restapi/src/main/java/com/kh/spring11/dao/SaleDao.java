package com.kh.spring11.dao;

import java.util.List;

import com.kh.spring11.dto.SaleDto;
import com.kh.spring11.vo.sale.SaleListItemVO;
import com.kh.spring11.vo.sale.SaleListRequestVO;

public interface SaleDao {
	int sequence();
	void insert(SaleDto saleDto);
	
	SaleDto selectOne(int saleNo);
	
	void connect(int saleNo, int attachNo);
	void connectDetailImage(int saleNo, int attachNo);
	
	List<SaleListItemVO> selecList(SaleListRequestVO request);
	Integer findAttach(int saleNo);
	
	List<Integer> findDetails(int saleNo);
	
	boolean delete(int saleNo);
	
	boolean update(SaleDto saleDto);
	
	List<SaleListItemVO> findOrders(List<Integer> saleNumbers);
	SaleListItemVO findOrder(int saleNo);
}




