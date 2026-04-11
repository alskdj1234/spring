package com.kh.spring04.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//메인페이지 및 최초방문용 페이지들을 보관 하는 컨트롤러
//스프링은 등록 되지 않은 건 도와주지 않는다
//제1규칙: (제어반전)
//등록은 @를 이용한 어노테이션으로 설정
@RestController//바로 아래있는 녀석을 RestController로 등록 해주세요
public class HomeController {
	
	@RequestMapping("/")//기본 주소는 가리고 /로 들어오면 이 메소드를 실행 해서 나온 거 보여줘
	public String home() {
		return "집 가고 싶어요";
	}

	//똑같은 주소가 2개면 켜져있어도 에러가 뜬다
//	@RequestMapping("/")//기본 주소는 가리고 /로 들어오면 이 메소드를 실행 해서 나온 거 보여줘
//	public String copy() {
//		return "집좀";
//	}
	// 위에꺼도 안됨
	
	//주소 외에도 ? 뒤에 있는 데이터를 받을 수 있다. 쿼리 파라미터
	//매개변수를 만들면 쿼리 파라미터가 있어야 된다는 뜻
	//매개변수 -> 유저의 입력
	//리턴 값 -> 사이트가 출력해주는 값
	@RequestMapping("/test")
	//어노테이션으로 명확하게 의미 설정 가능
	public String test(@RequestParam int a,@RequestParam int b) {
		int c = a+b;
		return "테스트 결과 : " + c;
		
=======
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//메인페이지 및 최초방문용 페이지들을 보관 하는 컨트롤러
//스프링은 등록 되지 않은 건 도와주지 않는다
//제1규칙: (제어반전)
//등록은 @를 이용한 어노테이션으로 설정
@RestController//바로 아래있는 녀석을 RestController로 등록 해주세요
public class HomeController {
	
	@RequestMapping("/")//기본 주소는 가리고 /로 들어오면 이 메소드를 실행 해서 나온 거 보여줘
	public String home() {
		return "집 가고 싶어요";
	}

	//똑같은 주소가 2개면 켜져있어도 에러가 뜬다
//	@RequestMapping("/")//기본 주소는 가리고 /로 들어오면 이 메소드를 실행 해서 나온 거 보여줘
//	public String copy() {
//		return "집좀";
//	}
	// 위에꺼도 안됨
	
	//주소 외에도 ? 뒤에 있는 데이터를 받을 수 있다. 쿼리 파라미터
	//매개변수를 만들면 쿼리 파라미터가 있어야 된다는 뜻
	//매개변수 -> 유저의 입력
	//리턴 값 -> 사이트가 출력해주는 값
	@RequestMapping("/test")
	//어노테이션으로 명확하게 의미 설정 가능
	public String test(@RequestParam int a,@RequestParam int b) {
		int c = a+b;
		return "테스트 결과 : " + c;
		
	}
	
	//국어 영어 수학 점수를 입력 받아 평균을 구해 출력하세요 라고 했을 때
	//국어 영어 수학 점수를 한 번에 받을 순 없을까
	//지금 배운대로라면 @RequestParam을 3번 사용하면 되는데 이게 맞을까?
	//연관이 있는 데이터를 객체 단위로 처리하기 위해 Student 클래스로 처리
	//이 때는 ModelAttributes를 사용(필드의 이름에 맞는 파라미터를 자동 수신),(파라미터를 줄일 수는 없다.)
	//단점은 파라미터가 없어도 객체가 생기면서 데이터를 수신함.(필수라면 추가검사가 필요)
	
	@RequestMapping("/score")
	public String score(@ModelAttribute Student student) {
		return "평균 : " + student.getAverage();
>>>>>>> branch 'main' of https://github.com/alskdj1234/spring.git
	}
}
