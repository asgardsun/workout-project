<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <%@ include file="../layout/menu.jsp" %>
	<%@include file="../layout/rightUser.jsp"%>
<title>나의 페이지</title>
<style type="text/css">
h1,h2,h3,h4,h5{
	font-weight: bold;
}
</style>

		<div align="center" style="margin-top: 10px;">
			<h3 style="font-weight: bold;">나의 페이지</h3>
		</div>
		<div id="containers" style="width: 70%; margin: 0 auto;">
			<div style="border-top: 2px solid black;"></div>
			<!-- 회원의 기본정보 -->
			<div>
				<table class="table">
					<tbody>
						<tr align="right">
							<!-- 개인정보 수정 -->
							<td>
								<button type="button" class="btn btn-outline-dark btn-sm" onclick="location.href='/modifyForm'">개인정보 수정</button>
							<!-- 회원 탈퇴 -->
								<button type="button" class="btn btn-outline-dark btn-sm" onclick="location.href='/deleteForm'">회원탈퇴</button>						
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<!-- 나의 게시글 -->
			<div>
				<h3>나의 게시글</h3>
				<table class="table">
					<tbody align="center" style="border-top: 2px solid black;">
						<tr> 
							<th>구분</th>
							<th>제목</th>
							<th colspan="3">등록일</th>
						</tr>
						<c:forEach items="${recommend}" var="rec">
						<tr>
							<td>자유</td>
							<td><a href="/recoomend/view?num=${rec.recomm_num}">${rec.recomm_title}</a></td>
							<td><fmt:formatDate value="${rec.recomm_reg_date}" pattern="yyyy-MM-dd"/></td>
						</tr>
						</c:forEach>
						<c:forEach items="${review}" var="r">
						<tr>
							<td>후기</td>
							<td><a href="/review/view?num=${r.review_num}">${r.review_title}</a></td>
							<td><fmt:formatDate value="${r.review_reg_date}" pattern="yyyy-MM-dd"/></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div style="border-top: 2px solid black;"></div>
		</div>
