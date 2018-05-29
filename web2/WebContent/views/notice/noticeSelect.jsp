<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="notice.model.vo.*" 
	import="member.model.vo.*"%>
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
<div style="height: 100px; width: 725px;">
<table border="1" id="title"  class="table table-hover">
	<tr><th>글번호</th><td><%=n.getNoticeNo() %></td>
	<th>글쓴이</th><td><%=n.getUserId() %></td>
	<th>작성일</th><td><%=n.getRegDate() %></td></tr>
	<tr><th>글제목</th><td colspan=5 width="600px"><%=n.getSubject() %></td></tr>
</table>
</div>
<br>
<textarea readonly rows="20" cols="100" style="resize:none;"><%=n.getContents() %></textarea>
<br>
<button onclick="back();">목록</button>
<%
	if(session.getAttribute("user")!=null&&
		((Member)session.getAttribute("user")).getUserId().equals("admin")){
%>
<form action="/noticeUpdateReady" style="display:inline;">
	<input type="hidden" name="noticeNo" value=<%=n.getNoticeNo() %>>
	<input type="submit" value="수정"/>
</form>
<form action="/deleteNotice" style="display:inline;">
	<input type="hidden" name="noticeNo" value=<%=n.getNoticeNo() %>>
	<input type="submit" value="삭제" onclick="return check();"/>
</form>
<form action="/views/notice/insertNotice.jsp" style="display:inline;">
	<input type="submit" value="글쓰기"/>
</form>
<%} %>
<script>
	function back(){
		location.href="/notice";
	}
	function check(){
		if(window.confirm("삭제하시겠습니까?")){
			return true;
		}
		else{
			return false;
		}
	}
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
 <script src="js/bootstrap.min.js"></script>
</body>
</html>