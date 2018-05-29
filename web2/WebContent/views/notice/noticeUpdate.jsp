<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="notice.model.vo.*" %>
<% Notice n = (Notice)request.getAttribute("notice"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<style>
	#title th{
		background-color: rgb(201, 255, 195);
	}
</style>
</head>
<body>
<div style="height: 100px; width: 725px; display:inline-block;">
<form action="/noticeUpdate" style="display:inline-block;">
<table border="1" id="title"  class="table table-hover">
	<tr><th>글번호</th><td><%=n.getNoticeNo() %></td>
	<th>글쓴이</th><td><%=n.getUserId() %></td>
	<th>작성일</th><td><%=n.getRegDate() %></td></tr>
	<tr><th>글제목</th><td colspan=5 width="600px">
	<input type="text" name="subject" value="<%=n.getSubject() %>" size="70%"/></td></tr>
</table>
<br>
<textarea rows="20" cols="100" style="resize:none;" name="contents"><%=n.getContents() %></textarea>
<br>
	<input type="hidden" name="noticeNo" value="<%=n.getNoticeNo() %>">
	<input type="submit" value="수정"/>
</form>
</div>
<button onclick="back();">목록</button>
<script>
	function back(){
		location.href="/notice";
	}
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
 <script src="js/bootstrap.min.js"></script>
</body>
</html>