package com.kh.spring11.service;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomService {

	private Random r = new Random();

	private String numbers = "0123456789";
	private String lowerCases = "abcdefghijklmnopqrstuvwxyz";
	private String upperCases = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private String special = "!@#$%^&*()-_+=";

	// 숫자 생성
	public String generateNumber(int size) {
		StringBuffer buffer = new StringBuffer();// 버퍼 생성
		for (int i = 0; i < size; i++) {// size번
			int index = r.nextInt(numbers.length());// 랜덤위치
			char ch = numbers.charAt(index);// 해당 위치 글자 추출
			buffer.append(ch);// 버퍼에 추가
		}
		return buffer.toString();// 반환
	}

	public String generateString(int size) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < size; i++) {
			int type = r.nextInt(4);
			// java 13이상 사용 가능한 switch var 구문
			String target = switch (type) {
			case 0 -> numbers;
			case 1 -> lowerCases;
			case 2 -> upperCases;
			default -> special;
			};
			int position = r.nextInt(target.length());

			buffer.append(target.charAt(position));
		}
		return buffer.toString();// 반환
	}
}
