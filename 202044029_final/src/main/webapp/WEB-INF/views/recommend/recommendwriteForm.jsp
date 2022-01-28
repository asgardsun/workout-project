<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/menu.jsp" %>
<%@include file="../layout/rightUser.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="/resources/js/board.js"></script>
<title>자유</title>
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
<style type="text/css">
.recommend-div{
	margin: 0 20% 0 20%;
}
</style>
</head>
<body>
<div class="recommend-div container">
	<div align="center">
		<h1 style="font-weight: bold;">자유 게시판</h1>
	</div>
		<div class="form-group">
		<form action="write" method="post" id="writeForm" enctype="multipart/form-data">
			<sec:authentication property="principal.username" var="id"/>
			<input type="hidden" name="member_id" value="${id}">
			<table class="table">
				<tr style="border-top: 2px solid black;">
					<th>제목</th>
					<td><input class="form-control" type="text" id="title" name="recomm_title" style="width:450px;"></td>
				</tr>
				<tr>
					<td colspan="2"><textarea class="form-control" id="content" name="recomm_content" rows="10" cols="50"></textarea>
					</td>
				</tr>
				<tr>
					<th>파일 첨부 (Max File:5MB)</th>
					<td>
						<input multiple="multiple" type="file" name="file" accept=".jpg, .jpeg, .png">
					</td>
				</tr>
			</table>
			<div align="center" style="border-top: 2px solid black;">
				<div style="margin-top: 10px;">  
					<input class='btn btn-outline-dark' type="submit" value="확인"> 
					<input class='btn btn-outline-dark' type="reset" value="취소">
					<input class='btn btn-outline-dark' type="button" onclick="location.href='/recommend/recommendboard'" value="목록">
				</div>
			</div>
		</form>
		</div>
</div>
<%@ include file="../layout/bottom.jsp"%>
</body>
</html>
