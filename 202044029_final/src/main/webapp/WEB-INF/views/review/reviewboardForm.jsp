<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../layout/menu.jsp" %>
<%@include file="../layout/rightUser.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파트너 찾기</title>
<style type="text/css">
.reivew-div{
	margin: 0 20% 0 20%;
}
th {
	text-align: center;
}
.review-ul{
	display: block;
    list-style-type: disc;
    padding: 20px;
    width: 100%;
}
</style>
</head>
<body>
<div class="reivew-div container">
		<div>
			<h3 style="font-weight: bold;">파트너 찾기</h3>
			<sec:authorize access="hasRole('ROLE_USER')">	
				<button onclick="location.href='/review/write'" style="margin: 10px;" class="btn btn-outline-dark btn-sm">글쓰기</button>
			</sec:authorize>
		</div>
		<div class="table-responsive">
			<div style="border-top: 2px solid black; margin: 10px;"></div>
			<ul class="review-ul">
					<c:forEach items="${reviewList}" var="review">
						<li style=" width:100%; height: 200px; padding: 20px; margin: 20px 0 20px 0; border: 1px solid gray;">
							<!---->
							<a href="view?num=${review.review_num}">
							<p style="float: right;"><i class="fas fa-eye"></i>&nbsp;조회수 : ${review.review_read_count}</p>
							<dl>
								<dt>${review.review_num}<br>
									[${review.review_exh_name}]
									<br>
									<br>
									${review.review_title}
									<c:if test="${review.reply_count != 0}">
										<span class="badge badge-secondary">댓글 : ${review.reply_count }</span>
									</c:if>
								</dt>
								<dd>
									<p style="display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical;width:100%; overflow: hidden; text-overflow: ellipsis; margin: 0;">
									${review.review_content}<p>
									<!-- 댓글수 -->
									<div style="text-align: right;">
										작성자 : ${review.member_id}&nbsp;&nbsp;
										<fmt:formatDate value="${review.review_reg_date}" pattern="yyyy-MM-dd"/>
									</div>
								</dd>
							</dl>	
							</a>
						</li>
					</c:forEach>
			</ul>
			<div style="border-top: 2px solid black; margin: 10px;"></div>
		</div>
		<div style="text-align: center; margin: 10px;">
				<form id="searchForm" action="/review/reviewboard" method="get">
					<select name="type">
						<option value="" 
							<c:out value="${pageMaker.cri.type eq null ? 'selected' : ''}"/>>검색
						</option>
						<option value="T" 
							<c:out value="${pageMaker.cri.type eq 'T'? 'selected' : ''}"/>>제목
						</option>
						<option value="E" 
							<c:out value="${pageMaker.cri.type eq 'E' ? 'selected': ''}"/>>운동명
						</option>
						<option value="I" 
							<c:out value="${pageMaker.cri.type eq 'I' ? 'selected': ''}"/>>작성자
						</option>
						<option value="C" 
							<c:out value="${pageMaker.cri.type eq 'C' ? 'selected': ''}"/>>내용
						</option>
						<option value="TC" 
							<c:out value="${pageMaker.cri.type eq 'TC' ? 'selected': ''}"/>>제목+내용
						</option>
						<option value="TI" 
							<c:out value="${pageMaker.cri.type eq 'TI' ? 'selected': ''}"/>>제목+작성자
						</option>
						<option value="TIC" 
							<c:out value="${pageMaker.cri.type eq 'TIC' ? 'selected': ''}"/>>제목+작성자+내용
						</option>
					</select>
					<input type="text" name="keyword" placeholder="검색어를 입력하세요." value="<c:out value="${pageMaker.cri.keyword }"/>">
					<%-- <c:out value="${pageMaker.cri.pageNum }"/> --%>
					
					<input type="hidden" name="amount" value="<c:out value="${pageMaker.cri.amount }"/>">
					<button class="btn btn-outline-dark btn-sm">검색</button>
				</form>
		</div> 
		<%-- 네비게이션 표시 --%>
		<div class="container" align="center" style="margin-top: 10px;">
			<ul class="pagination justify-content-center">
				<li class='${pageMaker.prev == true ? "page-item ":"page-item disabled"}'>
					<a class="page-link" href="reviewboard?pageNum=${pageMaker.startPage-1}&type=${param.type}&keyword=${param.keyword}">&laquo;</a>
				</li>
				<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
					<li class='${pageMaker.cri.pageNum == num ? "active":"page-item"}'>
						<a class="page-link" href="reviewboard?pageNum=${num}&type=${param.type}&keyword=${param.keyword}">${num}</a>
					</li>
				</c:forEach>
				<li class='${pageMaker.next == true ? "page-item":"page-item disabled"}'>
					<a class="page-link" href="reviewboard?pageNum=${pageMaker.endPage+1}&type=${param.type}&keyword=${param.keyword}">&raquo;</a>
				</li>
			</ul>
		</div>
</div>
<%@ include file="../layout/bottom.jsp"%>
</body>
</html>