<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>강좌 정보 수정</h1>

<form action="./edit" method="post" enctype="multipart/form-data">
	<input type="hidden" name="lectureNo" value="${lectureDto.lectureNo}">
	
	카테고리 
	<select name="lectureCategory" required>
		<option value="">선택하세요</option>
		<option ${lectureDto.lectureCategory == '이론' ? 'selected' : ''}>이론</option>
		<option ${lectureDto.lectureCategory == '실습' ? 'selected' : ''}>실습</option>
		<option ${lectureDto.lectureCategory == '시험' ? 'selected' : ''}>시험</option>
	</select>
	<br><br>
	강좌명 <input type="text" name="lectureTitle" value="${lectureDto.lectureTitle}" required> <br><br>
	수강시간 <input type="number" name="lectureDuration" min="30" step="30"
					value="${lectureDto.lectureDuration}" required> <br><br>
	수강료 <input type="number" name="lecturePrice" min="1000" step="1000"
					value="${lectureDto.lecturePrice}" required> <br><br>
	강의유형
	<select name="lectureType" required>
		<option value="">선택하세요</option>
		<option ${lectureDto.lectureType == '온라인' ? 'selected' : ''}>온라인</option>
		<option ${lectureDto.lectureType == '오프라인' ? 'selected' : ''}>오프라인</option>
		<option ${lectureDto.lectureType == '혼합' ? 'selected' : ''}>혼합</option>
	</select>
	<br><br>
	
	미리보기 이미지 <input type="file" name="attach" accept=".png, .jpg" multiple>
	<br>
	기존 이미지들 <br>
	<c:forEach var="attachNo" items="${images}">
		<img src="/download/modern?attachNo=${attachNo}" width="50">
	</c:forEach>
	
	<br><br>
	<button>수정하기</button>
</form>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>