<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../layout/menu.jsp" %>
<%@include file="../layout/rightUser.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유</title>
<style type="text/css">
.recommend-div{
	margin: 0 20% 0 20%;
}
</style>
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
</head>
<body>
<div class="recommend-div container">
	<div align="center">
		<h1 style="font-weight: bold;">자유 게시판</h1>
	</div>
	<div>
		<div style="border-top: 2px solid black;"></div>
		<form action="modify" method="post" enctype="multipart/form-data">
		<input type="hidden" name="num" value="${recomm.recomm_num}">
			<table class="table">
				<tr>
					<th>제목</th>
					<td>
						<input type="text" name="title" value="${recomm.recomm_title}" style="width:450px;">
					</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>
						${recomm.member_id}
					</td>
				</tr>
				<tr>
					<th>파일 첨부</th>
					<td>
						<c:choose>
							<c:when test="${empty imgs }">
								<p><input type="file" name="file" accept=".jpg, .jpeg, .png" multiple="multiple"></p>
							</c:when>
							<c:otherwise>
								<c:forEach items="${imgs }" var="img">
								<div>
									${img.recomm_filename }
									<a href="#this" name="fileDeleteBtn" class="btn" data-uuid="${img.recomm_uuid }">삭제</a>
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
						<textarea rows="10" cols="50" name="content" style="width: 100%">${recomm.recomm_content}</textarea>
					</td>
				</tr>
				<tr align="center" style="border-top: 2px solid black;">
					<td colspan="4">
						<input type="submit"  class="btn btn-outline-dark" value="확인">
					</td>
				</tr>			
			</table>
		</form>
	</div>
</div>
<%@ include file="../layout/bottom.jsp"%>
</body>
</html>