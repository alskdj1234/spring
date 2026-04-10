package com.kh.spring05quiz.quizcontroller;

import java.text.DecimalFormat;
import java.text.Format;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//spring->>스프링 컨테이너에 등록을 해야 쓸 수 있다.(제어반전)
@RestController
public class QuizController {
	@RequestMapping("/quiz01")
	public String quiz01(@RequestParam int year) {
		int current = LocalDate.now().getYear();
		int koreanAge = current - year + 1;
		return "한국나이 : " + koreanAge + "세";
	}

	@RequestMapping("/quiz02")
	public String quiz02(@RequestParam double cm, @RequestParam double kg) {
		double m = cm / 100f;
		double bmi = kg / Math.pow(m, 2);
		Format f = new DecimalFormat("#,##0.00");
		return "bmi : " + f.format(bmi);
	}
// @RequestMapping("/quiz03")
// public String quiz03(@RequestParam String begin , @RequestParam String end) {
//	LocalDate b = LocalDate.parse(begin);
//	LocalDate e = LocalDate.parse(end);
//	
//	long days = ChronoUnit.DAYS.between(b, e);
//	return "기간 : " + days;
// }

	@RequestMapping("/quiz03")
	// required가 false면 없어도 실행되고 null로 처리
	// null로 처리되는게 싫으면 defaultValue로 기본값을 지정할 수 있음.
	public String quiz03(@RequestParam String begin, @RequestParam(required = false) String end) {
		LocalDate b = LocalDate.parse(begin);
		LocalDate e = end == null ? LocalDate.now() : LocalDate.parse(end);

		long days = ChronoUnit.DAYS.between(b, e);
		return "기간 : " + days;
	}

	@RequestMapping("/quiz04")
	public String quiz04(@RequestParam int korean, @RequestParam int english, @RequestParam int math) {
		int total = korean + english + math;
		double average = total / 3d;
		Format f = new DecimalFormat("#,##0.00");
		// <br> html에서의 줄바꿈 명령
		return "총점 : " + f.format(total) + "<br>" + "평균 : " + f.format(average);
	}

	@RequestMapping("/quiz05")
 public String quiz05(@RequestParam(required = false, defaultValue = "0") int year) {
	int current = LocalDate.now().getYear();
	int age = current - year +1;
	int price;
	if(year == 0 || age >= 20 && age < 65) {
		price = 1550;
	}
	else if(age >= 14&&age<20) {
		price = 900 ;
	
	}
	else if (age >= 8 && age < 13) {
		price =550;
	}
	else {
		price = 0;
	}
	return "요금 : " + price + "원";
	}
}
