package com.kh.spring10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring10.dao.BookDao;
import com.kh.spring10.dto.BookDto;

@CrossOrigin
@RestController
@RequestMapping("/api/book")
public class BookRestController {
@Autowired
private BookDao bookDao;
	@GetMapping("/list")
	public List<BookDto> list(){
		return bookDao.selectList();
	}
}
