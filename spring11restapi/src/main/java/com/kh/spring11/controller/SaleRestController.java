package com.kh.spring11.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring11.annotation.AuthApiResponse;
import com.kh.spring11.dao.SaleDao;
import com.kh.spring11.service.SaleService;
import com.kh.spring11.vo.sale.SaleAddRequestVO2;
import com.kh.spring11.vo.sale.SaleAddResponseVO;
import com.kh.spring11.vo.sale.SaleDetailResponseVO;
import com.kh.spring11.vo.sale.SaleListRequestVO;
import com.kh.spring11.vo.sale.SaleListResponseVO;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
@Tag(name ="상품 api")
@AuthApiResponse
@RestController
@RequestMapping("/api/sale")
public class SaleRestController {
	
	@Autowired
	private SaleService saleService;
	@Autowired
	private SaleDao saleDao;

	
	@ApiResponse(responseCode = "200", description = "상품 등록 성공")
	@PostMapping(value = "/", produces ="application/json", consumes="multipart/form-data")
	public SaleAddResponseVO add(//@Valid @ModelAttribute SaleAddRequestVO request//모두 낱개로 오는 경우
			//데이터들이 파트별로 전송될 경우	
			@Valid @RequestPart(value ="sale") SaleAddRequestVO2 request,
				@RequestPart(value="thumbnail",required=false) MultipartFile thumbnail
				,@RequestPart(value="detailImages", required=false)List<MultipartFile> detailImages
			
			) throws IllegalStateException, IOException {
		
		//return saleService.add(request);[1]
		return saleService.add(request,thumbnail,detailImages);//[2]
	}
	
	@PostMapping("/list")
	public SaleListResponseVO list(@RequestBody SaleListRequestVO request) {
		return SaleListResponseVO.builder()
					.items(saleDao.selectList(request))
				.build();
	}
	
	@ApiResponse(responseCode = "200", description = "상세 정보 조회 성공")
	@GetMapping(value = "/{saleNo}", produces = "application/json")
	public SaleDetailResponseVO detail(@PathVariable int saleNo) {
		return saleService.findSaleDetail(saleNo);
	}
}
