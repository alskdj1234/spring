<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
 <h1> 국가 정보 등록 페이지</h1>
 
 <form action="./insert" method="post">
 
 대륙<input type="text" name="countryRegion"> <br><br>
 이름<input type="text" name="countryName"> <br><br>
 수도<input type="text" name="countryCapital"> <br><br>
 인구<input type="text" name="countryPopulation"> <br><br>
 <button>등록하기</button>
 
 </form>
 
 <h2><a href="./list">목록으로</a> </h2>
 <jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>