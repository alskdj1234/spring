<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<!-- 제목 -->
<h1>강좌 목록 및 검색</h1>

<h2>
	<a href="./insert"> 신규 등록 하기 </a>
</h2>

<table border="1" width="800">

	<thead align="center">
		<tr>
			<td>번호</td>
			<td width="40%">이름</td>
			<td>카테고리</td>
			<td>수강료</td>
			<td>강의 시간</td>
			<td>강의 유형</td>


		</tr>

	</thead>

	<tbody align="center">
		<c:forEach var="courseDto" items="${list}">

			<tr>
				
			
				<td>
				
				${courseDto.courseNo}</td>
				<td><a href="./detail?courseNo=${courseDto.courseNo}">${courseDto.courseName}</a></td>
				<td>
				<a href="list?column=category&keyword=${courseDto.category}">
				${courseDto.category}</a></td>
				<td>
				
				<fmt:formatNumber value ="${courseDto.fee}" pattern="#,##0"> </fmt:formatNumber></td>
				<td>${courseDto.lectureTime}시간</td>
				<td>${courseDto.classType}</td>
			</tr>
		</c:forEach>


	</tbody>






</table>



<!-- 검색 창 -->
<form action="./list" method="get">
	<select name="column">
		<option value="course_name"
			${param.column == "course_name" ? "selected" : ""}>강좌명</option>
		<option value="category"
			${param.column == "category" ? "selected" : ""}>카테고리</option>
		<option value="class_type"
			${param.column == "class_type" ? "selected" : ""}>수업방식</option>
	</select> <input type="text" name="keyword" placeholder="검색어 입력" required
		value="${param.keyword}">
	<button>검색</button>
</form>


<!-- 결과 출력 -->
<h2>결과 수 : ${list.size()}</h2>




<c:forEach var="courseDto" items="${list}">
	<div>

		<h3>강좌명 : ${courseDto.courseName} 카테고리 : ${courseDto.category}</h3>
		시간 : ${courseDto.lectureTime}시간 비용 :
		<fmt:formatNumber value="${courseDto.fee}" pattern="#,##0">
		</fmt:formatNumber>


		수업 방식 : ${courseDto.classType}






	</div>





</c:forEach>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>