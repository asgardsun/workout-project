<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../layout/rightUser.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>recommendPwForm</title>
<script type="text/javascript">
</script>
</head>
<body>

	<div align="center">
		<h1>자유 게시판</h1>
	</div>
	<div align="center">
		<h3>Please enter a password to ${param.button}.</h3>
		<form action="checkPw" method="post">
			<input type="hidden" name="button" value="${param.button }">
			<input type="hidden" name="num" value="${param.num}">
			<sec:authentication property="principal.username" var="id"/>
			<input type="hidden" name="id" value="${id}">
			<input type="password" name="pw">
			<input type="submit" value="ok">
		</form>
	</div>
<%@ include file="../layout/bottom.jsp"%>
</body>
</html>