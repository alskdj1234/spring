<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>


<h1>신규 글 작성</h1>
타인에 대한 무분별한 비방글은 경고 없이 삭제 됩니다.

<form action ="./write" method="post">

	제목<input type="text" name="boardTitle" required><br><br>
	구분
	<select name="boardHead">
	<option value="">선택 안 함></option>

	<c:if test="${sessionScope.loginLevel == '마스터'}">
	<!-- 	공지는 관리자만 보이게 -->
	<option>공지</option>
	</c:if>
	<option>정보</option>
	<option>유머</option>
	<option>자유</option>
	
	</select>
	<br><br>
	
	내용 <textarea name="boardContent" rows="10" cols="80"></textarea>
	<button>등록하기</button>




</form>







<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>