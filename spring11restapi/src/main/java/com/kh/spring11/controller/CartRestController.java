package com.kh.spring11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring11.annotation.CurrentUser;
import com.kh.spring11.dao.CartDao;
import com.kh.spring11.dto.CartDto;
import com.kh.spring11.vo.jwt.TokenParseResponseVO;
import com.kh.spring11.vo.purchase.CartAddRequestVO;
import com.kh.spring11.vo.purchase.CartAddResponseVO;
import com.kh.spring11.vo.purchase.CartChangeRequestVO;
import com.kh.spring11.vo.purchase.CartChangeResponseVO;
import com.kh.spring11.vo.purchase.CartListResponseVO;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
@Tag(name = "장바구니 API")

@RestController
@RequestMapping("/api/cart")
public class CartRestController {
	@Autowired
	private CartDao cartDao;

	@ApiResponse(responseCode = "200", description = "장바구니 추가 성공")
	@PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public CartAddResponseVO addCart(@Valid @RequestBody CartAddRequestVO request,
			@CurrentUser TokenParseResponseVO parseVO) {
			
		cartDao.insertOrUpdate(CartDto.builder()
				.cartOwner(parseVO.getAccountId())
				.cartItem(request.getItem())
				.cartQty(request.getQty())
				.build()
				);
		
		CartDto find = cartDao.selectOne(CartDto.builder()
				.cartItem(request.getItem())
				.cartOwner(parseVO.getAccountId())
				.build());
		
		return CartAddResponseVO.builder()
					.cart(find)
					.build();
	}

	@GetMapping(value = "/")
	public CartListResponseVO cartList(@CurrentUser TokenParseResponseVO parseVO) {
		return CartListResponseVO.builder()
				.cartItems(cartDao.selectList(parseVO.getAccountId()))
				.build();

	}

	@ApiResponse(responseCode="200", description= "장바구니 수량 변경 성공")
	@PatchMapping("/")
	public CartChangeResponseVO changeCartQty(
			@Valid @RequestBody CartChangeRequestVO request
			,@CurrentUser TokenParseResponseVO parseVO) {
		
	 	cartDao.update(CartDto.builder()
				.cartOwner(parseVO.getAccountId())
				.cartQty(request.getQty())
				.cartItem(request.getNo())
				.build());
	 	
	 	CartDto find = cartDao.selectOne(CartDto.builder()
	 			.cartOwner(parseVO.getAccountId())
	 			.cartItem(request.getNo())
	 			.build()
	 			);
	 	return CartChangeResponseVO.builder()
	 			.cartDto(find)
	 			.build();
		
	}

}