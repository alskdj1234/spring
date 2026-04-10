<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>강의 정보 등록 페이지</h1>

<form action="./insert" method="post">

	이름 <input type="text" name="courseName" required> <br>
<!-- 	<br> 카테고리<input type="text" name="category" placeholder="이론/실습/시험" required> <br> -->
	<br> 
	카테고리
	<select name="category" required>
		<option value=""> 선택하세요 </option> 
<!-- 		선택하세요는 보여지는거고 값은 빈칸 -->
		<option>이론</option>
		<option>실습</option>
		<option>시험</option>
	
	</select>
	<br>
	수강시간<input type="number" name="lectureTime"
	min="30" step="30" required> <br>
	<br> 수강료 <input type="number" name="fee" 
	min="1000" step="1000" required> <br>
	<br> 수업방식
	<select name="courseName" required>
		<option value=" ">선택하세요</option>
		<option>온라인</option>
		<option>오프라인</option>
		<option>혼합</option>
		
		
	</select>
	<br>
	<button>등록하기</button>

</form>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>


 <h2><a href="./list">목록으로</a> </h2>