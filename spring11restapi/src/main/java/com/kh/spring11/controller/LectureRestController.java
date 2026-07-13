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

import com.kh.spring11.annotation.CommonsApiResponse;
import com.kh.spring11.dao.LectureDao;
import com.kh.spring11.dto.LectureDto;
import com.kh.spring11.error.TargetNotfoundException;
import com.kh.spring11.vo.LectureInsertVO;
import com.kh.spring11.vo.LectureUpdateAllVO;
import com.kh.spring11.vo.LectureUpdateUnitVO;
import com.kh.spring11.vo.ListRequestVO;
import com.kh.spring11.vo.ListVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

//문서에 표시되기 위한 정보들도 Annotation 형태로 설정한다 (혼동되지 않도록 주의)
@Tag(name = "강좌API", description = "강좌 CRUD를 위한 API 입니다")

@CrossOrigin
@RestController
@RequestMapping("/api/lecture")
@CommonsApiResponse
public class LectureRestController {
	
	@Autowired
	private LectureDao lectureDao;
	
	//어떤 작업인지에 대해 설명하기 위한 코드
	@Operation(
		//deprecated = true,
		summary = "신규 강좌 생성",
		description = "새로운 강좌를 생성하고자 하는 AJAX 요청에 대응합니다",
		responses = {
			@ApiResponse(
				responseCode = "200", 
				description = "등록 성공",
				content = @Content(
					mediaType = "application/json",
					schema = @Schema(implementation = LectureDto.class)
				)
			)
		} 
	)
	
	@PostMapping("/")
	public LectureDto insert(@RequestBody LectureInsertVO lectureInsertVO) {
		int lectureNo = lectureDao.sequence();
		LectureDto lectureDto = new LectureDto();
		lectureDto.setLectureNo(lectureNo);
		lectureDto.setLectureCategory(lectureInsertVO.getLectureCategory());
		lectureDto.setLectureTitle(lectureInsertVO.getLectureTitle());
		lectureDto.setLectureDuration(lectureInsertVO.getLectureDuration());
		lectureDto.setLecturePrice(lectureInsertVO.getLecturePrice());
		lectureDto.setLectureType(lectureInsertVO.getLectureType());
		lectureDao.insert(lectureDto);
		return lectureDto;
	}
	
	
	
	
	@Operation(
		//deprecated = true,
		summary = "강좌 목록 조회",
		description = "강좌를 최근 등록된 순서대로 조회하여 출력합니다",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "강좌 목록 조회 성공",
				content = @Content(
					mediaType = "application/json",
					array = @ArraySchema(
						schema = @Schema(implementation = LectureDto.class)
					)
				) 
			)
		}
	)
	
	@GetMapping("/")
	public List<LectureDto> list() {
		return lectureDao.selectList(null, 10);
	}
	
	@PostMapping("/list-more")
	public ListVO listMore(@RequestBody ListRequestVO vo) {
		List list = lectureDao.selectList(vo.getLastNo(), vo.getSize());
		int count = lectureDao.count(vo.getLastNo());
		return ListVO.builder()
					.list(list)
					.last(vo.getSize() >= count)
				.build();
	}
	
	@Operation(
		//deprecated = true,
		summary = "강좌 상세 조회",
		description = "특정 강좌에 대한 모든 정보를 조회합니다",
		responses = @ApiResponse(
			responseCode = "200",
			description = "대상 조회 성공",
			content = @Content(
				schema = @Schema(implementation = LectureDto.class)
			)
		)
	)
	
	@GetMapping("/{lectureNo}")
	public LectureDto find(
			@Parameter(description = "조회할 강좌 번호", example = "1")
			@PathVariable int lectureNo) {
		LectureDto lectureDto = lectureDao.selectOne(lectureNo);
		if(lectureDto == null) throw new TargetNotfoundException();
		return lectureDto;
	}
	
	@Operation(
		//deprecated : true,
		summary = "강좌 정보 삭제",
		description = "대상 강좌의 모든 정보를 데이터베이스에서 삭제합니다",
		responses = @ApiResponse(
			responseCode = "200",
			description = "삭제 성공",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = LectureDto.class)
			)
		)
	)
	
	@DeleteMapping("/{lectureNo}")
	public LectureDto delete(
			@Parameter(description = "삭제할 강좌번호", example = "1")
			@PathVariable int lectureNo) {
		LectureDto lectureDto = lectureDao.selectOne(lectureNo);
		if(lectureDto == null) throw new TargetNotfoundException();
		lectureDao.delete(lectureNo);
		return lectureDto;
	}
	
	@Operation(
		summary = "강좌 정보 전체 수정",
		description = "요청한 강좌번호에 대해 번호를 제외한 모든 정보를 수정합니다",
		responses = @ApiResponse(
			responseCode = "200",
			description = "강좌 정보 수정 성공",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = LectureDto.class)
			)
		)
	)
	
	@PutMapping("/{lectureNo}")
	public LectureDto updateAll(
		@RequestBody LectureUpdateAllVO lectureUpdateAllVO,
		@Parameter(description = "수정할 강좌 고유 번호", example = "1")
		@PathVariable int lectureNo
	) {
		LectureDto lectureDto = lectureDao.selectOne(lectureNo);
		if(lectureDto == null) throw new TargetNotfoundException();
		
		lectureDto.setLectureCategory(lectureUpdateAllVO.getLectureCategory());
		lectureDto.setLectureTitle(lectureUpdateAllVO.getLectureTitle());
		lectureDto.setLectureDuration(lectureUpdateAllVO.getLectureDuration());
		lectureDto.setLecturePrice(lectureUpdateAllVO.getLecturePrice());
		lectureDto.setLectureType(lectureUpdateAllVO.getLectureType());
		
		lectureDao.update(lectureDto);
		return lectureDto;
	}
	
	@Operation(
		summary = "강좌 정보 부분 수정",
		description = "대상 강좌의 정보 중 원하는 일부 정보를 변경합니다",
		responses = @ApiResponse(
			responseCode = "200",
			description = "강좌 정보 변경 성공",
			content = @Content(
				mediaType = "application/json",
				schema = @Schema(implementation = LectureDto.class)
			)
		)
	)
	@PatchMapping("/{lectureNo}")
	public LectureDto updateUnit(
		@RequestBody LectureUpdateUnitVO lectureUpdateUnitVO,
		@Parameter(description = "수정할 강좌 고유 번호", example = "1")
		@PathVariable int lectureNo
	) {
		LectureDto lectureDto = lectureDao.selectOne(lectureNo);
		if(lectureDto == null) throw new TargetNotfoundException();
		
		if(lectureUpdateUnitVO.getLectureCategory() != null)
			lectureDto.setLectureCategory(lectureUpdateUnitVO.getLectureCategory());
		if(lectureUpdateUnitVO.getLectureTitle() != null)
			lectureDto.setLectureTitle(lectureUpdateUnitVO.getLectureTitle());
		if(lectureUpdateUnitVO.getLectureDuration() != null)
			lectureDto.setLectureDuration(lectureUpdateUnitVO.getLectureDuration());
		if(lectureUpdateUnitVO.getLecturePrice() != null)
			lectureDto.setLecturePrice(lectureUpdateUnitVO.getLecturePrice());
		if(lectureUpdateUnitVO.getLectureType() != null)
			lectureDto.setLectureType(lectureUpdateUnitVO.getLectureType());
		
		lectureDao.update(lectureDto);
		return lectureDto;
	}
	
}







