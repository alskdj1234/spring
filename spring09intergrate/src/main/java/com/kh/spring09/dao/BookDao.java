package com.kh.spring09.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring09.dto.BookDto;
import com.kh.spring09.mapper.BookMapper;

@Repository//파일orDB 제어도구
public class BookDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private BookMapper bookMapper;
	
	//내가 만드는건 Autowired를 쓰면 안된다
	private Set<String> allowColumns = Set.of(
		"book_title", "book_author", "book_publication_date",
		"book_publisher", "book_genre"
	);
	
	//등록
	public int sequence() {
		String sql = "select book_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	public void insert(BookDto bookDto) {
		String sql = "insert into book("
						+ "book_id, book_title, book_author,"
						+ "book_publication_date, book_price, book_publisher,"
						+ "book_page_count, book_genre"
					+ ") values(?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] params = {
			bookDto.getBookId(),
			bookDto.getBookTitle(), bookDto.getBookAuthor(),
			bookDto.getBookPublicationDate(), bookDto.getBookPrice(),
			bookDto.getBookPublisher(), bookDto.getBookPageCount(),
			bookDto.getBookGenre()
		};
		jdbcTemplate.update(sql, params);
	}
	
	//수정
	public boolean update(BookDto bookDto) {
		String sql = "update book "
					+ "set book_title=?, book_author=?,"
						+ "book_publication_date=?, book_price=?,"
						+ "book_page_count=?, book_publisher=?,"
						+ "book_genre=? "
					+ "where book_id=?";
		Object[] params = {
			bookDto.getBookTitle(), bookDto.getBookAuthor(),
			bookDto.getBookPublicationDate(), bookDto.getBookPrice(),
			bookDto.getBookPageCount(), bookDto.getBookPublisher(),
			bookDto.getBookGenre(), bookDto.getBookId()
		};
		return jdbcTemplate.update(sql, params) > 0;
	}
	
	//삭제
	public boolean delete(int bookId) {
		String sql = "delete book where book_id=?";
		Object[] params = {bookId};
		return jdbcTemplate.update(sql, params) > 0;
	}
	
	//목록 및 검색
	public List<BookDto> selectList(){
		String sql = "select * from book order by book_id asc";
		return jdbcTemplate.query(sql, bookMapper);
	}
	public List<BookDto> selectList(String column, String keyword){
		if(column == null || keyword == null) return selectList();
		if(column.isBlank() || keyword.isBlank()) return selectList();
		if(!allowColumns.contains(column)) return List.of();
		
		String sql = "select * from book "
					+ "where instr("+column+", ?) > 0 "
					+ "order by book_id asc";
		Object[] params = {keyword};
		return jdbcTemplate.query(sql, bookMapper, params);
	}
	
	//상세
	public BookDto selectOne(int bookId) {
		String sql = "select * from book where book_id=?";
		Object[] params = { bookId };
		List<BookDto> list = jdbcTemplate.query(sql, bookMapper, params);
		return list.isEmpty() ? null : list.get(0);
	}
	
	//연결
	public void connect(int bookId, int attachNo) {
		String sql = "insert into book_cover(book_id, attach_no) values(?, ?)";
		Object[] params = {bookId, attachNo};
		jdbcTemplate.update(sql, params);
	}
	
	//도서번호로 표지찾기
	public int searchCover(int bookId) {
		String sql = "select attach_no from book_cover where book_id=?";
		Object[] params = { bookId };
		return jdbcTemplate.queryForObject(sql, int.class, params);
	}
}











