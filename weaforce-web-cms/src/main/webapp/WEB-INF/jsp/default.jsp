<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<script src="${ctx}/static/jquery/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<link href="${ctx}/static/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/bootstrap/css/bootstrap-responsive.css"
	type="text/css" rel="stylesheet" />
<style type="text/css">
body {
	padding-top: 50px;
	padding-bottom: 40px;
}

body>.navbar .brand {
	padding-right: 0;
	padding-left: 0;
	margin-left: 5px;
	margin-right: 15px;
	float: left;
	font-weight: bold;
	color: #000;
	text-shadow: 0 1px 0 rgba(255, 255, 255, .1), 0 0 30px
		rgba(255, 255, 255, .125);
	-webkit-transition: all .2s linear;
	-moz-transition: all .2s linear;
	transition: all .2s linear;
}

.sidebar-nav {
	padding: 9px 0;
}

.bs-docs {
	position: relative;
	padding: 9px 9px 9px;
	background-color: white;
	border: 1px solid #DDD;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
}

@media ( max-width : 980px) { /* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
<title>Console for weaforce <sitemesh:title /></title>
<sitemesh:head />
</head>
<script type="text/javascript">
	$(document).ready(function() {
		//Add class to accordion header
		$(".accordion-heading a").children('i').addClass("icon-chevron-right");
		$(".accordion-heading a").click(function() {
			$('.accordion-toggle i').removeClass('icon-chevron-down');
			$('.accordion-toggle i').addClass('icon-chevron-right');
			if (!$($(this).attr('href')).hasClass('in')) {
				$(this).children('i').removeClass('icon-chevron-right');
				$(this).children('i').addClass('icon-chevron-down');
			}
		});
		
		$(".accordion-body a").click(function(){
			$("#leftMenu li").removeClass("active");
			$("#leftMenu li i").removeClass("icon-white");
			$(this).parent().addClass("active");
			$(this).children("i").addClass("icon-white");
		});

		var url = window.location.href;
		$('ul.nav-list a').each(function() {
			var href = $(this).attr("href");
			if (href != "" && url.indexOf(href) >= 0) {
				var id = $(this).parent().parent().parent().parent().attr("id");
				$('#' + id).addClass("in").css("height", "auto");
				$('#' + id).prev().children('a').children('i').removeClass('icon-chevron-right');
				$('#' + id).prev().children('a').children('i').addClass('icon-chevron-down');
				$(this).parent().addClass("active");
				//Exits the each 
				return false;
			}
		});
	});
</script>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container-fluid">
				<a class="brand" target="_blank" href="www.weaforce.com">维福斯软件</a>
				<div class="nav-collapse collapse">
					<p class="navbar-text pull-right">
						<a href="${ctx}/admin/user/config/${sessionScope.loginUser.userId}" class="navbar-link">${sessionScope.loginUser.userNameCn}</a>
						<a href="<c:url value="/j_spring_security_logout" />">退出</a>
					</p>
					<ul class="nav">
						<li class="active"><a href="#">首页</a></li>
						<li><a href="#about">关于</a></li>
						<li><a href="#contact">联系我们</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">系统管理 <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="/app/menu?parentId=1">基本设置</a></li>
								<li><a href="/app/menu?parentId=4">CMS管理</a></li>
							</ul></li>
					</ul>
					<form class="navbar-search pull-left" action="">
						<input type="text" class="search-query span2" placeholder="Search">
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span2">
				${sessionScope.leftMenu}
			</div>
			<!-- Body content -->
			<div class="span10 show-grid">
				<sitemesh:body />
			</div>
		</div>
		<div class="row-fluid">
			<footer>
				<p class="pull-right">
					<a href="#">回到首页</a>
				</p>
				<p>© 维福斯软件 2013</p>
			</footer>
		</div>
	</div>
</body>
</html>

