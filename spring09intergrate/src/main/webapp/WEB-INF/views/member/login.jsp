<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<h1>로그인</h1>

<form action="./login" method="post">
Id <input type="text" name="memberId" required><br>
Password <input type="text" name="memberPassword" required><br>
<button>로그인하기</button>
</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>