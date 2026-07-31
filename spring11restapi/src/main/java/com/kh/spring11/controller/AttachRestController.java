package com.kh.spring11.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring11.annotation.CommonsApiResponse;
import com.kh.spring11.service.AttachService;
import com.kh.spring11.vo.attach.AttachInfoVO;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "첨부파일 API")
@CommonsApiResponse

@RestController
@RequestMapping("/api/attach")
public class AttachRestController {
	
	@Autowired
	private AttachService attachService;
	
	@GetMapping("/{attachNo}")
	public ResponseEntity<?> download(
		@PathVariable int attachNo
	) throws IOException {
		//[1] AttachService를 이용해서 파일과 파일정보를 부른다
		AttachInfoVO vo = attachService.load(attachNo);
		
		//[2] 조회 결과를 이용해서 사용자에게 보낼 다운로드용 응답을 내보낸다
		//→ 다운로드 형식은 우리가 어찌할 수 없는 정해진 웹 통신 규격이다
		return ResponseEntity.ok()
				//헤더
				.header(HttpHeaders.CONTENT_ENCODING, "UTF-8")
				.header(HttpHeaders.CONTENT_TYPE, vo.getAttachDto().getAttachTypeString())
				.contentLength(vo.getAttachDto().getAttachSize())
				.header(
					HttpHeaders.CONTENT_DISPOSITION,
					ContentDisposition
						.attachment()
						.filename(
							vo.getAttachDto().getAttachName(),
							StandardCharsets.UTF_8
						)
						.build().toString()
				)
				//바디
				.body(vo.getResource());
	}
}




