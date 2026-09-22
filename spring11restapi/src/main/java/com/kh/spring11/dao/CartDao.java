package com.kh.spring11.dao;

import java.util.List;

import com.kh.spring11.dto.CartDto;
import com.kh.spring11.vo.purchase.CartItemVO;

public interface CartDao {
	void insertOrUpdate(CartDto cartDto);
	boolean update(CartDto cartDto);
	
	CartDto selectOne(CartDto cartDto);
	List<CartItemVO> selectList(String cartOwner);
//	boolean delete(CartDto cartDto);
	
	boolean delete(String cartOwner, int cartItem);
	boolean delete(String cartOwner, List<Integer> numbers);
}







