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
		CartDto find = sqlSession.selectOne("mapper.cart.find",cartDto);
		if(find == null) {
			sqlSession.insert("mapper.cart.add",cartDto);
		}
		else {
			find.setCartQty(find.getCartQty()+cartDto.getCartQty());
			sqlSession.update("mapper.cart.change", find);
		}
		
	}

	@Override
	public boolean delete(CartDto cartDto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CartDto selectOne(CartDto cartDto) {
		return sqlSession.selectOne("mapper.cart.find",cartDto);
	}

	@Override
	public List<CartItemVO> selectList(String cartOwner) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("mapper.cart.list", cartOwner);
	}
	@Override
	public boolean update(CartDto find) {
		return sqlSession.update("mapper.cart.change",find)>0;
	}

	@Override
	public boolean delete(String cartOwner, List<Integer> numbers) {
		Map<String,Object> params = new HashMap<>();
		params.put("cartOwner", cartOwner);
		params.put("numbers", numbers);
		return sqlSession.delete("mapper.cart.deleteItems", params)>0;
	}
}
