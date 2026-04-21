<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 페이지네이션 -->

<!-- 이전 -->
<c:if test="${pageVO.hasPrevious()}">
<a href="./list?page=${pageVO.getPreviousBlock()}&${pageVO.getSearchParams()}">&lt;</a>
</c:if>

<!-- 숫자 -->
<c:forEach var="i" begin="${pageVO.getBeginBlock()}" end="${pageVO.getEndBlock()}" step="1">
<c:if test="${pageVO.page == i}">${i}</c:if>
<c:if test="${pageVO.page != i}">
<a href="./list?page=${i}&${pageVO.getSearchParams()}">${i}</a></c:if>
</c:forEach>

<!-- 다음 -->
<c:if test="${pageVO.hasNext()}">
<a href="./list?page=${pageVO.getNextBlock()}&${pageVO.getSearchParams()}">&gt;</a>
</c:if>