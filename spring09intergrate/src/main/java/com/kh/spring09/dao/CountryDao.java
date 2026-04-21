package com.kh.spring09.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring09.dto.BoardDto;
import com.kh.spring09.dto.CountryDto;
import com.kh.spring09.mapper.CountryMapper;
import com.kh.spring09.vo.PageVO;

@Repository // db나 파일을 제어하기 우히나 도구 (영속성을 가진 대상 제어 도구)
public class CountryDao {
	@Autowired // 주세요 해봐 (의존성 주입)
	private JdbcTemplate jdbcTemplate;
	@Autowired // 주세요 해봐 (의존성 주입)
	private CountryMapper countryMapper;

	public void insert(CountryDto countryDto) {
		String sql = "insert into country" + "(country_no, country_region, country_name, "
				+ "country_capital, country_population" + ") values(country_seq.nextval,? ,? ,? ,?)";
		Object[] params = { countryDto.getCountryRegion(), countryDto.getCountryName(), countryDto.getCountryCapital(),
				countryDto.getCountryPopulation() };
		jdbcTemplate.update(sql, params);

	}

	// 수정처리
	public boolean update(CountryDto countryDto) {

		String sql = "update country " + "set country_region=?, country_name=?, "
				+ "country_capital=?, country_population=? " + "where country_no=?";

		Object[] params = { countryDto.getCountryRegion(), countryDto.getCountryName(), countryDto.getCountryCapital(),
				countryDto.getCountryPopulation(), countryDto.getCountryNo() };
		int rows = jdbcTemplate.update(sql, params);
		return rows > 0;// rows가 0보다 큰지 작은지 판단해서 반환
	}

	public boolean delete(int countryNo) {

		String sql = " delete country where country_no=?";
		Object[] params = { countryNo };
		return jdbcTemplate.update(sql, params) > 0;
	}

	public List<CountryDto> selectList(int page, int size) {
		String sql = "select * from (" + "select rownum rn, TMP.* from ("
				+ "select * from country order by country_no desc" + ") TMP" + ") where rn between ? and ?";
		int beginRow = page * size - (size - 1);
		int endRow = page * size;
		Object[] params = { beginRow, endRow };
		return jdbcTemplate.query(sql, countryMapper, params);
	}

	public List<CountryDto> selectList(PageVO pageVO) {
		if (pageVO.isList())
			return selectList(pageVO.getPage(), pageVO.getSize());

		Set<String> allowList = Set.of("country_region", "country_name", "country_capital");
		if (allowList.contains(pageVO.getColumn()) == false)
			return List.of();

		String sql = "select * from (" + "select rownum rn, TMP.* from (" + "select * from country" + "where instr("
				+ pageVO.getColumn() + ", ?) > 0 " + "order by country_no desc" + ") TMP"
				+ ") where rn between ? and ?";
		Object[] params = { pageVO.getKeyword(), pageVO.getBeginRownum(), pageVO.getEndRownum() };
		return jdbcTemplate.query(sql, countryMapper, params);
	}

	public CountryDto selectOne(int countryNo) {

		String sql = "select * from country where country_no = ? ";
		Object[] params = { countryNo };

		List<CountryDto> list = jdbcTemplate.query(sql, countryMapper, params);
		return list.isEmpty() ? null : list.get(0);

	}

	public int count() {
		String sql = "select count(*) from country";
		return jdbcTemplate.queryForObject(sql, int.class);
	}

	public int count(PageVO pageVO) {
		
		if (pageVO.isList())
			return count();
		Set<String> allowList = Set.of("country_region", "country_name", "country_capital");
		if (allowList.contains(pageVO.getColumn()) == false) return 0;//결과 없음

		String sql = "select count(*) from country " + "where instr(" + pageVO.getColumn() + ", ?) > 0";
		Object[] params = { pageVO.getKeyword() };
		return jdbcTemplate.queryForObject(sql, int.class, params);
	}
}
