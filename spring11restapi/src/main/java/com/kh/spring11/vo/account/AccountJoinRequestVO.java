package com.kh.spring11.vo.account;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

//회원가입시 요청할 정보들
// 스프링에서 fe 요청객체(json)를 자동으로 수신하여 자바 클래스 형태로 복원 시켜놓음
//jackson databind가 이를 처리 (스프링부트에 내장 , 레거시엔 없음)
//클래스의 모든 필드를 채우려고 노력하며, 못 채우면 에러가 발생함
//@JsonIgnore를 필드에 붙이면 해당 필드는 없어도 통과됨
@Schema(name="회원가입 정보 객체")
@Data
//클래스 단위로는 JsonIgnoreProperties=> 이 클래스 항목들이 다 안 차도 넘어가세요 
//(ignoreUnknown = true=>없는 애들이 오면 무시하세요)
//리퀘스트 바디, 오브젝트 매퍼를 이용한 수동 변환  쓸 때 씀(100프로는 아님)
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
