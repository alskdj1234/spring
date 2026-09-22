package com.kh.spring12.pokemon;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import com.kh.spring12.entity.Pokemon;
import com.kh.spring12.repo.PokemonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PokemonSelectTest3 {
	@Autowired
	private PokemonRepository pokemonRepository;
	
	@Test
	public void test() {
		//jpa 제공하는 메소드를 이용해서 조회
		//전기 속성만 조회
		//								(example이라 부름)
		// selecet from pokemon where [pokemon_type='전기']
		List<Pokemon> list = pokemonRepository.findAll(
				Example.of(Pokemon.builder().pokemonType("전기").build()),
				Sort.by("pokemonNo").ascending()
				
				);
		for(Pokemon p : list) {
			System.out.println(p);
		}
	}
	
	
}
