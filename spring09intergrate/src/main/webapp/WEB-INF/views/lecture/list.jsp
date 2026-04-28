<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<!-- 제목 -->
<h1>강좌 목록</h1>

<!-- 검색창 -->
<form action="./list" method="get">
	<select name="column">
		<option value="lecture_title" ${param.column == "lecture_title" ? "selected" : ""}>강좌이름</option>
		<option value="lecture_category" ${param.column == "lecture_category" ? "selected" : ""}>카테고리</option>
		<option value="lecture_type" ${param.column == 'lecture_type' ? 'selected' : ''}>강좌유형</option>
	</select>
	<input type="text" name="keyword" value="${param.keyword}" required>
	<button>검색</button>
</form>

<!-- 등록 링크 -->
<a href="./insert">신규 등록하기</a>

<!-- 결과 출력 -->
<table border="1" width="600">
	<thead>
		<tr>
			<th>강좌번호</th>
			<th>분류</th>
			<th width="40%">강좌명</th>
			<th>수강료</th>
			<th>수업시간</th>
			<th>유형</th>
		</tr>
	</thead>
	<tbody align="center">
		<c:forEach var="lectureDto" items="${list}">
		<tr>
			<td>${lectureDto.lectureNo}</td>
			<td>
				<!-- 카테고리를 클릭하면 해당 카테고리의 강좌만 보이게 -->
				<a href="./list?column=lecture_category&keyword=${lectureDto.lectureCategory}">
					${lectureDto.lectureCategory}
				</a>
			</td>
			<td>
				<!-- 제목을 클릭하면 번호를 전달하여 상세정보 페이지로 이동 -->
				<a href="./detail?lectureNo=${lectureDto.lectureNo}">
					${lectureDto.lectureTitle}
				</a>
			</td>
			<td align="right">
				<fmt:formatNumber value="${lectureDto.lecturePrice}" pattern="#,##0"></fmt:formatNumber>
			</td>
			<td>${lectureDto.lectureDuration}</td>
			<td>${lectureDto.lectureType}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<%--
<c:forEach var="lectureDto" items="${list}">
<div>
	<h3>
		[${lectureDto.lectureNo}] 
		${lectureDto.lectureTitle}
	</h3>
	카테고리 : ${lectureDto.lectureCategory} <br>
	수업시간 : ${lectureDto.lectureDuration}H <br>
	수강료 : <fmt:formatNumber value="${lectureDto.lecturePrice}" pattern="#,##0"/> 원 <br>
	수업유형 : ${lectureDto.lectureType}
</div>
</c:forEach>
--%>

<jsp:include page="/WEB-INF/views/template/pagination.jsp"></jsp:include>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>



