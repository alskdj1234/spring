<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action=".edit" method="post">

<input type="hidden" name="bookId" value="${bookDto.bookId}">

<input type="text" name="bookTitle" value="${bookDto.bookTitle}" required>

<input type="text" name="bookAuthor" value="${bookDto.bookAuthor}">

<select name="bookGenre" required>

  <option ${bookDto.bookGenre=="판타지" ? "selected" : "" }>판타지</option>
  <option ${bookDto.bookGenre=="교양" ? "selected" : "" } >교양</option>
  <option ${bookDto.bookGenre=="소설" ? "selected" : "" } >소설</option>
  <option ${bookDto.bookGenre=="역사" ? "selected" : "" }>역사</option>
  <option ${bookDto.bookGenre=="과학" ? "selected" : "" }>과학</option>
  <option ${bookDto.bookGenre=="추리소설" ? "selected" : "" }>추리소설</option>
  <option ${bookDto.bookGenre=="자기계발" ? "selected" : "" }>자기계발</option>
  <option ${bookDto.bookGenre=="수험서" ? "selected" : "" }>수험서</option>
  <option ${bookDto.bookGenre=="고전" ? "selected" : "" }>고전</option>
  <option ${bookDto.bookGenre=="인문" ? "selected" : "" }>인문</option>



</select>





</form>