<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="../layout/menu.jsp" %>
	<%@include file="../layout/rightUser.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/resources/js/board.js?var=1"></script>
<title>파트너잦기 글 등록</title>
<script type="text/javascript">
$(function(){
	$("#writeForm").on("submit",function(){
		//만약에 필수요소가 제대로 채워지지 않았으면 return false;
		var result = boardCheck("writeForm");
		if(!result){		
			return false;
		}
	});
});
</script>
</head>
<body>
<div class="reivew-div container">
		<div align="center">
			<h1 style="font-weight: bold;">파트너 찾기</h1>
		</div>
		<div class="form-group">
		<form action="write" method="post" id="writeForm" enctype="multipart/form-data">
			<sec:authentication property="principal.username" var="id"/>
			<input type="hidden" name="member_id" value="${id}">
			<table class="table">
				<tr style="border-top: 2px solid black;">
					<th>운동루틴</th>
					<td>
						<input class="form-control" type="text" name="review_exh_name" value="">
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td><input class="form-control" type="text" id="title" name="review_title"></td>
				</tr>
				<tr>
					<td colspan="2"><textarea class="form-control" id="content" name="review_content" rows="10" cols="50"></textarea>
					</td>
				</tr>
				<tr>
					<th>파일첨부 (Max File:5MB)</th>
					<td>
						<input multiple="multiple" type="file" name="file" accept=".jpg, .jpeg, .png">
					</td>
				</tr>
			</table>
			<div align="center" style="border-top: 2px solid black;">
				<div style="margin-top: 10px;">
					<input class='btn btn-outline-dark' type="submit" value="확인"> 
					<input class='btn btn-outline-dark' type="reset" value="취소">
					<input class='btn btn-outline-dark' type="button" onclick="location.href='/review/reviewboard'" value="목록">
				</div>
			</div>
		</form>
		</div>
</div>
<%@ include file="../layout/bottom.jsp"%>
</body>
</html>
