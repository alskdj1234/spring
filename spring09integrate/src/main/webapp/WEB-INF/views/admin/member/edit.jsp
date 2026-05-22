<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<h1>회원 정보 변경</h1>

<form action="./edit" method="post">
	<input type="hidden" name="memberId" value="${memberDto.memberId}">

	이메일 <input type="text" inputmode="email" name="memberEmail" value="${memberDto.memberEmail}" required> <br><br>
	닉네임 <input type="text" name="memberNickname" value="${memberDto.memberNickname}" required> <br><br>
	생년월일 <input type="date" name="memberBirth" value="${memberDto.memberBirth}"> <br><br>
	연락처 <input type="text" inputmode="tel" name="memberContact" value="${memberDto.memberContact}"> <br><br>
	우편번호 <input type="text" inputmode="numeric" name="memberPost" value="${memberDto.memberPost}"
				size="6" maxlength="6"> <br><br>
	기본주소 <input type="text" name="memberAddress1" value="${memberDto.memberAddress1}"
				size="80"> <br><br>
	상세주소 <input type="text" name="memberAddress2" value="${memberDto.memberAddress2}"
				size="80"> <br><br>
	상태메세지 <br>
	<input type="text" name="memberMessage" value="${memberDto.memberMessage}" size="80"> <br><br>
	
	<!-- (중요) 자신과 동일한 등급은 생성이 불가능 -->
	등급
	<select name="memberLevel">
		<option ${memberDto.memberLevel == '브론즈' ? 'selected':''}>브론즈</option>
		<option ${memberDto.memberLevel == '실버' ? 'selected':''}>실버</option>
		<option ${memberDto.memberLevel == '골드' ? 'selected':''}>골드</option>
		<option ${memberDto.memberLevel == '플래티넘' ? 'selected':''}>플래티넘</option>
		<option ${memberDto.memberLevel == '다이아' ? 'selected':''}>다이아</option>
	</select>
	<br><br>
	
	포인트 <input type="text" name="memberPoint" value="${memberDto.memberPoint}" required> <br><br>
	
	<button>정보 변경하기</button>
</form>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>


