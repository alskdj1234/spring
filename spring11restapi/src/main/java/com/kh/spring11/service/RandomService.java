package com.kh.spring11.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomService {

	private Random r = new Random();
	
	private String numbers = "0123456789";
	private String lowerCases = "abcdefghijklmnopqrstuvwxyz";
	private String upperCases = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	//숫자 생성
	public String generateNumber(int size) {
		StringBuffer buffer = new StringBuffer();//버퍼 생성
		for(int i=0; i < size; i++) {//size번
			int index = r.nextInt(numbers.length());//랜덤위치
			char ch = numbers.charAt(index);//해당 위치 글자 추출
			buffer.append(ch);//버퍼에 추가
		}
		return buffer.toString();//반환
	}
	
}
