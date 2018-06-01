<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="file.model.vo.*" import="java.util.*" %>
<% ArrayList<DataFile2> list = (ArrayList<DataFile2>)request.getAttribute("fileList"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>전체 파일 리스트</title>
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
	<h1>나의 파일 목록</h1>
	<table border=1 class="table table-hover" id="table">
		<tr><th>파일이름</th><th>파일사이즈</th><th>업로더</th><th>업로드시간</th><th>다운로드</th><th>삭제</th></tr>
		<% for(DataFile2 df:list){%>
			<tr>
			<td><%=df.getBeforeFileName() %></td>
			<td>
			<% if(df.getFileSize()<1024){ %>
			<%=df.getFileSize() %>byte
			<% } else if(df.getFileSize()<1024*1024) { %>	
			<%=df.getFileSize()/1024.0 %>KB
			<%} else {%>
			<%=df.getFileSize()/(1024.0*1024) %>MB
			<% } %>
			</td>
			<td><%=df.getFileUser() %></td>
			<td><%=df.getUploadTime() %></td>
			<td><form action="/fileDown2" method="post">
			<input type="submit" value="다운로드">
			<input type="hidden" name="fileName" value="<%=df.getAfterFileName() %>">
			</form>
			</td>
			<td><form action="/fileRemove2" method="post">
			<input type="hidden" name="afterFileName" value="<%=df.getAfterFileName() %>">
			<input type="submit" value="파일삭제" onclick="return check();"></form></td>
			</tr>
		<% } %>
	</table>
	<a href="/index.jsp">메인페이지로 이동</a>
</center>
<script>
	function check(){
		return confirm("정말로 삭제하시겠습니까?");
	}
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
 <script src="js/bootstrap.min.js"></script>
</body>
</html>