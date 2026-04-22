package com.kh.spring09.dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import lombok.Data;

@Data
public class BoardDto {
	private long boardNo;
	private String boardHead;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private Timestamp boardWtime, boardEtime;
	private long boardReadcount, boardLikecount, boardReplycount;
	
	private long boardGroup;
	private Long boardParent;
	private long boardDepth;
	//(추가) 오늘 작성한 글은 시간만, 이전에 작성한 글은 날짜만 반환하도록 계산하는 Getter 메소드
	public String getBoardWtimeString() {
		//작성일과 현재시각을 LocalDateTime 형태로 불러온다
		LocalDateTime current = LocalDateTime.now();//현재시각
		LocalDateTime writeTime = boardWtime.toLocalDateTime();//작성시각
		
		LocalDate currentDate = current.toLocalDate();//현재일자
		LocalDate writeDate = writeTime.toLocalDate();//작성일자
		
		if(writeDate.equals(currentDate)) {//작성일이 오늘이면
			//LocalTime time = writeTime.toLocalTime();//시간만 뽑아서
			//return time.toString();//반환하세요!
			DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm");
			return writeTime.format(f);
		}
		else {//작성일이 오늘이 아니라면
			return writeDate.toString();//작성일을 문자열로 반환하세요!
		}
	}
}





