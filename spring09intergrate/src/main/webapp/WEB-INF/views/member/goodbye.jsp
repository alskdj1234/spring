<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container w-400 mt-50 mb-50">
	<div class="cell center">
		<h1>회원 탈퇴</h1>
	</div>
	<div class="cell center">
		탈퇴를 위해 비밀번호를 한번 더 입력해주시기 바랍니다<br><br>
	</div>
	
	<form action="./goodbye" method="post">
	<div class="cell mt-40 flex-area">
		<input type="password" name="memberPassword" required class="field">
		<button class="btn btn-negative ms-20">탈퇴하기</button>
	</div>
	</form>
	
	<c:if test="${param.error != null}">
	<div class="cell center red">
		비밀번호가 일치하지 않습니다
	</div>
	</c:if>
</div>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>