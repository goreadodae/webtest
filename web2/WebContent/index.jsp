<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.*" %>
<% Member m = (Member)session.getAttribute("user"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인 페이지</title>
    <style>
        #idpw{
            display: inline-block;
            width: 220px;
            height: 50px;

        }
        #login{
            display: inline-block;
            width: 70px;
            height: 50px;
            box-sizing: border-box;
        }
        form{
            display: inline;
            width: 100px;
        }
        input{
            display: inline;
        }
        #infoBtn{
        	cursor:pointer;
        	text-decoration: underline;
        	color: blue;
        }
    </style>
</head>
<body>
	<% if(m==null){ %>
    <fieldset style="width:300px; height:100px;">
        <legend>로그인</legend>
        <form action="login" method="post">
            <div id="idpw">
                ID : <input type="text" placeholder="ID를 입력하세요" name="userId" /><br> 
                PW : <input type="password" placeholder="PW를 입력하세요" name="userPwd" /><br>
            </div>
                <input id="login" type="submit" value="로그인" />
        </form>
        <a href="/views/member/joinus.html">회원가입</a>
        <a href="">ID 찾기</a> /
        <a href="">PW 찾기</a>
    </fieldset>
    <%} else if (m.getUserId().equals("admin")){ %>
    <h1>[<%=m.getUserName() %>]님의 페이지</h1>
    <a href="">나의정보</a>
    <a href="/logout">로그아웃</a>
    <a href="">회원탈퇴</a>
    <a href="/selectAll">전체회원조회</a>
    <%} else { %>
    <h1>[<%=m.getUserName() %>]님의 페이지</h1>
    <label onclick="myInfo();" id="infoBtn">나의정보</label>
    <form action="/mypage" method="post" style="display:none;" id="myInfo">
    <label style="color:red;">비밀번호 입력 : </label>
    <input type="password" name="userPwd"/>
    <input type="submit" value="확인"/>
    </form><br>
    <a href="/logout">로그아웃</a><br>
    <label onclick="deleteMember();" id="infoBtn">회원탈퇴</label>
    <form action="/deleteMember" method="post" style="display:none;" id="deleteMember">
    <label style="color:red;">비밀번호 입력 : </label>
    <input type="password" name="userPwd"/>
    <input type="submit" value="확인"/>
    </form><br>
    <% } %>
</body>
<script>
function myInfo(){
	document.getElementById("myInfo").style="display:inline";
}
function deleteMember(){
	document.getElementById("deleteMember").style="display:inline";
}
</script>
</html>