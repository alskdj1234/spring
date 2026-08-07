package com.kh.spring11.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring11.dto.SaleDto;
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
		return sqlSession.selectOne("mapper.sale.find", saleNo);
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
	public List<SaleListItemVO> selecList(SaleListRequestVO request) {
		return sqlSession.selectList("mapper.sale.list", request);
	}
	@Override
	public Integer findAttach(int saleNo) {
		return sqlSession.selectOne("mapper.sale.findAttach", saleNo);
	}
	@Override
	public List<Integer> findDetails(int saleNo) {
		return sqlSession.selectList("mapper.sale.findDetails", saleNo);
	}
	@Override
	public boolean delete(int saleNo) {
		return sqlSession.delete("mapper.sale.delete", saleNo) > 0;
	}
	@Override
	public boolean update(SaleDto saleDto) {
		return sqlSession.update("mapper.sale.update", saleDto) > 0;
	}
	@Override
	public List<SaleListItemVO> findOrders(List<Integer> saleNumbers) {
		return sqlSession.selectList("mapper.sale.findOrders", saleNumbers);
	}
	@Override
	public SaleListItemVO findOrder(int saleNo) {
		return sqlSession.selectOne("mapper.sale.findOrder", saleNo);
	}
}







