package com.kh.spring11.dao;

import java.util.List;

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
		sqlSession.insert("mapper.purchase.purchaseInsert",purchaseDto);
		
	}

	@Override
	public int purchaseDetailSequence() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.purchase.purchaseDetailSequence");
	}

	@Override
	public void purchaseDetailInsert(PurchaseDetailDto purchaseDetailDto) {
		sqlSession.insert("mapper.purchase.purchaeDetailInsert",purchaseDetailDto);
		
	}

	@Override
	public PurchaseDto selectOne(int purchaseNo) {
		return sqlSession.selectOne("mapper.purchase.purchaseFind",purchaseNo);
	}

	@Override
	public List<PurchaseDetailDto> selectDetails(int purchaseDetailOrigin) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.purchase.purchaseDetailFind",purchaseDetailOrigin);
	}

	@Override
	public boolean purchaseCancel(int purchaseNo) {
		// TODO Auto-generated method stub
		return sqlSession.update("mapper.purchase.purchaseCancel",purchaseNo) > 0;
	}

}
