package com.kh.spring11.mybatis;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class Test08한글가지고놀기 {

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
	
	@Test
	public void test() {
		String text = "황";
		
		//계산해야되니 char로 변경
		char ch = text.charAt(0);
		log.debug("ch = {}, {}", ch, (int)ch);
		
		int seq = ch - '가';
		log.debug("순서 = {}", seq);
		
		int jongSeq = seq % 28;//종성은 28개씩 무한반복
		int jungSeq = seq / 28 % 21;//중성은 21개씩 반복 (종성이 변해야 변함)
		int choSeq = seq / 28 / 21;//초성은 중성/종성이 다 변해야 변함 
		log.debug("초 = {}, 중 = {}, 종 = {}", choSeq, jungSeq, jongSeq);
		log.debug("초 = {}, 중 = {}, 종 = {}", cho[choSeq], jung[jungSeq], jong[jongSeq]);
		
		StringBuffer buffer = new StringBuffer();
		buffer.append(cho[choSeq]);
		buffer.append(jung[jungSeq]);
		buffer.append(jong[jongSeq]);
		log.debug("해체 = {}", buffer.toString());
	}
	
}
