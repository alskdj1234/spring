package com.kh.spring11.vo.sale;

import com.kh.spring11.dto.AttachDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "썸네일 변경 응답 데이터")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class ChangeThumbnailResponseVO {
	private AttachDto attach;
}
