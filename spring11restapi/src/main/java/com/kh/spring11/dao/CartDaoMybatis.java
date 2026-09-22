package com.kh.spring11.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring11.dto.CartDto;
import com.kh.spring11.vo.purchase.CartItemVO;

@Repository
public class CartDaoMybatis implements CartDao {
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertOrUpdate(CartDto cartDto) {
		CartDto findDto = sqlSession.selectOne("mapper.cart.find", cartDto);
		if(findDto == null) {//처음
			sqlSession.insert("mapper.cart.add", cartDto);
		}
		else {//두번째 이상
			findDto.setCartQty(findDto.getCartQty() + cartDto.getCartQty());
			sqlSession.update("mapper.cart.change", findDto);
		}
	}
	
	@Override
	public CartDto selectOne(CartDto cartDto) {
		return sqlSession.selectOne("mapper.cart.find", cartDto);
	}
	
	@Override
	public List<CartItemVO> selectList(String cartOwner) {
		return sqlSession.selectList("mapper.cart.list", cartOwner);
	}
	
	@Override
	public boolean update(CartDto cartDto) {
		return sqlSession.update("mapper.cart.change", cartDto) > 0;
	}
	
	@Override
	public boolean delete(String cartOwner, List<Integer> numbers) {
		Map<String, Object> params = new HashMap<>();
		params.put("cartOwner", cartOwner);
		params.put("numbers", numbers);
		return sqlSession.delete("mapper.cart.deleteItems", params) > 0;
	}
	@Override
	public boolean delete(String cartOwner, int cartItem) {
		Map<String, Object> params = new HashMap<>();
		params.put("cartOwner", cartOwner);
		params.put("cartItem", cartItem);
		return sqlSession.delete("mapper.cart.deleteItem", params) > 0;
	}
}










