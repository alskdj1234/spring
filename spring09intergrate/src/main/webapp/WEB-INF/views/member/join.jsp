<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<form action="./join" method="post" enctype="multipart/form-data">
<div class="container w-800 mt-50 mb-50">
	<div class="cell center">
		<h1>회원 정보 입력</h1>
	</div>
	<div class="cell">
		<label>아이디 <i class="fa-solid fa-asterisk red"></i></label>
		<input type="text" name="memberId" required class="field w-100">
	</div>
	<div class="cell">
		<label>비밀번호 <i class="fa-solid fa-asterisk red"></i></label>
		<input type="password" name="memberPassword" required class="field w-100">
	</div>
	<div class="cell">
		<label>비밀번호 확인 <i class="fa-solid fa-asterisk red"></i></label>
		<input type="password" required class="field w-100">
	</div>
	
	<div class="cell">
		<label>닉네임 <i class="fa-solid fa-asterisk red"></i></label>
		<input type="text" name="memberNickname" required class="field w-100">
	</div>
	
	<div class="cell">
		<label>생년월일</label>
		<input type="date" name="memberBirth" class="field w-100">
	</div>
	
	<div class="cell">
		<label>연락처</label>
		<input type="text" inputmode="tel" name="memberContact" class="field w-100">
	</div>
	
	<div class="cell mb-0">
		<label>주소</label>
	</div>
	<div class="cell">
		<input type="text" inputmode="numeric" name="memberPost"
			class="field" size="6" maxlength="6" placeholder="우편번호">
		<button type="button" class="btn btn-neutral">
			<i class="fa-solid fa-magnifying-glass"></i>
			<span>검색</span>
		</button>
	</div>
	<div class="cell">
		<input type="text" name="memberAddress1" class="field w-100" placeholder="기본주소">
	</div>
	<div class="cell">
		<input type="text" name="memberAddress2" class="field w-100" placeholder="상세주소">
	</div>
	<div class="cell">
		<label>상태메세지</label>
		<textarea name="memberMessage" rows="5" class="field w-100"></textarea>
	</div>
	
	<div class="cell mt-50">
		<label>프로필 이미지</label>
		<input type="file" name="attach" accept=".png, .jpg" class="field w-100">
	</div>
	
	<div class="cell mt-50">
		<button type="submit" class="btn btn-positive w-100">회원가입</button> 
	</div>
</div>

</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>