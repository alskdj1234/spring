<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 여러 가지 정보들을 출력 -->
세션ID : ${pageContext.session.id} , 
loginId : ${sessionScope.loginId} ,
loginLevel : ${sessionScope.loginLevel} 
    
<!-- 템플릿 페이지에서 상대경로로 작성할 경우 작동하지 않을 수 있다 -->
<h1>KH정보교육원 웹개발 수업과정</h1>

<!-- 
	메뉴를 회원/비회원, 등급에 맞게 구분하여 출력
	- 비회원 : session의 loginId, loginLevel 중 하나 이상이 null인 경우
	- 회원 : session의 loginId, loginLevel 모두 null이 아니고 loginLevel이 '마스터'가 아님
	- 관리자 : session의 loginId, loginLevel 모두 null이 아니고 loginLevel이 '마스터'임
-->
<c:if test="${sessionScope.loginId == null || sessionScope.loginLevel == null}">
	
	<!-- 비회원 메뉴 -->
	<a href="/">HOME</a>
	<a href="/country/list">국가정보</a>
	<a href="/lecture/list">강좌정보</a>
	<a href="/board/list">자유게시판</a>
	<a href="/member/join">회원가입</a>
	<a href="/member/login">로그인</a>
	
</c:if>
<c:if test="${sessionScope.loginId != null && sessionScope.loginLevel != null}">
	<c:if test="${sessionScope.loginLevel != '마스터'}">
	
	<!-- 회원 메뉴 -->
	<a href="/">HOME</a>
	<a href="/country/list">국가정보</a>
	<a href="/lecture/list">강좌정보</a>
	<a href="/book/list">도서정보</a>
	<a href="/board/list">자유게시판</a>
	<a href="/member/mypage">마이페이지</a>
	<a href="/member/logout">로그아웃</a>
	
	</c:if>
	<c:if test="${sessionScope.loginLevel == '마스터'}">
	
	<!-- 관리자 메뉴 -->
	<a href="/">HOME</a>
	<a href="/country/list">국가정보</a>
	<a href="/lecture/list">강좌정보</a> 
	<a href="/book/list">도서정보</a>
	<a href="/board/list">자유게시판</a>
	<a href="/member/logout">로그아웃</a>
	<a href="/admin/member/list">회원 관리</a>
	
	</c:if>	
</c:if>


<hr>

<div style="min-height: 300px">