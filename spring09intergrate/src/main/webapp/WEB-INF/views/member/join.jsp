<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
<h1>회원 가입</h1>
<form action="./join" method="post">
Id <input type="text" name="memberId" required><br>
Email <input type="text" name="memberEmail" required><br>
비밀번호<input type="text" name="memberPassword" required><br>
닉네임 <input type="text" name="memberNickname" required><br>
생년월일 <input type="text" name="memberBirth"><br>
연락처 <input type="text" name="memberContact" required><br>
<h2>주소입력시 우편번호, 기본주소, 상세주소를 모두 입력 해 주세요</h2><br>
우편번호 <input type="text" name="memberPost"><br>
기본주소 <input type="text" name="memberAddress1"><br>
상세주소 <input type="text" name="memberAddress2"><br>
상태 메시지 <input type="text" name="memberMessage"><br>

<button>등록하기</button>


</form>



 <jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>