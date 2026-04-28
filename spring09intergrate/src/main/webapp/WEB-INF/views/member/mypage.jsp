<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>


<h1>${memberDto.memberNickname}님의 개인 정보</h1>

<img src="./profile?memberId=${memberDto.memberId}" width="100" height="100"
		style="border-radius:50%; box-shadow:0 0 1px 0 black">

<ul>
	<li>아이디 : ${memberDto.memberId}</li>
	<li>이메일 : ${memberDto.memberEmail}</li>
	<li>닉네임 : ${memberDto.memberNickname}</li>
	<li>생년월일 : ${memberDto.memberBirth}</li>
	<li>연락처 : ${memberDto.memberContact}</li>
	<li>주소 : ${memberDto.memberPost} ${memberDto.memberAddress1} ${memberDto.memberAddress2}</li>
	<li>등급 : ${memberDto.memberLevel}</li>
	<li>상태메세지 : ${memberDto.memberMessage}</li>
	<li>포인트 : <fmt:formatNumber value="${memberDto.memberPoint}" pattern="#,##0"/></li>
	<li>가입일 : <fmt:formatDate value="${memberDto.memberJoin}" pattern="y년 M월 d일 E a h시 m분"/></li>
	<li>최종로그인 : <fmt:formatDate value="${memberDto.memberLogin}" pattern="y년 M월 d일 E a h시 m분"/></li>
<%-- 	<li>최종변경일 : <fmt:formatDate value="${memberDto.memberChange}" pattern="y년 M월 d일 E a h시 m분"/></li> --%>
</ul>

<hr>

<h1>최근 로그인 이력 <a href="./history">더보기</a></h1>

<table>
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
			<td>${memberHistoryDto.memberHistoryTime}</td>
			<td>${memberHistoryDto.memberHistoryAddress}</td>
			<td>${memberHistoryDto.memberHistoryAgent}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>


<!-- <h2><a href="/member/password">비밀번호 변경하기</a></h2> -->
<h2><a href="./password">비밀번호 변경하기</a></h2>
<h2><a href="./edit">개인정보 변경하기</a></h2>
<h2><a href="./goodbye">회원 탈퇴하기</a></h2>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>






