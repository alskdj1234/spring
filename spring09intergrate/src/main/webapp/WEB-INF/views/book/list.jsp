<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>도서 목록</h1>

<form action="./list" method="get">
	<input type="text" name="keyword" placeholder="검색어 입력" value="${param.keyword}">
	<button>검색</button>
</form>

<c:if test="${listByBookTitle.size() > 0}">
	<h2>도서 제목으로 검색한 결과</h2>
	
	<c:forEach var="bookDto" items="${listByBookTitle}">
	<div>
		<img src="https://www.dummyimage.com/100x150?text=Book">
		<h2>
			<a href="./detail?bookId=${bookDto.bookId}">
				${bookDto.bookTitle}
			</a>
		</h2>
		지은이 : <a href="./list?keyword=${bookDto.bookAuthor}">${bookDto.bookAuthor}</a> <br>
		출판사 : <a href="./list?keyword=${bookDto.bookPublisher}">${bookDto.bookPublisher}</a> <br>
		장르 : <a href="./list?keyword=${bookDto.bookGenre}">${bookDto.bookGenre}</a><br>
		출간일 : <a href="./list?keyword=${bookDto.bookPublicationDate}">${bookDto.bookPublicationDate}</a> <br>
	</div>
	</c:forEach>
	
	<hr>
</c:if>

<c:if test="${listByBookAuthor.size() > 0}">
	<h2>지은이로 검색한 결과</h2>
	
	<c:forEach var="bookDto" items="${listByBookAuthor}">
	<div>
		<img src="https://www.dummyimage.com/100x150?text=Book">
		<h2>${bookDto.bookTitle}</h2>
		지은이 : ${bookDto.bookAuthor} <br>
		출판사 : ${bookDto.bookPublisher} <br>
		장르 : ${bookDto.bookGenre}<br>
		출간일 : ${bookDto.bookPublicationDate} <br>
	</div>
	</c:forEach>
	
	<hr>
</c:if>

<c:if test="${listByBookPublisher.size() > 0}">
	<h2>출판사로 검색한 결과</h2>
	
	<c:forEach var="bookDto" items="${listByBookPublisher}">
	<div>
		<img src="https://www.dummyimage.com/100x150?text=Book">
		<h2>${bookDto.bookTitle}</h2>
		지은이 : ${bookDto.bookAuthor} <br>
		출판사 : ${bookDto.bookPublisher} <br>
		장르 : ${bookDto.bookGenre}<br>
		출간일 : ${bookDto.bookPublicationDate} <br>
	</div>
	</c:forEach>
	
	<hr>
</c:if>

<c:if test="${listByBookPublicationDate.size() > 0}">
	<h2>출간일로 검색한 결과</h2>
	
	<c:forEach var="bookDto" items="${listByBookPublicationDate}">
	<div>
		<img src="https://www.dummyimage.com/100x150?text=Book">
		<h2>${bookDto.bookTitle}</h2>
		지은이 : ${bookDto.bookAuthor} <br>
		출판사 : ${bookDto.bookPublisher} <br>
		장르 : ${bookDto.bookGenre}<br>
		출간일 : ${bookDto.bookPublicationDate} <br>
	</div>
	</c:forEach>
	
	<hr>
</c:if>

<c:if test="${listByBookGenre.size() > 0}">
	<h2>장르로 검색한 결과</h2>
	
	<c:forEach var="bookDto" items="${listByBookGenre}">
	<div>
		<img src="https://www.dummyimage.com/100x150?text=Book">
		<h2>${bookDto.bookTitle}</h2>
		지은이 : ${bookDto.bookAuthor} <br>
		출판사 : ${bookDto.bookPublisher} <br>
		장르 : ${bookDto.bookGenre}<br>
		출간일 : ${bookDto.bookPublicationDate} <br>
	</div>
	</c:forEach>
	
	<hr>
</c:if>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>