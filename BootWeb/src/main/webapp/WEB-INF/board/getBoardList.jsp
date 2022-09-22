<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록</title>
<link rel="stylesheet" href="/static/css/style.css">
<!-- <style type="text/css">
	#container{width: 1000px; margin: 20px auto; text-align: center}
	table{width: 700px; margin: 0 auto;}
	table, th, td{border: 1px solid #ccc; border-collapse: collapse;}
	table th, td{padding: 10px;}
	table thead{background: #eee;}
</style> -->
</head>
<body>
	<div id="container">
		<h2>게시글 목록</h2>
		<table class="tbl_list">
			<thead>
				<tr>
					<th>번호</th><th>제목</th><th>작성자</th><th>등록일</th><th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${boardList}" var="board">
				<tr>
					<td><c:out value="${board.seq}" /></td>
					<td>
						 <a href='/getBoard?seq=<c:out value="${board.seq}" />'>
						 	<c:out value="${board.title}" />
						 </a>
					 </td>
					<td><c:out value="${board.writer}" /></td>
					<td>
						<fmt:formatDate value="${board.createDate}" pattern="yyyy/MM/dd HH:mm:ss"/> 
					</td>
					<td><c:out value="${board.cnt}" /></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<p><a href="/insertBoard">새글 등록</a></p>
	</div>
</body>
</html>