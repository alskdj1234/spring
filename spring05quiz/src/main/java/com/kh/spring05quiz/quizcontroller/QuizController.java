package com.kh.spring05quiz.quizcontroller;

import java.time.LocalDate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuizController {

    @RequestMapping("/quiz05")
    public String quiz05(
            
            @RequestParam(required = false, defaultValue = "2007") int year) {
        
        int currentYear = LocalDate.now().getYear();
        int age = currentYear - year + 1; 
        
        int price;
        if (age >= 65 || age < 8) {
            price = 0; 
        } else if (age >= 20) {
            price = 1400; 
        } else if (age >= 14) {
            price = 800; 
        } else if (age >= 8) {
            price = 500; 
        } else {
            price = 0;
        }

        return " 나이: " + age + "세, 요금: " + price + "원";
    }
}