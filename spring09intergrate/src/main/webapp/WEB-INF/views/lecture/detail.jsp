<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container w-900 mt-50 mb-50">
	<div class="cell">
		<h2>강좌 상세정보</h2>
	</div>
	
	<div class="cell">
		<c:if test="${images.isEmpty()}">
			<span class="gray">이미지 미리보기가 준비되지 않은 과정입니다.</span>
		</c:if>
		<c:if test="${images.size() > 0}">
			<h3>이미지 미리보기</h3>		
		</c:if>
	</div>
	
	<div class="cell mt-50">
		<div class="flex-area">
			<div class="w-200">강좌 코드</div>
			<div class="flex-fill blue">${lectureDto.lectureNo}</div>
		</div>
	</div>
	<div class="cell mt-20">
		<div class="flex-area">
			<div class="w-200">강좌 카테고리</div>
			<div class="flex-fill blue">${lectureDto.lectureCategory}</div>
		</div>
	</div>
	<div class="cell mt-20">
		<div class="flex-area">
			<div class="w-200">강좌명</div>
			<div class="flex-fill blue">${lectureDto.lectureTitle}</div>
		</div>
	</div>
	<div class="cell mt-20">
		<div class="flex-area">
			<div class="w-200">강의시간(H)</div>
			<div class="flex-fill blue">${lectureDto.lectureDuration} H</div>
		</div>
	</div>
	<div class="cell mt-20">
		<div class="flex-area">
			<div class="w-200">수강료</div>
			<div class="flex-fill blue">${lectureDto.lecturePrice} KRW</div>
		</div>
	</div>
	<div class="cell mt-20">
		<div class="flex-area">
			<div class="w-200">강좌 유형</div>
			<div class="flex-fill blue">${lectureDto.lectureType}</div>
		</div>
	</div>
	
	<hr class="mt-50">
	
	<div class="cell right">
		<a class="btn btn-positive" href="./insert">신규 강좌 등록하기</a>
		<c:if test="${sessionScope.loginId != null}">
		<a class="btn btn-negative" href="./edit?lectureNo=${lectureDto.lectureNo}">강좌 수정하기</a>
		<a class="btn btn-negative" href="./delete?lectureNo=${lectureDto.lectureNo}">강좌 삭제하기</a>
		</c:if>
		<a class="btn btn-neutral" href="./list">목록으로 돌아가기</a>
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>

