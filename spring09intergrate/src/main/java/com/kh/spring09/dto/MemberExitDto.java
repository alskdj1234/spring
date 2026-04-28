package com.kh.spring09.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberExitDto {
	//MemberDto의 내용
	private String memberId;
	private String memberEmail;
	private String memberPassword;
	private String memberNickname;
	private String memberBirth;
	private String memberContact;
	private String memberPost, memberAddress1, memberAddress2;
	private String memberLevel;
	private String memberMessage;
	private Timestamp memberJoin, memberLogin, memberChange;
	private String memberBlock;
	private int memberPoint;
	//추가된 내용
	private Timestamp memberExitTime;
	
	//컨트롤러 코드의 가독성을 높이기 위한 가상의 GETTER
	public boolean isWaitForDelete() {
		return memberExitTime != null;
		//if(memberExitTime != null) { 
		//	return true;
		//}
		//else { 
		//	return false;
		//}
	}
}









