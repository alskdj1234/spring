<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<!-- 제목 -->
<h1>국가 목록 및 검색</h1> 





<table>
	<thead>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>대륙</th>
			<th>수도</th>
			<th>인구</th>

		</tr>


	</thead>
	<tbody align="center">
		<c:forEach var="countryDto" items="${list}">
			<tr>
				<td>${countryDto.countryNo}</td>
				<td><a href="./detail?countryNo=${countryDto.countryNo}">${countryDto.countryName}</a></td>
				<td>${countryDto.countryRegion}</td>
				<td>${countryDto.countryCapital}</td>
				<td align="right">${countryDto.countryPopulation}</td>



			</tr>

		</c:forEach>
	</tbody>



</table>



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


<!-- 결과 출력 -->
<h2>결과 수 : ${list.size()}</h2>



<c:forEach var="countryDto" items="${list}">
        <hr>
        <div><!-- 비어있는 영역 (자동 줄바뀜) -->
            <h3>[${countryDto.countryRegion}] ${countryDto.countryName}</h3>
            수도 : ${countryDto.countryCapital}<br>
<%--             인구 : ${countryDto.countryPopulation} 명 <br> --%>
            인구 : <fmt:formatNumber value="${countryDto.countryPopulation}" pattern="#,##0"></fmt:formatNumber> 명 
        </div>
</c:forEach>


<h2>
<a href="./insert">
신규 등록 하기
</a>
</h2>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>