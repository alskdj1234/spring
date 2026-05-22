package com.kh.spring09.dto;

import java.util.Objects;

import lombok.Data;

@Data
public class BookDto {
	private int bookId;
	private String bookTitle;
	private String bookAuthor;
	private String bookPublicationDate;
	private int bookPrice;
	private int bookPageCount;
	private String bookPublisher;
	private String bookGenre;
	//표시용 메소드 추가
	public String getBookAuthorString() {
		if(bookAuthor == null) return "미상";
		return bookAuthor;
	}
	//표시용 메소드 추가
	public String getBookPublicationDateString() {
		if(bookPublicationDate == null) return "알 수 없음";
		return bookPublicationDate;
	}
	//표시용 메소드 추가
	public String getBookPublisherString() {
		if(bookPublisher == null) return "";
		return bookPublisher;
	}
}
