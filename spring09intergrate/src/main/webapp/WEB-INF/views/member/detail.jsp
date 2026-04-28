<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<<<<<<< HEAD
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
=======
>>>>>>> branch 'main' of https://github.com/alskdj1234/spring.git

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>국가 상세정보</h1>

<<<<<<< HEAD
<h1>${memberDto.memberNickname}님의 개인 정보</h1>
=======
<!-- 이곳에 국기를 출력하고 싶다 (이것도 다운로드) -->
<img src="http://localhost:8080/download/legacy?attachNo=${attachNo}" width="200">
>>>>>>> branch 'main' of https://github.com/alskdj1234/spring.git

<<<<<<< HEAD
<img src="./profile?memberId=${memberDto.memberId}" width="100" height="100"
		style="border-radius:50%; box-shadow:0 0 1px 0 black">

=======
<!-- 
	리스트 태그(ul, ol)
	- ul은 순서가 없는 리스트 (unorder list)
	- ol은 순서가 있는 리스트 (order list)
	- li는 리스트 내부의 항목 (list item)
-->
>>>>>>> branch 'main' of https://github.com/alskdj1234/spring.git
<ul>
<<<<<<< HEAD
	<li>아이디 : ${memberDto.memberId}</li>
	<li>닉네임 : ${memberDto.memberNickname}</li>
	<li>등급 : ${memberDto.memberLevel}</li>
	<li>상태메세지 : ${memberDto.memberMessage}</li>
	<li>가입일 : <fmt:formatDate value="${memberDto.memberJoin}" pattern="y년 M월 d일 E a h시 m분"/></li>
=======
	<li>번호 : ${countryDto.countryNo}</li>
	<li>대륙 : ${countryDto.countryRegion}</li>
	<li>이름 : ${countryDto.countryName}</li>
	<li>수도 : ${countryDto.countryCapital}</li>
	<li>인구 : ${countryDto.countryPopulation}명</li>
>>>>>>> branch 'main' of https://github.com/alskdj1234/spring.git
</ul>

<<<<<<< HEAD
<hr>

<h1>작성한 게시글 목록</h1>

<table border="1" width="800">
	<thead>
		<tr>
			<th>번호</th>
			<th width="45%">제목</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	</thead>
	<tbody align="center">
		<c:forEach var="boardDto" items="${boardList}">
		<tr>
			<td>${boardDto.boardNo}</td>
			<td align="left">
				<a href="/board/detail?boardNo=${boardDto.boardNo}">
					${boardDto.boardTitle}
				</a>
			</td>
			<td>${boardDto.getBoardWtimeString()}</td>
			<td>${boardDto.boardReadcount}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>






=======
<h2><a href="./list">목록으로 이동</a></h2>
<h2><a href="./insert">신규 등록</a></h2>
<h2><a href="./edit?countryNo=${countryDto.countryNo}">수정하기</a></h2>
<h2><a href="./delete?countryNo=${countryDto.countryNo}">삭제하기</a></h2>
<%-- <h2><a href="./delete?countryNo=${param.countryNo}">삭제하기</a></h2> --%>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
>>>>>>> branch 'main' of https://github.com/alskdj1234/spring.git
