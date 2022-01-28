<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>비밀번호 찾기</title>
<script type="text/javascript">

</script>
<script type="text/javascript">
function findPw(){
	if(document.findPwForm.member_id.value == ""){
		alert("아이디를 입력해주세요.");
		document.findPwForm.member_id.focus();
		return;
	}
	if(document.findPwForm.member_name.value == ""){
		alert("이름을 입력해주세요.");
		document.findPwForm.member_name.focus();
		return;
	}
	if(document.findPwForm.member_email.value == ""){
		alert("이메일를 입력해주세요.");
		document.findPwForm.member_email.focus();
		return;
	}
	document.findPwForm.method = "get";
	document.findPwForm.action = "/findPw";
	document.findPwForm.submit();
}
</script>
</head>
<body>
		<div align="center" style="margin-top: 20px;">
			<h3 style="font-weight: bold;">비밀번호 찾기</h3>
		</div>
		<div id="containers" class="div" align="center" style="width: 350px;">
				<div>
					<form name="findPwForm" id="findPwForm">
						<input type="text" id="member_id" name="member_id" class="form-control" placeholder="아이디">
						<br>
						<input type="text" id="member_name" name="member_name" class="form-control" placeholder="이름">
						<br>
						<input type="email" id="member_email" name="member_email" class="form-control" placeholder="이메일">
						<br><button type="button" id="checkId"  onclick="javascript:findPw();" class="btn btn-outline-dark" style="height: 50px; width: 350px; ">확인</button>
					</form>
				</div>
		</div>
</body>
</html>