package com.kh.spring11.vo.cert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class CertCheckResponseVO {
	private boolean valid;
}
