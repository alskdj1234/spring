package com.kh.spring09.stat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kh.spring09.mapper.StatMapper;
import com.kh.spring09.vo.StatVO;

@SpringBootTest
public class Test02대륙별국가개수조회 {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private StatMapper statMapper;
	
	@Test
	public void test() {
		String sql = "select country_region title, count(*) value "
					+ "from country group by country_region";
//		String sql = "select book_genre title, count(*) value "
//					+ "from book group by book_genre";
		
		List<StatVO> list = jdbcTemplate.query(sql, statMapper);
		System.out.println("조회 결과 수 : " + list.size());
		for(StatVO statVO : list) {
			System.out.println(statVO.getTitle() + ", " + statVO.getValue());
		}
	}
}