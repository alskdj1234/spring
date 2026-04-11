package com.kh.spring06.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.stereotype.Repository;

import com.kh.spring06.dto.BookDto;
import com.kh.spring06.mapper.BookMapper;
@Repository
public class BookDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private BookMapper bookMapper;
	
	public void insert (BookDto bookDto) {
	 
	    
	    
	    String sql = "INSERT INTO book ("
	            + "book_id, book_title, book_author, book_publication_date, "
	            + "book_price, book_publisher, book_page_count, book_genre"
	            + ") VALUES (book_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)"; 

	    
	    Object[] params = {
	        bookDto.getBookTitle(),            // 첫 번째 ?
	        bookDto.getBookAuthor(),           // 두 번째 ?
	        bookDto.getBookPublicationDate(),  // 세 번째 ?
	        bookDto.getBookPrice(),            // 네 번째 ?
	        bookDto.getBookPublisher(),        // 다섯 번째 ?
	        bookDto.getBookPageCount(),        // 여섯 번째 ?
	        bookDto.getBookGenre()             // 일곱 번째 ?
	    };

	    jdbcTemplate.update(sql, params);
	}

	public boolean update(BookDto bookDto) {
		
		String sql = "update book "
				+ "set book_title=?, book_author=?, book_publication_date=?, book_price=?, book_publisher=?, book_page_count=?, book_genre=? "
				+ "where book_id=?";
				
		Object[] params = {bookDto.getBookTitle(),bookDto.getBookAuthor(),bookDto.getBookPublicationDate(),bookDto.getBookPrice(),bookDto.getBookPublisher(),
						bookDto.getBookPageCount(),bookDto.getBookGenre(),bookDto.getBookId()
		};
		
		return jdbcTemplate.update(sql, params) > 0;
	}

	public boolean delete(int bookId) {
		
		String sql = "delete from book where book_id=? ";
		Object[]params= {bookId};
		return jdbcTemplate.update(sql,params)>0;
	}
	
	public List<BookDto> selectList(){
		
		String sql = "select * from book order by book_id asc";
		
		return jdbcTemplate.query(sql, bookMapper);
	}

	public List<BookDto> selectList(String column, String keyword){
		
		if(column == null|| keyword == null) {
			return selectList();
		}
		
		Set<String>allowSet =Set.of("book_title","book_author","book_publisher","book_genre");
		//배열로 만들고 어레이스.바이너리서치하면 -1위치 되는거로 필터링 가능
		if(!allowSet.contains(column)) return List.of();
		String sql = "select * from book where instr("+column+",?)>0 order by book_id asc";
		Object[] params = {keyword};
	
		return jdbcTemplate.query(sql, bookMapper,params);
	}
	
	public BookDto selectOne(int bookId) {

		String sql = "select * from book where book_id = ? ";
		
		Object[] params = {bookId};
		List<BookDto> list =jdbcTemplate.query(sql,bookMapper,params);
		return list.isEmpty()?null:list.get(0);
	}

}
