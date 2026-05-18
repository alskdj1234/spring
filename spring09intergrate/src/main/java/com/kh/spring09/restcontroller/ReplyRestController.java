package com.kh.spring09.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring09.dao.ReplyDao;
import com.kh.spring09.dto.ReplyDto;
import com.kh.spring09.vo.ReplyVO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/rest/reply")
public class ReplyRestController {
	@Autowired
	private ReplyDao replyDao;

	@PostMapping("/write")
	public ReplyVO write(@ModelAttribute ReplyDto replyDto, HttpSession session) {
		
		long replyNo=replyDao.sequence();
		String loginId = (String) session.getAttribute("loginId");
		
		replyDto.setReplyNo(replyNo);
		replyDto.setReplyWriter(loginId);

		boolean result = replyDao.insert(replyDto);

		return ReplyVO.builder().result(result).build();

	}
	
	@PostMapping("/list")
	public List<ReplyDto> list(@RequestParam long replyOrigin){
		return replyDao.selectList(replyOrigin);
	}
	
	@PostMapping("/delete")
	public void delete(@RequestParam long replyNo) {
		replyDao.delete(replyNo);
		
	}
}
