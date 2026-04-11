package com.kh.spring09.dto;

import lombok.Data;

@Data
public class BookDto {
	private int bookId;
	private String bookTitle;
	private String bookAuthor;
	private String bookPublicationDate;
	private long bookPrice;
	private String bookPublisher;
	private int bookPageCount;
	private String bookGenre;

	//조건이나 가상 게터는 직접 해야됨

}
