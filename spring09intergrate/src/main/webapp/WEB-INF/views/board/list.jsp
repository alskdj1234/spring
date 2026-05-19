<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container w-950 mt-50 mb-50">
    <div class="cell center mb-0">
        <h1 class="mb-0">자유 게시판</h1>
    </div>
    <div class="cell center mt-0">
        타인에 대한 무분별한 비방글은 예고 없이 삭제될 수 있습니다
    </div>
    
    <div class="cell right">
    	<!-- 글쓰기 버튼 -->
		<c:if test="${sessionScope.loginId != null}">
			<a href="./write" class="btn btn-neutral">글쓰기</a>
		</c:if>
    </div>
    <div class="cell right">
        ${pageVO.beginRownum}-${pageVO.endRownum} / 총 ${pageVO.count}개의 글
    </div>
    <div class="cell">
        <table class="table">
            <thead>
                <tr>
                    <th>번호</th>
                    <th class="w-40">제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                    <th>조회수</th>
                    <th>좋아요</th>
<!--                     확인용 컬럼(나중에 삭제할 예정) -->
<!-- 					<th>no</th> -->
<!-- 					<th>group</th> -->
<!-- 					<th>parent</th> -->
<!-- 					<th>depth</th> -->
                </tr>
            </thead>
            <tbody>
            	<!-- 일반 게시글 -->
				<!-- varStatus를 쓰면 반복문의 상태를 알 수 있다(index, count, first, last) -->
				<c:forEach var="boardDto" items="${list}" varStatus="stat">
				<tr bgcolor="${stat.index < noticeCount ? '#ffeaa7':''}">
					<td>${boardDto.boardNo}</td>
					<td align="left">
						<!-- 답변글인 경우 차수만큼 간격을 벌리고 추가 표시 -->
						<c:if test="${boardDto.boardDepth > 0}">
							<c:forEach var="i" begin="1" end="${boardDto.boardDepth}" step="1">
								&nbsp;&nbsp;&nbsp;&nbsp;
							</c:forEach> 
							→
						</c:if>
					
						<!-- 말머리가 있으면 표시 -->
						<c:if test="${boardDto.boardHead != null}">
						(${boardDto.boardHead})
						</c:if>
					
						<!-- 게시글 제목 -->
						<a href="./detail?boardNo=${boardDto.boardNo}&page=${pageVO.page}&${pageVO.searchParams}" class="link">
						${boardDto.boardTitle}
						</a>
						
						<!-- 댓글개수도 있으면(>0) 표시 -->
						<c:if test="${boardDto.boardReplycount > 0}">
						[${boardDto.boardReplycount}]
						</c:if>
					</td>
					<td>
						<c:if test="${boardDto.boardWriter == null}">
							(탈퇴한사용자)
						</c:if>
						<c:if test="${boardDto.boardWriter != null}">
							<!-- 누르면 이동하도록 링크 구현 -->
							<a href="/member/detail?memberId=${boardDto.boardWriter}">
								${boardDto.boardWriter}
							</a>
						</c:if>
					</td>
					<td>${boardDto.getBoardWtimeString()}</td>
					<td>${boardDto.boardReadcount}</td>
					<td>${boardDto.boardLikecount}</td>
<!-- 					확인용(나중에 삭제) -->
<%-- 					<td>${boardDto.boardNo}</td> --%>
<%-- 					<td>${boardDto.boardGroup}</td> --%>
<%-- 					<td>${boardDto.boardParent}</td> --%>
<%-- 					<td>${boardDto.boardDepth}</td> --%>
				</tr>
				</c:forEach>
            </tbody>
        </table>
    </div>
    
    <!-- 페이지네이션 -->
    <div class="cell mt-40">
		<jsp:include page="/WEB-INF/views/template/pagination.jsp"></jsp:include>
    </div>
    <div class="cell center">
        <!-- 검색창 -->
		<form action="./list" method="get">
			<select name="column" class="field">
				<option value="board_title" ${param.column == 'board_title' ? 'selected':''}>제목</option>
				<option value="board_writer" ${param.column == 'board_writer' ? 'selected':''}>작성자</option>
			</select>
			<input type="text" name="keyword" class="field" placeholder="검색어" value="${param.keyword}">
			<button type="submit" class="btn btn-positive">
				<i class="fa-solid fa-magnifying-glass"></i>
				<span>검색</span>
			</button>
		</form>
    </div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>



