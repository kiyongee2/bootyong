<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<link rel="stylesheet" href="/static/css/style.css">
<!-- <style type="text/css">
	#container{width: 1000px; margin: 20px auto; text-align: center}
	table{width: 700px; margin: 0 auto;}
	table, td{border: 1px solid #ccc; border-collapse: collapse;}
	table td{padding: 10px;}
	input{width: 580px; height: 25px;}
	textarea{width: 580px;}
	a{text-decoration: none;}
</style> -->
</head>
<body>
	<div id="container">
		<h2>글 상세 보기</h2>
		<form action="/updateBoard" method="post">
			<!-- 글 수정시에 반드시 기본키를 컨트롤러에 보내야 함 -->
			<input type="hidden" name="seq" value="${board.seq}">
			<table class="tbl_view">
				<tr>
					<td>제목</td>
					<td><input type="text" name="title" value="${board.title}"></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><input type="text" name="writer" value="${board.writer}"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td>
						<textarea name="content" rows="10" 
							cols="50"><c:out value="${board.content}" /></textarea>
					</td>
				</tr>
				<tr>
					<td>작성일</td>
					<td><fmt:formatDate value="${board.createDate}" 
						pattern="yyyy/MM/dd HH:mm:ss"/></td>
				</tr>
				<tr>
					<td>조회수</td>
					<td><input type="text" name="writer" value="${board.cnt}"></td>
				</tr>
				<tr>
					<td colspan="2">
					   <button type="submit">글수정</button>
					   <a href='/deleteBoard?seq=<c:out value="${board.seq}" />'
					      onclick="return confirm('정말로 삭제하시겠습니까?')">
					   		<button type="button">글삭제</button>
					   	</a>
					   <a href="/getBoardList"><button type="button">글목록</button></a>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>