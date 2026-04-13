package com.kh.spring09.dto;

<<<<<<< HEAD
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
=======
import java.sql.Timestamp;

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
    private Timestamp memberJoin,memberLogin,memberChange;
   
>>>>>>> branch 'main' of https://github.com/alskdj1234/spring.git
    private String memberBlock;
    private int memberPoint;
	

}
