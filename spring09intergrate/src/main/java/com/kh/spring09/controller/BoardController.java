package com.kh.spring09.controller;

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

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardDao boardDao;
	
	//목록 및 검색 매핑
	@RequestMapping("/list")
	public String list(Model model, 
			@RequestParam(required = false) String column,
			@RequestParam(required = false) String keyword
	) {
		List<BoardDto> list = boardDao.selectList(column, keyword);
		model.addAttribute("list", list);
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
		//정보 취합 후
		boardDto.setBoardWriter(loginId);
		boardDto.setBoardNo(boardNo);
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


