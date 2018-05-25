<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>마이 페이지</title>
<style>
    form{
        display: inline-block;
    }
</style>
</head>
<body>
<% Member m = (Member)request.getAttribute("user"); %>
<center>
<h1>[<%=m.getUserName() %>]님의 정보</h1>
<form action="/update" method="post">
ID : <input type="text" placeholder="ID를 입력하세요" name="userId" id="userId " value="<%=m.getUserId()%>"/><br><br>
PW : <input type="password" placeholder="PW를 입력하세요" name="userPwd" value="<%=m.getUserPwd()%>"/><br><br>
PW(re) : <input type="password" placeholder="PW를 재입력하세요" name="userPwd_re" value="<%=m.getUserPwd()%>"/><br><br>
Name : <input type="text" placeholder="이름을 입력하세요" name="userName" value="<%=m.getUserName()%>"/><br><br>
Age : <input type="text" placeholder="나이을 입력하세요" name="age" value="<%=m.getAge()%>"/><br><br>
Email : <input type="email" placeholder="이메일을 입력하세요" name="email" value="<%=m.getEmail()%>"/><br><br>
phone : <input type="text" placeholder="폰번호를 입력하세요" name="phone" value="<%=m.getPhone()%>"/><br><br>
Addr : <input type="text" placeholder="주소을 입력하세요" name="addr" value="<%=m.getAddress()%>"/><br><br>
Gender : <% if(m.getGender().equals("M")){
	%><input type="radio" name="gender" value="M" checked/>남
	<input type="radio" name="gender" value="F" disabled/>여<br><br><%
} else {%>
<input type="radio" name="gender" value="M" disabled/>남
<input type="radio" name="gender" value="F" checked/>여<br><br><%} %>
취미 : <input type="text" placeholder="취미를 입력하세요" name="hobby" value="<%=m.getHobby()%>"/><br><br>
<input type="submit" value="수정하기" style="align: left"/>
</form>
<button onclick="back();">취소</button>
<script>
	function back(){
		history.go(-1);
	}
</script>
</body>
</html>