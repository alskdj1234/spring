package com.kh.spring11.vo.account;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Schema(name ="계정 차단 결과 응답")

@Data @Builder @NoArgsConstructor @AllArgsConstructor

public class AccountBlockResponseVO {
	//private boolean result;//true 차단 false 차단해제
	private String accountId;
	private String accountEmail;
	private String accountNickname;
	private String accountBirth;
	private String accountContact;
	private String accountPost, accountAddress1, accountAddress2;
	private String accountLevel;
	private Timestamp accountJoin, accountLogin, accountChange;
	private int accountPoint;
	private String accountMessage;
	private String accountBlock;
}
