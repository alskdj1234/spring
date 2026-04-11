package com.kh.spring09.exception;



//커스텀 예외 클래스 데이터가 없는 상황을 강제로 예외로 만들기 위한 클래스
//checked exception인 런타임익셉션을 상속받아서 사용하기 편하게 처리 트라이 캐치 없어도 됨
//등록 하는 것이 아니라 예외가 발생 할 때마다 만들어서 사용

public class TargetNotfoundException extends RuntimeException {
	public TargetNotfoundException() {}
	public TargetNotfoundException(String message) {
		super(message);
		
	}
}
