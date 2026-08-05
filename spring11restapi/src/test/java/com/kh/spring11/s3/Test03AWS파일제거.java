package com.kh.spring11.s3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.configuration.StorageProperties;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectResponse;

@Slf4j
@SpringBootTest
public class Test03AWS파일제거 {
	@Autowired
	private S3Client s3Client;
	@Autowired
	private StorageProperties storageProperties;
	
	@Test
	public void test() {
		//삭제 요청은 DeleteObjectRequest, DeleteObjectReponse로 처리
		String objectKey = "uploads/test/dummy.txt";//지울 대상의 경로
		
		DeleteObjectRequest request = DeleteObjectRequest.builder()
					.bucket(storageProperties.getAwsBucket())
					.key(objectKey)
				.build();
		
		DeleteObjectResponse response = s3Client.deleteObject(request);
		
		log.debug("<AWS 파일 삭제 완료>");
		log.debug("HTTP status = {}", response.sdkHttpResponse().statusCode());
	}
}







