<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 테이블(table)

		줄 칸으로 이루어진 다량의 데이터를 간격을 맞춰 출력하는 도구
		다양한 태그들을 조합 하여 구현
		<table> : 표 전체 영역
			<thead> : 테이블 헤더, 제목을 표시할 때 사용
			<tbody> : 테이블 바디, 데이터를 표시할 때 사용
			<tfoot> : 테이블 푸터, 기타 정보 표시할 때 사용 잘 안 씀
			<tr> : 테이블 로우, 줄
			<th> : 테이블 헤더, 칸(제목용), 굵은 글씨에 가운데 정렬(align 속성으로 변경 가능)
			<td> : 테이블 데이터, 칸(데이터용),일반 크기에 왼쪽 정렬(align 속성으로 변경 가능)
 			얼라인은 최대 범위가 티바디
 			width : 가로 범위 조절
 			height : 세로 범위 조절 (높이 잘 안 건드림 이미지와 같은 이유)
 -->



<h1>표(테이블) 만들기</h1>

<table border="1" width="300">

	<thead>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>성별</th>
			<th>지역</th>



		</tr>


	</thead>
	<tbody align="center">
		<tr>
			<td>1</td>
			<td>피카츄</td>
			<td>남자</td>
			<td>서울 강남구</td>
			
		</tr>
		<tr>
			<td>2</td>
			<td>라이츄</td>
			<td>남자</td>
			<td>텍사스주</td>
			
		</tr>
		

	</tbody>

</table>

