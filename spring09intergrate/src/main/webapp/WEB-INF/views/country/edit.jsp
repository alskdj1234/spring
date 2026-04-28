<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>국가 정보 수정</h1>

<form action="./edit" method="post" enctype="multipart/form-data">
	<!-- 	기본키(번호, countryNo)를 숨김 첨부 -->
	<input type="hidden" name="countryNo" value="${countryDto.countryNo}">
	
	<!-- 	입력창에 value를 표시해서 기존의 값을 출력해야 한다 -->
	대륙 <input type="text" name="countryRegion" value="${countryDto.countryRegion}"> <br><br>
	이름 <input type="text" name="countryName" value="${countryDto.countryName}"> <br><br>
	수도 <input type="text" name="countryCapital" value="${countryDto.countryCapital}"> <br><br>
	인구 <input type="text" name="countryPopulation" value="${countryDto.countryPopulation}"> <br><br>
	
	<!-- 파일선택창에는 value를 줄 수 없다(보안상의 이유로) -->
	국기 <input type="file" name="attach" accept=".png, .jpg"> <br>
	
	(기존이미지) <br>
	<img src="./flag?countryNo=${countryDto.countryNo}" width="80">
	<br><br>
	
	<button>수정하기</button>
</form>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
