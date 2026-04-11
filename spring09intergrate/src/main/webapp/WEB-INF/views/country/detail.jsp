<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<h1>국가 상세정보</h1>

<!-- 
	리스트 태그(ul, ol)
	- ul은 순서가 없는 리스트 (unorder list)
	- ol은 순서가 있는 리스트 (order list)
	- li는 리스트 내부의 항목 (list item)
-->
<ul>
	<li>번호 : ${countryDto.countryNo}</li>
	<li>대륙 : ${countryDto.countryRegion}</li>
	<li>이름 : ${countryDto.countryName}</li>
	<li>수도 : ${countryDto.countryCapital}</li>
	<li>인구 : ${countryDto.countryPopulation}명</li>
</ul>

<h2><a href="./list">목록으로 이동</a></h2>
<h2><a href="./insert">신규 등록</a></h2>
<h2><a href="./edit?countryNo=${countryDto.countryNo}">수정하기</a></h2>
<h2><a href="./delete?countryNo=${countryDto.countryNo}">삭제하기</a></h2>
<%-- <h2><a href="./delete?countryNo=${param.countryNo}">삭제하기</a></h2> --%>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>