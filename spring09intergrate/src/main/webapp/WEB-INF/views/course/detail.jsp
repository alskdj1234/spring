<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<ul>

<li>번호 : ${courseDto.courseNo}</li>
<li>이름 : ${courseDto.courseName}</li>
<li>카테고리 : ${courseDto.category}</li>
<li>수강 시간 : ${courseDto.lectureTime}H</li>
<li>수강료 : <fmt:formatNumber value="${courseDto.fee}" pattern="#,##0">
</fmt:formatNumber>
원</li>
<li>수업 방식: ${courseDto.classType}</li>

</ul>


<h2>
<a href="./list">목록으로</a>
<h2><a href="./insert">신규 등록</a></h2>
<h2><a href="./edit?courseNo=${courseDto.courseNo}">수정하기</a></h2>
<h2><a href="./delete?courseNo=${courseDto.courseNo}">삭제하기</a></h2>
</h2>
<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>