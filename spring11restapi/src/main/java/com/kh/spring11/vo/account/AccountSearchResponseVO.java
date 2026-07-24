package com.kh.spring11.vo.account;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class AccountSearchResponseVO {
	private boolean last;
	private List<AccountSearchResultVO> list;
}
