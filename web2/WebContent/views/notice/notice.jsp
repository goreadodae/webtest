<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="notice.model.vo.*" import="java.util.*" %>
<% PageData pd = (PageData)request.getAttribute("pageData");
	ArrayList<Notice> list = pd.getNoticeList();
	String pageNavi = pd.getPageNavi();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항 목록</title>
</head>
<body>
<center>
<h1>공지사항</h1>
<table border="1">
	<tr><th>글번호</th><th>글제목</th><th>작성자</th><th>작성일</th></tr>
<% for(Notice n : list) {%>
	<tr>
		<td><%=n.getNoticeNo() %></td>
		<td><%=n.getSubject() %></td>
		<td><%=n.getUserId() %></td>
		<td><%=n.getRegDate() %></td>
	</tr>
<%} %>
</table>
<label><%=pageNavi %></label>
<form action="/search" method="get">
<select name="sel">
		<option value="subject">제목</option>
		<option value="userid">작성자</option>
</select>
<input type="text" name="search"/>
<input type="submit" value="검색"/>
</form>
</center>
</body>
</html>