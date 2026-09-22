package com.kh.spring12.pokemon2;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring12.entity.Pokemon;
import com.kh.spring12.repo.PokemonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PokemonAdvancedTest01 {
	
	@Autowired
	private PokemonRepository pokemonRepository;
	
	@Test
	public void test() {
		//목표 : 100번 이후의 포켓몬스터 정보를 조회
//		List<Pokemon> list = pokemonRepository.findByPokemonNoGreaterThanEqual(100);
//		System.out.println(list.size());

		//목표 : 100~130번 사이 조회
		//List<Pokemon> list = pokemonRepository.findByPokemonNoGreaterThanEqualAndPokemonNoLessThanEqual(100,130);
		//System.out.println(list.size());
		
		//List<Pokemon> list = pokemonRepository.findByPokemonNoBetween(100,130);
		//System.out.println(list.size());
		LocalDateTime today = LocalDateTime.now();
		LocalDateTime weekAgo = today.minusDays(7L).withHour(0).withMinute(0).withSecond(0);
		//List<Pokemon> list = pokemonRepository.findByPokemonWtimeAfter(weekAgo);
		//List<Pokemon> list = pokemonRepository.findByPokemonWtimeBetween(weekAgo, today);
	}
}
