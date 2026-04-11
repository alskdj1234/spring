package com.kh.spring09.dto;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class MemberDto {
	private String memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberNickname;
    private String memberBirth;
    private String memberContact;
    private String memberPost;
    private String memberAddress1;
    private String memberAddress2;
    private String memberLevel;
    private String memberMessage;
    private LocalDateTime memberJoin;
    private LocalDateTime memberLogin;
    private LocalDateTime memberChange;
    private String memberBlock;
    private int memberPoint;
	

}
