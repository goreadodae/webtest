<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<style>
	#title th{
		background-color: rgb(201, 255, 195);
	}
</style>
<title>글쓰기</title>
</head>
<body>
<form action="/insertNotice">
	<table border="1" id="title"  class="table table-hover">
	<tr><th>글제목</th><td colspan=5 width="550px"><a>
	<input type="text" name="subject" size="100%"/>
	</a></td></tr>	
	<tr><th width=100px>내용</th>
	<td>
	<textarea rows="20" cols="100" name="contents" style="resize:none;">
	</textarea>
	</td>
	</tr>
	</table>
	<input type="submit" value="게시하기"/>
</form>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
 <script src="js/bootstrap.min.js"></script>
</body>
</html>