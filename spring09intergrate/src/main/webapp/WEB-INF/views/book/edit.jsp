<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<form action="./edit" method="post" enctype="multipart/form-data">
<input type="hidden" name="bookId" value="${bookDto.bookId}">

<div class="container w-600 mt-50 mb-50">
	<div class="cell center">
		<h1>도서 정보 수정</h1>
	</div>
	
	<div class="cell">
		<label>도서명 <i class="fa-solid fa-asterisk red"></i></label>
		<input type="text" name="bookTitle" required class="field w-100"
				value="${bookDto.bookTitle}">
	</div>
	<div class="cell">
		<label>지은이</label>
		<input type="text" name="bookAuthor" class="field w-100"
				value="${bookDto.bookAuthor}">
	</div>
	<div class="cell">
		<label>출판사</label>
		<input type="text" name="bookPublisher" class="field w-100"
				value="${bookDto.bookPublisher}">
	</div>
	<div class="cell">
		<label>출간일</label>
		<input type="date" name="bookPublicationDate" class="field w-100"
				value="${bookDto.bookPublicationDate}">
	</div>
	<div class="cell">
		<label>판매가 <i class="fa-solid fa-asterisk red"></i></label>
		<input type="number" name="bookPrice" required class="field w-100"
				value="${bookDto.bookPrice}">
	</div>
	<div class="cell">
		<label>페이지 <i class="fa-solid fa-asterisk red"></i></label>
		<input type="number" name="bookPageCount" required class="field w-100"
				value="${bookDto.bookPageCount}">
	</div>
	<div class="cell">
		<label>장르 <i class="fa-solid fa-asterisk red"></i></label>
		<select name="bookGenre" required class="field w-100">
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
	</div>
	
	<div class="cell mt-50">
		<label>표지</label>
		<input type="file" name="attach" accept=".png, .jpg" class="field w-100">
	</div>
	<div class="cell mb-0">
		<label>기존 표지</label>
	</div>
	<div class="cell mt-0">
		<img src="./cover?bookId=${bookDto.bookId}" width="100">
	</div>
	
	<div class="cell mt-50">
		<button type="submit" class="btn btn-positive w-100">
			<i class="fa-solid fa-plus"></i>
			<span>도서 정보 변경하기</span>
		</button>	
	</div>
</div>

</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
