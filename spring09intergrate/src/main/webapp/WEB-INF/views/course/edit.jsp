<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/template/header.jsp"></jsp:include>
    <h1>강좌 정보 수정</h1>
    
   
    <form action="./edit" method="post">
    
    	<input type="hidden" name="countryNo" value="${courseDto.courseNo}">
    	
    	
    	<select name="category" required>
    	
    	<option ${courseDto.category=='이론' ? 'selected' : ''}>이론</option>
    	<option ${courseDto.category=='실습' ? 'selected' : ''}>실습</option>
    	<option ${courseDto.category=='시험' ? 'selected' : ''}>시험</option>
    	
    	</select>
    	<br><br>
    	<input type="text" name="courseName" value="${courseDto.courseName}" required>
    	<select name="classType" required>
    	
    	<option ${courseDto.classType=='온라인' ? 'selected' : ''}>온라인</option>
    	<option ${courseDto.classType=='오프라인' ? 'selected' : ''}>오프라인</option>
    	<option ${courseDto.classType=='혼합' ? 'selected' : ''}>혼합</option>
    	
    	</select>
    	<br><br>
    	
    	<input type="number" name="lectureTime" min="30" step="30" value="${courseDto.lectureTime}" required>
    	<input type="number" name="fee" step="1000" min="1000" value="${couresDto.fee}" required>
    
    	<button>수정하기</button>
 		   
    
    </form>
    <jsp:include page="/WEB-INF/views/template/footer.jsp"></jsp:include>