<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../layout/menu.jsp" %>
<%@include file="../layout/rightUser.jsp"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.div{
	margin: 0 auto;
	padding: 0;
	box-sizing: border-box;
	display: block;
}
ul, ol, li {
    list-style: none;
}
</style>
<meta charset="UTF-8">
<title>로그인</title>
<script type="text/javascript">
//회원 아이디와 비밀번호 입력 체크 
function goLogin(){
	if(document.loginForm.member_id.value == ""){
		alert("아이디를 입력해주세요.");
		document.loginForm.member_id.focus();
		return;
	}
	if(document.loginForm.member_password.value == ""){
		alert("비밀번호를 입력해주세요.");
		document.loginForm.member_password.focus();
		return;
	}
	document.loginForm.method = "post";
	document.loginForm.action = "/login";
	document.loginForm.submit();
}
</script>
</head>
<body>
	<div class="container" style="background: #f8fafc; padding: 10px; ">

		<h1 align="center" style="margin-top: 20px;">로그인</h1>
<!-- 		<div style="border: 1px solid black;"></div> -->
		<br>
		<br>
		<%-- ${sessionScope } --%>
		<div class="" style="margin: 0 auto; width: 350px; overflow: hidden;">
			<div class="form-group" style="float: center; width: 350px;">
				<h3 align="center">WORKOUT ID 로그인</h3>
				<fieldset>
					<form name="loginForm" id="loginForm">
						<input type="text" name="username" id="member_id" class="form-control" placeholder="아이디"><br>
						<input type="password" name="password" id="member_password" class="form-control" placeholder="비밀번호"><br>
						<button type="button" id="checkLogin" onclick="javascript:goLogin();" class="btn btn-outline-dark" style="height: 50px; width: 350px; ">로그인</button><br><br>
						<p style="color: red;"><c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">아이디 또는 비밀번호를 잘못 입력하셨습니다.<br>
						아이디 찾기 / 비밀번호 찾기를 이용하여 개인정보를 확인해 주세요.</c:if></p>
						<div class="checkbox">
							<label><input type="checkbox" name="remember-me">자동 로그인</label>
						</div>
					</form>
				</fieldset> 

				<div style="margin: 20px auto 0; overflow: hidden; width: 320px;">
					<ul>
						<li style="float: left;"><a href="/findidForm">아이디 찾기</a>&nbsp;&nbsp;|</li>
						<li style="float: left;">&nbsp;&nbsp;<a href="/findpwForm">비밀번호 찾기</a>&nbsp;&nbsp;|</li>
						<li style="float: left;">&nbsp;&nbsp;<a href="/joinForm">회원가입</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>