<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>회원 로그인 내역 상세조회</h1>

<form action="./history" method="get">
	<input type="date" name="beginDate" value="${param.beginDate}" required>
	부터
	<input type="date" name="endDate" value="${param.endDate}" required>
	까지
	
	<button>조회하기</button>
</form>

<table border="1" width="1200">
	<thead>
		<tr>
			<th>일시</th>
			<th>접속주소</th>
			<th>에이전트</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="memberHistoryDto" items="${loginHistory}">
		<tr>
			<td>
				<fmt:formatDate value="${memberHistoryDto.memberHistoryTime}" 
										pattern="yyyy-MM-dd HH:mm:ss"/>
			</td>
			<td>${memberHistoryDto.memberHistoryAddress}</td>
			<td>${memberHistoryDto.memberHistoryAgent}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
