package com.kh.spring11.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring11.dao.SaleDao;
import com.kh.spring11.dto.SaleDto;
import com.kh.spring11.vo.sale.SaleAddRequestVO;
import com.kh.spring11.vo.sale.SaleAddRequestVO2;
import com.kh.spring11.vo.sale.SaleAddResponseVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SaleServiceImpl implements SaleService{
	@Autowired
	private SaleDao saleDao;
	@Autowired
	private AttachService attachService;
	
	@Transactional//이 메소드에서 발생하는 DB작업은 all or nothing 처리가 됨
	@Override
	public SaleAddResponseVO add(SaleAddRequestVO request) throws IllegalStateException, IOException {
		//[1] 상품 번호 생성
		int saleNo = saleDao.sequence();
		
		//[2] 상품 등록을 위한 DTO 생성
		SaleDto saleDto = new SaleDto();
		saleDto.setSaleNo(saleNo);//번호 설정
		BeanUtils.copyProperties(request, saleDto, "saleDiscountPrice");//할인가격 빼고 복사
		
		//(+추가) 할인가격이 없으면 판매가격과 동일하게 할인가격을 설정
		if(request.getSaleDiscountPrice() == null) {
			saleDto.setSaleDiscountPrice(request.getSaleOriginalPrice());
		}
		else {
			saleDto.setSaleDiscountPrice(request.getSaleDiscountPrice());
		}
		
		//[3] 상품 등록
		saleDao.insert(saleDto);
		
		//[4] 사용자에게 알려주기 위해 등록된 정보를 재조회
		SaleDto resultDto = saleDao.selectOne(saleNo);
		SaleAddResponseVO response = new SaleAddResponseVO();
		BeanUtils.copyProperties(resultDto, response);
		
		//(+추가) 첨부파일이 있으면 첨부파일 등록 후 상품정보와 연결
		MultipartFile thumbnail = request.getThumbnail();
		if(thumbnail.isEmpty() == false) {
			int attachNo = attachService.save(thumbnail);
			saleDao.connect(saleNo, attachNo);
		}
		
		return response;
	}
	
	@Transactional//이 메소드에서 발생하는 DB작업은 all or nothing 처리가 됨
	@Override
	public SaleAddResponseVO add(
		SaleAddRequestVO2 request, 
		MultipartFile thumbnail,
		List<MultipartFile> detailImages
	) throws IllegalStateException, IOException {
		//[1] 상품 번호 생성
		int saleNo = saleDao.sequence();
		
		//[2] 상품 등록을 위한 DTO 생성
		SaleDto saleDto = new SaleDto();
		saleDto.setSaleNo(saleNo);//번호 설정
		BeanUtils.copyProperties(request, saleDto, "saleDiscountPrice");//할인가격 빼고 복사
		
		//(+추가) 할인가격이 없으면 판매가격과 동일하게 할인가격을 설정
		if(request.getSaleDiscountPrice() == null) {
			saleDto.setSaleDiscountPrice(request.getSaleOriginalPrice());
		}
		else {
			saleDto.setSaleDiscountPrice(request.getSaleDiscountPrice());
		}
		
		//[3] 상품 등록
		saleDao.insert(saleDto);
		
		//[4] 사용자에게 알려주기 위해 등록된 정보를 재조회
		SaleDto resultDto = saleDao.selectOne(saleNo);
		SaleAddResponseVO response = new SaleAddResponseVO();
		BeanUtils.copyProperties(resultDto, response);
		
		//(+추가) 썸네일이 있으면 첨부파일 등록 후 상품정보와 연결
		if(thumbnail.isEmpty() == false) {
			int attachNo = attachService.save(thumbnail);
			saleDao.connect(saleNo, attachNo);
		}
		
		//(+추가) 상세이미지가 있으면 첨부파일 등록 후 상품정보와 연결
		boolean exist = detailImages != null && detailImages.size() > 0;
		if(exist) {//파라미터가 있으면
			for(MultipartFile detail : detailImages) {//반복하며
				if(detail.isEmpty() == false) {//비어있지 않은 이미지를
					int attachNo = attachService.save(detail);//등록
					saleDao.connectDetailImage(saleNo, attachNo);//연결
				}
			}
		}
		
		return response;
	}
}