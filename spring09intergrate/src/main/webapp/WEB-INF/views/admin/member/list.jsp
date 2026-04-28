<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>회원 관리</h1>

<form action="./list" method="get">
	<select name="column">
		<option value="member_id" ${param.column == 'member_id' ? 'selected' : ''}>아이디</option>
		<option value="member_email" ${param.column == 'member_email' ? 'selected' : ''}>이메일</option>
		<option value="member_nickname" ${param.column == 'member_nickname' ? 'selected' : ''}>닉네임</option>
		<option value="member_contact" ${param.column == 'member_contact' ? 'selected' : ''}>연락처</option>
	</select>
	<input type="text" name="keyword" value="${param.keyword}" required>
	<button>검색</button>
</form>

<c:if test="${param.column != null && param.keyword != null}">

	<h3>총 ${list.size()}명의 회원이 검색되었습니다</h3>

	<c:if test="${list.size() > 0}">
		
		<table border="1" width="1000">
			<thead>
				<tr>
					<th>아이디</th>
					<th>이메일</th>
					<th>닉네임</th>
					<th>연락처</th>
					<th>가입일</th>
					<th>등급</th>
					<th>차단여부</th>
					<th>관리</th>
				</tr>
			</thead>
			<tbody align="center">
				<c:forEach var="memberDto" items="${list}">
				<tr>
					<td>${memberDto.memberId}</td>
					<td>${memberDto.memberEmail}</td>
					<td>${memberDto.memberNickname}</td>
					<td>${memberDto.memberContact}</td>
					<td><fmt:formatDate value="${memberDto.memberJoin}" pattern="yyyy-MM-dd"/></td>
					<td>${memberDto.memberLevel}</td>
					<td>${memberDto.memberBlock}</td>
					<td>
						<a href="./detail?memberId=${memberDto.memberId}">이동</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	
	</c:if>
</c:if>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>




