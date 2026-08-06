package com.kh.spring11.vo.sale;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SaleOrderResponseVO {
	
	private List<SaleListItemVO> saleList;
}
