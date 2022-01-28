<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   <%@ include file="../layout/menu.jsp" %>
   <%@include file="../layout/rightUser.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">

</script>
<script type="text/javascript">
$(function(){
	$("a[name='fileDeleteBtn']").on("click", function() {
		$(this).parent().remove();
		var d = $(this).data("uuid");
		$.ajax({
			url: "fileDelete",
			data: {"uuid" : d},
			type: "post",
			dataType: "json",
			success: function(result){
				if(result){
					alert("file ajax() 삭제 성공");
				}else{
					alert("file ajax() 삭제 실패");
				}
			},
			error: function(){
				alert("file ajax() 삭제 오류");
			}
		});
	});
});
</script>
<style type="text/css">
.reivew-div{
	margin: 0 20% 0 20%;
}
</style>
</head>
<body>
<div class="reivew-div container">
	<div align="center">
		<h1 style="font-weight: bold;"></h1>
	</div>
	<div>
		<div style="border-top: 2px solid black;"></div>
		<form action="modify" method="post" enctype="multipart/form-data">
		<input type="hidden" name="num" value="${review.review_num}">
			<table class="table">
				<tr>
					<th>운동명</th>
					<td>
						${review.review_exh_name}
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>
						<input type="text" name="title" value="${review.review_title}" style="width : 450px;">
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>
						${review.member_id}
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<c:choose>
							<c:when test="${empty imgs }">
								<p><input type="file" name="file" accept=".jpg, .jpeg, .png" multiple="multiple"></p>
							</c:when>
							<c:otherwise>
								<c:forEach items="${imgs }" var="img">
								<div>
									${img.review_filename }
									<a href="#this" name="fileDeleteBtn" class="btn" data-uuid="${img.review_uuid }">삭제</a>
								</div>
								</c:forEach>
								<!-- 파일 추가하기 -->
								<div>
								파일 추가<input type="file" name="file" multiple="multiple">
								</div>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<textarea rows="10" cols="50" name="content" style="width: 100%">${review.review_content}</textarea>
					</td>
				</tr>
				<tr align="center" style="border-top: 2px solid black;">
					<td colspan="4">
						<input type="submit" class="btn btn-outline-dark" value="확인">		
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
<%@ include file="../layout/bottom.jsp"%>
</body>
</html>