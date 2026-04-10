package com.kh.spring02;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@RestController
public class HomeController {
	@RequestMapping("/")
		public String blank() {
			return "KH정보교육원";
		}
	
	@RequestMapping("/dice")
	public String dice() {
		Random r = new Random();
		
		int number = r.nextInt(6)+1;
		
		//String dice = Integer.toString(number);
		
		//return dice;
		return "번호: " + number;
	}
	@RequestMapping("/lotto")
	public String lotto() {
		//Random r = new Random();
		
	//	int number = r.nextInt(45)+1;
		
	//	String lotto = Integer.toString(number);
		
		//return lotto;
		
		List<Integer> list = new ArrayList<>();
		for(int i =1; i<=45;i++) {
			list.add(i);
		}
		Collections.shuffle(list);
		List<Integer>numbers = list.subList(0, 6);
		return numbers.toString();
	}
}
