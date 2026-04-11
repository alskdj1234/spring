package com.kh.spring04.controller;

public class Student {
	private int korean;
	private int english;
	private int math;
	
	public Student() {
		super();
	}
	//가상의 게터
	
	public int getTotal() {
		return korean+english+math;
	}
	
	public double getAverage() {
		return getTotal()/3d;
	}
	@Override
	public String toString() {
		return "Student [korean=" + korean + ", english=" + english + ", math=" + math + "]";
	}
	public int getKorean() {
		return korean;
	}
	public void setKorean(int korean) {
		this.korean = korean;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	
	
	
}
