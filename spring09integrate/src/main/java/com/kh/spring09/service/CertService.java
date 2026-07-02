package com.kh.spring09.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kh.spring09.dao.CertDao;

//인증과 관련된 처리를 하는 서비스
@Service
public class CertService {
	@Autowired
	private CertDao certDao;
	//스케줄링 메소드
	//cron (초 분 시 일 월 요일 연도)
	//*은 every의미 매초 매분 매시 매일 매월 매요일 매년
	// 범위는 ~대신 -을 사용
	// 요일은 월(1)~일(7) 로 작성하거나 영어도 가능 MON ~ SUN
	// 일과 요일은 서로 충돌이 생기는 경우가 많아 무관(?)(any)으로 설정 하는 것이 좋다
	//@Scheduled(fixedRate = 1000L) 1000ms마다 실행
	//@Scheduled(cron ="* * * * * *") 매초
	//@Scheduled(cron ="0 0 * * * * *") 매시 정각 마다
	//@Scheduled(cron ="0 30 * * * *") 매시 30분마다 
	//@Scheduled(cron = "*/5 * * * * *") 5초마다
	//@Scheduled(cron = "0 0 9-18 ? * MON-FRI")
	//@Scheduled(cron = "0 0 9-18 ? * 1-5")
		
//	출근 시점과 퇴근 시점에만 실행되도록 스케줄 설정(콤마 사용)
	//@Scheduled(cron="0 0 9,18 ? * MON-FRI")
	
//  특정 주차를 언급하여 실행 할 수 있음
	//@Scheduled(cron ="0 0 0 ? * 3#2") #+주차 #앞에 숫자만 가능
	//@Scheduled(cron ="0 0 0 ? * 3L") //마지막 주 수요일
//  22일에 가장 가까운 평일(22일이 평일이면 22일 토요일이면 21일 일요일이면 23일)
	@Scheduled(cron ="0 0 * 22W * ?")
	public void clear() {
		System.out.println("청소시작" + LocalDateTime.now());
		certDao.clear(10, 30);
	}
}
