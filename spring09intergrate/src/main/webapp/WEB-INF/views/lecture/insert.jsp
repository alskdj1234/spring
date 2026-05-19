<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script src="/js/lecture/insert.js"></script>

<form action="./insert" method="post" enctype="multipart/form-data" 
								autocomplete="off" class="form-check">

    <div class="container w-600 mt-50 mb-50">
        <div class="cell center">
            <h1>강좌 정보 등록</h1>
        </div>

        <div class="cell mt-40">
            <label>강좌명 <i class="fa-solid fa-asterisk red"></i></label>
            <input type="text" name="lectureTitle" placeholder="e.g., 정보처리 산업기사"
                class="field w-100">
            <div class="success-feedback">올바른 형식입니다</div>
            <div class="fail-feedback">필수 항목입니다</div>
        </div>

        <div class="cell">
            <label>카테고리 <i class="fa-solid fa-asterisk red"></i></label>
            <select name="lectureCategory" class="field w-100">
                <option value="">선택하세요</option>
                <option>이론</option>
                <option>실습</option>
                <option>시험</option>
            </select>
            <div class="fail-feedback">필수 선택 항목입니다</div>
        </div>

        <div class="cell">
            <label>강의시간 <i class="fa-solid fa-asterisk red"></i></label>
            <input type="text" inputmode="numeric" name="lectureDuration" class="field w-100" value="0">
            <div class="success-feedback">강의시간이 설정되었습니다</div>
            <div class="fail-feedback">30시간 단위로 최대 300시간 이내에서 설정 가능합니다</div>
        </div>

        <div class="cell">
            <label>수강료 <i class="fa-solid fa-asterisk red"></i></label>
            <input type="text" inputmode="numeric" name="lecturePrice" class="field w-100" value="0">
            <div class="success-feedback">수강료가 설정되었습니다</div>
            <div class="fail-feedback">수강료는 0 이상으로 설정해야 합니다</div>
        </div>

        <div class="cell">
            <label>강의형태 <i class="fa-solid fa-asterisk red"></i></label>
            <select name="lectureType" class="field w-100">
                <option value="">선택하세요</option>
                <option>온라인</option>
                <option>오프라인</option>
                <option>혼합</option>
            </select>
            <div class="fail-feedback">필수 선택 항목입니다</div>
        </div>

        <div class="cell mt-50">
            <label>미리보기</label>
            <input type="file" name="attach" accept=".png, .jpg" multiple class="field w-100">
        </div>

        <div class="cell mt-50">
            <button type="submit" class="btn btn-positive w-100">
                <i class="fa-solid fa-plus"></i>
                <span>신규 강좌 개설하기</span>
            </button>
        </div>

    </div>

</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>
