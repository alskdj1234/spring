<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>

<script type="text/javascript">
	//좋아요 확인
	$(function(){
		var params = new URLSearchParams(location.search);
		var countryNo = params.get("countryNo");
		$.ajax({
			url:"/rest/country/like-check",
			method:"post",
			data: {countryNo : countryNo},
			success : function(response){
				$(".fa-heart").removeClass("fa-solid fa-regular")
							.addClass(response.action ? "fa-solid" : "fa-regular");
				$(".heart-count").text(response.count);
			}
		});
	});
</script>

<c:if test="${sessionScope.loginId != null}">
<script type="text/javascript">
	//좋아요 처리
	$(function(){
		var params = new URLSearchParams(location.search);
		var countryNo = params.get("countryNo");
		$(".fa-heart").on("click", function(){
			$.ajax({
				url:"/rest/country/like-action",
				method:"post",
				data: { countryNo : countryNo },
				success : function(response){
					$(".fa-heart").removeClass("fa-solid fa-regular")
								.addClass(response.action ? "fa-solid" : "fa-regular");
					$(".heart-count").text(response.count);
				}
			});
		});
	});
</script>
</c:if>

<div class="container w-800 mt-50 mb-50">
	<div class="cell">
		<h2>국가 상세정보</h2>
	</div>
	<div class="cell">
		<div class="flex-area">
			<div class="w-25 p-20 flex-area flex-center">
				<img src="./flag?countryNo=${countryDto.countryNo}" class="w-100">
			</div>
			<div class="w-200 flex-fill ms-20">
				<div>
					<span style="font-size:32px;">${countryDto.countryName}</span>
					<span class="badge blue ms-20">${countryDto.countryRegion}</span>
				</div>
				<div>
					
				</div>
				<div class="mt-10">
					<span class="blue">${countryDto.countryRegion}</span>
					대륙에 속해있으며
					수도 이름은
					<span class="blue">${countryDto.countryCapital}</span> 
					입니다. <br>
					이 나라의 인구는  
					<span class="blue"><fmt:formatNumber value="${countryDto.countryPopulation}" pattern="#,##0"/></span>
					명 입니다.
				</div>
				<div class="mt-10">
					<i class="fa-solid fa-heart red"></i>
					<span class="heart-count">0</span>
				</div>
			</div>
		</div>
	</div>
	
	<div class="cell mt-50 right">
		<a class="btn btn-neutral" href="./list">
			<i class="fa-solid fa-list"></i>
			<span>목록으로 이동</span>
		</a>
		<a class="btn btn-positive" href="./insert">
			<i class="fa-solid fa-plus"></i>
			<span>신규 등록하기</span>
		</a>
		<a class="btn btn-negative" href="./edit?countryNo=${countryDto.countryNo}">
			<i class="fa-solid fa-pen"></i>
			<span>수정하기</span>
		</a>
		<a class="btn btn-negative" href="./delete?countryNo=${countryDto.countryNo}">
			<i class="fa-solid fa-trash"></i>
			<span>삭제하기</span>
		</a>
	</div>
</div>

<jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>