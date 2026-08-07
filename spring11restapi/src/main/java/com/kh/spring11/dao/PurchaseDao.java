package com.kh.spring11.dao;

import java.util.List;

import com.kh.spring11.dto.PurchaseDetailDto;
import com.kh.spring11.dto.PurchaseDto;

public interface PurchaseDao {
	int purchaseSequence();
	void purchaseInsert(PurchaseDto purchaseDto);
	
	int purchaseDetailSequence();
	void purchaseDetailInsert(PurchaseDetailDto purchaseDetailDto);
	
	List<PurchaseDetailDto> selectDetails(int purchaseDetailOrigin);
	
	boolean purchaseCancel(int purchaseNo);
	boolean purchaseDetailCancel(int purchaseDetailOrigin);
	
	PurchaseDto selectOne(int purchaseNo);
	PurchaseDetailDto selectDetailOne(int purchaseDetailNo);
	
	boolean purchaseCancel(int purchaseNo, int amount);
	boolean purchaseDetailCancelUnit(int purchaseDetailNo);
}
