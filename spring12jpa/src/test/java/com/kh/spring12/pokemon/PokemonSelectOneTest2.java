package com.kh.spring12.pokemon;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import com.kh.spring12.entity.Pokemon;
import com.kh.spring12.repo.PokemonRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PokemonSelectOneTest2 {
	@Autowired
	private PokemonRepository pokemonRepository;
	//조회는 최소한 3개 ( 목록 검색 상세)
			//가능한 경우는 페이징도 되야함
			//목록 / 검색 반환형은 List<Pokemon>
			//상세 반환형은 null/default/exception or pokemon
	@Test
	@Transactional
	public void test() {
		//jpa에서 제공하는 메소드를 이용해서 상세조회
		// 번호가 5번인걸 조회
		
		//jpa가 추구하는 건 자동 최적화라 조회를 하는 시점에 실제 구문을 실행하는 게 아니라 값을 쓸 때 실제 구문을 실행할려고 한다.
		Pokemon p = pokemonRepository.getOne(5L);
		if(p == null) {
			System.out.println("존재하지 않는 몬스터 번호입니다.");
		}
		else {
			System.out.println(p.getPokemonNo());
			System.out.println(p.getPokemonName());
			System.out.println(p.getPokemonType());
		}
	}
}
