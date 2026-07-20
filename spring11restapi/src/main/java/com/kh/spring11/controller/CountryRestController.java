package com.kh.spring11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring11.dao.CountryDao;
import com.kh.spring11.dto.CountryDto;
import com.kh.spring11.error.TargetNotfoundException;
import com.kh.spring11.vo.CountryComplexRequestVO;
import com.kh.spring11.vo.ListRequestVO;
import com.kh.spring11.vo.ListVO;

@CrossOrigin
@RestController
@RequestMapping("/api/country")
public class CountryRestController {
	
	//인터페이스를 Autowired하면 상속받은 클래스 중 등록된 클래스의 객체가 주입된다
	//(주의) 등록은 반드시 하나만 되어 있어야 한다 (여러개를 두고 선택하려면 추가 작업이 필요)
	@Autowired
	private CountryDao countryDao;
	
	//등록
	@PostMapping("/")
	public CountryDto insert(@RequestBody CountryDto countryDto) {
		int countryNo = countryDao.sequence();
		countryDto.setCountryNo(countryNo);
		countryDao.insert(countryDto);
		return countryDto;
		//return countryDao.selectOne(countryNo);
	}
	
	//조회
	@GetMapping("/")
	public List<CountryDto> list() {
		return countryDao.selectList(1, Integer.MAX_VALUE);
	}
	
//	리액트를 위한 더보기 방식의 조회
//	- 조회는 GetMapping으로 구현 (정보가 너무 많으면 Post로도 가능)
//	- 정보가 많다의 기준은 2개
//	@GetMapping("/lastCountryNo/{lastCountryNo}/size/{size}")
//	public ListVO listForReact(
//		@PathVariable int lastCountryNo,
//		@PathVariable int size
//	) {
//		List list = countryDao.selectListForReact(lastCountryNo, size);
//		int count = countryDao.countForReact(lastCountryNo);
//		
//		return ListVO.builder()
//			.list(list)
//			.last(count <= size)//보기로 한 개수보다 데이터가 같거나 적으면 마지막
//		.build();
//	}
	
	@PostMapping("/list-more")
	public ListVO listForReact(@RequestBody ListRequestVO vo) {
		List list = countryDao.selectList(vo.getLastNo(), vo.getSize());
		int count = countryDao.count(vo.getLastNo());
		
		return ListVO.builder()
			.list(list)
			.last(count <= vo.getSize())//보기로 한 개수보다 데이터가 같거나 적으면 마지막
		.build();
	}
	
	//상세 : PK를 경로 변수로 받음
	@GetMapping("/{countryNo}")
	public CountryDto find(@PathVariable int countryNo) {
		CountryDto countryDto = countryDao.selectOne(countryNo);
		if(countryDto == null) throw new TargetNotfoundException();
		return countryDto;
	}
	
	//삭제
	@DeleteMapping("/{countryNo}")
	public CountryDto delete(@PathVariable int countryNo) {
		CountryDto countryDto = countryDao.selectOne(countryNo);
		if(countryDto == null) throw new TargetNotfoundException();
		countryDao.delete(countryNo);
		return countryDto;//삭제한 데이터 정보
	}
	
	//전체변경
	//- 원래 번호도 변경 가능하다고 하지만 우리는 하지 않는다
	@PutMapping("/{countryNo}")
	public CountryDto updateAll(@RequestBody CountryDto countryDto,
							@PathVariable int countryNo) {
		CountryDto findCountryDto = countryDao.selectOne(countryNo);
		if(findCountryDto == null) throw new TargetNotfoundException();
		
		countryDto.setCountryNo(countryNo);
		countryDao.update(countryDto);
		
		return countryDto;
	}
	
	//부분수정
	@PatchMapping("/{countryNo}")
	public CountryDto updateUnit(@RequestBody CountryDto countryDto, 
							@PathVariable int countryNo) {
		//countryDto에 뭐가 있을지 모름
		//일단 countryNo로 원본을 조회해서 countryDto에 있는 정보만 복사한 뒤 변경
		CountryDto findCountryDto = countryDao.selectOne(countryNo);
		if(findCountryDto == null) throw new TargetNotfoundException();
		
		//countryDto에 존재하는 항목들을 findCountryDto에 복사
		if(countryDto.getCountryRegion() != null) {
			findCountryDto.setCountryRegion(countryDto.getCountryRegion());
		}
		if(countryDto.getCountryName() != null) {
			findCountryDto.setCountryName(countryDto.getCountryName());
		}
		if(countryDto.getCountryCapital() != null) {
			findCountryDto.setCountryCapital(countryDto.getCountryCapital());
		}
		if(countryDto.getCountryPopulation() > 0L) {
			findCountryDto.setCountryPopulation(countryDto.getCountryPopulation());
		}
		countryDao.update(findCountryDto);
		
		return findCountryDto;
	}
	
	@GetMapping("/countryName/{keyword}")
	public List<CountryDto> searchByKeyword(@PathVariable String keyword) {
		return countryDao.searchByCountryName(keyword);
	}
	
	@PostMapping("/complexSearch")
	public ListVO complexSearch(@RequestBody CountryComplexRequestVO vo) {
		int count = countryDao.complexSearchCount(vo);
		boolean last = vo.getSize() == null ? true : count <= vo.getSize();
		
		return ListVO.builder()
					.list(countryDao.complexSearch(vo))
					.last(last)
				.build();
	}
	
}











