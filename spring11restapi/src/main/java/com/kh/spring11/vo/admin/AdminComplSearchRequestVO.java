package com.kh.spring11.vo.admin;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class AdminComplSearchRequestVO {

    // 1. 아이디 완전 일치
    private String accountId;

    // 2. 닉네임 완전 일치
    private String accountNickname;

    // 3. 이메일 완전 일치
    private String accountEmailExact;

    // 4. 연락처 완전 일치
    private String accountContact;

    // 5. 이메일 유사 검색
    private String accountEmailLike;

    // 6. 주소 유사 검색
    // 우편번호 + 기본주소 + 상세주소를 모두 검색
    private String accountAddress;

    // 7. 가입일 범위
    private LocalDate accountJoinBegin;
    private LocalDate accountJoinEnd;

    // 8. 로그인일 범위
    private LocalDate accountLoginBegin;
    private LocalDate accountLoginEnd;

    // 9. 포인트 범위
    private Long accountPointMin;
    private Long accountPointMax;

    // 10. 회원 등급 복수 선택
    private List<String> accountLevels;

    // 11. 차단 여부
    // Y, N, null 또는 빈 문자열이면 전체
    private String accountBlock;

    // 12. 정렬
    // accountId, accountNickname, accountEmail,
    // accountJoin, accountLogin, accountPoint, accountLevel
    private String sortField;

    // ASC 또는 DESC
    private String sortDirection;

    // 13. 더보기 방식
    // 이미 조회한 데이터 개수
    private Integer offset = 0;

    // 한 번에 조회할 데이터 개수
    private Integer size = 20;
}