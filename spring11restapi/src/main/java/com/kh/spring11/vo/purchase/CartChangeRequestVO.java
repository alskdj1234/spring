package com.kh.spring11.vo.purchase;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Schema(name ="장바구니 변경 사항 요청")
@Data @NoArgsConstructor @AllArgsConstructor
public class CartChangeRequestVO {
@NotNull @Positive private int no;
@NotNull @Positive private int qty;
}
