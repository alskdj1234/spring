package com.kh.spring12.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor

@Entity//이 클래스는 테이블과 연결되는 개체
@Table(name = "pokemon")//실제 연결될 테이블의 이름은 pokemon이다
public class Pokemon {
	@Id//이 항목은 pk이다. (단, 가급적이면 raw type 사용은 자제할 것)
	@Column
	private Long pokemonNo;
	@Column(nullable= false, length = 30)
	private String pokemonName;
	@Column (nullable= false, length =30)
	private String pokemonType;
}
