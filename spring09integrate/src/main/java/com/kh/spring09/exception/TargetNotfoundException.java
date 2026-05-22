package com.kh.spring09.exception;

import lombok.NoArgsConstructor;

//커스텀 예외 클래스 
//- 데이터가 없는 상황을 강제로 예외로 만들기 위한 클래스
//- checked exception인 RuntimeException을 상속받아서 사용하기 편하게 처리
//- 등록하는것이 아니라 예외가 발생할 때마다 만들어서 사용
@NoArgsConstructor
public class TargetNotfoundException extends RuntimeException{
	//public TargetNotfoundException() {}
	public TargetNotfoundException(String message) {
		super(message);
	}
}




