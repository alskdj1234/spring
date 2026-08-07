package com.kh.spring11.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring11.dto.PurchaseDetailDto;
import com.kh.spring11.dto.PurchaseDto;

@Repository
public class PurchaseDaoMybatis implements PurchaseDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int purchaseSequence() {
		return sqlSession.selectOne("mapper.purchase.purchaseSequence");
	}
	@Override
	public void purchaseInsert(PurchaseDto purchaseDto) {
		sqlSession.insert("mapper.purchase.purchaseInsert", purchaseDto);
	}
	
	@Override
	public int purchaseDetailSequence() {
		return sqlSession.selectOne("mapper.purchase.purchaseDetailSequence");
	}
	@Override
	public void purchaseDetailInsert(PurchaseDetailDto purchaseDetailDto) {
		sqlSession.insert("mapper.purchase.purchaseDetailInsert", purchaseDetailDto);
	}
	@Override
	public PurchaseDto selectOne(int purchaseNo) {
		return sqlSession.selectOne("mapper.purchase.purchaseFind", purchaseNo);
	}
	@Override
	public List<PurchaseDetailDto> selectDetails(int purchaseDetailOrigin) {
		return sqlSession.selectList(
			"mapper.purchase.purchaseDetailFind", purchaseDetailOrigin
		);
	}
	@Override
	public boolean purchaseCancel(int purchaseNo) {
		return sqlSession.update("mapper.purchase.purchaseCancel", purchaseNo) > 0;
	}
	@Override
	public boolean purchaseDetailCancel(int purchaseDetailOrigin) {
		return sqlSession.update(
				"mapper.purchase.purchaseDetailCancel", 
				purchaseDetailOrigin
		) > 0;
	}
	@Override
	public PurchaseDetailDto selectDetailOne(int purchaseDetailNo) {
		return sqlSession.selectOne("mapper.purchase.selectDetailOne", purchaseDetailNo);
	}
	@Override
	public boolean purchaseCancel(int purchaseNo, int amount) {
		Map<String, Object> params = new HashMap<>();
		params.put("purchaseNo", purchaseNo);
		params.put("amount", amount);
		return sqlSession.update("mapper.purchase.purchaseCancel", params) > 0;
	}
	@Override
	public boolean purchaseDetailCancelUnit(int purchaseDetailNo) {
		return sqlSession.update("mapper.purchase.purchaseDetailCancelUnit", purchaseDetailNo) > 0;
	}
}








