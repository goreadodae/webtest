<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="notice.model.vo.*" 
	import="member.model.vo.*"
	import="java.util.*"%>
<% Notice n = (Notice)request.getAttribute("notice");
	ArrayList<NoticeComment> list = (ArrayList<NoticeComment>)request.getAttribute("comment");
%>
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

<center>
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
<div style="width: 725px;">
<h1 style="text-align: left;">댓글</h1>
<% if(list.isEmpty()){%>
	<h4>댓글이 없습니다.</h4>
<%} else {%>

<% for(NoticeComment nc:list){ %>
	<div style="border:1px solid black;">
	<span style="text-align: right; display: block;">작성자 : <%=nc.getUserId() %> / 작성일 : <%=nc.getRegDate() %></span><br>
	<span style="text-align: left; display: block;" id="<%="content"+nc.getCommentNo()%>"><%=nc.getContent() %></span>
	<div style="text-align: right;">
	<%if(session.getAttribute("user")!=null) {%>
	<%if(nc.getUserId().equals(((Member)session.getAttribute("user")).getUserId())) { %>
	<form action="/updateComment" style="display: inline;">
	<input type="text" style="display: none;" id="<%="contentText"+nc.getCommentNo()%>" value="<%=nc.getContent() %>" name="content"/>
	<input type="hidden" name="commentNo" value="<%=nc.getCommentNo()%>"/>
	<input type="hidden" name="noticeNo" value="<%=nc.getNoticeNo()%>"/>
	<input type="submit" value="수정" id="<%="updateBtn2"+nc.getCommentNo()%>" style="display: none;"/>
	</form>
	<button id="<%="deleteBtn2"+nc.getCommentNo()%>" onclick="cancelBtn(<%=nc.getCommentNo()%>)" style="display: none;">취소</button>
	<button onclick="updateComment(<%=nc.getCommentNo()%>);" id="<%="updateBtn1"+nc.getCommentNo()%>">수정</button>
	<button onclick="deleteComment(<%=nc.getCommentNo()%>, <%=nc.getNoticeNo()%>);" id="<%="deleteBtn1"+nc.getCommentNo()%>">삭제	</button>
	<% }} %>
	</div><br>
	</div>
<%}} %>
</div>
<form action="/writeComment">
	<%if(((Member)session.getAttribute("user"))==null){ %>
	<textarea readonly rows="5" cols="100" style="resize: none;" 
	placeholder="로그인한 사용자만 댓글작성이 가능합니다" name="comment" onclick="login();"></textarea>
	<% } else { %>
	<textarea rows="5" cols="100" style="resize: none;" placeholder="댓글을 작성하세요" name="comment"></textarea>
	<input type="hidden" value="<%=n.getNoticeNo() %>" name="noticeNo"/>
	<input type="submit" value="댓글작성"/>
	<%} %>
</form>
</center>
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
	function login(){
		alert("로그인을 먼저 진행해주세요");
		window.open("/views/member/login_popup.html","_blanck","width=400px,height=200px")
	}
	function updateComment(i){
		document.getElementById('content'+i).style="display: none;";
		document.getElementById('contentText'+i).style="display: block;";
		document.getElementById('updateBtn2'+i).style="display: inline;";
		document.getElementById('updateBtn1'+i).style="display: none;";
		document.getElementById('deleteBtn2'+i).style="display: inline;";
		document.getElementById('deleteBtn1'+i).style="display: none;";
	}
	function cancelBtn(i){
		document.getElementById('content'+i).style="text-align: left; display: block;";
		document.getElementById('contentText'+i).style="display: none;";
		document.getElementById('updateBtn2'+i).style="display: none;";
		document.getElementById('updateBtn1'+i).style="display: inline;";
		document.getElementById('deleteBtn2'+i).style="display: none;";
		document.getElementById('deleteBtn1'+i).style="display: inline;";
	}
	function deleteComment(commentNo, noticeNo){
		if(confirm("정말로 삭제하시겠습니까?")){
			location.href="/deleteComment?commentNo="+commentNo+"&noticeNo="+noticeNo;
		}
	}
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>