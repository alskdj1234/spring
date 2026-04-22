<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>파일 업로드 테스트</h1>

<!-- 파일은 정보가 많아 기존방식이 아닌 멀티파트 방식으로 전송해야 한다.
	폼에 담긴 데이터 형태로 전송하는 걸 멀티파트/폼-데이터라고 부른다.
	고유한 칸막이가 생기고 그 안에 여러 가지 데이터가 합쳐져 하나의 데이터 섹터를 이룬다.
	
	기존 방식으로는 해석 불가능 -> 새로운 방식이 필요함.(스프링 설정만 해주면된다.)

 -->
<form action="./uploadTest" method="post" enctype="multipart/form-data">
	<input type="text" name="uploader">
	<input type="file" name="attach"><br><br>
	<br><br>
	<button>전송</button>



</form>