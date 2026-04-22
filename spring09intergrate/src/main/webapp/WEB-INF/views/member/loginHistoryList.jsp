<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<h1>${memberHistoryOrigin}님의 
${param.beginDate}부터 ${param.endDate}까지의
로그인 이력 </h1>

<c:forEach var="loginHistoryList" items="${loginHistoryList}">

로그인 시각:${loginHistoryList.memberHistoryTime}

<jsp:include page="/WEB-INF/views/template/pagination.jsp"></jsp:include>










</c:forEach>













<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
