<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container w-800 mt-50 mb-50">
	<div class="cell">
		<h1 class="blue">비밀번호 변경 필요 안내</h1>
	</div>
	<div class="cell mt-50">
		비밀번호를 변경한지 <span class="blue">오~~~~래</span> 되셨습니다 <br>
		변경을 원하시면 <a href="./password" class="link">여기</a>를 눌러주세요 <br><br>
		
		지금 바꾸길 원하지 않으신다면 <a href="/">메인페이지</a>로 이동하세요 <br>
		30일 뒤에 다시 알려주길 원한다면 <a href="./later">여기</a>를 누르세요		
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>