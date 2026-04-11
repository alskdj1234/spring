<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- 폼 
	데이터를 전송할 수 있는 눈에 보이지 않는 도구
	내부에 있는 입력값 전송 (주소를 만들어냄)
	액션이란 속성으로 폼이 전송될 목적지를 설정 (?전까지)
	<input> 입력창 태그
	<button> 클릭이 가능한 태그
-->


<h1> 입력 폼(form)</h1>


<form action="https://genie.co.kr/search/searchMain">
	<input name="query">
	<button>전송</button>
</form>