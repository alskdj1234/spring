package com.kh.spring09.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring09.dao.BookDao;
import com.kh.spring09.dto.BookDto;
import com.kh.spring09.exception.TargetNotfoundException;
import com.kh.spring09.service.AttachService;


@Controller
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookDao bookDao;

	@Autowired
	private AttachService attachService;
	
	@GetMapping("/insert")
	public String insert() {

		
		return "book/insert";
	}
	@PostMapping("/insert")
	public String insert(@ModelAttribute BookDto bookDto, @RequestParam MultipartFile attach ) throws IllegalStateException, IOException {
		int bookId= bookDao.sequence();
		bookDto.setBookId(bookId);
		
		bookDao.insert(bookDto);
		
		if(!attach.isEmpty()) {
			int attachNo = attachService.save(attach);
			bookDao.connect(bookId, attachNo);
		}
		
		return "redirect:./insertComplete";
	}
	
	@RequestMapping("/insertComplete")
	public String insertComplete() {

		
		return "book/insertComplete";
	}
	

	@RequestMapping("/list")
	public String list (Model model, @RequestParam(required=false) String keyword){
		
		if(keyword!=null) {
			List<BookDto> listByBookTitle = bookDao.selectList("book_title",keyword);
			List<BookDto> listByBookAuthor = bookDao.selectList("book_author",keyword);
			List<BookDto> listByBookPublicationDate = bookDao.selectList("book_pulication_date",keyword);
			List<BookDto> listByBookPublisher = bookDao.selectList("book_publisher",keyword);
			List<BookDto> listByBookGenre = bookDao.selectList("book_genre",keyword);
			model.addAttribute("listByBookTitle", listByBookTitle);
			model.addAttribute("listByBookAuthor", listByBookAuthor);
			model.addAttribute("listByBookGenre", listByBookGenre);
			model.addAttribute("listByBookPublicationDate", listByBookPublicationDate);
			model.addAttribute("listByBookPublisher", listByBookPublisher);
		}
	
		
		return"book/list";
	}
	
	@RequestMapping("/detail")
	public String detail (Model model, @RequestParam int bookId) {
		BookDto bookDto = bookDao.selectOne(bookId);
		if(bookDto==null)throw new TargetNotfoundException("도서가 존재하지 않아요");
		model.addAttribute("bookDto", bookDto);
		return "book/detail";
	}
	
	@RequestMapping("/delete")
	public String delete (Model model, @RequestParam int bookId) {
		BookDto bookDto = bookDao.selectOne(bookId);
		if(bookDto==null)throw new TargetNotfoundException("도서가 존재하지 않아요");
		bookDao.delete(bookId);
		return "redirect:./list";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam int bookId, Model model) {
		BookDto bookDto = bookDao.selectOne(bookId);
		if(bookDto==null)throw new TargetNotfoundException("도서가 존재하지 않아요");
		
		model.addAttribute("bookDto", bookDto);
		return "book/edit";
	}
	
}
