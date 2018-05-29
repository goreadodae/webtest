<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="notice.model.vo.*" import="member.model.vo.*" import="java.util.*" %>
<% PageData pd = (PageData)request.getAttribute("pageData");
	ArrayList<Notice> list = pd.getNoticeList();
	String pageNavi = pd.getPageNavi();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항 목록</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<style>
	#table{
		width: 70%;
	}
	th{
        background-color: lightgray;
    }
</style>
</head>
<body>

<center>
<h1>공지사항</h1>
<table border="1" class="table table-hover" id="table">
	<tr><th>글번호</th><th>글제목</th><th>작성자</th><th>작성일</th></tr>
<% for(Notice n : list) {%>
	<tr>
		<td><%=n.getNoticeNo() %></td>
		<td><a href="/noticeSelect?noticeNo=<%=n.getNoticeNo()%>"><%=n.getSubject() %></a></td>
		<td><%=n.getUserId() %></td>
		<td><%=n.getRegDate() %></td>
	</tr>
<%} %>
</table>
<ul class="pagination">
<%=pageNavi %>
</ul>
<form action="/search" method="get">
<select name="sel">
		<option value="subject">제목</option>
		<option value="userid">작성자</option>
</select>
<% String currentSearch = request.getParameter("search");
	if(currentSearch==null){
		currentSearch="";
	}
%>
<input type="text" name="search" value="<%= currentSearch%>"/>	
<input type="submit" value="검색"  class="btn btn-primary"/>
</form>
<%
	if(session.getAttribute("user")!=null&&
		((Member)session.getAttribute("user")).getUserId().equals("admin")){
%>
<button type="button" onclick="insert();"class="btn btn-primary">글쓰기</button>
<%} %>
<script>
	function insert(){
		location.href = "/views/notice/insertNotice.jsp";
	}
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
 <script src="js/bootstrap.min.js"></script>
</center>
</body>
</html>