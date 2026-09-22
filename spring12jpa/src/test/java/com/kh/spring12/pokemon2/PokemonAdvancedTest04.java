package com.kh.spring12.pokemon2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.kh.spring12.entity.Pokemon;
import com.kh.spring12.repo.PokemonRepository;

public class PokemonAdvancedTest04 {
	@Autowired
	private PokemonRepository pokemonRepository;
	
	@Test
	public void test() {
		//목표 : 전체 목록의 1페이지(10개씩) 조회
		Pageable option = PageRequest.of(0, 10);
		Page<Pokemon> page =pokemonRepository.findAll(option);
		
		System.out.println(page.getTotalPages());
		System.out.println(page.getTotalElements());
		}
}
