package com.kh.spring11.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "도서 등록 정보")
public class BookInsertVO {
	@Schema(description = "도서 제목", example = "정보처리기사 필기")
	private String bookTitle;

	@Schema(description = "저자명", example = "홍길동")
	private String bookAuthor; // 저자 변수 새로 추가

	@Schema(description = "발간일", example = "2026-07-08")
	private String bookPublicationDate; // 발간일은 날짜니까 String

	@Schema(description = "판매가", examples = {"100000", "200000", "300000"})
	private int bookPrice; // 판매가는 숫자니까 int

	@Schema(description = "페이지수", examples = {"100", "200", "300"})
	private int bookPageCount; // 페이지수도 숫자니까 int

	@Schema(description = "출판사", examples = {"좋은글", "나쁜글"})
	private String bookPublisher; // 마지막에 변수 선언 제대로 닫아줌
	
	@Schema(description = "장르", examples = {"판타지","인문"})
	private String bookGenre;
}
