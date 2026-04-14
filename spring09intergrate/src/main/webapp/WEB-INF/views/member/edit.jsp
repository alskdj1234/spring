<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>내 정보 변경</h1>

<form action="./edit" method="post">
	
	<h2>변경할 정보 입력</h2>
	이메일 <input type="text" inputmode="email" name="memberEmail" value="${memberDto.memberEmail}" required> <br><br>
	닉네임 <input type="text" name="memberNickname" value="${memberDto.memberNickname}" required> <br><br>
	생년월일 <input type="date" name="memberBirth" value="${memberDto.memberBirth}"> <br><br>
	연락처 <input type="text" inputmode="tel" name="memberContact" value="${memberDto.memberContact}"> <br><br>
	우편번호 <input type="text" inputmode="numeric" name="memberPost" value="${memberDto.memberPost}"
				size="6" maxlength="6"> <br><br>
	기본주소 <input type="text" name="memberAddress1" value="${memberDto.memberAddress1}"
				size="80"> <br><br>
	상세주소 <input type="text" name="memberAddress2" value="${memberDto.memberAddress2}"
				size="80"> <br><br>
	상태메세지 <br>
	<input type="text" name="memberMessage" value="${memberDto.memberMessage}" size="80">
<%-- 	<textarea name="memberMessage" rows="5" cols="80">${memberDto.memberMessage}</textarea> --%>
	
	<h2>비밀번호 확인</h2>
	<input type="password" name="memberPassword" required> <br><br>
	
	<c:if test="${param.error != null}">
	비밀번호가 일치하지 않습니다 <br><br>
	</c:if>
	
	<button>정보 변경하기</button>
</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>






