<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<style>
	.outer {
		width:50%;
		padding:10px;
	}
	.outer > .inner {
		border: 1px solid gray;
		padding:10px;
		border-radius: 10px;
		position:relative;
	}
	.outer > .inner .more {
		position:absolute;
		bottom:10px;
		right:10px;
	}
</style>

<div class="container w-900 mt-50 mb-50">
	<div class="cell">
		<div class="flex-area">
			<div>
				<h1 class="mt-0 mb-0">도서 목록</h1>
			</div>
			<div class="ms-20">
				<form action="./list" method="get">
					<input type="text" name="keyword" class="field" placeholder="검색어 입력" value="${param.keyword}">
					<button class="btn btn-positive">
						<i class="fa-solid fa-magnifying-glass"></i>
						<span>검색</span>
					</button>
				</form>
			</div>
			<div class="flex-fill right">
				<a href="./insert" class="btn btn-neutral">
					<i class="fa-solid fa-plus"></i>
					<span>신규 등록</span>
				</a>
			</div>
		</div>
	</div>
	
	<!-- 제목 검색 결과 -->
	<c:if test="${listByBookTitle.size() > 0}">
	<div class="cell mt-50">
		<h2>도서명에 <span class="blue">${param.keyword}</span>가 포함된 결과</h2>
	</div>
	<div class="cell">
		<div class="flex-area" style="flex-wrap:wrap">
			<c:forEach var="bookDto" items="${listByBookTitle}">
				<div class="outer">
					<div class="inner">
						<div class="flex-area">
							<div class="w-25">
								<img src="./cover?bookId=${bookDto.bookId}" width="100%">
							</div>
							<div class="ms-20 flex-fill">
								<h3 class="mt-0 mb-0">${bookDto.bookTitle}</h3>
								<c:if test="${bookDto.bookPublisher != null}">
									<div class="gray">${bookDto.bookPublisher}</div>
								</c:if>
								<c:if test="${bookDto.bookAuthor != null}">
									<div class="blue">${bookDto.bookAuthor}</div>
								</c:if>
								<div class="more">
									<a href="./detail?bookId=${bookDto.bookId}" class="link">
										<span>더보기</span>
										<i class="fa-solid fa-arrow-right"></i>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	</c:if>
	
	
	<!-- 지은이 검색 결과 -->
	<c:if test="${listByBookAuthor.size() > 0}">
	<div class="cell mt-50">
		<h2>지은이에 <span class="blue">${param.keyword}</span>가 포함된 결과</h2>
	</div>
	<div class="cell">
		<div class="flex-area" style="flex-wrap:wrap">
			<c:forEach var="bookDto" items="${listByBookAuthor}">
				<div class="outer">
					<div class="inner">
						<div class="flex-area">
							<div class="w-25">
								<img src="./cover?bookId=${bookDto.bookId}" width="100%">
							</div>
							<div class="ms-20 flex-fill">
								<h3 class="mt-0 mb-0">${bookDto.bookTitle}</h3>
								<c:if test="${bookDto.bookPublisher != null}">
									<div class="gray">${bookDto.bookPublisher}</div>
								</c:if>
								<c:if test="${bookDto.bookAuthor != null}">
									<div class="blue">${bookDto.bookAuthor}</div>
								</c:if>
								<div class="more">
									<a href="./detail?bookId=${bookDto.bookId}" class="link">
										<span>더보기</span>
										<i class="fa-solid fa-arrow-right"></i>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	</c:if>
	
	<!-- 출판사 검색 결과 -->
	<c:if test="${listByBookPublisher.size() > 0}">
	<div class="cell mt-50">
		<h2>출판사에 <span class="blue">${param.keyword}</span>가 포함된 결과</h2>
	</div>
	<div class="cell">
		<div class="flex-area" style="flex-wrap:wrap">
			<c:forEach var="bookDto" items="${listByBookPublisher}">
				<div class="outer">
					<div class="inner">
						<div class="flex-area">
							<div class="w-25">
								<img src="./cover?bookId=${bookDto.bookId}" width="100%">
							</div>
							<div class="ms-20 flex-fill">
								<h3 class="mt-0 mb-0">${bookDto.bookTitle}</h3>
								<c:if test="${bookDto.bookPublisher != null}">
									<div class="gray">${bookDto.bookPublisher}</div>
								</c:if>
								<c:if test="${bookDto.bookAuthor != null}">
									<div class="blue">${bookDto.bookAuthor}</div>
								</c:if>
								<div class="more">
									<a href="./detail?bookId=${bookDto.bookId}" class="link">
										<span>더보기</span>
										<i class="fa-solid fa-arrow-right"></i>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	</c:if>
	
	<!-- 출간일 검색 결과 -->
	<c:if test="${listByBookPublicationDate.size() > 0}">
	<div class="cell mt-50">
		<h2>출간일이 <span class="blue">${param.keyword}</span>인 결과</h2>
	</div>
	<div class="cell">
		<div class="flex-area" style="flex-wrap:wrap">
			<c:forEach var="bookDto" items="${listByBookPublicationDate}">
				<div class="outer">
					<div class="inner">
						<div class="flex-area">
							<div class="w-25">
								<img src="./cover?bookId=${bookDto.bookId}" width="100%">
							</div>
							<div class="ms-20 flex-fill">
								<h3 class="mt-0 mb-0">${bookDto.bookTitle}</h3>
								<c:if test="${bookDto.bookPublisher != null}">
									<div class="gray">${bookDto.bookPublisher}</div>
								</c:if>
								<c:if test="${bookDto.bookAuthor != null}">
									<div class="blue">${bookDto.bookAuthor}</div>
								</c:if>
								<div class="more">
									<a href="./detail?bookId=${bookDto.bookId}" class="link">
										<span>더보기</span>
										<i class="fa-solid fa-arrow-right"></i>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	</c:if>
	
	<!-- 장르 검색 결과 -->
	<c:if test="${listByBookGenre.size() > 0}">
	<div class="cell mt-50">
		<h2>장르가 <span class="blue">${param.keyword}</span>인 결과</h2>
	</div>
	<div class="cell">
		<div class="flex-area" style="flex-wrap:wrap">
			<c:forEach var="bookDto" items="${listByBookGenre}">
				<div class="outer">
					<div class="inner">
						<div class="flex-area">
							<div class="w-25">
								<img src="./cover?bookId=${bookDto.bookId}" width="100%">
							</div>
							<div class="ms-20 flex-fill">
								<h3 class="mt-0 mb-0">${bookDto.bookTitle}</h3>
								<c:if test="${bookDto.bookPublisher != null}">
									<div class="gray">${bookDto.bookPublisher}</div>
								</c:if>
								<c:if test="${bookDto.bookAuthor != null}">
									<div class="blue">${bookDto.bookAuthor}</div>
								</c:if>
								<div class="more">
									<a href="./detail?bookId=${bookDto.bookId}" class="link">
										<span>더보기</span>
										<i class="fa-solid fa-arrow-right"></i>
									</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	</c:if>
	
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>



