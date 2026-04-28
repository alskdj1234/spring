<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h1>도서 정보 수정</h1>

<form action="./edit" method="post" enctype="multipart/form-data">
	<input type="hidden" name="bookId" value="${bookDto.bookId}">

	도서명 <input type="text" name="bookTitle" required value="${bookDto.bookTitle}"> <br><br>
	출판사 <input type="text" name="bookPublisher" value="${bookDto.bookPublisher}"> <br><br>
	지은이 <input type="text" name="bookAuthor" value="${bookDto.bookAuthor}"> <br><br>
	출간일 <input type="date" name="bookPublicationDate" value="${bookDto.bookPublicationDate}"> <br><br>
	판매가 <input type="number" name="bookPrice" required value="${bookDto.bookPrice}"> <br><br>
	페이지 <input type="number" name="bookPageCount" required value="${bookDto.bookPageCount}"> <br><br>
	장르
	<select name="bookGenre" required>
		<option value="">선택</option>
		<option ${bookDto.bookGenre=='판타지'?'selected':''}>판타지</option>
		<option ${bookDto.bookGenre=='교양'?'selected':''}>교양</option>
		<option ${bookDto.bookGenre=='소설'?'selected':''}>소설</option>
		<option ${bookDto.bookGenre=='역사'?'selected':''}>역사</option>
		<option ${bookDto.bookGenre=='과학'?'selected':''}>과학</option>
		<option ${bookDto.bookGenre=='추리소설'?'selected':''}>추리소설</option>
		<option ${bookDto.bookGenre=='자기계발'?'selected':''}>자기계발</option>
		<option ${bookDto.bookGenre=='수험서'?'selected':''}>수험서</option>
	</select>
	<br><br>
	
	표지 <input type="file" name="attach" accept=".png, .jpg"> <br>
	(기존 표지) <img src="./cover?bookId=${bookDto.bookId}" width="100">
	<br><br>	
	
	<button>도서 수정하기</button>
</form>
