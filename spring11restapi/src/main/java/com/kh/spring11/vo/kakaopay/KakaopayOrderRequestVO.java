package com.kh.spring11.vo.kakaopay;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KakaopayOrderRequestVO {

    @NotBlank
    private String cid;

    @NotBlank
    private String tid;	
}
