package com.kh.spring11.service;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

//한글 처리를 도와주는 코드를 가진 서비스 (특히 초/중/종성 분리 및 병합, 검색)
//한글의 첫 글자 		= 가(44032) = ㄱ + ㅏ + (없음)
//한글의 마지막 글자 	= 힣(55203) = ㅎ + ㅣ + ㅎ
//한글은 종성 → 중성 → 초성이 변하는 순으로 배치됨
//패턴을 알아야 분해를 할 수 있다 (각 파츠의 개수를 알아야함)
@Slf4j
@Service
public class KoreanService {
	//초성 (19개)
	private String[] cho = {
		"ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ",
		"ㄹ", "ㅁ", "ㅂ", "ㅃ", "ㅅ",
		"ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ",
		"ㅋ", "ㅌ", "ㅍ", "ㅎ"
	};
	//중성 (21개)
	private String[] jung = {
		"ㅏ", "ㅐ", "ㅑ", "ㅒ",
		"ㅓ", "ㅔ", "ㅕ", "ㅖ",
		"ㅗ", "ㅘ", "ㅙ", "ㅚ",
		"ㅛ", "ㅜ", "ㅝ", "ㅞ",
		"ㅟ", "ㅠ", "ㅡ", "ㅢ", "ㅣ"
	};
	//종성 (28개)
	private String[] jong = {
		"", 
		"ㄱ", "ㄲ", "ㄳ", "ㄴ", 
		"ㄵ", "ㄶ", "ㄷ", "ㄹ",
		"ㄺ", "ㄻ", "ㄼ", "ㄽ", 
		"ㄾ", "ㄿ", "ㅀ", "ㅁ", 
		"ㅂ", "ㅄ", "ㅅ", "ㅆ", 
		"ㅇ", "ㅈ", "ㅊ", "ㅋ", 
		"ㅌ", "ㅍ", "ㅎ"
	};
	
	public String disassemble(String origin) {
		if(origin == null || origin.isEmpty()) return "";
		
		//계산해야되니 char로 변경
		StringBuilder builder = new StringBuilder();
		for(int i=0; i < origin.length(); i++) {
			char ch = origin.charAt(i);
			int seq = ch - '가';
			int jongSeq = seq % 28;//종성은 28개씩 무한반복
			int jungSeq = seq / 28 % 21;//중성은 21개씩 반복 (종성이 변해야 변함)
			int choSeq = seq / 28 / 21;//초성은 중성/종성이 다 변해야 변함 
			
			builder.append(cho[choSeq]);
			builder.append(jung[jungSeq]);
			builder.append(jong[jongSeq]);
		}
		return builder.toString();
	}
	
	public boolean isMatch(String target, String keyword) {
		String target2 = disassemble(target);
		String keyword2 = disassemble(keyword);
		log.debug("target = {}, keyword = {}", target, keyword);
		log.debug("target2 = {}, keyword2 = {}", target2, keyword2);
		return target2.startsWith(keyword2);
	}
}






