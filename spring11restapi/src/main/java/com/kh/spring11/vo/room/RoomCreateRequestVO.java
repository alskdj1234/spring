package com.kh.spring11.vo.room;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Schema(name = "방 생성 요청에 필요한 데이터")
@Data @JsonIgnoreProperties(ignoreUnknown = true)
public class RoomCreateRequestVO {
	@NotBlank
	private String name;
	@Positive
	private Integer limit;
}
