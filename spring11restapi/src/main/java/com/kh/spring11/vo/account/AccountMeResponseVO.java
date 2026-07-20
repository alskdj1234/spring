package com.kh.spring11.vo.account;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name= "내 정보 조회 응답용 데이터")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class AccountMeResponseVO {
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
}
