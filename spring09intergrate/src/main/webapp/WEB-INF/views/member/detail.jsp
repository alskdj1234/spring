<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script type="text/javascript">
	//좋아요 확인
	$(function(){
		var params = new URLSearchParams(location.search);
		var memberTarget = params.get("memberId");
		$.ajax({
			url:"/rest/member/like-check",
			method:"post",
			data: {memberTarget : memberTarget},
			success : function(response){
				$(".fa-heart").removeClass("fa-solid fa-regular")
							.addClass(response.action ? "fa-solid" : "fa-regular");
				$(".heart-count").text(response.count);
			}
		});
	});
</script>

<c:if test="${sessionScope.loginId != null && sessionScope.loginId != params.memberId}">
<script type="text/javascript">
	//좋아요 처리
	$(function(){
		var params = new URLSearchParams(location.search);
		var memberTarget = params.get("memberId");
		$(".fa-heart").on("click", function(){
			$.ajax({
				url:"/rest/member/like-action",
				method:"post",
				data: { memberTarget : memberTarget },
				success : function(response){
					$(".fa-heart").removeClass("fa-solid fa-regular")
								.addClass(response.action ? "fa-solid" : "fa-regular");
					$(".heart-count").text(response.count);
				}
			});
		});
	});
</script>
</c:if>


<h1>${memberDto.memberNickname}님의 개인 정보</h1>

<img src="./profile?memberId=${memberDto.memberId}" width="100" height="100"
		style="border-radius:50%; box-shadow:0 0 1px 0 black">

<div class="mt-10">
	<i class="fa-solid fa-heart red"></i>
	<span class="heart-count">0</span>
</div>

<ul>
	<li>아이디 : ${memberDto.memberId}</li>
	<li>닉네임 : ${memberDto.memberNickname}</li>
	<li>등급 : ${memberDto.memberLevel}</li>
	<li>상태메세지 : ${memberDto.memberMessage}</li>
	<li>가입일 : <fmt:formatDate value="${memberDto.memberJoin}" pattern="y년 M월 d일 E a h시 m분"/></li>
</ul>

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





