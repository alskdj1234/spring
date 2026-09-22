package com.kh.spring12.pokemon;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring12.entity.Pokemon;
import com.kh.spring12.repo.PokemonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PokemonSelectOneTest {
	@Autowired
	private PokemonRepository pokemonRepository;
	//조회는 최소한 3개 ( 목록 검색 상세)
	//가능한 경우는 페이징도 되야함
	//목록 / 검색 반환형은 List<Pokemon>
	//상세 반환형은 null/default/exception or pokemon
	
	@Test
	
	public void test() {
		//jpa에서 제공하는 메소드를 이용해서 상세조회
		// 번호가 5번인걸 조회
		
		// findById를 사용하면 즉시(Eager) 조회 하는 전략을 사용함
		//java 8+에서 등장한 OPtional 형태로 데이터를 반환(데이터가 있을 수도 없을 수도 있어서 그에 따른 처리가 가능한 도구)
		
		//jpa가 추구하는 건 자동 최적화라 조회를 하는 시점에 실제 구문을 실행하는 게 아니라 값을 쓸 때 실제 구문을 실행할려고 한다.
		Optional<Pokemon>p = pokemonRepository.findById(5L);
		if(p.isEmpty()) {
			System.out.println("존재하지 않는 몬스터 번호입니다.");
		}
		else {
			System.out.println(p.get());
		}
	}
}
