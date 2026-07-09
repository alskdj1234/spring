package com.kh.spring10.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.spring10.dto.BookDto;

@Component//외부도구없이 단위작업을 수행하는 도구
public class BookMapper implements RowMapper<BookDto>{
	@Override
	public BookDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		BookDto bookDto = new BookDto();
		bookDto.setBookId(rs.getInt("book_id"));
		bookDto.setBookTitle(rs.getString("book_title"));
		bookDto.setBookAuthor(rs.getString("book_author"));
		bookDto.setBookPublicationDate(rs.getString("book_publication_date"));
		bookDto.setBookPrice(rs.getInt("book_price"));
		bookDto.setBookPageCount(rs.getInt("book_page_count"));
		bookDto.setBookPublisher(rs.getString("book_publisher"));
		bookDto.setBookGenre(rs.getString("book_genre"));
		return bookDto;
	}
}





