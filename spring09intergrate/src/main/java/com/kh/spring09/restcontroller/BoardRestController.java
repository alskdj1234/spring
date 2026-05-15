package com.kh.spring09.restcontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring09.dao.BoardLikeDao;

import jakarta.servlet.http.HttpSession;

//게시글과 관련된 비동기 통신을 처리하는 도구(board, board_like, board_read...)
@RestController
@RequestMapping("/rest/board")
public class BoardRestController {
	@Autowired
	private BoardLikeDao boardLikeDao;
	
	//최초 접속시 좋아요 여부, 현재 글의 좋아요 개수를 구해주는 매핑
	@PostMapping("/like-check")
	public Map<String, Object> likeCheck(@RequestParam int boardNo, HttpSession session){
		String loginId = (String)session.getAttribute("loginId");
		boolean action = boardLikeDao.check(loginId, boardNo);//좋아요 여부 계산
		
		int count=boardLikeDao.count(boardNo);
		
		return Map.of("action", action, "count", count);
	}
	//좋아요 클릭시 좋아요 토글 처리와 결과적으로 만들어진 좋아요 개수를 구해주는 매핑
	@PostMapping("/like-action")
}
