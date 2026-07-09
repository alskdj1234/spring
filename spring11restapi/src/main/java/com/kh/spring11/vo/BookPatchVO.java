package com.kh.spring11.vo;

import lombok.Data;

@Data
public class BookPatchVO {
	
	private String bookTitle;
	private String bookAuthor;
	private String bookPublicationDate;
	private Integer bookPrice;
	private Integer bookPageCount;
	private String bookPublisher;
	private String bookGenre;
}
