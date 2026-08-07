package com.kh.spring11.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CartDto {
  private String cartOwner;
  private int cartItem;
  private int cartQty;
}
