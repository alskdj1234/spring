package com.kh.spring11.vo.cert;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class CertCheckRequestVO {
	private String certEmail, certNumber;
}
