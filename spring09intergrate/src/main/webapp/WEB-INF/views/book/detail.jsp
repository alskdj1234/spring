<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>『${bookDto.bookTitle}』 상세정보</h1>

<img src="./cover?bookId=${bookDto.bookId}" width="120">

<ul>
	<li>도서코드 : ${bookDto.bookId}</li>
	<li>지은이 : ${bookDto.bookTitle}</li>
	<li>출판사 : ${bookDto.bookPublisher}</li>
	<li>판매가 : ${bookDto.bookPrice}원</li>
	<li>페이지 : ${bookDto.bookPageCount}p</li>
	<li>출간일 : ${bookDto.bookPublicationDate}</li>
	<li>장르 : ${bookDto.bookGenre}</li>
</ul>

<h2><a href="./insert">신규 도서 등록</a></h2>
<h2><a href="./list">목록으로 이동</a></h2>
<h2><a href="./edit?bookId=${bookDto.bookId}">정보 수정하기</a></h2>
<h2><a href="./delete?bookId=${bookDto.bookId}">도서 삭제하기</a></h2>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
