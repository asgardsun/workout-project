<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>아이디찾기</title>
<script type="text/javascript">
</script>
<script type="text/javascript">
function findId(){
	if(document.findIdForm.member_name.value == ""){
		alert("이름을 입력해주세요.");
		document.findIdForm.member_name.focus();
		return;
	}
	if(document.findIdForm.member_email.value == ""){
		alert("이메일을 입력해주세요.");
		document.findIdForm.member_email.focus();
		return;
	}
	document.findIdForm.method = "get";
	document.findIdForm.action = "/findId";
	document.findIdForm.submit();
}
</script>
</head>
<body>
		<div align="center" style="margin-top: 20px;">
			<h3 style="font-weight: bold;">아이디 찾기</h3>
		</div>
			
		<div id="containers" class="div" align="center">
				<div style="width: 350px;">
					<form name="findIdForm" id="findIdForm">
						<input type="text" id="member_name" name="member_name" class="form-control" placeholder="이름">
						<br>
						<input type="email" id="member_email" name="member_email" class="form-control" placeholder="이메일">
						<br><button type="button" id="checkId"  onclick="javascript:findId();" class="btn btn-outline-dark" style="height: 50px; width: 350px; ">확인</button>
					</form>
				</div>
				<c:if test="${param.findResult eq 'success'}">
					<div align="center">
						<br>
						<h4>ID : ${param.member_id}</h4>
						<br>
						<button type="button" onclick="location.href='/loginForm'" class="btn btn-outline-dark">로그인</button>
						<button type="button" onclick="location.href='/findpwForm'" class="btn btn-outline-dark">비밀번호 찾기</button>
					</div>
				</c:if>
			
		</div>

</body>
</html>