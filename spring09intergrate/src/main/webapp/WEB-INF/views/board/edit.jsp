<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>게시글 수정</h1>

<form action="./edit" method="post">



<input type="hidden" name="boardNo" value="${boardDto.boardNo}">

	제목<input type="text" name="boardTitle" value="${boardDto.boardTitle}" required>
	구분
	<select name="boardHead">
	<option value="">선택 안 함</option>
	<c:if test="${sessionScope.loginLevel == '마스터'}">
	<!-- 	공지는 관리자만 보이게 -->
	<option ${boardDto.boardHead=='공지' ? 'selected':''}>공지</option>
	</c:if>
	<option ${boardDto.boardHead=='유머' ? 'selected':''}>유머</option>
	<option ${boardDto.boardHead=='자유' ? 'selected':''}>자유</option>
	<option ${boardDto.boardHead=='정보' ? 'selected':''}>정보</option>
	
	
	
	
	
	</select>
	
	내용<textarea name="boardContent" rows="10" cols="80" required>${boardDto.boardContent}</textarea>
	<br><br>
	
	<button>수정하기</button>
	<a href="./list">목록으로</a>

</form>






<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>