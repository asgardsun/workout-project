<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>접근 거부</title>
</head>
<body>
	<h1>권한이 없는 사용자입니다.<br> 페이지에 접속할 수 없습니다.</h1>
	<h3><c:out value="${SPRING_SECURITY_403_EXCEPTION.getMessage()}"/></h3>
	<h3><c:out value="${msg}"/> </h3>
</body>
</html>