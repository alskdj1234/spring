<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<form action="./edit" method="post" enctype="multipart/form-data" autocomplete="off">
<!-- 	기본키(번호, countryNo)를 숨김 첨부 -->
<input type="hidden" name="countryNo" value="${countryDto.countryNo}">

<div class="container w-400 mt-50 mb-50">
	<div class="cell center">
		<h1>국가 정보 수정</h1>
	</div>
	
	<div class="cell">
		<label>대륙 <i class="fa-solid fa-asterisk red"></i></label>
		<select class="field w-100" name="countryRegion" required>
            <option value="">선택하세요</option>
            <option ${countryDto.countryRegion == '아시아' ? 'selected' : ''}>아시아</option>
            <option ${countryDto.countryRegion == '아프리카' ? 'selected' : ''}>아프리카</option>
            <option ${countryDto.countryRegion == '북아메리카' ? 'selected' : ''}>북아메리카</option>
            <option ${countryDto.countryRegion == '남아메리카' ? 'selected' : ''}>남아메리카</option>
            <option ${countryDto.countryRegion == '유럽' ? 'selected' : ''}>유럽</option>
            <option ${countryDto.countryRegion == '오세아니아' ? 'selected' : ''}>오세아니아</option>
        </select>
	</div>
	<div class="cell">
		<label>이름 <i class="fa-solid fa-asterisk red"></i></label>
		<input type="text" name="countryName" value="${countryDto.countryName}"
				class="field w-100" required> 
	</div>
	<div class="cell">
		<label>수도 <i class="fa-solid fa-asterisk red"></i></label>
		<input type="text" name="countryCapital" value="${countryDto.countryCapital}"
				class="field w-100" required> 
	</div>
	<div class="cell">
		<label>인구 <i class="fa-solid fa-asterisk red"></i></label>
		<input type="text" name="countryPopulation" value="${countryDto.countryPopulation}"
				class="field w-100" required>
	</div>
	
	<div class="cell mt-40">
		<label>국기</label>
		<input type="file" name="attach" accept=".png, .jpg" class="field w-100">
	</div>
	<div class="cell">
		<img src="./flag?countryNo=${countryDto.countryNo}" width="80">
	</div>
	
	<div class="cell mt-50">
		<button type="submit" class="btn btn-positive w-100">수정하기</button>
	</div>
</div>

</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>