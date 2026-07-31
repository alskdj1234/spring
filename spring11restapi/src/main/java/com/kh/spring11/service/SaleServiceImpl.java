package com.kh.spring11.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring11.dao.AttachDao;
import com.kh.spring11.dao.SaleDao;
import com.kh.spring11.dto.AttachDto;
import com.kh.spring11.dto.SaleDto;
import com.kh.spring11.error.TargetNotfoundException;
import com.kh.spring11.vo.sale.ChangeThumbnailResponseVO;
import com.kh.spring11.vo.sale.SaleAddRequestVO;
import com.kh.spring11.vo.sale.SaleAddRequestVO2;
import com.kh.spring11.vo.sale.SaleAddResponseVO;
import com.kh.spring11.vo.sale.SaleDetailResponseVO;
import com.kh.spring11.vo.sale.SaleEditRequestVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SaleServiceImpl implements SaleService{
	@Autowired
	private SaleDao saleDao;
	@Autowired
	private AttachService attachService;
	@Autowired
	private AttachDao attachDao;
	
	@Transactional//이 메소드에서 발생하는 DB변경작업은 all or nothing 처리가 됨
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
		
		//(+추가) 상세이미지가 있으면 첨부파일 등록 후 상품정보와 연결
		List<MultipartFile> detailImages = request.getDetailImages();
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
		if(thumbnail != null && thumbnail.isEmpty() == false) {
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
	
	@Override
	public SaleDetailResponseVO findSaleDetail(int saleNo) {
		//[1] SaleDto를 조회
		SaleDto saleDto = saleDao.selectOne(saleNo);
		if(saleDto == null) throw new TargetNotfoundException();
		
		//[2] thumbnail을 조회 (없을 수도 있음)
		Integer attachNo = saleDao.findAttach(saleNo);
		AttachDto thumbnail = attachDao.selectOne(attachNo);
		
		//[3] details 조회
		List<Integer> attachNumbers = saleDao.findDetails(saleNo);
		List<AttachDto> details = attachDao.selectList(attachNumbers);
		
		return SaleDetailResponseVO.builder()
					.saleDto(saleDto)
					.thumbnail(thumbnail)
					.details(details)
				.build();
	}
	
	@Transactional
	@Override
	public void deleteSale(int saleNo) {
		//상품 정보 및 이미지 정보 + 실물파일까지 삭제
		//[1] 썸네일과 상세이미지의 파일번호를 찾아야함
		Integer thumbnailNo = saleDao.findAttach(saleNo);
		List<Integer> detailNumbers = saleDao.findDetails(saleNo);
		//[2] 다 지워진 뒤 상품정보를 삭제
		saleDao.delete(saleNo);//상품 정보(마지막)
		//[3] DB의 파일정보를 먼저 삭제하고 실물파일을 삭제하도록 처리 + @Transactional
		//	→ 파일번호만 알면 AttachService에서 가능 (파일 1개에 대해서)
		attachService.delete(thumbnailNo);//썸네일 삭제 지시
		for(Integer attachNo : detailNumbers) {
			attachService.delete(attachNo);//상세이미지 삭제 지시
		}
	}
	
	@Override
	public void edit(int saleNo, SaleEditRequestVO request) {
		//요청에 saleDiscountPrice가 없는 경우는 saleOriginalPrice와 동일하게 변경
		if(request.getSaleDiscountPrice() == null)
			request.setSaleDiscountPrice(request.getSaleOriginalPrice());
		
		SaleDto saleDto = new SaleDto();
		saleDto.setSaleNo(saleNo);//번호 복사
		BeanUtils.copyProperties(request, saleDto);//나머지 전달된 데이터 복사
		saleDao.update(saleDto);//정보 변경 요청
	}
	
	@Transactional
	@Override
	public ChangeThumbnailResponseVO changeThumbnail(int saleNo, MultipartFile thumbnail) throws IllegalStateException, IOException {
		//썸네일 변경을 위한 구체적인 코드
		//[1] 기존 썸네일 번호를 조회
		Integer thumbnailNo = saleDao.findAttach(saleNo);
		//[2] 기존 썸네일이 있다면 제거
		attachService.delete(thumbnailNo);//null은 알아서 제거됨
		//[3] 신규 썸네일 추가
		int newThumbnailNo = attachService.save(thumbnail);//저장
		saleDao.connect(saleNo, newThumbnailNo);//연결
		//[4] 신규 썸네일 정보 조회
		AttachDto attachDto = attachDao.selectOne(newThumbnailNo);
		return ChangeThumbnailResponseVO.builder()
					.attach(attachDto)
				.build();
	}
	
}







