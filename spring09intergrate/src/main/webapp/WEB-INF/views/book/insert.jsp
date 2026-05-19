<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script src="/js/book/insert.js"></script>

<form action="./insert" autocomplete="off" method="post" enctype="multipart/form-data" class="form-check">
        
    <div class="container w-500 mt-50 mb-50">
        <div class="cell center">
            <h1>신규 도서 등록</h1>
        </div>

        <div class="cell">
            <label>도서명 <i class="fa-solid fa-asterisk red"></i></label>
            <input type="text" name="bookTitle" class="field w-100">
            <div class="success-feedback">도서명 설정이 완료되었습니다</div>
            <div class="fail-feedback">필수 입력 항목입니다</div>
        </div>

        <div class="cell">
            <label>지은이</label>
            <input type="text" name="bookAuthor" class="field w-100">
            <div class="fail-feedback">특수문자는 사용하실 수 없습니다</div>
        </div>

        <div class="cell">
            <label>출판사</label>
            <input type="text" name="bookPublisher" class="field w-100">
        </div>

        <div class="cell">
            <label>출간일</label>
            <input type="date" name="bookPublicationDate" class="field w-100">
            <div class="fail-feedback">유효한 날짜 형식이 아닙니다</div>
        </div>

        <div class="cell">
            <label>판매가 <i class="fa-solid fa-asterisk red"></i></label>
            <input type="text" inputmode="numeric" name="bookPrice" class="field w-100">
            <div class="success-feedback">판매가 설정이 완료되었습니다</div>
            <div class="fail-feedback">0원 이상 10억원 이하로만 설정 가능합니다</div>
        </div>

        <div class="cell">
            <label>페이지 <i class="fa-solid fa-asterisk red"></i></label>
            <input type="text" inputmode="numeric" name="bookPageCount" class="field w-100">
            <div class="success-feedback">페이지수 설정이 완료되었습니다</div>
            <div class="fail-feedback">페이지수는 0보다 커야합니다</div>
        </div>

        <div class="cell">
            <label>장르 <i class="fa-solid fa-asterisk red"></i></label>
            <select name="bookGenre" class="field w-100">
                <option value="">선택</option>
                <option>판타지</option>
                <option>교양</option>
                <option>소설</option>
                <option>역사</option>
                <option>과학</option>
                <option>추리소설</option>
                <option>자기계발</option>
                <option>수험서</option>
            </select>
            <div class="fail-feedback">필수 항목입니다</div>
        </div>

        <div class="cell">
            <label>표지</label>
            <input type="file" name="attach" class="field w-100">
        </div>

        <div class="cell mt-40">
            <button class="btn btn-positive w-100">
                등록
            </button>
        </div>

    </div>

</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>