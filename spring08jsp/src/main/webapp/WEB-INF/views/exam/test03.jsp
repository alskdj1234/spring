<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 
	a 태그
	- 다른 대상으로 이동할 수 있는 기능을 제공하는 태그 
	- 태그 안에 옵션(attributes, 속성)을 작성해서 추가 정보를 알려줘야한다
-->

<h1>예제 3번 - 링크</h1>

<a href="http://localhost:8080/exam/test01">예제 1번 보기</a> <br>
<a href="http://localhost:8080/exam/test02">예제 2번 보기</a> <br>

<a href="https://www.google.com/">구글로 이동</a> <br>
<a href="https://www.naver.com/">네이버로 이동</a> <br>

<!-- 
	내가 만든 페이지는 여러 상황에 의해 경로가 변할 수 있고 그래도 작동해야 한다 
	주소에서 변할 수 있는 부분을 제거하고 작성해야 한다
	
	1. 프로토콜(protocol) - http, https처럼 통신방식을 정의하는 키워드
	2. 호스트(host) - localhost처럼 서버의 위치를 나타내는 키워드(IP도 이곳에 해당)
	3. 포트(port) - 서버에서 서비스가 실행중인 번호
	
	1, 2, 3번을 제거하고 작성하면 이를 절대경로(absolute path)라 부른다
	현재 상황에 맞게 1, 2, 3번을 자동 설정
	
	* 슬래시로 시작하는 주소는 절대경로다!
-->
<a href="/exam/test01">예제 1번 보기(절대경로)</a> <br>
<a href="/exam/test02">예제 2번 보기(절대경로)</a> <br>

<!-- 
	상대경로(relative path)
	- 현재 위치를 기준으로 계산하는 경로 
	- 현재 위치는 `/exam/test03`이고 목적지가 `/exam/test01`이라면 둘은 같은 엔드포인트에 위치
	- 이 경우 `./test01` 또는 `test01`이라고 작성하면 연결이 가능하다
	
	* 슬래시로 시작하지 않으면 상대경로다!
-->

<a href="test01">예제 1번 보기(상대경로)</a> <br>
<a href="test02">예제 2번 보기(상대경로)</a> <br>

<!-- 다른 태그와 결합이 가능하며 들여쓰기로 표시 -->
<h1>
	<a href="test01">예제 1번 보기(상대경로)</a> 
</h1>

<a href="test01">
	<h1>예제 1번 보기(상대경로)</h1>
</a> 