package com.kh.spring09.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {
	private long replyNo;          
    private String replyWriter;   
    private long replyOrigin;     
    private String replyContent;  
    private Timestamp replyWtime; 
    private Timestamp replyEtime;
}
