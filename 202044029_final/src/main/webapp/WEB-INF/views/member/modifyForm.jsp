<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   	<%@ include file="../layout/menu.jsp" %>
   	<%@include file="../layout/rightUser.jsp"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.modify-div{
	margin: 0 20% 0 20%;
}
</style>
<meta charset="UTF-8">
<title>개인정보 수정</title>
<script type="text/javascript">
	$(function(){
		$("#submit").on("click",function(){
			var inputPw = $("#inputPw");
			if(inputPw.val() == "" && inputPw.val() == null){
				alert("비밀번호를 입력해주세요.");
				inputPw.focus();
			}
		});
		
		//카카오톡,네이버인 회원의 id는 간편로그인상태 라고만 표시
		var id = '${member.member_id}';
		//0:남자,1:여자,3:확인불가
		var gender = '${member.member_gender}';
		var email = '${member.member_email}';
		var password = '${member.member_password}';
		//카카오톡,네이버로 로그인한 정보란에 주소와 핸드폰번호가 null인 경우 입력부탁할것.
		var phone = '${member.member_phonenum}';
		var address = '${member.member_address}';
		
		if(id.match(".*(naver).*") || id.match(".*(kakao).*")){
			$("#inputId").val("간편 로그인");
			$("#inputHid").val(id);
			$("#inputPw").attr('readonly','readonly');
			$("#inputPw").val(password);
		}else{
			$("#inputId").val(id);
		}
		
		if(gender == 0){
			$("input[name='gender']").val("남자");
		}else if(gender == 1){
			$("input[name='gender']").val("여자");
		}else if(gender == 3){//확인불가
			//간편로그인에서 성별관련 정보 제공을 하지 않은 경우 값이 없다.
			$("input[name='gender']").val("");
		}
		
		if(email != "" && email != null){
			$("input[name='email1']").val(email.split("@")[0]);
			$("input[name='email2']").val(email.split("@")[1]);
			$("select[name='email3']").val(email.split("@")[1]);
		}
		
		if(phone != "" && phone != null){
			$("select[name='tel1']").val(phone.split("-")[0]);
			$("input[name='tel2']").val(phone.split("-")[1]);
			$("input[name='tel3']").val(phone.split("-")[2]);
		}
		
		if(address !="" && address != null){
			$("input[id='zipNo']").val(address.substring(address.lastIndexOf("[")+1,address.lastIndexOf("]")));
			$("input[id='roadAddrPart1']").val(address.substring(address.lastIndexOf("]")+1,address.lastIndexOf(",")));
			$("input[id='addrDetail']").val(address.split(",")[1]);
		}
		
	});
	function goPopup(){
		window.open("jusoPopup","pop","width=570,height=420, scrollbars=yes, resizable=yes");
	}
	function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn , detBdNmList, bdNm, bdKdcd, siNm, sggNm, emdNm, liNm, rn, udrtYn, buldMnnm, buldSlno, mtYn, lnbrMnnm, lnbrSlno, emdNo){ 
		document.form.roadAddrPart1.value = roadAddrPart1;
		document.form.addrDetail.value = addrDetail;
		document.form.zipNo.value = zipNo;
	}
</script>
</head>
<body>
<div class="modify-div container">
		<div align="center">
			<h3 style="font-weight: bold;">개인정보 수정</h3>
		</div>
		<div class="div" id="containers" style="width: 720px; margin: 0 auto;">
			<form id="form" name="form" method="post" action="/modify">
				<input type="hidden" id="inputHid" name="SNSid">
				<table class="table">
				<tbody>
					<tr>
						<th>ID</th>
						<td>
							<input type="text"  id="inputId" readonly="readonly" class="form-control" name="id" style="width: 40%;">
						</td>
					</tr>
					<tr>
						<th>PASSWORD</th>
						<td>
							<input type="password" id="inputPw" class="form-control" name="password" style="width: 40%;">
						</td>
					</tr>
					<tr>
						<th>NAME</th>
						<td>
							<input type="text"  class="form-control" value="${member.member_name}" readonly="readonly" name="name" style="width: 40%; ">
						</td>
					</tr>
					<tr>
						<th>GENDER</th>
						<td>
							<input type="text" class="form-control" name="gender" readonly="readonly" style="width: 40%;">
						</td>
					</tr>
					<tr>
						<th>NUMBER</th>
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
						<th>E-MAIL</th>
						<td>
						<div class="form-inline">
							<input  class="form-control"  type="text" name="email1" style="width: 20%;">
							&nbsp;@&nbsp;
							<input  class="form-control" id="inputEmail" type="text" name="email2" style="width: 20%;">
							<select class="form-control" id="selectEmail" name="email3" title="직접입력" style="width: 20%;">
								<option value="">직접입력</option>
								<option value="naver.com">네이버</option>
								<option value="daum.net">다음</option>
								<option value="gmail.com">구글</option>
							</select>
						</div>
						</td>
					</tr>
					<tr>
						<th>ADDRESS</th>
						<td>
						<div class="form-inline">
							<input class="form-control" type="text" id="zipNo" readonly="readonly" name="zipNo" style="width: 15%;">
							<input  class="form-control" id="roadAddrPart1" type="text" readonly="readonly" name="roadAddrPart1" style="width: 60%;">
							<button type="button" class="btn btn-link" id="buttonAdr" onclick="goPopup()">주소찾기</button>
							<br><br>							
							<input  class="form-control" id="addrDetail" type="text" name="addrDetail" style="width: 70%;">
						</div>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						<div>
							<input type="submit" id="submit" value="확인" class="btn btn-outline-dark">
							<input type="button" value="취소" onclick="history.back()" class="btn btn-outline-dark">
						</div>
						</td>
					</tr>
					</tbody>
				</table>
			</form>
		</div>
</div>
</body>
</html>