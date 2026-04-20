package com.kh.spring09.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring09.dto.BoardDto;
import com.kh.spring09.mapper.BoardMapper;

@Repository
public class BoardDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private BoardMapper boardMapper;
	
	//검색 허용할 컬럼
	private Set<String> allowColumns = Set.of("board_writer", "board_title");
	
	//목록 및 조회
	public List<BoardDto> selectList() {
		String sql = "select * from board_list order by board_no desc";
		return jdbcTemplate.query(sql, boardMapper);
	}
	public List<BoardDto> selectList(String column, String keyword) {
		if(column == null || keyword == null) return selectList();
		if(!allowColumns.contains(column)) return selectList();
		
		String sql = "select * from board_list "
					+ "where instr("+column+", ?) > 0 "
					+ "order by board_no desc";
		Object[] params = { keyword };
		return jdbcTemplate.query(sql, boardMapper, params);
	}
	
	//상세
	public BoardDto selectOne(long boardNo) {
		String sql = "select * from board where board_no = ?";
		Object[] params = { boardNo };
		List<BoardDto> list = jdbcTemplate.query(sql, boardMapper, params);
		return list.isEmpty() ? null : list.get(0);
	}
	
	//[변형] 이전글 정보
	public BoardDto selectPreviousOne(long boardNo) {
		String sql = "select * from board where board_no = ("
						+ "select max(board_no) from board where board_no < ?"
					+ ")";
		Object[] params = { boardNo };
		List<BoardDto> list = jdbcTemplate.query(sql, boardMapper, params);
		return list.isEmpty() ? null : list.get(0);
	}
	//[변형] 다음글 정보
	public BoardDto selectNextOne(long boardNo) {
		String sql = "select * from board where board_no = ("
						+ "select min(board_no) from board where board_no > ?"
					+ ")";
		Object[] params = { boardNo };
		List<BoardDto> list = jdbcTemplate.query(sql, boardMapper, params);
		return list.isEmpty() ? null : list.get(0);
	}

	//달라진 등록
	// 기존 : 시퀀스 번호를 생성하면서 등록
	// 변경 : 시퀀스 번호 먼저 생성 후 등록을 나중에 -> 자바가 등록될 대상의 기본키를 알 수 있도록
	public long sequence() {
		String sql = "select board_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, long.class);
		//return jdbcTemplate.queryForObject(sql, Long.class);(null 허용)

	}
	public void insert(BoardDto boardDto) {
		String sql = "insert into board(board_no, board_writer, "
				+ "board_head, board_title, board_content)"
				+ "values(?, ?, ?, ?, ?)";
		Object[] params = {
				boardDto.getBoardNo(), boardDto.getBoardWriter(),
				boardDto.getBoardHead(),boardDto.getBoardTitle(),
				boardDto.getBoardContent()
		};
		jdbcTemplate.update(sql, params);
				
	}
	
	public boolean delete (long boardNo) {
		String sql = "delete board where board_no = ?";
				Object [] params = {boardNo};
		return jdbcTemplate.update(sql, params)>0;
	}
	
	public boolean update (BoardDto boardDto) {
		String sql = "update board set board_title=?, board_head=?, board_content=?, board_etime=systimestamp "
				+ "where board_no = ?";
			Object [] params= {boardDto.getBoardTitle(), boardDto.getBoardHead()
					, boardDto.getBoardContent(), boardDto.getBoardNo()};
			
			return jdbcTemplate.update(sql, params)>0;
	}
	
	public List<BoardDto> selectNoticeList() {
		String sql ="select * from board_list where board_head ='공지' order by board_no desc";
		return jdbcTemplate.query(sql, boardMapper);
	}
}






