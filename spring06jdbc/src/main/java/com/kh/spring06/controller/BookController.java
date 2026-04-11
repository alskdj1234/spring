package com.kh.spring06.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring06.dao.BookDao;
import com.kh.spring06.dto.BookDto;


@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookDao bookDao;

	@RequestMapping("/insert")
	public String insert(@ModelAttribute BookDto bookDto) {

		bookDao.insert(bookDto);
		return " 등록 완 ";
	}
	

	@RequestMapping("/update")
	public String update(@ModelAttribute BookDto bookDto) {
		boolean success = bookDao.update(bookDto);

		if (success) {
			return "도서 정보 변경이 완료됨";
		} else {
			return "도서 존재 x";
		}

	}
	@RequestMapping("/delete") // 마지막 /<-엔드포인트
	public String delete(@RequestParam int bookId) {
		boolean success = bookDao.delete(bookId);
		if (success) {
			return "도서 정보 변경이 완료됨";
		} else {
			return "도서 존재 x";
		}
	}
	
	@RequestMapping("/list")
	public String list(@RequestParam(required = false) String column, @RequestParam(required = false) String keyword) {

		// 검색어가 있든 없든 상관없이 검색을 수행(내부적으로 구분)
		List<BookDto> list = bookDao.selectList(column, keyword);
		// StringBuffer를 이용해서 문자열로 만들어서 반환
		// 화면으로 바뀌는 부분
		StringBuffer buffer = new StringBuffer();
		buffer.append("결과 수 : " + list.size());
		buffer.append("<br>");
		for (BookDto bookDto : list) {
			buffer.append(bookDto);
			buffer.append("<br>");
			
		}
		return buffer.toString();

	}

	@RequestMapping("/detail")
	public String detail(@RequestParam int bookId) {
		BookDto bookDto =bookDao.selectOne(bookId);
		
		if(bookDto==null) {
			return "도서 존재 x";
		}
		else {
			StringBuffer buffer = new StringBuffer();
			buffer.append(bookDto.getBookId()+"<br>");
			buffer.append("책 명 : "+bookDto.getBookTitle()+"<br>");
			buffer.append("저자 명 : "+bookDto.getBookAuthor()+"<br>");
			buffer.append("장르 명 : "+bookDto.getBookGenre()+"<br>");
			buffer.append("가격 : "+bookDto.getBookPrice()+"<br>");
			buffer.append("출간일 : "+bookDto.getBookPublicationDate()+"<br>");
			buffer.append("페이지 수 : "+bookDto.getBookPageCount()+"<br>");
			buffer.append("출판사 : "+bookDto.getBookPublisher());
			
			return buffer.toString();
		
		}
	
	}
	
	
}
