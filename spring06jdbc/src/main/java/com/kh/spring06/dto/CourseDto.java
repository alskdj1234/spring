package com.kh.spring06.dto;

public class CourseDto {
	private int courseNo;
	private String courseName;
	private String category;
	private int lectureTime;
	private long fee;
	private String classType;
	public CourseDto() {
		super();
	}
	public int getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(int courseNo) {
		this.courseNo = courseNo;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getLectureTime() {
		return lectureTime;
	}
	public void setLectureTime(int lectureTime) {
		this.lectureTime = lectureTime;
	}
	public long getFee() {
		return fee;
	}
	public void setFee(long fee) {
		this.fee = fee;
	}
	public String getClassType() {
		return classType;
	}
	public void setClassType(String classType) {
		this.classType = classType;
	}
	@Override
	public String toString() {
		return "CourseDto [courseNo=" + courseNo + ", courseName=" + courseName + ", category=" + category
				+ ", lectureTime=" + lectureTime + ", fee=" + fee + ", classType=" + classType + "]";
	}
	
}
