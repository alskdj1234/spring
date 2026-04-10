<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1> 도서 목록 </h1>

<form action="./list" method="get">

<input type="text" name="keyword" placeholder="검색어 입력" value="${param.keyword}">



</form>




<c:if test="${listByBookTitle.size()}>)"><h2>제목 검색 결과</h2></c:if>
<c:forEach>
<div>
<h2>

<a href="./detail?bookId=${bookDto.bookId}">${bookDto.bookTitle}</a>
</h2>
    지은이 :<a href=".list?keyword=${bookDto.bookAuthor}">${bookDto.bookAuthor}</a> 
    출판사 : ${bookDto.bookPublisher}
    장르 : ${bookDto.bookGenre}
    출간일 : ${bookDto.bookPublicationDate}
    
</div>

</c:forEach>
<c:if test="${listByBookAuthor.size()}>)"><h2>저자 검색 결과</h2></c:if>
<c:if test="${listByBookPublisher.size()}>)"><h2>출판사 검색 결과</h2></c:if>
<c:if test="${listByBookPublicationDate.size()}>)"><h2>출간일 검색 결과</h2></c:if>
<c:if test="${listByBookGenre.size()}>)"><h2>장르 검색 결과</h2></c:if> 
    
    
    	
    	
    	
    	
    	
  