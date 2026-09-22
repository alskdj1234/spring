package com.kh.spring12.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor

@Entity//이 클래스는 테이블과 연결되는 개체
@Table(name = "pokemon")//실제 연결될 테이블의 이름은 pokemon이다
@SequenceGenerator(
		name = "pokemon_seq",//jpa가 기억할 시퀀스의 이름(실제 시퀀스 이름 x)
		sequenceName= "pokemon_seq",//실물 db에 시퀀스 생성이 필요할 경우 만들어질 이름
		initialValue = 1,//시퀀스 시작번호
		//allocationSize = 20//캐시 크기(오라클 기본값 20)
		allocationSize =1 //연습용 캐시 미사용 설정
		)
public class Pokemon {
	@Id//이 항목은 pk이다. (단, 가급적이면 raw type 사용은 자제할 것)
	@GeneratedValue(//이 항목은 생성시 시퀀스 사용
			generator = "pokemon_seq",//생성된 @SequenceGenerator 중에 name이 pokemon_seq인 항목을 연결해라
			strategy = GenerationType.AUTO//미리 설정한 db의 종류와 버전에 맞게 자동으로 배정해
			)
	@Column
	private Long pokemonNo;
	@Column(nullable= false, length = 30)
	private String pokemonName;
	@Column (nullable= false, length =30)
	private String pokemonType;
	
	//시간 추가
	//시간의 형식이 자유
	// @CreationTimestamp 생성 시각이 자동 기록
	// @UpdateTimestamp 수정 시각이 자동 기록

	@CreationTimestamp
	private LocalDateTime pokemonWtime;
	
	@UpdateTimestamp
	private LocalDateTime pokemonEtime;
}
