<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1> 유튜브</h1>


<form action="https://www.youtube.com/results">
	<input name="search_query">
	<button>전송</button>
</form>

<h1> 깃허브</h1>
<!-- type은 입력창 형태, value는 초기값 placeholder는 기본 안내 멘트-->
<form action="https://github.com/search">
	<input type="search" name="q" placeholder="저장소 이름 입력">
	<input type="hidden" name="type" value="repositories">
	<button>전송</button>
</form>

<h1> 네이버 쇼핑 </h1>

<form action="https://search.shopping.naver.com/search/all">
	<input name="query">
	<button>전송</button>
</form>

<h1> 다나와 </h1>
<form action="https://search.danawa.com/dsearch.php">
	<input type="search" name="k1">
	<input type="hidden" name="module" value="goods">
	<input type="hidden" name="act" value="dispMain">
	<button>전송</button>
</form>

<h1> 다음 </h1>

<form action="https://search.daum.net/search">
	<input type="hidden" name="w" value="tot">
	<input type="hidden" name="DA" value="YZR">
	<input type="hidden" name="t__nil_searchbox" value="btn">
	<input type="text" name="q">
	<button>전송</button>
</form>