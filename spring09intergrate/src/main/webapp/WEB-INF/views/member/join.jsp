<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>회원 정보 입력</h1>

<form action="./join" method="post" enctype="multipart/form-data">
	아이디* <input type="text" name="memberId" required> <br><br>
	이메일* <input type="text" inputmode="email" name="memberEmail" required> <br><br>
	비밀번호* <input type="text" name="memberPassword" required> <br><br>
	닉네임* <input type="text" name="memberNickname" required> <br><br>
	생년월일 <input type="date" name="memberBirth"> <br><br>
	연락처 <input type="text" inputmode="tel" name="memberContact"> <br><br>
	우편번호 <input type="text" inputmode="numeric" name="memberPost"> <br><br>
	기본주소 <input type="text" name="memberAddress1"> <br><br>
	상세주소 <input type="text" name="memberAddress2"> <br><br>
	상태메세지 
	<input type="text" name="memberMessage">
<!-- 	<textarea name="memberMessage"></textarea> -->
	<br><br>	
	
	프로필이미지 <input type="file" name="attach" accept=".png, .jpg"> <br><br>
	
	<button>회원가입</button> 
</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>