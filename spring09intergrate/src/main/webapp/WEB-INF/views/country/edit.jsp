<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
    <h1>국가 정보 수정</h1>
    
   
    <form action="./edit" method="post">
    
    	<input type="hidden" name="countryNo" value="${countryDto.countryNo}">
    	
    	
    	<input type="text" name="countryRegion" value="${countryDto.countryRegion}">
    	<input type="text" name="countryName" value="${countryDto.countryName}">
    	<input type="text" name="countryCapital" value="${countryDto.countryCapital}">
    	<input type="text" name="countryPopulation" value="${countryDto.countryPopulation}">
    
    	<button>수정하기</button>
 		   
    
    </form>
    <jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>