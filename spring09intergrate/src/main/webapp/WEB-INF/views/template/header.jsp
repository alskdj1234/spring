<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>KH정보교육원</title>
    <link rel="icon" href="/images/kh.jpg" type="image/jpeg">

    <!-- 아이콘 -->
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css">

    <!-- 디자인을 작성하기 위한 영역 -->
    <link rel="stylesheet" type="text/css" href="/css/commons.css">
    <style>
        /* div { box-shadow: 0 0 0 1px gray ;} */
    </style>
    
    <!-- jQuery CDN -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    
    <script src="/js/checkbox.js"></script>
    
    <!-- lightpick cdn -->
    <link href="https://cdn.jsdelivr.net/npm/lightpick@1.6.2/css/lightpick.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/moment@2.30.1/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.30.1/locale/ko.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/lightpick@1.6.2/lightpick.min.js"></script>
    
    <link rel="stylesheet" type="text/css" href="/lib/multipage/multipage.css">
    <script src="/lib/multipage/multipage.js"></script>
</head>
<body>
    <!-- 메인 컨테이너1 + 내부영역4 -->
    <div class="container w-1200">
        <div class="flex-area flex-vertical">
            <!-- 헤더 영역 -->
            <div class="flex-area">
                <div class="w-25 flex-area flex-center">
                    <img src="https://www.dummyimage.com/200x50">
                </div>
                <div class="w-50 flex-area flex-center">
                    <h1>KH정보교육원 스프링개발자 수업자료</h1>
                </div>
                <div class="w-25 flex-area flex-center">
                    <div class="center">
                        <h2 class="mt-0 mb-0">24시간상담</h2>
                        <div>1588-0000</div>                        
                    </div>
                </div>
            </div>

            <!-- 메뉴 -->
            <div>
				<c:if test="${sessionScope.loginId == null || sessionScope.loginLevel == null}">
					<jsp:include page="/WEB-INF/views/template/menu-normal.jsp"></jsp:include>
				</c:if>     
				<c:if test="${sessionScope.loginId != null && sessionScope.loginLevel != null}">
					<c:if test="${sessionScope.loginLevel != '마스터'}">
						<jsp:include page="/WEB-INF/views/template/menu-member.jsp"></jsp:include>
					</c:if>
					<c:if test="${sessionScope.loginLevel == '마스터'}">
						<jsp:include page="/WEB-INF/views/template/menu-admin.jsp"></jsp:include>
					</c:if>
				</c:if>
            </div>

            <!-- 사이드바 및 컨텐츠 -->
            <div style="min-height: 450px;" class="flex-area">
                <div class="w-200">
                    <div class="container w-100">
                    
                    	<c:if test="${sessionScope.loginId == null || sessionScope.loginLevel == null}">
                        <!-- 비회원 상태 -->
                        <div class="cell center">
                            <h3>비회원 상태</h3>
                        </div>
                        <div class="cell center">
                            <a href="/member/login">
                                <i class="fa-solid fa-right-to-bracket"></i>
                                <span>로그인</span>
                            </a>
                        </div>
                        <div class="cell center">
                            <a href="/member/join">
                                <i class="fa-solid fa-user-plus"></i>
                                <span>회원가입</span>
                            </a>
                        </div>
                        </c:if>

                        <!-- 회원 상태 -->
                        <c:if test="${sessionScope.loginId != null && sessionScope.loginLevel != null}">
                        <div class="cell center">
                            <h3>
                            	${sessionScope.loginId}님<br>
                            	(${sessionScope.loginLevel})
                            </h3>
                        </div>
                        
                        <c:if test="${sessionScope.loginLevel != '마스터'}">
                        <div class="cell center">
                            <!-- 이미지와 글자를 겹쳐서 배치하기 위해 영역을 설정하고 내부에 요소 배치 -->
                            <div class="image-hover image-circle image-shadow"
                                    style="width: 150px; margin: 0 auto;">
                                <img src="/member/profile?memberId=${sessionScope.loginId}">
                                <div class="content">
                                    <a href="/member/mypage" class="white">
                                        <i class="fa-solid fa-user"></i>
                                        <span>내 정보 보기</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                        </c:if>
                        
                        </c:if>
                        
                    </div>
                </div>
                <div class="w-200 flex-fill">
