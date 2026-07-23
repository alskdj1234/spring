package com.kh.spring11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring11.annotation.CommonsApiResponse;
import com.kh.spring11.dao.BookDao;
import com.kh.spring11.dto.BookDto;
import com.kh.spring11.error.TargetNotfoundException;
import com.kh.spring11.vo.BookPatchVO;
import com.kh.spring11.vo.ListRequestVO;
import com.kh.spring11.vo.ListVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "도서 처리 시스템", description = "도서 정보에 대해 DB처리를 수행하는 컨트롤러입니다")
@CommonsApiResponse

@RestController
@RequestMapping("/api/book")
public class BookRestController {
	@Autowired
	private BookDao bookDao;
	
	//CRUD 처리 매핑
	//1. 기본적으로 자원관련된 CRUD는 주소를 최소화하고 전송방식으로 구분
	//2. 쿼리스트링은 선택적 데이터에 사용 (옵션)
	//3. 경로변수는 반드시 필요한 데이터에 사용 (필수)
	//4. 전송 데이터가 많으면 조회라도 POST 등을 사용할 수 있다
	
	@ApiResponse(responseCode = "200", description = "등록 성공")
	//@PostMapping("/")
	@PostMapping(value = "/", produces = "application/json")
	public BookDto insert(@RequestBody BookDto bookDto) {
		int bookId = bookDao.sequence();
		bookDto.setBookId(bookId);
		bookDao.insert(bookDto);
		return bookDto;
	}
	
	@GetMapping("/")
	public List<BookDto> list() {
		return bookDao.selectList();
	}
	
	@GetMapping("/{bookId}")
	public BookDto find(@PathVariable int bookId) {
		BookDto bookDto = bookDao.selectOne(bookId);
		if(bookDto == null) throw new TargetNotfoundException();
		return bookDto;
	}
	
	@GetMapping("/lastBookId/{lastBookId}/size/{size}")
	public ListVO listForReact1(
			@PathVariable int lastBookId, 
			@PathVariable int size) {
		List<BookDto> list = bookDao.selectListForReact(lastBookId, size);
		int count = bookDao.countForReact(lastBookId);
		return ListVO.builder()
				.list(list)
				.last(size >= count)
			.build();
	}
	@PostMapping("/list-more")
	public ListVO listForReact2(@RequestBody ListRequestVO vo) {
		List<BookDto> list = bookDao.selectListForReact(vo);
		int count = bookDao.countForReact(vo);
		System.out.println("count = " + count);
		return ListVO.builder()
					.list(list)
					.last(vo.getSize() >= count)
				.build();
	}
	
	@PutMapping("/{bookId}")
	public BookDto updateAll(@PathVariable int bookId,
							@RequestBody BookDto bookDto) {
		BookDto findBookDto = bookDao.selectOne(bookId);
		if(findBookDto == null) throw new TargetNotfoundException();
		
		findBookDto.setBookTitle(bookDto.getBookTitle());
		findBookDto.setBookAuthor(bookDto.getBookAuthor());
		findBookDto.setBookPublisher(bookDto.getBookPublisher());
		findBookDto.setBookPublicationDate(bookDto.getBookPublicationDate());
		findBookDto.setBookPrice(bookDto.getBookPrice());
		findBookDto.setBookPageCount(bookDto.getBookPageCount());
		findBookDto.setBookGenre(bookDto.getBookGenre());
		bookDao.update(findBookDto);
		
		return findBookDto;
	}
	@PatchMapping("/{bookId}")
	public BookDto updateUnit(@PathVariable int bookId,
							@RequestBody BookPatchVO bookPatchVO) {
		BookDto findBookDto = bookDao.selectOne(bookId);
		if(findBookDto == null) throw new TargetNotfoundException();
		
		if(bookPatchVO.getBookTitle() != null)
			findBookDto.setBookTitle(bookPatchVO.getBookTitle());
		if(bookPatchVO.getBookAuthor() != null)
			findBookDto.setBookAuthor(bookPatchVO.getBookAuthor());
		if(bookPatchVO.getBookPublisher() != null)
			findBookDto.setBookPublisher(bookPatchVO.getBookPublisher());
		if(bookPatchVO.getBookPublicationDate() != null)
			findBookDto.setBookPublicationDate(bookPatchVO.getBookPublicationDate());
		if(bookPatchVO.getBookPrice() != null)
			findBookDto.setBookPrice(bookPatchVO.getBookPrice());
		if(bookPatchVO.getBookPageCount() != null)
			findBookDto.setBookPageCount(bookPatchVO.getBookPageCount());
		if(bookPatchVO.getBookGenre() != null)
			findBookDto.setBookGenre(bookPatchVO.getBookGenre());
		bookDao.update(findBookDto);
		
		return findBookDto;
	}
	
	@DeleteMapping("/{bookId}")
	public BookDto delete(@PathVariable int bookId) {
		BookDto bookDto = bookDao.selectOne(bookId);
		if(bookDto == null) throw new TargetNotfoundException();
		bookDao.delete(bookId);
		return bookDto;
	}
	
}









