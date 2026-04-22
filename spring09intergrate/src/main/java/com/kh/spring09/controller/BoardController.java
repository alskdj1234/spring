package com.kh.spring09.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring09.dao.BoardDao;
import com.kh.spring09.dto.BoardDto;
import com.kh.spring09.exception.GetOutException;
import com.kh.spring09.exception.TargetNotfoundException;
import com.kh.spring09.vo.PageVO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardDao boardDao;
	
	//목록 및 검색 매핑
	@RequestMapping("/list")
	public String list(Model model, @ModelAttribute PageVO pageVO) {
		//공지사항 게시글
		//List<BoardDto> noticeList = boardDao.selectList("board_head", "공지");
		List<BoardDto> noticeList = boardDao.selectNoticeList();
		
		//일반 게시글 (공지사항도 포함되어 있음)
		List<BoardDto> boardList = boardDao.selectList(pageVO);
		
		//두 개를 합쳐서 전달
		List<BoardDto> list = new ArrayList<>();
		list.addAll(noticeList);//공지사항 먼저
		list.addAll(boardList);//게시글은 나중에
		
		model.addAttribute("list", list);
		model.addAttribute("noticeCount", noticeList.size());//공지사항 개수 전달
		
		//페이징을 위해 추가로 전달할 값이 있다면 전달해야 한다
		int count = boardDao.count(pageVO);
		pageVO.setCount(count);//데이터 개수 설정
		model.addAttribute("pageVO", pageVO);
		return "board/list";
	}
	
	//상세 매핑
	@RequestMapping("/detail")
	public String detail(@RequestParam long boardNo, Model model) {
		BoardDto boardDto = boardDao.selectOne(boardNo);
		if(boardDto == null) throw new TargetNotfoundException("존재하지 않는 게시글");
		model.addAttribute("boardDto", boardDto);
		return "board/detail";
	}
	
	//등록 매핑
	
	@GetMapping("/write")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("/write")
	public String write(@ModelAttribute BoardDto boardDto, HttpSession session) {
		//아이디 추출
		String loginId = (String)session.getAttribute("loginId");
		//공지라면 마스터인지 확인
		if(boardDto.getBoardHead()!=null&&boardDto.getBoardHead().equals("공지")) {
		 String loginLevel = (String)session.getAttribute("loginLevel");
		 if(!loginLevel.equals("마스터"))throw new GetOutException();
		}
		//글 번호 생성을 먼저
		long boardNo = boardDao.sequence();
		//정보 취합 후 필요 항목을 계산(새글이냐 답글이냐)후 등록 요청
		boardDto.setBoardWriter(loginId);
		boardDto.setBoardNo(boardNo);
		if(boardDto.getBoardParent()==null){//새글
			boardDto.setBoardGroup(boardNo);//그룹번호를 글 번호로 설정하세요
		
		}
		
		else {//답글이라면
			BoardDto findBoardDto = boardDao.selectOne(boardDto.getBoardParent());
			boardDto.setBoardGroup(findBoardDto.getBoardGroup());
			
			boardDto.setBoardParent(findBoardDto.getBoardNo());//생략 가능
			boardDto.setBoardDepth(findBoardDto.getBoardDepth()+1);//원본글의 차수 +1
		}
			
		
		//등록하고
		boardDao.insert(boardDto);
		//상세 페이지로 리다이렉트
		return "redirect:./detail?boardNo="+boardNo;
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam long boardNo) {
		BoardDto boardDto = boardDao.selectOne(boardNo);
		if(boardDto == null) throw new TargetNotfoundException("존재하지 않는 게시글");
		boardDao.delete(boardNo);
		return "redirect:./list";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam long boardNo,Model model) {
		BoardDto boardDto = boardDao.selectOne(boardNo);
		if(boardDto == null) throw new TargetNotfoundException("존재 하지 않는 게시글");
		model.addAttribute("boardDto", boardDto);
		return "board/edit";
	}
	
	@PostMapping("/edit")
	public String edit(@ModelAttribute BoardDto boardDto, HttpSession session) {
		if(boardDto.getBoardHead()!=null&&boardDto.getBoardHead().equals("공지")) {
			 String loginLevel = (String)session.getAttribute("loginLevel");
			 if(!loginLevel.equals("마스터"))throw new GetOutException();
			}
		
		BoardDto findboardDto = boardDao.selectOne(boardDto.getBoardNo());
		if(findboardDto == null) throw new TargetNotfoundException("존재 하지 않는 게시글");
		
		boardDao.update(boardDto);
		return "redirect:./detail?=boardNo="+boardDto.getBoardNo();
	}
}


