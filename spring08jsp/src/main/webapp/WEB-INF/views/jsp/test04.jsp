<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <% 

		JSTL
		JSP에서만 쓸 수 있는 특별한 태그 모음들
		JSP에서 데이터를 출력할 때 발생 할 수 있는 프로그래밍적 상황(조건 반복 예외 형식)을 처리
		태그 형태여서 HTML과 잘 어우러짐
		다양한 종류 존재 필요 태그를 '등록'하여 사용
			c(core) 조건 반복 예외(예외는 여기서 안 함) 등 기초 프로그래밍 로직을 태그로 구현
			fmt(format) 날짜형식 숫자형식 등을 제어 할 수 있는 태그 모음
			functions 문자열 처리를 도와줌(사용하지 않음)
			xml xml 형식을 해석하고 제어 (사용x)
			sql	db제어 (사용x)
		불러올 때는 무조건 EL 씀
%> --%>

<h1>이대로 찍으세요</h1>
<%-- <h2>${lotto}</h2> --%>
<c:forEach var ="number" items="${lotto}">

	<h2>번호 = ${number}</h2>

</c:forEach>
