package com.kh.spring09.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring09.dto.CountryDto;
import com.kh.spring09.mapper.CountryMapper;
import com.kh.spring09.vo.PageVO;

@Repository//DB나 파일을 제어하기 위한 도구 (영속성을 가진 대상 제어 도구)
public class CountryDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;//JdbcTemplate 주세요!
	@Autowired
	private CountryMapper countryMapper;//CountryMapper 주세요!
	
	//필요한 기능을 메소드로 구현
	public int sequence() {
		String sql = "select country_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	public void insert(CountryDto countryDto) {
		String sql = "insert into country("
						+ "country_no, country_region, country_name, "
						+ "country_capital, country_population"
					+ ") values(?, ?, ?, ?, ?)";
		Object[] params = {
			countryDto.getCountryNo(),
			countryDto.getCountryRegion(), countryDto.getCountryName(),
			countryDto.getCountryCapital(), countryDto.getCountryPopulation()
		};
		jdbcTemplate.update(sql, params);
	}
	
	//수정 메소드
	public boolean update(CountryDto countryDto) {
		String sql = "update country "
						+ "set country_region=?, country_name=?, "
							+ "country_capital=?, country_population=? "
						+ "where country_no=?";
		Object[] params = {
			countryDto.getCountryRegion(), countryDto.getCountryName(),
			countryDto.getCountryCapital(), countryDto.getCountryPopulation(),
			countryDto.getCountryNo()
		};
		int rows = jdbcTemplate.update(sql, params);
		return rows > 0;//한줄로 표현
	}
	
	//삭제 메소드
	public boolean delete(int countryNo) {
		String sql = "delete country where country_no = ?";
		Object[] params = { countryNo };
		return jdbcTemplate.update(sql, params) > 0;
	}
	
	//조회 메소드
	public List<CountryDto> selectList(int beginRownum, int endRownum) {
		String sql = "select * from ("
						+ "select rownum rn, TMP.* from ("
							+ "select * from country order by country_no asc"
						+ ")TMP"
					+ ") where rn between ? and ?";
		Object[] params = { beginRownum, endRownum };
		return jdbcTemplate.query(sql, countryMapper, params);
	}
	
	//검색 메소드
	public List<CountryDto> selectList(PageVO pageVO) {
		if(pageVO.isList()) //검색항목이 없으면 목록 반환
			return selectList(pageVO.getBeginRownum(), pageVO.getEndRownum());
		
		Set<String> allowList = Set.of("country_region", "country_name", "country_capital");
		if(allowList.contains(pageVO.getColumn()) == false) 
			return List.of();//허용되는 검색항목이 아니면 비어있는 결과 반환
		
		String sql = "select * from ("
						+ "select rownum rn, TMP.* from ("
							+ "select * from country "
							+ "where instr("+pageVO.getColumn()+", ?) > 0 "
							+ "order by country_no asc"
						+ ")TMP"
					+ ") where rn between ? and ?";
		Object[] params = { 
			pageVO.getKeyword(), pageVO.getBeginRownum(), pageVO.getEndRownum()
		};
		return jdbcTemplate.query(sql, countryMapper, params);
	}
	
	//상세 메소드
	public CountryDto selectOne(int countryNo) {
		String sql = "select * from country where country_no = ?";
		Object[] params = { countryNo };
		List<CountryDto> list = jdbcTemplate.query(sql, countryMapper, params);//일단 목록으로 조회
		return list.isEmpty() ? null : list.get(0);
	}
	
	//카운트 메소드
	public int count() {
		String sql = "select count(*) from country";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	public int count(PageVO pageVO) {
		if(pageVO.isList()) return count();
		
		Set<String> allowList = Set.of("country_region", "country_name", "country_capital");
		if(allowList.contains(pageVO.getColumn()) == false) 
			return 0;//허용되는 검색항목이 아니면 결과가 없다고 반환
		
		String sql = "select count(*) from country "
					+ "where instr("+pageVO.getColumn()+", ? ) > 0";
		Object[] params = { pageVO.getKeyword() };
		return jdbcTemplate.queryForObject(sql, int.class, params);
	}
	
	//국기 등록
	public void connect(int countryNo, int attachNo) {
		String sql = "insert into country_flag(country_no, attach_no) values(?, ?)";
		Object[] params = { countryNo, attachNo };
		jdbcTemplate.update(sql, params);
	}
	//국기 번호 찾기
	public int searchFlag(int countryNo) {
		String sql = "select attach_no from country_flag where country_no = ?";
		Object[] params = { countryNo };
		return jdbcTemplate.queryForObject(sql, int.class, params);
	}
}








