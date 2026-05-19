package com.kh.spring09;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest//Spring 환경을 연동한 테스트를 준비해라!
public class Test02통합테스트 {
	//등록된 요소 중 테스트를 원하는 항목을 주입하여 사용할 수 있다
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void test() {
		String sql = "insert into menu values(menu_seq.nextval, ?, ?, ?, ?)";
		Object[] params = {
			"음료", "아무거나", 5000, "N"
		};
		jdbcTemplate.update(sql, params);
	}
}



