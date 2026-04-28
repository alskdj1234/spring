<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<!-- 제목 -->
<h1>국가 목록 및 검색</h1>

<!-- 검색창 -->
<form action="./list">
	<select name="column">
		<option value="country_name" ${param.column == "country_name" ? "selected" : ""}>국가명</option>
		<option value="country_region" ${param.column == "country_region" ? "selected" : ""}>대륙명</option>
		<option value="country_capital" ${param.column == "country_capital" ? "selected" : ""}>수도명</option>
	</select>
	<input type="text" name="keyword" placeholder="검색어 입력"
				value="${param.keyword}">
	<button>검색</button>
</form>

<!-- 신규 등록 링크 -->
<a href="./insert">신규 등록하기</a>

<!-- 결과 출력 -->
<h2>결과 수 : ${pageVO.beginRownum} ~ ${pageVO.endRownum} (총 ${pageVO.count}개)</h2>

<!-- 테이블 -->
<table border="1" width="600">
	<!-- 제목 영역 -->
	<thead>
		<tr>
			<th>번호</th>
			<th>대륙</th>
			<th>이름</th>
			<th>수도</th>
			<th>인구</th>
		</tr>
	</thead>
	<!-- 데이터 영역 -->
	<tbody align="center">
		<c:forEach var="countryDto" items="${list}">
		<tr>
			<td>${countryDto.countryNo}</td>
			<td>${countryDto.countryRegion}</td>
			<td>
				<img src="./flag?countryNo=${countryDto.countryNo}" width="20">
			
				<a href="./detail?countryNo=${countryDto.countryNo}">
					${countryDto.countryName}
				</a>
			</td>
			<td>${countryDto.countryCapital}</td>
			<td align="right">
				<fmt:formatNumber value="${countryDto.countryPopulation}" 
									pattern="#,##0"/>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<%--
<!-- for(CountryDto countryDto : list) -->
<c:forEach var="countryDto" items="${list}">
	<hr>
	<div><!-- 비어있는 영역 (자동 줄바뀜) -->
		<h3>[${countryDto.countryRegion}] ${countryDto.countryName}</h3>
		수도 : ${countryDto.countryCapital} <br>
		인구 : <fmt:formatNumber value="${countryDto.countryPopulation}" 
								pattern="#,##0"></fmt:formatNumber> 명
	</div>
</c:forEach>
 --%>


<jsp:include page="/WEB-INF/views/template/pagination.jsp"></jsp:include>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>



