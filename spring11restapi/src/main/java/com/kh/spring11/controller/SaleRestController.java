package com.kh.spring11.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring11.annotation.AuthApiResponse;
import com.kh.spring11.dao.SaleDao;
import com.kh.spring11.service.SaleService;
import com.kh.spring11.vo.sale.ChangeThumbnailResponseVO;
import com.kh.spring11.vo.sale.SaleAddRequestVO2;
import com.kh.spring11.vo.sale.SaleAddResponseVO;
import com.kh.spring11.vo.sale.SaleDetailResponseVO;
import com.kh.spring11.vo.sale.SaleEditRequestVO;
import com.kh.spring11.vo.sale.SaleListRequestVO;
import com.kh.spring11.vo.sale.SaleListResponseVO;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Encoding;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "상품 API")
@AuthApiResponse
@RestController
@RequestMapping("/api/sale")
public class SaleRestController {
	
	@Autowired
	private SaleService saleService;
	@Autowired
	private SaleDao saleDao;

	@ApiResponse(responseCode = "200", description = "상품 등록 성공")
	@PostMapping(
		value = "/", 
		produces = "application/json",
		consumes = "multipart/form-data"//springdoc을 위하여 요구형태 명시
	)
	public SaleAddResponseVO add(
		//[1] 리액트에서 데이터들이 낱개로 전송될 경우
		//@Valid @ModelAttribute SaleAddRequestVO request
	
		//[2] 리액트에서 데이터들이 파트별로 전송될 경우
		
		//RequestPart가 application/json임을 명시해서 SpringDoc 테스트시 혼선이 없도록
		//→ Custom Annotation으로 만들면 경우에 따라 안될 가능성이 존재하므로 직접 작성 권장
		@io.swagger.v3.oas.annotations.parameters.RequestBody(
			content = @Content(
				encoding = @Encoding(
					name = "sale",
					//contentType = "application/json"
					contentType = MediaType.APPLICATION_JSON_VALUE
				)
			)
		)
		@Valid @RequestPart(value = "sale") SaleAddRequestVO2 request,
		
		@RequestPart(value = "thumbnail", required = false) 
		MultipartFile thumbnail,
		
		@RequestPart(value = "detailImages", required = false)
		List<MultipartFile> detailImages
	) throws IllegalStateException, IOException {
		//return saleService.add(request);//[1]
		return saleService.add(request, thumbnail, detailImages);//[2]
	}
	
	@PostMapping("/list")
	public SaleListResponseVO list(@RequestBody SaleListRequestVO request) {
		return SaleListResponseVO.builder()
					.items(saleDao.selecList(request))
				.build();
	}
	
	@ApiResponse(responseCode = "200", description = "상세 정보 조회 성공")
	@GetMapping(value = "/{saleNo}", produces = "application/json")
	public SaleDetailResponseVO detail(@PathVariable int saleNo) {
		return saleService.findSaleDetail(saleNo);
	}
	
	@ApiResponse(responseCode = "200", description = "상품 정보 삭제 성공")
	@DeleteMapping(value = "/{saleNo}")
	public void delete(@PathVariable int saleNo) {
		saleService.deleteSale(saleNo);
	}
	
	@ApiResponse(responseCode = "200", description = "상품 정보 수정 성공")
	@PutMapping(
		value = "/{saleNo}",
		consumes = "multipart/form-data"
	)
	public void edit(
		@PathVariable int saleNo,
		
		//RequestPart가 application/json임을 명시해서 SpringDoc 테스트시 혼선이 없도록
		//→ Custom Annotation으로 만들면 경우에 따라 안될 가능성이 존재하므로 직접 작성 권장
		@io.swagger.v3.oas.annotations.parameters.RequestBody(
			content = @Content(
				encoding = @Encoding(
					name = "sale",
					//contentType = "application/json"
					contentType = MediaType.APPLICATION_JSON_VALUE
				)
			)
		)
		@Valid @RequestPart(value = "sale") SaleEditRequestVO request,
		@RequestPart(value="detailImages", required= false)
		List<MultipartFile> detailImages
			) throws IllegalStateException, IOException {
		
		saleService.edit(saleNo, request, detailImages);
	}
	
	//썸네일만 변경하는 매핑
	@ApiResponse(responseCode = "200", description = "썸네일 변경 완료")
	@PatchMapping(value = "/thumbnail/{saleNo}")
	public ChangeThumbnailResponseVO changeThumbnail(
		@PathVariable int saleNo,
		@RequestPart(value = "thumbnail") MultipartFile thumbnail
	) throws IllegalStateException, IOException {
		//기존의 이미지가 있다면 제거
		//신규 이미지를 추가
		//추가된 이미지의 정보를 반환
		return saleService.changeThumbnail(saleNo, thumbnail);
	}
	@ApiResponse(responseCode = "200", description ="썸네일 삭제 성공")
	@DeleteMapping("/thumbnail/{saleNo}")
	public void deleteThumbnail(@PathVariable int saleNo) {
		saleService.deleteThumbnail(saleNo);
	}
	@DeleteMapping("/detailImage/sale/{saleNo}/attach/{attachNo}")
	public void deleteDetailImage(@PathVariable int saleNo, @PathVariable int attachNo) {
	
		saleService.deleteDetailImage(saleNo,attachNo);
		
	}
	
	//삭제여도 데이터를 많이 보내야 해서
	@ApiResponse(responseCode ="200", description ="상세 이미지들 삭제 성공")
	@PostMapping("/deleteDetailImages/{saleNo}")
	public void deleteDetailImages(@PathVariable int saleNo, @RequestBody List<Integer>detailNumbers) {
		
	}
}






