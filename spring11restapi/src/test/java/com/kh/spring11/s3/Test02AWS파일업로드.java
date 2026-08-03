package com.kh.spring11.s3;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

@Slf4j
@SpringBootTest
public class Test02AWS파일업로드 {
	//aws에서 제공하는 sdk관련 의존성이 필요
	//bom을 설치하여 라이브러리를 가져와야함
	
	@Test
	public void test() {
		//1 aws s3 전용 클라이언트 생성
		S3Client s3Client = S3Client.builder()
				.region(Region.of("ap-northeast-2"))
				.build();
	
		//2 업로드할 파일명과 내용을 준비
		String objectKey = "uploads/test/dummy.txt";
		String content ="aws s3 업로드 완";
		
		//3 업로드 요청(PutObjectRequest)을 보낼 요청 객체 응답 객체 준비
		PutObjectRequest request = PutObjectRequest.builder()
				.bucket("kh17-storage-846328243046-ap-northeast-2-an")
				.key(objectKey)
				.contentType("text/plain; charset=UTF-8")
				.build();
		PutObjectResponse response = s3Client.putObject(request, RequestBody.fromBytes(
				content.getBytes(StandardCharsets.UTF_8)
				)
			);
		log.debug("업로드 성공");
		log.debug("객체 키={)", objectKey);
		log.debug("ETag = {}",response.eTag());
	
	}	
}
