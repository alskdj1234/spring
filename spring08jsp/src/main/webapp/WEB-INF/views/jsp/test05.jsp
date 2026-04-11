<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1>6잡이</h1>

<c:forEach var="number" items="${dice}">
	<h2>
	번호 : ${number}
	<c:if test="${number==6}">

		
		당첨

	</c:if>

</h2>

</c:forEach>

<hr>

<%-- <c:forEach var="i" begin="0" end="${dice.size()-1}" step="1"> --%>

<!-- 	<h2> -->
<%-- 		번호 = ${dice.get(i)} --%>
<%-- 		<c:if test="${dice.get(i)==6}"> --%>

<%-- 			<h2>번호 = ${number} 당첨</h2> --%>

<%-- 		</c:if> --%>

<!-- 	</h2> -->


<%-- </c:forEach> --%>