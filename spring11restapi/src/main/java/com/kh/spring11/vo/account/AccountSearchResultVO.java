package com.kh.spring11.vo.account;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class AccountSearchResultVO {
	private String accountId;
	private String accountEmail;
	
	private String accountNickname;
	private String accountBirth;
	private String accountContact;
	private String accountPost, accountAddress1, accountAddress2;
	private String accountLevel;
	private Timestamp accountJoin, accountLogin, accountChange;
	private String accountBlock;
	private int accountPoint;
	private String accountMessage;
}
