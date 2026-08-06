package com.kh.spring11.dao;

import java.util.List;

import com.kh.spring11.dto.PurchaseDetailDto;
import com.kh.spring11.dto.PurchaseDto;

public interface PurchaseDao {
	int purchaseSequence();
	void purchaseInsert(PurchaseDto purchaseDto);
	int purchaseDetailSequence();
	void purchaseDetailInsert(PurchaseDetailDto purchaseDetailDto);
	PurchaseDto selectOne(int purchaseNo);
	List<PurchaseDetailDto> selectDetails(int purchaseDetailOrigin);
}
