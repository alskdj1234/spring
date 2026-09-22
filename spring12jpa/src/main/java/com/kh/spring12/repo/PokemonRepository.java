package com.kh.spring12.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kh.spring12.entity.Pokemon;

//JPA에서 자동으로 CRUD를 처리할 수 있도록 관리하는 저장소
//- 등록 필요없음 (인터페이스라 등록도 안됨 jpa가 자동으로 프록시 객체를 만들어 등록함)
//- 사용할 명령을 가진 인터페이스를 상속받고 pk 정보만 알려주면 된다
//extends JpaRepository<Pokemon, Long> : Long이 PK인 포켓몬에 대해 crud 수행 관리도구 만들자
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

}
