<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<div class="container w-600 mt-50 mb-50">
	<div class="cell">
		<h1 class="red">탈퇴 대기중인 회원입니다</h1>
	</div>
	<div class="cell">
		탈퇴 대기중이기 때문에 로그인하실 수 없습니다 <br>
		만약 실수로 탈퇴신청을 하셨다면 고객센터에 문의바랍니다
	</div>
	<div class="cell mt-50">
		<h2>
			<a href="./login" class="link">
				<i class="fa-solid fa-right-to-bracket"></i>
				<span>로그인 페이지로</span>
			</a>
		</h2>
	</div>
	<div class="cell">
		<h2>
			<a href="/" class="link">
				<i class="fa-solid fa-house"></i>
				<span>메인 페이지로</span>
			</a>
		</h2>	
	</div>
</div>



<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>