package com.kh.spring11.dao;

import java.util.List;

import com.kh.spring11.dto.CartDto;
import com.kh.spring11.vo.purchase.CartItemVO;

public interface CartDao {
		void insertOrUpdate(CartDto cartDto);
		boolean delete(CartDto cartDto);
		CartDto selectOne(CartDto cartDto);
		List<CartItemVO> selectList(String cartOwner);
		boolean update (CartDto cartDto);
		boolean delete(String partnerUserId, List<Integer> numbers);
}
