<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>강좌 상세정보</h1>

<ul>
	<li>강좌코드번호 : ${lectureDto.lectureNo}</li>
	<li>강좌카테고리 : ${lectureDto.lectureCategory}</li>
	<li>강좌명 : ${lectureDto.lectureTitle}</li>
	<li>강의시간 : ${lectureDto.lectureDuration}H</li>
	<li>수강료 : ${lectureDto.lecturePrice} KRW</li>
	<li>강의유형 : ${lectureDto.lectureType}</li>
</ul>

<h2>이미지 미리보기</h2>

<%-- <img src="./image?lectureNo=${lectureDto.lectureNo}" width="100"> --%>
<c:forEach var="attachNo" items="${images}">
	<img src="/download/legacy?attachNo=${attachNo}" width="100" height="100">
</c:forEach>

<h2><a href="./list">목록으로 돌아가기</a></h2>
<h2><a href="./insert">신규 강좌 등록하기</a></h2>
<h2><a href="./edit?lectureNo=${lectureDto.lectureNo}">강좌 수정하기</a></h2>
<h2><a href="./delete?lectureNo=${lectureDto.lectureNo}">강좌 삭제하기</a></h2>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>

