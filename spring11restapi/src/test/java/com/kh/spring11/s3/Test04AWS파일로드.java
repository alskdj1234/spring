package com.kh.spring11.s3;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.configuration.StorageProperties;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

@Slf4j
@SpringBootTest
public class Test04AWS파일로드 {

	@Autowired
	private StorageProperties storageProperties;

	@Autowired
	private S3Client s3Client;

	@Test
	public void test() throws IOException {
		//AWS S3에서 파일 불러오기
//		1. 실제로 파일을 다운로드 받아 불러 오기 - 가끔 압축이나 검사 할 때... 씀
//		2. 파일에 접근할 수 있는 임시 URL을 발급 받는 것(Presigned URL) - 권장
//		3. 파일에 접근할 수 있는 영구 URL을 발급 받는 것(걍 절대 쓰지마셈)
		
		//1번 코드
		String objectKey = "uploads/test/dummy.txt";
		
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
		//문자열로 복원(파일마다 다름)
		String str = new String(data, "UTF-8");
		log.debug("str ={}",str);
		
		stream.close();
	}
}
