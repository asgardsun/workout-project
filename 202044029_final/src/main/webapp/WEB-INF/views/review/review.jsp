<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../layout/menu.jsp" %>
<%@include file="../layout/rightUser.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="/resources/css/style.css">
<meta charset="UTF-8">
<title>파트너찾기</title>
<script type="text/javascript">
	$(function() {

		ReplyView();
		var num = $("#num").val();
		$("#rbtnWrite").on("click", function() {
			var data = $("#rwriteForm").serialize();
			$.ajax({
				url : "/reviewreply/rwrite?num="+num,
				data : data,
				type : "post",
				dataType : "json",
				success : function(result) {
					if (result) {
						alert("작성되었습니다.");
					} else {
						alert("다시 시도해주세요.");
					}
					$("#rcontent").val("");
					ReplyView();
				},
				error : function() {
					alert("reivewreplyWrtie error");
				}
			});
			return false;
		});

		/* 		$("#rbtnClose").on("click", function() {
		 $("#modalDiv").hide("slow");
		 });
		 $("#rbtnClose2").on("click", function() {
		 $("#modalDiv2").hide("slow");
		 }); */
		/* 		$("#modalForm input[type='button']").on("click", function() {
		 $("#modalDiv").hide("slow");
		 });
		 $("#modalForm2 input[type='button']").on("click", function() {
		 $("#modalDiv2").hide("slow");
		 }); */
		 
		 
		 //* 신고 처리 이벤트 등록 */
		//신고 글 메서드
		$("#btn-block").on("click", function(){
			var category = $("#blockForm").find('input[name="category"]');
			category.val("Review_Board");
			var writer = '${review.member_id}';
			var blockMemId = $("#blockForm").find('input[name="blockMemId"]');
			blockMemId.val(writer);	//관리자의 경우 아이디 넣기(현재아이디)
			var blockForm = document.blockForm;
			var url = "../block/form";
			window.open("", "Report", "width=400, height=500, top=300, left=300");
			
			blockForm.action = url;
			blockForm.target = "Report";
			blockForm.submit();
		});
	});

	function ReplyView() {
		var table = $("#replyTable");
		$("#replyTable tr:gt(0)").remove();
		var reviewNum = ${review.review_num};
		var seqid = $("#seqid").val(); //현재로그인한 아이디
		/* member_id 수정해야됨 */
		$.ajax({
					url : "/reviewreply/replyView?num=" + reviewNum,
					type : "get",
					dataType : "json",
					success : function(data) {
						for ( var i in data) {
							console.log(data);
							
							var blockStatus = data[i].block;
// 							alert(blockStatus);
							if(blockStatus == 'true'){
								var content = '관리자에 의해 삭제처리 된 댓글입니다.';
							}else{
								var content = data[i].review_reply_content;
							}
							
							var tr = $("<tr>");

							var modiText = $("<div id='mod"+i+"' class='collapse form-group'><input type='hidden' name='num' value='"+data[i].review_reply_num+"'><input type='hidden' name='id' value='"+data[i].member_id+"'><textarea class='form-control' name='content' rows='3' cols='80'>"+data[i].review_reply_content+"</textarea></div>");
							var remvText = $("<div id='modd"+i+"' class='collapse form-group'><input type='hidden' id='replynum' name='num' value='"+data[i].review_reply_num+"'></div>");
							
							var rbtnModify = $("<button type='button' class='btn btn-outline-dark btn-sm' data-toggle='collapse' data-target='#mod"+i+"'>수정</button>");
							var rbtnRemove = $("<button type='button' class='btn btn-outline-dark btn-sm' data-toggle='collapse' data-target='#modd"+i+"'>삭제</button>");
							
							//댓글 신고 버튼
							var blockBtn = $("<button type='button' class='btn btn-outline-danger btn-sm'>신고</button>");
							
							var form = $("<form action='#'></form>");
							var form2 = $("<form action='#'></form>");
							var btnSubmit = $("<button type='button' class='btn btn-outline-dark btn-sm'>확인</button>");

							$("<td>").text(data[i].member_id).appendTo(tr);
							$("<td style='width: 50%;'>").text(content)
									.append(form.append(modiText.append(btnSubmit)))
									.appendTo(tr);
							$("<td>").append(form2.append(remvText)).appendTo(tr);
							$("<td>").text(data[i].review_reply_reg_date)
									.appendTo(tr);
							
							if(seqid == data[i].member_id){
								$("<td>").append(rbtnModify).append("&nbsp;&nbsp;").append(rbtnRemove).appendTo(tr);								
							}else{
								$("<td>").append(blockBtn).appendTo(tr);		
							}
							
							if(blockStatus == 'true'){
								rbtnModify.attr("disabled", "disabled");
							}
							tr.appendTo(table);

							rbtnModify.on("click", function() {
								var removeBtn = $(this).next();
								if (removeBtn.attr('disabled') == 'disabled') {
									removeBtn.removeAttr('disabled');
								} else {
									removeBtn.attr('disabled', 'true');
								}
							});

							rbtnRemove.on("click", function() {
								var modifyBtn = $(this).prev();
								if(modifyBtn.attr('disabled') == 'disabled'){
									modifyBtn.removeAttr('disabled');
								}else{
									modifyBtn.attr('disabled','true');
								}
								
								var data = $(this).closest("form").serialize();
								var replynum = $("#replynum").val();
								/* alert(data); */
								$.ajax({
									url : "/reviewreply/rdelete?num="+replynum,
									data : data,
									type : "post",
									dataType : "json",
									success : function(result) {
										if (result) {
											alert("삭제되었습니다.");
											ReplyView();
										} else {
											alert("다시 시도해주세요.");
											ReplyView();
										}
									},
									error : function() {
										alert("reviewreplyDelete error");
									}
								});
								return false;
							});

							btnSubmit.on("click", function() {
								var data = $(this).closest("form").serialize();
								/* alert(data); */
								$.ajax({
									url : "/reviewreply/rmodify",
									data : data,
									type : "post",
									dataType : "json",
									success : function(result) {
										if (result) {
											alert("수정되었습니다.");
											ReplyView();
										} else {
											alert("다시 시도해주세요.");
											ReplyView();
										}
									},
									error : function() {
										alert("reviewreplyModify error");
									}
								});
								return false;
							});
							
							////* 댓글 신고 method */
							(function(m) {
								blockBtn.on("click", function(){
								var category = $("#blockForm").find('input[name="category"]');
								category.val("Review_Reply");
								var replyMemId = data[m].member_id;
								var blockMemId = $("#blockForm").find('input[name="blockMemId"]');
								blockMemId.val(replyMemId);
								var replyNum = data[m].review_reply_num;
								var rInput = $("#blockForm").find('input[name="replyNum"]');
								rInput.val(replyNum);
//		 						alert(rInput.val());
								var blockForm = document.blockForm;
								var url = "../block/form";
								window.open("", "Report", "width=400, height=500, top=300, left=300");
								
								blockForm.action = url;
								blockForm.target = "Report";
								blockForm.submit();
								});
							})(i)	// 댓글 해당 인덱스 보내기(클로저 방지)
						}

					}
				});

	};
</script>
<style type="text/css">
.reivew-div{
	margin: 0 20% 0 20%;
}
</style>
</head>
<body>
<div class="reivew-div container">
		<!--리뷰내용-->
		<div align="center">
			<h1 style="font-weight: bold;">파트너 찾기</h1>
		</div>
		<div>
			<div style="border-top: 2px solid black;"></div>
			<input type="hidden" name="num" id="num" value="${review.review_num}">
			<table class="table">
				<tr>
					<th>운동명</th>
					<td>${review.review_exh_name}</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${review.review_title}</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${review.member_id}</td>
				</tr>
				<tr>
					<th>입력일</th>
					<td><fmt:formatDate value="${review.review_reg_date}"
							pattern="yyyy-MM-dd" /></td>
				</tr>
				<tr>
					<td colspan="5" align="center">
					<c:choose>
						<c:when test="${empty imgs }">
							<p>첨부된 파일이 없습니다.</p>
						</c:when>
						<c:otherwise> 
							<c:forEach items="${imgs }" var="img" varStatus="vs">
							<div class='col-sm-4'>
								<img onerror="this.style.display='none'" alt='' style='max-width: 100%; height: auto;' src="downloadThumb?uuid=${img.review_uuid}">
								<label><a href="download?uuid=${img.review_uuid}">${img.review_filename }</a></label><br>
							</div>
						    </c:forEach>
						</c:otherwise>
					</c:choose>
					</td>
				</tr>
				<tr>					
					<td colspan="5">
						<c:choose>
							<c:when test="${review.block eq true}">
								<p>관리자에 의해 삭제처리된 게시글입니다.</p>
							</c:when>
							<c:otherwise>
								<p>${review.review_content }</p>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr align="right" style="border-top: 2px solid black;">
					<td colspan="4">
					<input type="button" onclick="location.href='/review/reviewboard'" value="목록" class="btn btn-outline-dark btn-sm"> 
					<input type="hidden" id="seqid" value="${id}"> 
					<c:if test="${id eq review.member_id}">
						<input type="button" onclick="location.href='/review/modify?num=${review.review_num}'" value="수정" class="btn btn-outline-dark btn-sm"> 
						<input type="button" onclick="location.href='/review/remove?num=${review.review_num}'" value="삭제" class="btn btn-outline-dark btn-sm">
					</c:if>
					<!-- 신고처리(Board) -->
					<c:if test="${review.block != true}">
						<button id="btn-block" class="btn btn-outline-danger btn-sm">신고</button>
					</c:if>
					</td>
				</tr>
			</table>
		</div>
		<!-- 리뷰댓글등록 -->
		<div class="form-group">
			<form id="rwriteForm">
				<input type="hidden" name="member_id" value="${id}"> 
				<input type="hidden" name="review_num" value="${review.review_num}">
				<table>
					<tr>
						<td style="width: 90%;"><textarea class="form-control" id="rcontent" name="review_reply_content" rows="3"
								cols="100" style="width: 100%;"></textarea></td>
						<td style="width: 20%;">
							<button class="btn btn-outline-dark" id="rbtnWrite">댓글쓰기</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<!-- 리뷰댓글목록(AJax) -->
		<div class="table">
			<table id="replyTable" class="table table-hover">
				<tr></tr>
			</table>
		</div>
		
	<!-- 신고pop에 보낼 내용 -->
	<form id="blockForm" name="blockForm" method="post">
		<input type="hidden" name="blockMemId" value="">
		<input type="hidden" name="category" value="">
		<input type="hidden" name="boardNum" value="${review.review_num }">
		<input type="hidden" name="replyNum" value="">
		<input type="hidden" name="boardTitle" value="${review.review_title}">
	</form>
</div>
<%@ include file="../layout/bottom.jsp"%>
</body>
</html>