package com.kh.spring11.vo.purchase;

import com.kh.spring11.dto.CartDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Schema(name ="장바구니 변경 사항 응답")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CartChangeResponseVO {
private CartDto cartDto;
}
