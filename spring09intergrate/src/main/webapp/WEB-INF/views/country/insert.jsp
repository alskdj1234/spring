<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<form action="./insert" method="post" enctype="multipart/form-data" autocomplete="off">

<div class="container w-400 mt-50">
    <div class="cell center">
        <h1>국가 정보 등록</h1>
    </div>
    <div class="cell">
        <label>대륙명 *</label> 
        <select class="field w-100" name="countryRegion" required>
            <option value="">선택하세요</option>
            <option>아시아</option>
            <option>아프리카</option>
            <option>북아메리카</option>
            <option>남아메리카</option>
            <option>유럽</option>
            <option>오세아니아</option>
        </select>
    </div>
    <div class="cell">
        <label>국가명 *</label>
        <input type="text" name="countryName"
            class="field w-100" required
            placeholder="e.g., 대한민국">
    </div>
    <div class="cell">
        <label>수도명 *</label>
        <input type="text" name="countryCapital"
            class="field w-100" required
            placeholder="e.g., 서울">
    </div>
    <div class="cell">
        <label>인구수 *</label>
        <input type="text" inputmode="numeric"
            name="countryPopulation"
            class="field w-100" required
            placeholder="e.g., 55000000">
    </div>
    <div class="cell">
    	<label>국기</label>
    	<input type="file" name="attach" class="field w-100">
    </div>    
    <div class="cell mt-40 right">
        <button class="btn btn-positive w-100">
            등록하기
        </button>
    </div>
</div>

</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>





