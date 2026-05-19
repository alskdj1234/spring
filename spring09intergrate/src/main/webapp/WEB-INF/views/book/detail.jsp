<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container w-800 mt-50 mb-50">
	<div class="cell">
		<h1>『${bookDto.bookTitle}』 상세정보</h1>
	</div>
	
	<div class="cell mt-50">
		<div class="flex-area">
			<div class="w-40 p-20">
				<img src="./cover?bookId=${bookDto.bookId}" width="100%">
			</div>
			<div class="w-60 p-20">
				<div class="cell flex-area">
					<div class="w-25">도서ID</div>
					<div class="w-75 blue">${bookDto.bookId}</div>
				</div>
				<div class="cell flex-area">
					<div class="w-25">도서명</div>
					<div class="w-75 blue">${bookDto.bookTitle}</div>
				</div>
				<div class="cell flex-area">
					<div class="w-25">지은이</div>
					<div class="w-75 blue">${bookDto.bookAuthor}</div>
				</div>
				<div class="cell flex-area">
					<div class="w-25">출판사</div>
					<div class="w-75 blue">${bookDto.bookPublisher}</div>
				</div>
				<div class="cell flex-area">
					<div class="w-25">출간일</div>
					<div class="w-75 blue">${bookDto.bookPublicationDate}</div>
				</div>
				<div class="cell flex-area">
					<div class="w-25">판매가</div>
					<div class="w-75 blue">${bookDto.bookPrice} KRW</div>
				</div>
				<div class="cell flex-area">
					<div class="w-25">페이지</div>
					<div class="w-75 blue">${bookDto.bookPageCount}p</div>
				</div>
			</div>
		</div>
	</div>
	
	<hr class="mt-50">
	<div class="cell right">
		<a class="btn btn-positive" href="./insert">
			<i class="fa-solid fa-plus"></i>
			<span>신규 도서 등록</span>
		</a>
		<a class="btn btn-negative" href="./edit?bookId=${bookDto.bookId}">
			<i class="fa-solid fa-pen"></i>
			<span>정보 수정하기</span>
		</a>
		<a class="btn btn-negative" href="./delete?bookId=${bookDto.bookId}">
			<i class="fa-solid fa-trash"></i>
			<span>도서 삭제하기</span>
		</a>
		<a class="btn btn-neutral" href="./list">
			<i class="fa-solid fa-list"></i>
			<span>목록으로 이동</span>
		</a>
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
