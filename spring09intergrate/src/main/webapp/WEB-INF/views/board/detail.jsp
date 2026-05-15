<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<!-- 좋아요 처리 관련 스크립트 -->
<script type="text/javascript">
//헤더에 cdn이 있음
$(function(){
	//시작하자마자 서버에 물어봐서 좋아요 상태와 좋아요 개수를 알아낸다
	
	//주소창에 있는 파라미터 중 boardNo를 꺼내는 코드
	var params = new URLSearchParams(window.location.search);
	var boardNo = params.get("boardNo";)
	
	$.ajax({
		url: "/rest/board/like-check",
		method: "post",
		data: {boardNo : boardNo },
		success: function(response){//response에 action, count가 있을것으로 기대
			//action은 좋아요 여부, count는 좋아요 개수
			$(".fa-heart").removeClass("fa-regular fa-solid")
						.addClass(response.action ? "fa-solid" : "fa-regular");
			
			$(".fa-heart").next(".heart-count").text(response.count);
		}
		
	});
	
});

</script>

<div class="container w-950 mt-50 mb-50">
	<div class="cell">
		<div class="flex-area" style="align-items: end">
			<div>
				<h1 class="mt-0 mb-0">
					<!-- 말머리가 있으면 표시 -->
					<c:if test="${boardDto.boardHead != null}">
					(${boardDto.boardHead})
					</c:if>
					<!-- 제목 -->
					${boardDto.boardTitle}
					<!-- 수정되었다면 추가 표시 -->
					<c:if test="${boardDto.boardEtime != null}">
					(수정됨)
					</c:if>
				</h1>
			</div>
			<div class="ms-40">
				<!-- 목록과 동일하게 사용자 아이디 출력 -->
				<c:if test="${boardDto.boardWriter == null}">
					(탈퇴한사용자)
				</c:if>
				<c:if test="${boardDto.boardWriter != null}">
					<!-- 누르면 이동하도록 링크 구현 -->
					<a href="/member/detail?memberId=${boardDto.boardWriter}"
						class="link"> ${boardDto.boardWriter} </a>
				</c:if>
			</div>
		</div>
	</div>

	<div class="cell mt-20 flex-area">
		<div>
			<fmt:formatDate value="${boardDto.boardWtime}"
				pattern="yyyy-MM-dd HH:mm"></fmt:formatDate>
		</div>
		<div class="ms-20">조회수 ${boardDto.boardReadcount}</div>
	</div>

	<hr>
	<div class="cell" style="min-height: 300px">
		<!-- 있는 그대로의 출력을 수행하는 태그(엔터, 스페이스 등을 인정) -->
		<pre>${boardDto.boardContent}</pre>
	</div>

	<div class="cell mt-20 flex-area">
		<!-- 
 1.이 페이지가 최초로 로딩 되었을 때, 현재 사용자가 이 글에 좋아요를 누른적이 있는지 +현재 좋아요 개수 불러옴
  하트를 채울지 비울지 결정, 하트 옆에 적어야될 숫자를 표시
  비회원도 가능한 기능
 
 2.하트를 클릭하면 글번호를 알려주면서 좋아요/해제 처리를 요청
 	서버에서 결과적으로 좋아요/해제 중 어떤것이 처리되었는지와 현재 좋아요 개수를 알려줌
	회원만 가능한 기능

 -->
		<div>
			좋아요 <i class="fa-solid fa-heart red"></i> <span class="heart-count">?</span>


		</div>
		<div class="ms-20">댓글 ${boardDto.boardReplycount}</div>
	</div>
	<hr>

	<!-- 이전글/다음글 출력 -->
	<div class="cell">
		<span class="badge blue me-20">이전글</span> <a
			href="./detail?boardNo=${prevBoardDto.boardNo}" class="link">${prevBoardDto.boardTitle}</a>
	</div>
	<div class="cell">
		<span class="badge blue me-20">다음글</span> <a
			href="./detail?boardNo=${nextBoardDto.boardNo}" class="link">${nextBoardDto.boardTitle}</a>
	</div>

	<hr>
	<div class="cell right">
		<c:if test="${sessionScope.loginId != null}">
			<a class="btn btn-positive" href="./write">글쓰기</a>
			<a class="btn btn-positive"
				href="./write?boardParent=${boardDto.boardNo}">답글쓰기</a>
		</c:if>

		<c:if
			test="${boardDto.boardWriter != null && boardDto.boardWriter == sessionScope.loginId}">
			<a class="btn btn-negative" href="./edit?boardNo=${boardDto.boardNo}">수정</a>
			<a class="btn btn-negative"
				href="./delete?boardNo=${boardDto.boardNo}">삭제</a>
		</c:if>

		<a class="btn btn-neutral" href="./list">목록으로</a>
	</div>
</div>


<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>

