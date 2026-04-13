<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--     템플릿 페이지에서 상대경로로 작성할 경우 작동 안 한다(사실상 백프로) -->
<!-- 여러 정보 출력 -->
세션id : ${pageContext.session.id}
loginId : ${sessionScope.loginId}
loginLevel : ${sessionScope.loginLevel}

<h1>KH정보교육원 웹개발 수업과정</h1>

<!-- 메뉴를 회원/비회원, 등급에 맞게 출력 -->
<c:if test="${sessionScope.loginId == null || sessionScope.loginLevel == null}">
	<a href="/">HOME</a>
	<a href="/country/list">국가정보</a>
	<a href="/course/list">강좌정보</a>
	<a href="/member/join">회원가입</a>
	<a href="/member/login">로그인</a>
</c:if>
<c:if test="${sessionScope.loginId !=null && sessionScope.loginLevel !='null'}">
	<c:if test="${sessionScope.loginLevel !='마스터'}">

	
	<a href="/">HOME</a>
	<a href="/country/list">국가정보</a>
	<a href="/course/list">강좌정보</a>
	<a href="/book/list">도서정보</a>
<!-- 	세션에 정보가 있어서 매개변수가 필요가 없다. -->
	<a href="/member/mypage">마이페이지</a>
	<a href="/member/logout">로그아웃</a>
	</c:if>	
	<c:if test="${sessionScope.loginLevel =='마스터'}">

	<a href="/">HOME</a>
	<a href="/country/list">국가정보</a>
	<a href="/course/list">강좌정보</a>
	<a href="/book/list">도서정보</a>
	<a href="/member/logout">로그아웃</a>
	<a href="#">홈페이지 관리</a>
	
	</c:if>	
</c:if>








<hr>
<div style="min-height: 600px">