package com.kh.spring11.controller;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring11.annotation.CommonsApiResponse;
import com.kh.spring11.configuration.StorageProperties;
import com.kh.spring11.dao.AttachDao;
import com.kh.spring11.dto.AttachDto;
import com.kh.spring11.error.TargetNotfoundException;
import com.kh.spring11.service.AttachService;
import com.kh.spring11.vo.attach.AttachInfoVO;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

@Tag(name = "첨부파일 API")
@CommonsApiResponse
@Slf4j
@RestController
@RequestMapping("/api/attach")
public class AttachRestController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private AttachService attachService;
	
	@Autowired
	private AttachDao attachDao;
	
	@Autowired
	private StorageProperties storageProperties;
	
	@Autowired
	private S3Presigner s3Presigner;
	@GetMapping("/{attachNo}")
	public ResponseEntity<?> download(
		@PathVariable int attachNo
	) throws IOException {
		
		
		if(environment.matchesProfiles("cloud")) {
			return ResponseEntity.status(302)
					.location(URI.create("/api/attach/p/"+attachNo))
					//.location(URI.create("./p/"+attachNo))
					
					.build();
		}
		
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
	
	@GetMapping("/p/{attachNo}")
	public ResponseEntity<String> presigned(@PathVariable int attachNo) {
		
		AttachDto attachDto = attachDao.selectOne(attachNo);
		if(attachDto == null) throw new TargetNotfoundException();
		String objectKey = storageProperties.getAwsRoot()+"/"+ attachNo;
		
		GetObjectRequest request = GetObjectRequest.builder()
				.bucket(storageProperties.getAwsBucket())
				.key(objectKey)
				.responseContentDisposition(
						ContentDisposition
						.attachment()
						.filename(
							attachDto.getAttachName(),
							StandardCharsets.UTF_8
						)
						.build().toString())
				
				.build();
		
		GetObjectPresignRequest presignRequest =
				GetObjectPresignRequest.builder()
					.signatureDuration(Duration.ofMinutes(storageProperties.getPresignedLimit()))
					.getObjectRequest(request)
					.build();
		
		//S3가 발급한 임시 다운로드 주소 뽑기
		String url =s3Presigner.presignGetObject(presignRequest)
									.url().toString();
		System.out.printf("URL :",url);
		
		//성공하면 200이 아니라 302번 응답을 발생 시켜 S3 presigned url로 이동
		return ResponseEntity.status(HttpStatusCode.valueOf(302))
				.location(URI.create(url))
				.build();
	}
}




