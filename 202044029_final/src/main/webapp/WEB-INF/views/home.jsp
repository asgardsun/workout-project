<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>

<html>
<head>
<title>Home</title>
</head>
<script type="text/javascript">
	$(function() {
		tick2();
	});
	function tick2() {
		$("#ticker_02 li:first").slideUp(function() {
			$(this).appendTo($("#ticker_02")).slideDown();
		});
	}
	setInterval(function() {
		tick2()
	}, 3000);
</script>
<style>
#div3 {
	max-width: 100%;
	height: 500px;
	background-color: #f8fafc;
	padding: 0 15% 5% 15%;
}

.div3-div {
	max-width: 700px;
	height: 380px;
	background-color: white;
}

#div3-div2 {
	
}

.ticker {
	width: 500px;
	height: 40px;
	overflow: hidden;
	border: 1px solid #DDD;
	margin: 0;
	padding: 0;
	list-style: none;
	border-radius: 5px;
	box-shadow: 0px 0px 5px #DDD;
}

#ticker_02 {
	height: 250px;
}

.ticker li {
	height: 30px;
	border-bottom: 1px dotted #DDD;
	padding: 5px;
	margin: 0px 5px;
}

body {
	padding: 0px;
	margin: 0px;
}

.jb-box {
	width: 70%;
	height: 500px;
	overflow: hidden;
	margin: 0px auto;
	position: relative;
}

video {
	width: 100%;
}

.jb-text {
	position: absolute;
	top: 12%;
	width: 100%;
}

.jb-text p {
	margin-top: -24px;
	text-align: center;
	font-size: 55px;
	 line-height: 100%;
	font-family : Impact;
	color: #282828;

}
</style>
<body>

	<table style="width: 100%">
		<thead>
			<tr>
				<td><jsp:include page="/WEB-INF/views/layout/menu.jsp"
						flush="false" /></td>
			</tr>
		</thead>
	</table>
	<jsp:include page="/WEB-INF/views/layout/rightUser.jsp" flush="false" />
	  <div class="jb-box">
      <video muted autoplay loop>
        <source src="/resources/videos/Gym-20422.mp4" type="video/mp4">
        <strong class="s">Your browser does not support the video tag.</strong>
      </video>
      <div class="jb-text">
        <p>Hit the weights</p>
      </div>
    </div>

	<div id="div3" style="font-weight: bold;">
		<div class="div3-div row"
			style="width: 600px; height: 380px; display: inline-block; position: relative;">
			<div class="col-md-12" style="margin-top: 20px;">

				<ul id="tabsJustified" class="nav nav-tabs">
					<li class="nav-item"><a href="" data-target="#recommend"
						data-toggle="tab" class="nav-link">자유 게시판</a></li>
				</ul>
				<div id="tabsJustifiedContent" class="tab-content">
					<div id="recommend" class="tab-pane fade active show"
						style="padding: 1.5rem; text-align: center">
						<div class="table-responsive d-flex align-items-center">
							<table class="table">
								<thead style="text-align: center">
									<tr>
										<th>No</th>
										<th>Title</th>
										<th>Date</th>
										<th>Views</th>
									</tr>
								</thead>
								<c:forEach items="${recList }" var="recommend" varStatus="vs">
									<fmt:formatDate value="${recommend.recomm_reg_date }"
										var="regDate" pattern="yyyy-MM-dd" />
									<tr>
										<td style="text-align: center">${vs.index+1}</td>
										<td><a href="/recommend/view?num=${recommend.recomm_num}">${recommend.recomm_title }</a></td>
										<td style="text-align: center">${regDate}</td>
										<td style="text-align: center">${recommend.recomm_read_count }</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div
			style="display: inline-block; overflow: hidden; width: 600px; height: 380px; float: right; background-color: white; padding: 40px;">
			<h4 style="font-weight: bold; padding-bottom: 20px;">파트너 찾기</h4>
			<ul id="ticker_02" class="ticker">
				<c:forEach items="${review}" var="r">
					<li><a href="/review/view?num=${r.review_num}">제목 :
							${r.review_title}</a> &nbsp;&nbsp;작성자 :
						${r.member_id}&nbsp;&nbsp;&nbsp;&nbsp; <fmt:formatDate
							value="${r.review_reg_date }" pattern="yyyy-MM-dd" /></li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<%@include file="layout/bottom.jsp"%>
</body>
</html>
