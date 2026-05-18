<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container w-600">
	<!-- 제목 -->
	<div class="cell center">
		<h1 class="mb-0">국가 목록 및 검색</h1>
	</div>
	
	<!-- 신규 등록 링크 -->
	<div class="cell right">
		<h3>
			<a href="./insert" class="link">
				<i class="fa-solid fa-plus"></i>
				<span>신규 등록하기</span>
			</a>
		</h3>
	</div>	
	
	<!-- 검색창 -->
	<div class="cell center">
		<form action="./list">
			<select name="column" class="field">
				<option value="country_name" ${param.column == "country_name" ? "selected" : ""}>국가명</option>
				<option value="country_region" ${param.column == "country_region" ? "selected" : ""}>대륙명</option>
				<option value="country_capital" ${param.column == "country_capital" ? "selected" : ""}>수도명</option>
			</select>
			<input type="text" name="keyword" placeholder="검색어 입력"
						value="${param.keyword}" class="field" required>
			<button type="submit" class="btn btn-positive">검색</button>
		</form>
	</div>
	
	<!-- 결과 출력 -->
	<div class="cell">
		결과 수 : ${pageVO.beginRownum} ~ ${pageVO.endRownum} (총 ${pageVO.count}개)
	</div>	
	
	<!-- 테이블 -->
	<div class="cell">
		<table class="table table-stripe">
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
	</div>
	
	<div class="cell mt-20">
		<jsp:include page="/WEB-INF/views/template/pagination.jsp"></jsp:include>
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>


