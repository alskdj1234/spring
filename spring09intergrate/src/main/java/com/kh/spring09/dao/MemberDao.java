package com.kh.spring09.dao;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring09.dto.MemberDto;
import com.kh.spring09.mapper.MemberMapper;
import com.kh.spring09.vo.PageVO;

@Repository
public class MemberDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private MemberMapper memberMapper;
	
	//등록 메소드
	public void insert(MemberDto memberDto) {
		String sql = "insert into member("
						+ "member_id, member_email, member_password, "
						+ "member_nickname, member_birth, member_contact, "
						+ "member_post, member_address1, member_address2, "
						+ "member_message"
					+ ") "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Object[] params = {
			memberDto.getMemberId(), memberDto.getMemberEmail(),
			memberDto.getMemberPassword(), memberDto.getMemberNickname(),
			memberDto.getMemberBirth(), memberDto.getMemberContact(),
			memberDto.getMemberPost(), memberDto.getMemberAddress1(), 
			memberDto.getMemberAddress2(), memberDto.getMemberMessage()
		};
		jdbcTemplate.update(sql, params);
	}
	
	//상세 메소드
	public MemberDto selectOne(String memberId) {
		String sql = "select * from member where member_id = ?";
		Object[] params = { memberId };
		List<MemberDto> list = jdbcTemplate.query(sql, memberMapper, params);
		return list.isEmpty() ? null : list.get(0);
	}
	
	//수정 메소드
	public boolean updateMemberLogin(String memberId) {
		String sql = "update member set member_login=systimestamp where member_id=?";
		Object[] params = { memberId };
		return jdbcTemplate.update(sql, params) > 0;
	}
//	public boolean updateMemberPassword(String memberId, String memberPw) {
	public boolean updateMemberPassword(MemberDto memberDto) {
		String sql = "update member "
					+ "set member_password=?, member_change=systimestamp "
					+ "where member_id=?";
		Object[] params = {memberDto.getMemberPassword(), memberDto.getMemberId()};
		return jdbcTemplate.update(sql, params) > 0;
	}
	public boolean update(MemberDto memberDto) {
		String sql = "update member "
					+ "set member_email=?, member_nickname=?, member_birth=?, "
						+ "member_contact=?, member_post=?, member_address1=?, "
						+ "member_address2=?, member_message=? "
					+ "where member_id=?";
		Object[] params = {
			memberDto.getMemberEmail(), memberDto.getMemberNickname(),
			memberDto.getMemberBirth(), memberDto.getMemberContact(),
			memberDto.getMemberPost(), memberDto.getMemberAddress1(),
			memberDto.getMemberAddress2(), memberDto.getMemberMessage(),
			memberDto.getMemberId()
		};
		return jdbcTemplate.update(sql, params) > 0;
	}
	
	//삭제
	public boolean delete(String memberId) {
		String sql = "delete member where member_id=?";
		Object[] params = { memberId };
		return jdbcTemplate.update(sql, params) > 0;
	}
	
	public List<MemberDto> selectList (PageVO pageVO){
		if(pageVO.isList())
			return List.of();
		Set<String> allow = Set.of("member_id","member_nickname","member_email","member_contact");
		if(!allow.contains(pageVO.getColumn()))return List.of();
		
		String sql = "select * from member where instr(" + pageVO.getColumn() + ",?)>0 and member_level != '마스터' order by "+pageVO.getColumn()+" asc, member_id asc";
		
		Object[] params = {pageVO.getKeyword()};
		
		return jdbcTemplate.query(sql, memberMapper, params);
		
	}
	
	public List<MemberDto> detailInfo(String memberId) {
		String sql = "select * from member where member_id = ?";
		Object[] params = {memberId};
		return jdbcTemplate.query(sql, memberMapper, params);
		
	}
	
	//차단 수정
	public boolean updateMemberBlock(MemberDto memberDto) {
		String sql = "update member set member_block = ? where member_id=?";
		Object[] params = {memberDto.getMemberBlock(),memberDto.getMemberId()};
		return jdbcTemplate.update(sql, params)>0;
	}

	
	//관리자 수정
	public boolean updateByMaster(MemberDto memberDto) {
		String sql = "update member "
				+ 			"set member_email=?, member_nickname=? "
				+ "member_birth=?, member_contact=?, member_post=? "
				+ "member_address1=?, member_address2=?, member_level=?, "
				+ "member_point=?, member_message=? "
				+ "where member_id=?";
		
		Object [] params = {
				memberDto.getMemberEmail(),memberDto.getMemberNickname(),
				memberDto.getMemberBirth(),memberDto.getMemberContact(),
				memberDto.getMemberPost(),memberDto.getMemberAddress1(),
				memberDto.getMemberAddress2(), memberDto.getMemberLevel(),
				memberDto.getMemberPoint(), memberDto.getMemberMessage(),
				memberDto.getMemberId()
		};
		
		return jdbcTemplate.update(sql, params)>0;
			
	}
	
	public int count() {
		String sql = "select count(*) from member";
		return jdbcTemplate.queryForObject(sql, int.class);
	}
	public int count(PageVO pageVO) {
		if(pageVO.isList()) return count();
		Set<String> allow = Set.of("member_id","member_nickname","member_email","member_contact");
		if(!allow.contains(pageVO.getColumn()))return 0;
		String sql = "select count(*) from member "
					+ "where instr("+pageVO.getColumn()+", ?) > 0";
		Object[] params = { pageVO.getKeyword() };
		return jdbcTemplate.queryForObject(sql, int.class, params);
	}

	public void connect(String memberId, int attachNo) {
		String sql = "insert into memeber_profile(member_id, attach_no) values(?,?)";
		Object[] params = { memberId,attachNo};
		jdbcTemplate.update(sql,params);
	}
}











