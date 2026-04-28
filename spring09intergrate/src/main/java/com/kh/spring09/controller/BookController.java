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
@RequestMapping("/book")//무조건 RequestMapping만 가능(GET/POST 선택불가)
public class BookController {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private AttachService attachService;
	
	//등록
	@GetMapping("/insert")
	public String insert() {
		return "book/insert";
	}
	@PostMapping("/insert")
	public String insert(@ModelAttribute BookDto bookDto, 
						@RequestParam MultipartFile attach) throws IllegalStateException, IOException {
		
		int bookId = bookDao.sequence();
		bookDto.setBookId(bookId);
		bookDao.insert(bookDto);//도서저장
		
		if(!attach.isEmpty()) {
			int attachNo = attachService.save(attach);//파일저장
			bookDao.connect(bookId, attachNo);//연결
		}
		
		//return "redirect:/book/insertComplete";
		return "redirect:./insertComplete";
	}
	@RequestMapping("/insertComplete")
	public String insertComplete() {
		return "book/insertComplete";
	}
	
	
//	@RequestMapping("/list")
//	public String list(Model model,
//			@RequestParam(required = false) String column, 
//			@RequestParam(required = false) String keyword) {

	@RequestMapping("/list")
	public String list(Model model,
			@RequestParam(required = false) String keyword) {
		if(keyword != null) {
			List<BookDto> listByBookTitle = bookDao.selectList("book_title", keyword);
			List<BookDto> listByBookAuthor = bookDao.selectList("book_author", keyword);
			List<BookDto> listByBookPublicationDate = bookDao.selectList("book_publication_date", keyword);
			List<BookDto> listByBookPublisher = bookDao.selectList("book_publisher", keyword);
			List<BookDto> listByBookGenre = bookDao.selectList("book_genre", keyword);
			model.addAttribute("listByBookTitle", listByBookTitle);
			model.addAttribute("listByBookAuthor", listByBookAuthor);
			model.addAttribute("listByBookPublicationDate", listByBookPublicationDate);
			model.addAttribute("listByBookPublisher", listByBookPublisher);
			model.addAttribute("listByBookGenre", listByBookGenre);
		}
		return "book/list";
	}
	
	@RequestMapping("/detail")
	public String detail(@RequestParam int bookId, Model model) {
		BookDto bookDto = bookDao.selectOne(bookId);
		if(bookDto == null) throw new TargetNotfoundException("존재하지 않는 도서");
		
		model.addAttribute("bookDto", bookDto);
		return "book/detail";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam int bookId) {
		BookDto bookDto = bookDao.selectOne(bookId);
		if(bookDto == null) throw new TargetNotfoundException("존재하지 않는 도서");
		
		try {
			int attachNo = bookDao.searchCover(bookId);
			attachService.delete(attachNo);//Attach 테이블 데이터 삭제 + 파일삭제
		}
		catch(Exception e) {}
		
		bookDao.delete(bookId);
		return "redirect:./list";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam int bookId, Model model) {
		BookDto bookDto = bookDao.selectOne(bookId);
		if(bookDto == null) throw new TargetNotfoundException("존재하지 않는 도서");
		
		model.addAttribute("bookDto", bookDto);
		return "book/edit";
	}
	@PostMapping("/edit")
	public String edit(@ModelAttribute BookDto bookDto,
						@RequestParam MultipartFile attach) throws IllegalStateException, IOException {
		bookDao.update(bookDto);
		
		//첨부파일이 있다면 기존것을 제거하고 신규 등록
		if(!attach.isEmpty()) {
			//삭제
			try {
				int attachNo = bookDao.searchCover(bookDto.getBookId());
				attachService.delete(attachNo);//지워
			} catch(Exception e) {}
			
			//등록
			int attachNo = attachService.save(attach);//새로 저장해!
			bookDao.connect(bookDto.getBookId(), attachNo);//연결도 시켜놔
		} 
		
		return "redirect:./detail?bookId="+bookDto.getBookId();
	}
	
	@RequestMapping("/cover")
	public String cover(@RequestParam int bookId) {
		try {
			int attachNo = bookDao.searchCover(bookId);
			return "redirect:/download/modern?attachNo="+attachNo;
		}
		catch(Exception e) {
			return "redirect:/images/no_image.png";
		}
	}
}









