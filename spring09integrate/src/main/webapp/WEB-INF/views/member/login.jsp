<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<form action="./login" method="post">

<div class="container w-500 mt-50 mb-50">
	<div class="cell center">
		<h1>로그인</h1>
	</div>
	<div class="cell mt-50">
		<input type="text" name="memberId" placeholder="아이디" required
				class="field w-100">
	</div>	
	<div class="cell mt-20">
		<input type="password" name="memberPassword" placeholder="비밀번호" required
				class="field w-100">
	</div>
	<div class="cell mt-50">
		<button class="btn btn-positive w-100">로그인</button>
	</div>
	<c:if test="${param.error != null}">
	<div class="cell center red">
		입력한 정보가 일치하지 않습니다
	</div>
	</c:if>
</div>

</form>



<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>






