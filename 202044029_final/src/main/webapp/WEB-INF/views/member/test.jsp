<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
html.open { overflow: hidden; }
#btn { 
	width : 100px; 
	height : 100px; 
	position : absolute; 
	right : 0px; 
	top : 50%; 
	z-index : 10; 
 	background-image : url("/resources/img/icon.png"); 
	background-size : 50%; 
	background-repeat : no-repeat; 
	background-position : center;  
	cursor : pointer; 
} 
#menu { 
	width: 200px; 
	height: 100%; 
	position: fixed; 
	top: 0px; 
	right: -220px; 
	z-index: 10; 
	border: 1px solid #c9c9c9; 
	background-color: white; 
	text-align: center; 
	transition: All 0.2s ease; 
	-webkit-transition: All 0.2s ease; 
	-moz-transition: All 0.2s ease; 
	-o-transition: All 0.2s ease;
	font-weight: bold; 	
} 
#menu.open { right: 0px; } 
.page_cover.open { display: block; }
.page_cover { 
	width: 100%; 
	height: 100%; 
	position: fixed; 
	top: 0px; 
	left: 0px; 
	background-color: rgba(0,0,0,0.4); 
	z-index: 4; 
	display: none; 
}
#user-div{
	margin-top: 400px;
}
#ul{
	display: inline-block;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$("#btn").click(function () { $("#menu,.page_cover,html").addClass("open"); // 메뉴 버튼을 눌렀을때 메뉴, 커버, html에 open 클래스를 추가해서 효과를 준다. 
	window.location.hash = "#open"; // 페이지가 이동한것 처럼 URL 뒤에 #를 추가해 준다. 
	}); 
	window.onhashchange = function () { 
	if (location.hash != "#open") { // URL에 #가 있을 경우 아래 명령을 실행한다. 
		$("#menu,.page_cover,html").removeClass("open"); // open 클래스를 지워 원래대로 돌린다. 
	}};
	var currentPosition = parseInt($("#btn").css("top")); 
	$(window).scroll(function() { 
		var position = $(window).scrollTop(); 
		$("#btn").stop().animate({"top":position+currentPosition+"px"},1000); 
	});

});
</script>
</head>
<body>
<div class="btn" id="btn">
</div> 

<div onclick="history.back();" class="page_cover"></div>

<div id="menu"> 
	<div id="user-div">
		<ul id="ul" class="nav justify-content-center">
			<sec:authorize access="isAuthenticated()">
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_MANAGER')">
					<li class="nav-item" id="side_item"><a class="nav-link"
						href="/admin/main">관리자 페이지</a></li>
				</sec:authorize>
				<sec:authorize access="!hasRole('ROLE_ADMIN')">
					<li class="nav-item" id="side_item"><a class="nav-link"
						href="/member/myPage">나의 페이지</a></li>
				</sec:authorize>
				<li class="nav-item" id="side_item"><a class="nav-link"
					href="/logout">로그아웃</a></li>
				<li class="nav-item" id="side_item"><a class="nav-link"
					href="/exhibition/favoriteList">관심목록</a></li>
				<li class="nav-item" id="side_item"><a class="nav-link"
					href="/exhibition/recentlyView">최근본상품</a>
				<div></div></li>
				<input id="userid" type="hidden"
					value='<sec:authentication property="principal.Username"/>'>
					
			</sec:authorize>
			<sec:authorize access="isAnonymous()">
				<li class="nav-item" id="side_item"><a class="nav-link"
					href="/member/joinForm">회원가입</a></li>
				<li class="nav-item" id="side_item"><a class="nav-link"
					href="/member/loginForm">로그인</a></li>
			</sec:authorize>
		</ul>
	</div>
</div>
</body>
</html>