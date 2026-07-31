package com.kh.spring11.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.kh.spring11.vo.sale.ChangeThumbnailResponseVO;
import com.kh.spring11.vo.sale.SaleAddRequestVO;
import com.kh.spring11.vo.sale.SaleAddRequestVO2;
import com.kh.spring11.vo.sale.SaleAddResponseVO;
import com.kh.spring11.vo.sale.SaleDetailResponseVO;
import com.kh.spring11.vo.sale.SaleEditRequestVO;

import jakarta.validation.Valid;

public interface SaleService {
	SaleAddResponseVO add(SaleAddRequestVO request) throws IllegalStateException, IOException;
	SaleAddResponseVO add(
		SaleAddRequestVO2 request,  
		MultipartFile thumbnail,
		List<MultipartFile> detailImages
	) throws IllegalStateException, IOException;
	
	SaleDetailResponseVO findSaleDetail(int saleNo);
	void deleteSale(int saleNo);
	
	void edit(int saleNo, SaleEditRequestVO request);
	
	ChangeThumbnailResponseVO changeThumbnail(int saleNo, MultipartFile thumbnail) throws IllegalStateException, IOException;
}




