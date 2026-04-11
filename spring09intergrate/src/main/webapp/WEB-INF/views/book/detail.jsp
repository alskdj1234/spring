<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
 <ul>
 <li> ${bookDto.bookTitle} 상세정보</li>
 <li> 코드: ${bookDto.bookId }</li>
 <li> 			저자 : ${bookDto.bookAuthor}</li>
<li> 			장르 : ${bookDto.bookGenre}</li>
<li> 			페이지 수 : ${bookDto.bookPageCount}p</li>
 <li> 			출간일 : ${bookDto.bookPublicationDate}</li>
 <li> 			출판사 : ${bookDto.bookPublisher}</li>
 <li> 			판매가 : <fmt:formatNumber value="${bookDto.bookPrice}" pattern="#,##0"></fmt:formatNumber> </li>
 
 </ul>
 
 
 <h2>
<a href="./list">목록으로</a>
<h2><a href="./insert">신규 등록</a></h2>
<h2><a href="./edit?bookId=${bookDto.bookId}">수정하기</a></h2>
<h2><a href="./delete?bookId=${bookDto.bookId}">삭제하기</a></h2>
</h2>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>