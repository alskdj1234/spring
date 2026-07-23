package com.kh.spring11.vo.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
@Schema(name="본인정보 변경용 VO")
@JsonIgnoreProperties(ignoreUnknown=true)
@Data
public class ChangeAccountRequestVO {
	@NotNull @Pattern(regexp ="^[가-힣A-Za-z0-9]{1,10}$")
	private String accountNickname;
	@NotNull @Email
	private String accountEmail;
	@NotNull
	private String accountPassword;

	private String accountBirth;

	private String accountContact;
	@Null @Pattern(regexp = "^[0-9]{5,6}$")
	private String accountPost;

	private String accountAddress1;

	private String accountAddress2;

	private String accountMessage;
	
}
