package com.kh.spring12.pokemon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.spring12.entity.Pokemon;
import com.kh.spring12.error.TargetNotfoundException;
import com.kh.spring12.repo.PokemonRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class PokemonUpdateTest {

	@Autowired
	private PokemonRepository pokemonRepository;
	
	@Test
	public void test() {
		//[상식] 수정을 하려면 pk와 수정할 정보가 있어야한다.(다 바꿀 수도 있고, 일부만 바꿀 수도 있고)
		
		//변경할 정보들을 객체로 생성(= 화면에서 넘어오는 정보 수신과 동일)
		
		Pokemon p = Pokemon.builder()
					.pokemonNo(1L)
					.pokemonName("테스트")
					.pokemonType("테스트")
					.build();
		
		//상세 조회 후 검증을 거쳐 정보를 수정하도록 처리
		
	Pokemon target = pokemonRepository.findById(p.getPokemonNo()).orElseThrow(()->new TargetNotfoundException());
		BeanUtils.copyProperties(p, target, "pokemonNo","pokemonWtime","pokemonEtime");
		Pokemon result = pokemonRepository.save(target);
		
		System.out.println(result);
		
	}
}
