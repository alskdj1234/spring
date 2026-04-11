package com.kh.spring06.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.kh.spring06.dto.BookDto;
@Component
public class BookMapper implements RowMapper<BookDto> {

	

	@Override
	public BookDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		BookDto bookDto = new BookDto();
		bookDto.setBookId(rs.getInt("book_id"));
		bookDto.setBookTitle(rs.getString("book_title"));
		bookDto.setBookAuthor(rs.getString("book_author"));
		bookDto.setBookPrice(rs.getLong("book_price"));
		bookDto.setBookGenre(rs.getString("book_genre"));
		bookDto.setBookPageCount(rs.getInt("book_page_count"));
		bookDto.setBookPublicationDate(rs.getString("book_publication_date"));
		bookDto.setBookPublisher(rs.getString("book_publisher"));
		
		return bookDto;
	}

}
