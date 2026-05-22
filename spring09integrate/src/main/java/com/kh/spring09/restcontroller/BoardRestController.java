package com.kh.spring09.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring09.dao.BoardDao;
import com.kh.spring09.dao.BoardLikeDao;
import com.kh.spring09.vo.LikeVO;

import jakarta.servlet.http.HttpSession;

//게시글과 관련된 비동기 통신을 처리하는 도구(board, board_like, board_read 등)
//@CrossOrigin
@RestController
@RequestMapping("/rest/board")
public class BoardRestController {
	@Autowired
	private BoardDao boardDao;
	@Autowired
	private BoardLikeDao boardLikeDao;

	//[1] 최초 접속 시 좋아요 여부와 현재 글의 좋아요 개수를 구해주는 매핑
	@PostMapping("/like-check")
	public LikeVO likeCheck(@RequestParam int boardNo, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		
		boolean action = boardLikeDao.check(loginId, boardNo);//좋아요 여부 계산
		int count = boardLikeDao.count(boardNo);//현재 글의 좋아요 계산
		
//		LikeVO likeVO = new LikeVO();	//Map<String, Object> map = new HashMap<>();
//		likeVO.setAction(action);		//map.put("action", action);
//		likeVO.setCount(count);			//map.put("count", count);
//		return likeVO;					//return map;
		
//		return Map.of("action", action, "count", count);
		return LikeVO.builder().action(action).count(count).build();//Builder 패턴
	}
	
	//[2] 좋아요 클릭 시 좋아요 토글 처리와 결과적으로 만들어진 좋아요 개수를 구해주는 매핑
	//- 비회원 차단은 인터셉터에서 구현
	@PostMapping("/like-action")
	public LikeVO likeAction(@RequestParam int boardNo, HttpSession session) {
		String loginId = (String)session.getAttribute("loginId");
		
		boolean current = boardLikeDao.check(loginId, boardNo);
		if(current) {//좋아요 설정한 적이 있으면
			boardLikeDao.delete(loginId, boardNo);//좋아요 해제
		}
		else {
			boardLikeDao.insert(loginId, boardNo);//좋아요 설정
		}
		
		int count = boardLikeDao.count(boardNo);
		
		//게시글 테이블 좋아요 개수를 최신화
		boardDao.updateBoardLikecount(boardNo);
		
		//return Map.of("action", !current, "count", count);
		return LikeVO.builder().action(!current).count(count).build();
	}
}







