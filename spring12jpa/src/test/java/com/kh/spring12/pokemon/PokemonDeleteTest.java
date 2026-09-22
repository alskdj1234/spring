package com.kh.spring12.pokemon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring12.entity.Pokemon;
import com.kh.spring12.error.TargetNotfoundException;
import com.kh.spring12.repo.PokemonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PokemonDeleteTest {
	@Autowired
	private PokemonRepository pokemonRepository;
	
	@Test
	public void test() {
		long pokemonNo = 1L;
		//1. example 이용
//		Pokemon p = pokemonRepository.findById(pokemonNo).orElseThrow(()->new TargetNotfoundException());
//		pokemonRepository.delete(p);
		//2 id를 전달
		Pokemon p = pokemonRepository.findById(pokemonNo).orElseThrow(()->new TargetNotfoundException());
		pokemonRepository.deleteById(pokemonNo);
	}
}
