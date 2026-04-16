<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>회원 조회</h1>

<form action="./list" method="get">
	
	<select name="column">
		<option value="member_id"
		 ${param.column == "member_id" ? "selected" : ""}>아이디명</option>
		 <option value="member_nickname"
		 ${param.column == "member_nickname" ? "selected" : ""}>닉네임</option>
		 <option value="member_email"
		 ${param.column == "member_email" ? "selected" : ""}>이메일명</option>
		 <option value="member_contact"
		 ${param.column == "member_contact" ? "selected" : ""}>연락처</option>
	 
	</select>
	<input type="text" name="keyword" placeholder="검색어 입력" value="${param.keyword}">
	
	
	<button>검색</button>
</form>
	
	<table>
	
		<thead>
		<tr>
		<th>아이디</th>
		<th>닉네임</th>
		<th>이메일</th>
		<th>연락처</th>
		<th>가입일</th>
		<th>등급</th>
		<th>차단여부</th>	
		</tr>
		</thead>
		
		<tbody align="center" width ="1000">
		<c:forEach var="countryDto" items="${list}">
			<tr>
				<td>${memberDto.memberId}</td>
				<td>${memberDto.memberNickname}</td>
				<td>${memberDto.memberEmail}</td>
				<td>${memberDto.memberContact}</td>
				<td>${memberDto.memberJoin}</td>
				<td>${memberDto.memberLevel}</td>
				<td>${memberDto.member차단여부}</td>
			</tr>
			
								
			
		
		
		
		
		
		
		</c:forEach>
		</tbody>
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	</table>





<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>

