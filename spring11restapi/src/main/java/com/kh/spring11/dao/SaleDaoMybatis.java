package com.kh.spring11.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring11.dto.AttachDto;
import com.kh.spring11.dto.SaleDto;
import com.kh.spring11.vo.sale.SaleDetailRequestVO;
import com.kh.spring11.vo.sale.SaleListItemVO;
import com.kh.spring11.vo.sale.SaleListRequestVO;

@Repository
public class SaleDaoMybatis implements SaleDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int sequence() {
		return sqlSession.selectOne("mapper.sale.sequence");
	}
	@Override
	public void insert(SaleDto saleDto) {
		sqlSession.insert("mapper.sale.add", saleDto);
	}
	@Override
	public SaleDto selectOne(int saleNo) {
	
		return sqlSession.selectOne("mapper.sale.find",saleNo);
	}
	@Override
	public void connect(int saleNo, int attachNo) {
		Map<String, Object> params = new HashMap<>();
		params.put("saleNo", saleNo);
		params.put("attachNo", attachNo);
		sqlSession.insert("mapper.sale.connect", params);
	}
	@Override
	public void connectDetailImage(int saleNo, int attachNo) {
		Map<String, Object> params = new HashMap<>();
		params.put("saleNo", saleNo);
		params.put("attachNo", attachNo);
		sqlSession.insert("mapper.sale.connectDetailImages", params);
	}
	@Override
	public List<SaleListItemVO> selectList(SaleListRequestVO request) {
		return sqlSession.selectList("mapper.sale.list", request);
	}
	@Override
	public AttachDto selectThumbnail(int saleNo) {
		return sqlSession.selectOne("mapper.sale.selectThumbnail",saleNo);
	}
	@Override
	public List<AttachDto> selectDetails(int saleNo) {
		return sqlSession.selectList("mapper.sale.selectDetails", saleNo);
	}
	

}