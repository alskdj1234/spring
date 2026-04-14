<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>회원 탈퇴</h1>
탈퇴를 위해 비밀번호를 한번 더 입력해주시기 바랍니다<br><br>

<form action="./goodbye" method="post">
	비밀번호 <input type="password" name="memberPassword" required>
	<button>탈퇴하기</button>
</form>

<c:if test="${param.error != null}">
	비밀번호가 일치하지 않습니다
</c:if>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include></html>