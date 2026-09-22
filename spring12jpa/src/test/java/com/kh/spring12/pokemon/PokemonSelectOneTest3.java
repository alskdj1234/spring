package com.kh.spring12.pokemon;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import com.kh.spring12.entity.Pokemon;
import com.kh.spring12.error.TargetNotfoundException;
import com.kh.spring12.repo.PokemonRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PokemonSelectOneTest3 {
	@Autowired
	private PokemonRepository pokemonRepository;
	//조회는 최소한 3개 ( 목록 검색 상세)
			//가능한 경우는 페이징도 되야함
			//목록 / 검색 반환형은 List<Pokemon>
			//상세 반환형은 null/default/exception or pokemon
	@Test
	@Transactional
	public void test() {
		long pokemonNo = 999L;
		
		//jpa에서 제공하는 메소드를 이용해서 상세조회
		// 번호가 5번인걸 조회
		// findById를 사용하면 즉시(Eager) 조회 하는 전략을 사용함
		//java 8+에서 등장한 OPtional 형태로 데이터를 반환(데이터가 있을 수도 없을 수도 있어서 그에 따른 처리가 가능한 도구)
		//모던 스타일로 구현
		
		//jpa가 추구하는 건 자동 최적화라 조회를 하는 시점에 실제 구문을 실행하는 게 아니라 값을 쓸 때 실제 구문을 실행할려고 한다.
		
		//Pokemon p = pokemonRepositoy.findById(pokemonNo).orElse(null);
		
		//Pokemon p = pokemonRepository.findById(5L).orElse(Pokemon.builder().pokemonNo(25L).pokemonName("피카츄").pokemonType("전기").build());//있으면 내놓고 없으면 ()의 내용
		
		//Pokemon p = pokemonRepository.findById(pokemonNo).orElseThrow();//기본 지정 예외인 NoSuchElementException
		
		//Pokemon p = pokemonRepository.findById(pokemonNo).orElseThrow(()->new TargetNotfoundException());
		
		Pokemon p = pokemonRepository.findById(pokemonNo).orElseThrow(TargetNotfoundException:: new);//메소드 레퍼런스 (콜백 함수)
		
		System.out.println(p);
		
	}
}
