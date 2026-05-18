<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
.reply-wrapper {
	display: flex;
	padding: 15px;
	box-shadow: 0 0 0 1px lightgray;
}

.reply-wrapper>.profile-wrapper {
	width: 100px;
}

.reply-wrapper>.profile-wrapper>.img {
	width: 100%;
	aspect-ratio: 1/1;
}

.reply-wrapper>.content-wrapper {
	flex-grow: 1;
}
</style>
<!-- 좋아요 처리 관련 스크립트 -->
<script type="text/javascript">
$(function(){
	// 시작하자마자 서버에 물어봐서 좋아요 상태와 좋아요 개수를 알아낸다
	var params = new URLSearchParams(window.location.search);
	var boardNo = params.get("boardNo"); // 괄호 오타 수정
	
	$.ajax({
		url: "/rest/board/like-check",
		method: "post",
		data: { boardNo : boardNo },
		success: function(response){
			$(".fa-heart").removeClass("fa-regular fa-solid")
						.addClass(response.action ? "fa-solid" : "fa-regular");
			
			$(".fa-heart").next(".heart-count").text(response.count);
		} // 마지막 세미콜론 제거
	});
});
</script>

<c:if test="${sessionScope.loginId != null}">
	<!-- 좋아요 토글 자바스크립트(회원만 가능) -->
	<script type="text/javascript">
 $(function(){
		var params = new URLSearchParams(window.location.search);
		var boardNo = params.get("boardNo"); // 괄호 오타 수정
	 
	 $(".fa-heart").on("click", function(){
		 $.ajax({
			 url: "/rest/board/like-action",
			 method: "post",
			 data: { boardNo : boardNo },
			 success: function(response){
				 $(".fa-heart").removeClass("fa-regular fa-solid")
					.addClass(response.action ? "fa-solid" : "fa-regular");
		
				 $(".fa-heart").next(".heart-count").text(response.count);
			 }
		 });
	 });
 });
</script>
</c:if>

<!-- 댓글 시스템 js -->
<script type="text/javascript">
$(function(){
	var params = new URLSearchParams(window.location.search);
	var boardNo = params.get("boardNo"); // 괄호 오타 수정
	//시작 하자마자 목록 부르기
	
	$.ajax({
		url:"/rest/reply/list"
		method:"post",
		data:{replyOrigin : boardNo},
		success: function(response){
			//response는 백엔드에서의 List<ReplyDto>
			//반복을 통해 템플릿을 배치하고 정보를 갈아 끼운다.
			for(var i=0; i<response.length; i++){
				var template = $("#reply-template");//템플릿 불러와
				var html = $.parseHTML(template);//html로 변환
				$(".reply-area").append(html);//화면에 추가
				
				
			}
			
			
		}
		
		
	});
	
	//등록 버튼 누르면 발생 하는 이벤트
	$(".btn-reply").on("click", function(){ // 괄호 위치 수정
		var replyContent = $(".field-reply").val();
		if(replyContent.length == 0) return;
		
		$.ajax({
			url: "/rest/reply/write",
			method: "post",
			data: {
				replyContent : replyContent, // 쉼표 추가!
				replyOrigin : boardNo
			},
			success: function(response){
				if(response.result) {
					alert("댓글이 등록되었습니다.");
					$(".field-reply").val(""); 
					
				} else {
					alert("댓글 등록에 실패했습니다.");
				}
			}
		});
	});
});
</script>

<script type="text/template" id="reply-template">

<div class="cell">

		<div class="reply-wrapper">
			<div class="profile-wrapper">

				<img src="https://picsum.photos/500" class="image-circle">
			</div>
			<div class="content-wrapper ms-20">
				<h3 class="mt-0 mb-0">작성자</h3>
				<pre class="mt-10 mb-0">내용 샘플</pre>
				<div class="mt-20">
					<span class="gray">yyyy-MM-dd HH:mm</span>
				</div>
			</div>

		</div>

	</div>
</script>

<div class="container w-950 mt-50 mb-50">
	<div class="cell">
		<div class="flex-area" style="align-items: end">
			<div>
				<h1 class="mt-0 mb-0">
					<c:if test="${boardDto.boardHead != null}">
					(${boardDto.boardHead})
					</c:if>
					${boardDto.boardTitle}
					<c:if test="${boardDto.boardEtime != null}">
					(수정됨)
					</c:if>
				</h1>
			</div>
			<div class="ms-40">
				<c:if test="${boardDto.boardWriter == null}">
					(탈퇴한사용자)
				</c:if>
				<c:if test="${boardDto.boardWriter != null}">
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
		<pre>${boardDto.boardContent}</pre>
	</div>

	<div class="cell mt-20 flex-area">
		<div>
			<!-- 아이콘 기본값을 fa-regular로 두거나 스크립트가 채워줄 때까지 대기 -->
			<i class="fa-regular fa-heart red" style="cursor: pointer;"></i> <span
				class="heart-count">?</span>
		</div>
		<div class="ms-20">댓글 ${boardDto.boardReplycount}</div>
	</div>
	
	<c:if test="${sessionScope.loginId != null}">
		
		<div class="cell reply-area">
			<textarea class="field w-100 field-reply" rows="4"
				placeholder="댓글 작성"></textarea>
			<button type="button" class="btn btn-positive w-100 mt-10 btn-reply">
				<i class="fa-solid fa-pen"></i> <span>댓글 작성하기</span>
			</button>
		</div>
	</c:if>

	<!-- = 을 == 으로 수정 -->
	<c:if test="${sessionScope.loginId == null}">
		<div class="cell">
			<h3>
				댓글작성을 원하시면 <a href="/member/login">로그인</a>하세요
			</h3>
		</div>
	</c:if>
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