<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@
 	taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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

<h1>빠리 올림픽 순위표</h1>

<hr>
<table border="1" width="300">

	<thead>
		<tr>
			<th>순위</th>
			<th>국가</th>
			<th>금메달</th>
			<th>은메달</th>
			<th>동메달</th>
			<th>총메달</th>



		</tr>


	</thead>
	<tbody align="center">
		<tr>
			<td>1</td>
			<td>미국</td>
			<td>40개</td>
			<td>44개</td>
			<td>42개</td>
			<td>126개</td>

		</tr>
		<tr>
			<td>2</td>
			<td>중국</td>
			<td>48</td>
			<td>27</td>
			<td>24</td>
			<td>91</td>


		</tr>


	</tbody>

</table>



<h1>빠리 올림픽 순위표</h1>

<hr>
<table border="1" width="300">

	<thead>
		<tr>
			<!--     		row span	2줄에 걸쳐 위치하도록 설정 -->
			<th rowspan="2">순위</th>
			<th rowspan="2">국가</th>

			<!--     			4칸에 걸쳐 위치 -->
			<th colspan="4">메달 현황</th>


		</tr>

		<tr>




			<th>금</th>
			<th>은</th>
			<th>동</th>
			<th>계</th>

		</tr>



	</thead>
	<tbody align="center">
		<tr>
			<td>1</td>
			<td>미국</td>
			<td>40개</td>
			<td>44개</td>
			<td>42개</td>
			<td>126개</td>

		</tr>
		<tr>
			<td>2</td>
			<td>중국</td>
			<td>48개</td>
			<td>27개</td>
			<td>24개</td>
			<td>91개</td>


		</tr>


	</tbody>

</table>


<h1>메뉴판</h1>
<hr>

<table border="1" width="100%">
	<thead>
		<tr>

			<th>카테고리</th>
			<th>메뉴명</th>
			<th>판매가</th>
			<th>행사여부</th>



		</tr>

	</thead>

	<tbody>

		<tr>

			<td>음료</td>
			<td>아메리카노</td>
			<td>2,500</td>
			<td>행사중</td>



		</tr>
		<tr>

			<td>음료</td>
			<td>고구마라뗴</td>
			<td>3,000</td>
			<td></td>



		</tr>
		<tr>

			<td>디저트</td>
			<td>티라미수</td>
			<td>4,000</td>
			<td>행사중</td>



		</tr>
		<tr>

			<td>디저트</td>
			<td>마카롱</td>
			<td>2,000</td>
			<td></td>



		</tr>


	</tbody>




</table>



<h1>상품 목록</h1>

<hr>
<table border="1">
	<thead>

		<tr>

			<th>상품명</th>
			<th>분류</th>
			<th>가격</th>
			<th>재고</th>
			<th>할인율</th>
			<th>새벽배송</th>
		</tr>

	</thead>

	<tbody>

		<tr>

			<th>점보도시락</th>
			<th>라면</th>
			<th>8,500</th>
			<th>3</th>
			<th>5</th>
			<th>Y</th>
		</tr>


	</tbody>





</table>