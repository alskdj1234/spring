<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>


<div class="container w-950 mt-50 mb-50">
	<div class="cell">
		<h1>${memberDto.memberNickname}님의 개인 정보</h1>
	</div>
	
	<div class="cell">
		<div class="flex-area">
			<div class="w-25">아이디</div>
			<div class="w-75 blue">${memberDto.memberId}</div>
		</div>
		<div class="flex-area mt-10">
			<div class="w-25">이메일</div>
			<div class="w-75 blue">${memberDto.memberEmail}</div>
		</div>
		<div class="flex-area mt-10">
			<div class="w-25">닉네임</div>
			<div class="w-75 blue">${memberDto.memberNickname}</div>
		</div>
		<div class="flex-area mt-10">
			<div class="w-25">생년월일</div>
			<div class="w-75 blue">${memberDto.memberBirth}</div>
		</div>
		<div class="flex-area mt-10">
			<div class="w-25">연락처</div>
			<div class="w-75 blue">${memberDto.memberContact}</div>
		</div>
		<div class="flex-area mt-10">
			<div class="w-25">주소</div>
			<div class="w-75 blue">${memberDto.memberPost} ${memberDto.memberAddress1} ${memberDto.memberAddress2}</div>
		</div>
		<div class="flex-area mt-10">
			<div class="w-25">회원등급</div>
			<div class="w-75 blue">${memberDto.memberLevel}</div>
		</div>
		<div class="flex-area mt-10">
			<div class="w-25">상태메세지</div>
			<div class="w-75 blue"><pre>${memberDto.memberMessage}</pre></div>
		</div>
		<div class="flex-area mt-10">
			<div class="w-25">포인트</div>
			<div class="w-75 blue"><fmt:formatNumber value="${memberDto.memberPoint}" pattern="#,##0"/></div>
		</div>
		<div class="flex-area mt-10">
			<div class="w-25">가입일</div>
			<div class="w-75 blue"><fmt:formatDate value="${memberDto.memberJoin}" pattern="y년 M월 d일 E a h시 m분"/></div>
		</div>
		<div class="flex-area mt-10">
			<div class="w-25">최종 로그인</div>
			<div class="w-75 blue"><fmt:formatDate value="${memberDto.memberLogin}" pattern="y년 M월 d일 E a h시 m분"/></pre></div>
		</div>
	</div>
	
	<hr class="mt-50 mb-50">
	
	<div class="cell">
		<div class="flex-area" style="align-items:end;">
			<h1 class="mt-0 mb-0">최근 로그인 이력</h1>
			<a class="link ms-20" href="./history">더보기 <i class="fa-solid fa-arrow-right"></i></a></h1>
		</div>
	</div>
	<div class="cell">
		<table class="table">
			<thead>
				<tr>
					<th class="w-40">일시</th>
					<th>접속주소</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="memberHistoryDto" items="${loginHistory}">
				<tr>
					<td>
						<fmt:formatDate value="${memberHistoryDto.memberHistoryTime}" pattern="y년 M월 d일 H시 m분"/>
					</td>
					<td>${memberHistoryDto.memberHistoryAddress}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<hr class="mt-50 mb-50">
	
	<div class="cell red">
		<h1>계정 관리</h1>
	</div>
	<div class="cell mt-50">
		<a href="./password" class="btn btn-negative w-100">비밀번호 변경하기</a>
	</div>
	<div class="cell mt-20">
		<a href="./edit" class="btn btn-negative w-100">개인정보 변경하기</a>
	</div>
	<div class="cell mt-20">
		<a href="./goodbye" class="btn btn-negative w-100">회원 탈퇴하기</a>
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>






