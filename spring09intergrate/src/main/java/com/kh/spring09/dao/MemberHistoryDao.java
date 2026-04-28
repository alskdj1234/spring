package com.kh.spring09.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring09.dto.MemberHistoryDto;
import com.kh.spring09.mapper.MemberHistoryMapper;

@Repository
public class MemberHistoryDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private MemberHistoryMapper memberHistoryMapper;
	
	//등록
	public void insert(MemberHistoryDto memberHistoryDto) {
		String sql = "insert into member_history("
						+ "member_history_no, member_history_origin, "
						+ "member_history_address, member_history_agent "
					+ ") values(member_history_seq.nextval, ?, ?, ?)";
		Object[] params = {
			memberHistoryDto.getMemberHistoryOrigin(),
			memberHistoryDto.getMemberHistoryAddress(),
			memberHistoryDto.getMemberHistoryAgent()
		};
		jdbcTemplate.update(sql, params);
	}
	
	//조회(Top N + 아이디)
	public List<MemberHistoryDto> selectList(String memberHistoryOrigin, int beginRow, int endRow) {
		String sql = "select * from ("
						+ "select rownum RN, TMP.* from ("
							+ "select * from member_history "
							+ "where member_history_origin=? "
							+ "order by member_history_time desc, member_history_no desc"
						+ ")TMP"
					+ ") where RN between ? and ?";
		Object[] params = {memberHistoryOrigin, beginRow, endRow};
		return jdbcTemplate.query(sql, memberHistoryMapper, params);
	}
	public List<MemberHistoryDto> selectList(String memberHistoryOrigin, String beginDate, String endDate, int beginRow, int endRow) {
		//날짜가 없다면 검색결과를 보여주지 마세요
		if(beginDate == null || endDate == null) return List.of();
		
		String sql = "select * from ("
						+ "select rownum RN, TMP.* from ("
							+ "select * from member_history " 
							+ "where "
								+ "member_history_origin = ? "
								+ "and "
								+ "("
									+ "member_history_time between "
									+ "to_timestamp(? || ' ' || '00:00:00.000', 'YYYY-MM-DD HH24:MI:SS.FF3') "
									+ "and "
									+ "to_timestamp(? || ' ' || '23:59:59.999', 'YYYY-MM-DD HH24:MI:SS.FF3') "
								+ ") "
							+ "order by member_history_time desc, member_history_no desc "
						+ ")TMP"
					+ ") where RN between ? and ?";
		Object[] params = {
			memberHistoryOrigin, beginDate, endDate, beginRow, endRow
		};
		return jdbcTemplate.query(sql, memberHistoryMapper, params);
	}
}







