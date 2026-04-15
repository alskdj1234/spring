<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>로그인 이력 조회를 위해 다음의 정보를 입력 해 주세요</h1>
<form action="./loginHistory" method="post">

아이디 <input type="text" name="memberHistoryOrigin" required><br><br>
비밀번호 <input type="password" name="memberHistoryPassword" required><br><br>

조회 시작일<input type="text" inputmode="numeric" name="beginDate" required><br><br>
조회 종료일<input type="text" inputmode="numeric" name="endDate" required><br><br>

<button>조회하기</button>
</form>







<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>