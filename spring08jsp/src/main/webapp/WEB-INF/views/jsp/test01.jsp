<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 
Expression Language
JSP에서 프로그래밍 결과를 화면에 출력하는 방법
문법 : ${이름}
장점 : 없어도 null이 안 나옴
자료형 구분을 따로 하지 않는다.
비교를 비교 연산자로 한다.
그 외에도 출력 관련 불편 했던 점들을 대폭 개선

프로그래밍 처리(if,for) 등은 불가능. -> JSTL까지 같이 쓰면 가능


 --%>
<h1>JSP예제 1번</h1>


컨트롤러에서 전달되는 데이터들을 화면에 출력할 수 있습니다. <br><br>

message : ${message}<br><br>
dice : ${dice}<br><br>
lotto : ${lotto}<br><br>
hello : ${hello}<br><br>