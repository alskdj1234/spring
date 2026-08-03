package com.kh.spring11.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring11.configuration.StorageProperties;
import com.kh.spring11.dao.AttachDao;
import com.kh.spring11.dto.AttachDto;
import com.kh.spring11.error.TargetNotfoundException;
import com.kh.spring11.vo.attach.AttachInfoVO;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
@Slf4j
@Service
@Profile("cloud")
public class AttachServiceCloud implements AttachService {
	@Autowired
	private AttachDao attachDao;
	@Autowired
	private S3Client s3Client;
	@Autowired
	private StorageProperties storageProperties;
	
	@Transactional
	@Override
	public int save(MultipartFile attach) throws IllegalStateException, IOException {
		
		int attachNo = attachDao.sequence();
		attachDao.insert(AttachDto.builder()
					.attachNo(attachNo)
					.attachName(attach.getOriginalFilename())
					.attachType(attach.getContentType())
					.attachSize(attach.getSize())
				.build());//DB저장
		
		//1 aws s3 전용 클라이언트 생성(이미 만들어서 패스)
		
	
		//2 업로드할 파일명과 내용을 준비
		String objectKey = storageProperties.getAwsRoot()+"/" + attachNo;//올라갈 파일
		
		
		//3 업로드 요청(PutObjectRequest)을 보낼 요청 객체 응답 객체 준비
		PutObjectRequest request = PutObjectRequest.builder()
				.bucket(storageProperties.getAwsBucket())
				.key(objectKey)
				.contentType(attach.getContentType()+"; charset=UTF-8")
				.build();
		PutObjectResponse response = s3Client.putObject(request, RequestBody.fromBytes(
				attach.getBytes()//업로드 된 파일
				)
			);
		
		log.debug("업로드 완료");
		log.debug("Object key ={}", objectKey);
		log.debug("ETag ={}" ,response.eTag());
		return attachNo;
	}
	@Transactional
	@Override
	public void delete(Integer attachNo) {
		if (attachNo == null)
			return;

		// DB정보 삭제
		attachDao.delete(attachNo);

		String objectKey = "uploads/" + attachNo;

		DeleteObjectRequest request = DeleteObjectRequest.builder().bucket(storageProperties.getAwsBucket())
				.key(objectKey).build();
		DeleteObjectResponse response = s3Client.deleteObject(request);

		log.debug("파일 삭제 완료");
		log.debug("HTTP status ={}", response.sdkHttpResponse().statusCode());
		
		
	}
	@Override
	public AttachInfoVO load(int attachNo) throws IOException {
		//[1] 정보 조회
				AttachDto attachDto = attachDao.selectOne(attachNo);
				if(attachDto == null) throw new TargetNotfoundException();
				
				String objectKey = "uploads/"+attachNo;
				
				//[2] 실제 파일 데이터를 불러와서 Resource 형태로 포장
				GetObjectRequest request = GetObjectRequest.builder()
						.bucket(storageProperties.getAwsBucket())
						.key(objectKey)
						.build();
				
				//byte로 추출(in-memory 방식)
				ResponseInputStream<GetObjectResponse> stream = s3Client.getObject(request);
				GetObjectResponse response = stream.response();
				
				log.debug("content-Type ={}",response.contentType());
				log.debug("content-Length ={}",response.contentLength());
				log.debug("ETag ={}", response.eTag());
				
				byte[] data = stream.readAllBytes();
				Resource resource = new ByteArrayResource(data);
				
				stream.close();
				//[3] 조회 결과를 포장해서 반환
				return AttachInfoVO.builder()
							.attachDto(attachDto)
							.resource(resource)
						.build();
	}
}
