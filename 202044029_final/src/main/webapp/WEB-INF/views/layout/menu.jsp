<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<sec:authentication property="principal" var="pinfo" />
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<!-- Bootstrap import -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- 추가 import -->

<link rel="stylesheet" type="text/css" href="/resources/css/respond.css">
<script type="text/javascript" src="/resources/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="/resources/js/layout.js"></script>
<script src="/resources/js/sockjs.js"></script>
<script src="/resources/js/stomp.js"></script>
<!-- menu css -->
<!-- <link href="http://www.blueb.co.kr/data/201010/IJ12873478039948/style.css" media="all" rel="stylesheet" type="text/css" /> -->
<!-- font -->
<!-- <link href="https://fonts.googleapis.com/css?family=Prompt:400,500,700" rel="stylesheet"> -->
<!-- kakao login -->
<script>
$(document).ready(function(){
	$(document).on('mouseover', '.topMenu span', function() {
	    $('.dept01').slideDown(400);
	});
	$(document).on('mouseover', 'div', function () {
	    if (!$(this).hasClass('topMenu')) {
	        $('.dept01').slideUp(400);
	    }
	});
});
</script>
<style>
@font-face { font-family: 'GyeonggiBatang'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/GyeonggiBatang.woff') format('woff'); font-weight: normal; font-style: normal; }
*{
	/* font-family: 'Arita-dotum-Medium'; */
	font-family: 'GyeonggiBatang';
}
.topMenu {
	width: 100%;
 	margin-bottom: 20px;
  	height: 40px;
  	font-size: 20px;
    padding :20px;
    padding-left : 35%;

    }
.topMenu:after {
	content: "";
 	display: block;
  	clear: both; }
.menu01>li {float: left; width: 170px; line-height: 40px;}
.topMenu li {list-style: none;}
.menu01 span {font-size: 25px; font-weight: bold; }            
.dept01 {display: none; padding: 20px 0;  position: relative; font-size: 20px; font-weight: bold;}            
#nop {float: none;}     
.none:after {content: ""; display: block; clear: both; width: 100%; height: 1px;}
.nono{ display: block; width: 100%;height: 1px;}
.header-div{
	font-family: 'GyeonggiBatang';
	padding-left: 42%;
	padding-top: 10px;
	margin: 0 auto;
	border: 1px solid none;
}
.header-title{
	display: block;
	padding-top: 10px;
}
.header-login{
	padding-top: 50px;
	justify-content: flex-end;
	margin-left: 300px;
}
.header-menu{
	display: flex;
	border-bottom: 1px solid nono;
	font-family: 'Arita-dotum-Medium';
	padding-left: 33%;
	margin-left: 15px;
	font-size: 20px;
}
.pagination, .page-item a {
	color: #5C5A5C ;
	font-weight: normal ;
}
.pagination li.active a{
	color: #5C5A5C ;
	background-color: #E1DAD2;
	font-weight: bold;
}
.dropdown-item:hover {
	background-color: #E1DAD2;
}
a:link {text-decoration: none; color: #333333;}
a:visited {text-decoration: none; color: #333333;}
a:active {text-decoration: none; color: #333333;}
}

</style>
</head>
<body>
	<header>
		<div class='header-div'>
			<div class='header-title'>
				<h1>
					<a href="/"><img class="header_img maintitle" src="/resources/img/muscle.png" alt="Cinque Terre" width="312" height="140"></a>
				</h1>
			</div>
		</div> 
		<div class="topMenu" >
            <ul class="menu01">
                <li><span>이용안내</span>
                    <ul class="dept01">
                    	<li id="nop"><a href="/">HOME</a></li>
                        <!--  <li id="nop"><a href="#">추천</a></li>-->
                    </ul>
                </li>
                <li><span>자유 게시판</span>
                    <ul class="dept01">
                        <li id="nop"><a href="/recommend/recommendboard">자유 게시판</a></li>
                    </ul>
                </li>
                <li><span>파트너 찾기</span>
                    <ul class="dept01">
                        <li id="nop"><a href="/review/reviewboard">파트너 게시판</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <div class="none">
        </div>
</header>

