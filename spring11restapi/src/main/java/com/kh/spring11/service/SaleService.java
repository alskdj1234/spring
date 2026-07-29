package com.kh.spring11.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.spring11.vo.sale.SaleAddRequestVO;
import com.kh.spring11.vo.sale.SaleAddRequestVO2;
import com.kh.spring11.vo.sale.SaleAddResponseVO;

public interface SaleService {
	SaleAddResponseVO add(SaleAddRequestVO request) throws IllegalStateException, IOException;
	SaleAddResponseVO add(
		SaleAddRequestVO2 request,  
		MultipartFile thumbnail,
		List<MultipartFile> detailImages
	) throws IllegalStateException, IOException;
}