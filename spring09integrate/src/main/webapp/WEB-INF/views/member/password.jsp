<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<form action="./password" method="post">

<div class="container w-600 mt-50 mb-50">
	<div class="cell">
		<h1 class="mt-0 mb-0">비밀번호 입력</h1>
	</div>
	<div class="cell">
		비밀번호 변경을 위해 기존 비밀번호를 한번 더 입력하고 신규 비밀번호를 입력해주세요
	</div>
	
	<c:if test="${param.error != null}">
	<div class="cell red">
		오류 : 비밀번호가 불일치하거나 동일한 값으로 변경할 수 없습니다
	</div>
	</c:if>
	
	<div class="cell mt-40">
		<label>기존 비밀번호 <i class="fa-solid fa-asterisk red"></i></label>
		<input type="password" name="originPw" required class="field w-100">
	</div>
	<div class="cell">
		<label>신규 비밀번호 <i class="fa-solid fa-asterisk red"></i></label>
		<input type="password" name="changePw" required class="field w-100">
	</div>
	<div class="cell">
		<label>신규 비밀번호 재확인(구현예정) <i class="fa-solid fa-asterisk red"></i></label>
		<input type="password" class="field w-100">
	</div>
	
	<div class="mt-50">
		<button type="submit" class="btn btn-negative w-100">
			<i class="fa-solid fa-lock fa-fade"></i>
			<span>비밀번호 변경하기</span>
		</button>
	</div>
</div>

</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>