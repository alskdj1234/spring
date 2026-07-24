package com.kh.spring11.vo.admin;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AdminComplSearchResponseVO {

    private Long accountNo;

    private String accountId;
    private String accountNickname;
    private String accountEmail;
    private String accountContact;

    private String accountPost;
    private String accountAddress1;
    private String accountAddress2;

    private LocalDateTime accountJoin;
    private LocalDateTime accountLogin;

    private Long accountPoint;
    private String accountLevel;
    private String accountBlock;
}