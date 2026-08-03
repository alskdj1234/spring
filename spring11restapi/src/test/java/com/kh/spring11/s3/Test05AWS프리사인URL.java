package com.kh.spring11.s3;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring11.configuration.StorageProperties;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

@Slf4j
@SpringBootTest
public class Test05AWS프리사인URL {
	
	@Autowired private S3Client s3Client;
	@Autowired private StorageProperties storageProperties;
	
	@Test
	public void test() {
		//AWS S3에서 파일 불러오기
//		1. 실제로 파일을 다운로드 받아 불러 오기 - 가끔 압축이나 검사 할 때... 씀
//		2. 파일에 접근할 수 있는 임시 URL을 발급 받는 것(Presigned URL) - 권장
//		3. 파일에 접근할 수 있는 영구 URL을 발급 받는 것(걍 절대 쓰지마셈)
		
		//2번 코드
		String objectKey = "uploads/test/dummy.txt";
		
		GetObjectRequest request = GetObjectRequest.builder()
				.bucket(storageProperties.getAwsBucket())
				.key(objectKey)
				.responseContentDisposition(
						"attachment; filename=s3-dummy.txt")
				.build();
		S3Presigner s3Presigner = S3Presigner.builder()
				.region(Region.of(storageProperties.getAwsRegion()))
				.build();
		GetObjectPresignRequest presignRequest =
				GetObjectPresignRequest.builder()
					.signatureDuration(Duration.ofMinutes(10L))
					.getObjectRequest(request)
					.build();
		
		//S3가 발급한 임시 다운로드 주소 뽑기
		String url =s3Presigner.presignGetObject(presignRequest)
									.url().toString();
		
		log.debug("임시 url 발급 완료");
		log.debug("URL ={}",url);
	}
}
