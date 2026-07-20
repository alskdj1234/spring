package com.kh.spring11.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kh.spring11.dto.CountryDto;
import com.kh.spring11.mapper.CountryMapper;
import com.kh.spring11.vo.CountryComplexRequestVO;

//@Repository//DB나 파일을 제어하기 위한 도구 (영속성을 가진 대상 제어 도구)
public class CountryDaoSpringJDBC  implements CountryDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;//JdbcTemplate 주세요!
	@Autowired
	private CountryMapper countryMapper;//CountryMapper 주세요!
	
	//필요한 기능을 메소드로 구현
	@Override
	public int sequence() {
		String sql = "select country_seq.nextval from dual";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	@Override
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
	@Override
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
	@Override
	public boolean delete(int countryNo) {
		String sql = "delete country where country_no = ?";
		Object[] params = { countryNo };
		return jdbcTemplate.update(sql, params) > 0;
	}
	
	//상세 메소드
	@Override
	public CountryDto selectOne(int countryNo) {
		String sql = "select * from country where country_no = ?";
		Object[] params = { countryNo };
		List<CountryDto> list = jdbcTemplate.query(sql, countryMapper, params);//일단 목록으로 조회
		return list.isEmpty() ? null : list.get(0);
	}
	
	//REST API 용 페이징 메소드
	//- 동일 데이터가 두번 나오지 않도록 번호로 필터링하여 10개를 추출
	@Override
	public List<CountryDto> selectList(Integer lastCountryNo, int size) {
//		String sql = "select * from ("
//						+ "select rownum rn, TMP.* from ("
//							+ "select * from country "
//							+ "where country_no > ? "
//							+ "order by country_no asc"
//						+ ")TMP"
//					+ ") where rn between 1 and ?";
		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from (");
		buffer.append("		select rownum rn, TMP.* from (");
		buffer.append("			select * from country ");
		if(lastCountryNo > 0) {
			buffer.append("			where country_no > ? ");
		}
		buffer.append("			order by country_no asc");
		buffer.append("		)TMP");
		buffer.append(") where rn between 1 and ?");
		
		String sql = buffer.toString();
		System.out.println("sql = " + sql);//로그
		Object[] params;
		if(lastCountryNo > 0) 
			params = new Object[]{ lastCountryNo, size };
		else 
			params = new Object[]{ size };
		return jdbcTemplate.query(sql, countryMapper, params);
	}
	@Override
	public int count(Integer lastCountryNo) {
		String sql = "select count(*) from country where country_no > ?";
		Object[] params = { lastCountryNo };
		return jdbcTemplate.queryForObject(sql, int.class, params);
	}
	@Override
	public List<CountryDto> searchByCountryName(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<CountryDto> complexSearch(CountryComplexRequestVO vo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int complexSearchCount(CountryComplexRequestVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
}








