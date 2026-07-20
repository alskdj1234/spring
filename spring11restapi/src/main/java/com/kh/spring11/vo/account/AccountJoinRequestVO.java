package com.kh.spring11.vo.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

//회원가입 요청용 VO
//- 스프링에서 FE의 요청객체(JSON)를 자동으로 수신하여 자바 클래스 형태로 복원시켜놓음
//- jackson-databind가 이를 처리(스프링부트에 내장되어 있음, 레거시엔 없음)
//- 클래스의 모든 필드를 채우려고 노력하며, 못채우면 에러가 발생함
//- @JsonIgnore를 필드에 붙이면 해당 필드는 없어도 통과됨
//- 클래스 레벨에 @JsonIgnoreProperties(ignoreUnknown = true) 표시할 수 있음
//- 주로 RequestBody를 수신할 때 자주 사용 (또는 ObjectMapper를 이용한 수동변환 시)

@Schema(name = "회원가입 정보객체")
@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountJoinRequestVO {
	private String accountId;
	private String accountEmail;
	private String accountPassword;
	private String accountNickname;
	@JsonIgnore
	private String accountBirth;
	@JsonIgnore
	private String accountContact;
	@JsonIgnore
	private String accountPost, accountAddress1, accountAddress2;
	@JsonIgnore
	private String accountMessage;
}
