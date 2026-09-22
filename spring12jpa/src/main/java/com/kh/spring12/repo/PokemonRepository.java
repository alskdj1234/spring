package com.kh.spring12.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.spring12.entity.Pokemon;

//JPA에서 자동으로 CRUD를 처리할 수 있도록 관리하는 저장소
//- 등록 필요없음 (인터페이스라 등록도 안됨 jpa가 자동으로 프록시 객체를 만들어 등록함)
//- 사용할 명령을 가진 인터페이스를 상속받고 pk 정보만 알려주면 된다
//extends JpaRepository<Pokemon, Long> : Long이 PK인 포켓몬에 대해 crud 수행 관리도구 만들자
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
	
	//1 naming method
	// select * from pokemon where pokemon_no  >= 100;
	// 규칙 : findBy(조회) + 항목 + 상태 +매개변수 ,조건 병합시 and/or을 사용
	
	List<Pokemon> findByPokemonNoGreaterThanEqual(int min);
	
	List<Pokemon> findByPokemonNoGreaterThanEqualAndPokemonNoLessThanEqual(int min, int max);
	
	List<Pokemon> findByPokemonNoBetween(int min, int max);
	
	List<Pokemon> findByPokemonWtimeAfter(LocalDateTime begin);
	List<Pokemon> findByPokemonWtimeBetween(LocalDateTime begin, LocalDateTime end);
	
	//몬스터명 자동완성 검색
	List<Pokemon> findByPokemonNameStartingWithOrderByPokemonNoAsc(String pokemonName);
	List<Pokemon> findByPokemonNameStartingWithOrderByPokemonNameAsc(String pokemonName);

	List<Pokemon> findByPokemonNameStartingWithOrderByPokemonNameAscPokemonNoAsc(String pokemonName);

	
	//페이징 적용
	//페이징은 조회 결과 외에도 정보가 많이 필요하다 (마지막인지 전체가 몇갠지 현재 어딘지)->page<T> 형태로 변환
}
