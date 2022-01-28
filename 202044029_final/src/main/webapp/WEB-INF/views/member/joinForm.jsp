<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   	<%@include file="../layout/menu.jsp" %>
   	<%@include file="../layout/rightUser.jsp"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.join-div{
    padding: 0;
	margin: 0 auto;
    box-sizing: border-box;
}
</style>
<meta charset="UTF-8">
<title>회원가입</title>
<script type="text/javascript">
	$(function(){
		$("#inputId").on("blur",function() {
			var inputId = $(this).val();
			$.ajax({
				url:"/checkId?id="+inputId,
				data:inputId,
				type:"post",
				dataType:"json",
				success:function(data){
					console.log(data);
					if(data==true){
						$("#msgId").text("사용가능한 아이디입니다.");
						//다시 재입력시
					}else if(data==false){
						$("#msgId").text("이미 사용중인 아이디입니다.");		
						$("#inputId").focus();
						$("#inputId").val("");
					}else if(data==no){
						$("#msgId").text("");					
					}
				},
				error:function(request, error){
					$("#msgId").text("");
					console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
			});
			return false;
		});
		$("#selectEmail").on("blur",function(){
			var optEmail = $("#selectEmail option:selected").val();
			if(optEmail=="naver.com"){
				$("#inputEmail").val(optEmail);
				$("#inputEmail").focus();
			}
			if(optEmail=="daum.net"){
				$("#inputEmail").val(optEmail);	
				$("#inputEmail").focus();
			}
			if(optEmail=="gmail.com"){
				$("#inputEmail").val(optEmail);
				$("#inputEmail").focus();
			}
			if(optEmail==""){
				$("#inputEmail").val("");
				$("#inputEmail").focus();
			}
		});
	});
	
function goPopup(){
	window.open("jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes");
}
function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn , detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm, rn, udrtYn, buldMnnm, buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo){ 
	document.form.roadAddrPart1.value = roadAddrPart1;
	document.form.addrDetail.value = addrDetail;
	document.form.zipNo.value = zipNo;
}
function joinSubmit(){
	if(document.form.id.value == ""){
		alert("아이디을 입력해주세요.");
		document.form.id.focus();
		return;
	}
	if(document.form.password.value == ""){
		alert("비밀번호을 입력해주세요.");
		document.form.password.focus();
		return;
	}
	if(document.form.name.value == ""){
		alert("이름을 입력해주세요.");
		document.form.name.focus();
		return;
	}
	if(document.form.tel1.value == ""){
		alert("전화번호을 입력해주세요.");
		document.form.tel1.focus();
		return;
	}
	if(document.form.tel2.value == ""){
		alert("전화번호을 입력해주세요.");
		document.form.tel2.focus();
		return;
	}
	if(document.form.tel3.value == ""){
		alert("전화번호을 입력해주세요.");
		document.form.tel3.focus();
		return;
	}
	if(document.form.email1.value == ""){
		alert("이메일을 입력해주세요.");
		document.form.email1.focus();
		return;
	}
	if(document.form.email2.value == ""){
		alert("이메일을 입력해주세요.");
		document.form.email2.focus();
		return;
	}
	if(document.form.roadAddrPart1.value == ""){
		alert("주소을 입력해주세요.");
		return;
	}
	document.form.method = "get";
	document.form.action = "/join";
	document.form.submit();
}
function noSpaceForm(obj) { // 공백사용못하게
    var str_space = /\s/;  // 공백체크
    if(str_space.exec(obj.value)) { //공백 체크
    	$("#msgId").text("공백을 사용할 수 없습니다.\n공백은 자동적으로 제거 됩니다.");
        obj.focus();
        obj.value = obj.value.replace(' ',''); // 공백제거
        return false;
    }
    var re = /[~!@\#$%^&*\()\-=+_']/gi; 
    if(re.test(obj.value))
    {
    	$("#msgId").text("특수문자을 사용할 수 없습니다.\n특수문자는 자동적으로 제거 됩니다.");
    	obj.value = obj.value.replace(re,"");
    	return false;
    }
 // onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"
}
function noSpacePw(obj){
	var str_space = /\s/;  // 공백체크
    if(str_space.exec(obj.value)) { //공백 체크
    	$("#msgPw").text("공백을 사용할 수 없습니다.\n공백은 자동적으로 제거 됩니다.");
        obj.focus();
        obj.value = obj.value.replace(' ',''); // 공백제거
        return false;
    }
    $("#msgPw").text("");
}
//이름 한글과 영문만 체크
//휴대폰 한글넣는지 체크
//이메일 형식 체크
</script>
</head>
<body>
<div class="join-div container">
		<div align="center">
			<h1>회원가입</h1>
		</div>

		<div class="div" id="containers" style="width: 800px; margin: 0 auto;">
			<form id="form" name="form" method="post" action="/join">
				<table class="table">
				<tbody>
					<tr>
						<th>아이디</th>
						<td>
							<div class="form-inline" id="dataId">
								<input type="text" id="inputId" onkeyup="noSpaceForm(this);" class="form-control" name="id" style="width: 40%;">
								<!-- 아이디 중복 확인 -->
							</div>
							<small><span id="msgId"></span></small>
						</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>
							<input type="password" id="inputPw" onkeyup="noSpacePw(this);" onchange="noSpacePw(this);" class="form-control" name="password" style="width: 40%;">
							<!-- 비밀번호 확인 -->
							<small><span id="msgPw"></span></small>
						</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>
							<input type="text" class="form-control"  name="name" style="width: 40%;">
							<small><span id="msgName"></span></small>
						</td>
					</tr>
					<tr>
						<th>성별</th>
						<td>
							<input type="radio" name="gender" checked="checked" value="0">남자
							&nbsp;&nbsp;
							<input type="radio" name="gender" value="1">여자
						</td>
					</tr>
					<tr>
						<th>휴대폰</th>
						<td>
						<div class="form-inline">
							<select class="form-control" name="tel1" title="010" style="width: 20%;">
								<option value="010">010</option>
								<option value="011">011</option>
								<option value="016">016</option>
								<option value="017">017</option>
								<option value="018">018</option>
								<option value="019">019</option>
							</select>
							&nbsp;-&nbsp;
							<input  class="form-control" type="number" name="tel2" style="width: 20%;">
							&nbsp;-&nbsp;
							<input  class="form-control" type="number" name="tel3" style="width: 20%;">
						</div>
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>
						<div class="form-inline">
							<input  class="form-control"  type="text" name="email1" style="width: 20%;">
							&nbsp;@&nbsp;
							<input  class="form-control" id="inputEmail" type="text" name="email2" style="width: 20%;">
							<select class="form-control" id="selectEmail" name="email3" title="직접입력" style="width: 20%;">
								<option value="">직접입력</option>
								<option value="naver.com">네이버</option>
								<option value="hanmail.net">다음</option>
								<option value="gmail.com">구글</option>
							</select>
						</div>
						</td>
					</tr>
					<tr>
						<th>주소</th>
						<td>
						<div class="form-inline">
							<input class="form-control" type="text" readonly="readonly" name="zipNo" id="zipNo" style="width: 15%;">
							<input  class="form-control" id="roadAddrPart1" type="text" readonly="readonly"  id="roadAddrPart1" name="roadAddrPart1" style="width: 60%;">
							<button type="button" class="btn btn-link" id="buttonAdr" onclick="goPopup()">주소찾기</button>
							<br><br>							
							<input  class="form-control" id="addrDetail" type="text" name="addrDetail" style="width: 70%;">
						</div>
						</td>
					</tr>
					<tr align="center">
						<td colspan="2">
							<button type="button" onclick="javascript:joinSubmit();" class="btn btn-outline-dark">확인</button>
							<button type="button" onclick="location.href='/member/loginForm'" class="btn btn-outline-dark">취소</button>
						</td>
					</tr>
					</tbody>
				</table>
			</form>
		</div>
</div>
</body>
</html>