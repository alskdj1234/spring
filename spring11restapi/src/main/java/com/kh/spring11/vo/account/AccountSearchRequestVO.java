package com.kh.spring11.vo.account;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name="관리자 회원조회 요청 VO")
@Data @JsonIgnoreProperties(ignoreUnknown = true)
public class AccountSearchRequestVO {
	private String accountId;
	private String accountNickname;
	private String accountContact;
	private String accountEmail;
	private String accountAddress;
	private String accountBirthBegin;
	private String accountBirthEnd;
	private String accountJoinBegin;
	private String accountJoinEnd;
	private String accountLoginBegin, accountLoginEnd;
	private String accountPointMin, accountPointMax;
	private Set<String> accountLevels;
	private String accountBlock;
}
