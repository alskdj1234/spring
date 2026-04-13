<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<h1>로그인</h1>

<form action="./login" method="post">
Id <input type="text" name="memberId"><br>
Password <input type="password" name="memberPassword"><br>
<button>로그인하기</button>
</form>

<c:if test="${param.error!=null}">
입력하신 정보가 일치하지 않아요
</c:if>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>