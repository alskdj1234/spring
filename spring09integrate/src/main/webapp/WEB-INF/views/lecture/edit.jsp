<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<form action="./edit" method="post" enctype="multipart/form-data">
	<input type="hidden" name="lectureNo" value="${lectureDto.lectureNo}">
	
	<div class="container w-600 mt-50 mb-50">
		<div class="cell center">
			<h1>강좌 정보 수정</h1>
		</div>
		
		<div class="cell">
			<label>강좌명 <i class="fa-solid fa-asterisk red"></i></label>
			<input type="text" name="lectureTitle" placeholder="정보처리 산업기사 필기" 
					required class="field w-100" value="${lectureDto.lectureTitle}">
		</div>
		<div class="cell">
			<label>카테고리 <i class="fa-solid fa-asterisk red"></i></label>
			<select name="lectureCategory" required class="field w-100">
				<option value="">선택하세요</option>
				<option ${lectureDto.lectureCategory == '이론' ? 'selected':''}>이론</option>
				<option ${lectureDto.lectureCategory == '실습' ? 'selected':''}>실습</option>
				<option ${lectureDto.lectureCategory == '시험' ? 'selected':''}>시험</option>
			</select>
		</div>
		<div class="cell">
			<label>강의시간 <i class="fa-solid fa-asterisk red"></i></label>
			<input type="number" name="lectureDuration" min="30" step="30" 
				value="${lectureDto.lectureDuration}" required class="field w-100">
		</div>
		<div class="cell">
			<label>수강료 <i class="fa-solid fa-asterisk red"></i></label>
			<input type="text" inputmode="numeric" name="lecturePrice" 
				value="${lectureDto.lecturePrice}" required class="field w-100">
		</div>
		<div class="cell">
			<label>강의형태 <i class="fa-solid fa-asterisk red"></i></label>
			<select name="lectureType" required class="field w-100">
				<option value="">선택하세요</option>
				<option ${lectureDto.lectureType == '온라인' ? 'selected' : ''}>온라인</option>
				<option ${lectureDto.lectureType == '오프라인' ? 'selected' : ''}>오프라인</option>
				<option ${lectureDto.lectureType == '혼합' ? 'selected' : ''}>혼합</option>
			</select>
		</div>
		
		<div class="cell mt-50">
			<label>미리보기 이미지</label>
			<input type="file" name="attach" accept=".png, .jpg" multiple class="field w-100">
		</div>
		<div class="cell mb-0 gray">이미 등록된 이미지</div>
		<div class="cell mt-0">
			<c:forEach var="attachNo" items="${images}">
				<img src="/download/modern?attachNo=${attachNo}" width="80" class="me-20">
			</c:forEach>
		</div>
		
		<div class="cell mt-50">
			<button class="btn btn-positive w-100">
				<i class="fa-solid fa-pen"></i>
				<span>강좌 정보 수정하기</span>
			</button>
		</div>
	</div>
	
</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>