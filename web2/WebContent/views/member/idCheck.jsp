<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="member.model.service.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ID 중복확인</title>
</head>
<body>
	<script>
		function check() {
			var checkId = document.getElementById('checkId').value;
			if(checkId==""){
				alert("ID를 입력하세요");
				return;
			}
			location.href = "idCheck.jsp?checkId=" + checkId;
		}
		window.onload = function() {
		<%
		String checkId = request.getParameter("checkId");
			if (checkId != null) {
				
				if(request.getAttribute("check")==null){
					RequestDispatcher view = request.getRequestDispatcher("/idCheck");
					request.setAttribute("checkId", checkId);
					view.forward(request, response);
				} else if(request.getAttribute("check").equals("Y")){
					%>
					document.getElementById('message').innerHTML = "사용중인 아이디입니다.";
					<%
				} else if(request.getAttribute("check").equals("N")){
					%>
					var useYesNo = window.confirm("사용가능한 ID 입니다. 사용하시겠습니까?");
					if(useYesNo==true){
						//자신(팝업)을 호출한 부모의 userId에 ID값을 넣어줌
						opener.document.getElementById('userId').value='<%=checkId%>';
						window.close();
					} else {
						document.getElementById('checkId').value='<%=checkId%>';
					}
				<%
				}
			}%>
		}
	</script>
	중복 확인할 ID 입력 :
	<input type="text" id="checkId" />
	<button onclick="check();">중복체크</button>
	<br>
	<br>
	<span id="message" style='color: red;'></span>
</body>
</html>