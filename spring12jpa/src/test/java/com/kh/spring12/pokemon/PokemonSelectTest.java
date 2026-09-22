package com.kh.spring12.pokemon;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.kh.spring12.entity.Pokemon;
import com.kh.spring12.repo.PokemonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PokemonSelectTest {
	@Autowired
	private PokemonRepository pokemonRepository;
	
	@Test
	public void test() {
		//조회는 최소한 3개 ( 목록 검색 상세)
		//가능한 경우는 페이징도 되야함
		//목록 / 검색 반환형은 List<Pokemon>
		//상세 반환형은 null or pokemon
		List<Pokemon> list = pokemonRepository.findAll(
				Sort.by("pokemonNo").ascending()
				
				);
		for(Pokemon p : list) {
			System.out.println(p);
		}
	}
	
	
}
