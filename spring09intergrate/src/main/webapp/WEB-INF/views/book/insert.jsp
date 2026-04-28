<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>신규 도서 등록</h1>

<form action="./insert" method="post" enctype="multipart/form-data">
	도서명* <input type="text" name="bookTitle" required> <br><br>
	출판사 <input type="text" name="bookPublisher"> <br><br>
	지은이 <input type="text" name="bookAuthor"> <br><br>
	출간일 <input type="date" name="bookPublicationDate"> <br><br>
	판매가* <input type="number" name="bookPrice" required> <br><br>
	페이지* <input type="number" name="bookPageCount" required> <br><br>
	
	장르*
	<select name="bookGenre" required>
		<option value="">선택</option>
		<option>판타지</option>
		<option>교양</option>
		<option>소설</option>
		<option>역사</option>
		<option>과학</option>
		<option>추리소설</option>
		<option>자기계발</option>
		<option>수험서</option>
	</select>
	<br><br>
	
	표지 <input type="file" name="attach" accept=".png, .jpg" onchange="previewImage(this)"> 
	<br>
	<img id="preview" style="width:100px; display:none; margin-top:10px;">
	<br><br>
	
	<button>도서 등록하기</button>
</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>