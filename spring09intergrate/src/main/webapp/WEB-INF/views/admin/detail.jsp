<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>


<div>
<ul>

<li>아이디 : ${everyInfo.memberId}</li>
	<li>이메일 : ${everyInfo.memberEmail}</li>
	<li>닉네임 : ${everyInfo.memberNickname}</li>
	<li>생년월일 : ${everyInfo.memberBirth}</li>
	<li>연락처 : ${everyInfo.memberContact}</li>
	<li>주소 : ${everyInfo.memberPost} ${everyInfo.memberAddress1} ${everyInfo.memberAddress2}</li>
	<li>등급 : ${everyInfo.memberLevel}</li>
	<li>상태메세지 : ${everyInfo.memberMessage}</li>
	<li>포인트 : <fmt:formatNumber value="${everyInfo.memberPoint}" pattern="#,##0"/></li>
	<li>가입일 : <fmt:formatDate value="${everyInfo.memberJoin}" pattern="y년 M월 d일 E a h시 m분"/></li>
	<li>최종로그인 : <fmt:formatDate value="${everyInfo.memberLogin}" pattern="y년 M월 d일 E a h시 m분"/></li>
	<li>최종변경일 : <fmt:formatDate value="${everyInfo.memberChange}" pattern="y년 M월 d일 E a h시 m분"/></li>
	<li>차단여부 : ${everyInfo.memberBlock}</li>
	<li>탈퇴신청일 : <fmt:formatDate value="${everyInfo.memberExitTime}" pattern="y년 M월 d일 E a h시 m분"/></li>


</ul>
<h2>최근 로그인 이력</h2>
</div>


<c:forEach var="memberHistoryDto" items="${personalHistory}">


<ul>
<li>

${memberHistoryDto.memberHistoryTime}

</li>

</ul>


</c:forEach>
<h2><a href="./block?memberId=${memberDto.memberId}">${memberDto.memberBlock== 'y' ? '해제' : '차단' }</a></h2>
<h2><a href="./edit?memberId=${memberDto.memberId}">${memberDto.memberId}의 정보 변경</a></h2>
<h2><a href="./list">목록으로</a></h2>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>