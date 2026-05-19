package com.kh.spring09;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//무언가 테스트를 하고 싶을 때 어떤 방식으로 코드를 작성해야 하는가?
//- (기존) 화면이 완성되기 전까지 내가 만든게 잘 돌아가는지 알 수 없다
//- (향후) 원하는 도구들을 마음대로 가져다가 테스트하여 정상 작동을 확인할 수 있다

//Spring에서는 JUnit이라는 기술을 이용해서 테스트를 수행할 수 있도록 도와준다
//→ 100% 그대로 가져다 쓰진 않고 스프링의 나머지 구성요소들과 호환되도록 중간라이브러리를 이용
//→ spring-boot-starter-test

@SpringBootTest//나는 테스트 파일이에요~
public class Test01단위테스트 {

	@Test//나는 테스트 메소드에요~ (JUnit 제공) - 0.475s
	public void test() {
		//System.out.println("안녕~");
		
		for(int i=1; i <= 99; i+=2) {
			System.out.println("첫번째 = " + i);
		}
	}
//	@Test//나는 테스트 메소드에요~ (JUnit 제공) - 0.594s
	public void test2() {
		//System.out.println("안녕2~");
		
		for(int i=1; i <= 100; i++) {
			if(i % 2 == 1) {
				System.out.println("두번째 = " + i);
			}
		}
	}
	
//	public static void main(String[] args) {
//		System.out.println("안녕~");
//	}
	
}







