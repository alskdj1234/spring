<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 이미지 태그(img) 
	원하는 이미지를 화면에 원하는 크기로 출력 해 주는 태그
	-내가 가지고 있는 이미지 또는 외부에 업로드 되어 있는 이미지 모두 가능
	종료 태그가 없음
	
	src(source) 속성으로 이미지의 주소를 제공해야 하낟.
	width, height로 크기 설정 가능(단위 px), 비율(%)도 가능
	alt 속성을 통해 이미지에 대한 설명(접근성 향상을 위한) 설정 가능
-->


<h1>예제 4번 - 이미지</h1>

<img
	src="https://i.pinimg.com/originals/37/59/0f/37590f3eb78594a9afb6a28a9f294060.gif"
	width="200px">


<hr>

<!--   
내가 가진 이미지 출력 
보안 이슈로 하드디스크에 있는 이미지는 표시할 수 없다.(물리적 위치는 불가능)
프로젝트 포함된 주소를 가지는 이미지만 가능(static 폴더)
-->

<!-- <img src="C:\Users\user\Desktop\test.jpg"> -->
<img src="/test.jpg" width="200"><br>


<!-- 더미 이미지

개발 단계에서 이미지가 준비되지 않았을 때 사용할 수 있는 대체 이미지
여러 업체들이 제공해주는 사이트가 존재

 -->
<img src="https://dummyimage.com/200x200/000/fff&text=hi"><br>
<img src="https://picsum.photos/200"><br>
<img src="https://picsum.photos/id/77/200"><br>
<img alt = "번짐" src="https://picsum.photos/id/77/200?blur=5"><br>
