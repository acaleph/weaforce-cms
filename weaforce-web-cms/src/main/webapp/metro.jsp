<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link href="${ctx}/static/bootstrap/css/bootstrap.min.css"
	type="text/css" rel="stylesheet" />
<link href="${ctx}/static/bootstrap/css/bootstrap-responsive.css"
	type="text/css" rel="stylesheet" />
<link href="${ctx}/static/metro/MetroJs.css" type="text/css"
	rel="stylesheet" />
<script src="${ctx}/common/jquery/jquery-1.7.1.min.js"
	type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.js"
	type="text/javascript"></script>
<script src="${ctx}/static/metro/MetroJs.js" type="text/javascript"></script>
<title>Metro</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12 show-grid"></div>
		</div>
		<div class="span12 well pricehover">
			<div class="navbar">
				<div class="navbar-inner">
					<div class="container">
						<a class="btn btn-navbar" data-toggle="collapse"
							data-target=".nav-collapse"> <span class="icon-bar"></span> <span
							class="icon-bar"></span> <span class="icon-bar"></span>
						</a> <a class="brand" href="#">我的相册</a>
						<div class="nav-collapse">
							<ul class="nav">
								<li class="active"><a href="#">Home</a></li>
								<li><a href="#">Link</a></li>
								<li><a href="#">Link</a></li>
								<li><a href="#">Link</a></li>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown">Dropdown <b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li><a href="#">Action</a></li>
										<li><a href="#">Another action</a></li>
										<li><a href="#">Something else here</a></li>
										<li class="divider"></li>
										<li class="nav-header">Nav header</li>
										<li><a href="#">Separated link</a></li>
										<li><a href="#">One more separated link</a></li>
									</ul></li>
							</ul>
							<form class="navbar-search pull-left" action="">
								<input type="text" class="search-query span2"
									placeholder="Search">
							</form>
							<ul class="nav pull-right">
								<li><a href="#">Link</a></li>
								<li class="divider-vertical"></li>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown">Dropdown <b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li><a href="#">Action</a></li>
										<li><a href="#">Another action</a></li>
										<li><a href="#">Something else here</a></li>
										<li class="divider"></li>
										<li><a href="#">Separated link</a></li>
									</ul></li>
							</ul>
						</div>
						<!-- /.nav-collapse -->
					</div>
				</div>
				<!-- /navbar-inner -->
			</div>
			<!-- /navbar -->
		</div>
		<div class="row-fluid">
			<div class="span3 red live-tile flip ha" data-mode="flip"
				data-delay="4000">
				<div class="flip-front ha"
					style="-webkit-animation-play-state: running; -webkit-animation: flipback180 1000ms;">
					<img src="/static/metro/01.jpg" alt="3" />
				</div>
				<div class="flip-back ha"
					style="-webkit-animation-play-state: running; -webkit-animation: flipfront180 1000ms;">
					<img src="/static/metro/02.jpg" alt="4" />
				</div>
			</div>
			<div class="span3 live-tile slide ha" data-speed="750"
				data-delay="3000">
				<div>
					<img src="/static/metro/00.jpg" alt="1" />
				</div>
				<div>
					<img src="/static/metro/03.jpg" alt="2" />
				</div>
			</div>
		</div>

		<div class="row-fluid">
			<div class="span3 live-tile slide ha" data-speed="750"
				data-delay="3000">
				<div>
					<img src="/static/metro/04.jpg" alt="1" />
				</div>
				<div>
					<img src="/static/metro/05.jpg" alt="2" />
				</div>
			</div>
			<div class="span3 red live-tile flip ha" data-mode="flip"
				data-delay="4000">
				<div class="flip-front ha"
					style="-webkit-animation-play-state: running; -webkit-animation: flipback180 1000ms;">
					<img src="/static/metro/09.jpg" alt="3" />
				</div>
				<div class="flip-back ha"
					style="-webkit-animation-play-state: running; -webkit-animation: flipfront180 1000ms;">
					<img src="/static/metro/18.jpg" alt="4" />
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span6">
				<div class="list-tile mango">
					<ul class="flip-list fourTiles" data-mode="flip-list"
						data-delay="2000">
						<li class="ha">
							<div class="span3 flip-front ha"
								style="-webkit-animation-play-state: running; -webkit-animation: flipfront180 1000ms;">
								<img src="/static/metro/13.jpg" alt="1">
							</div>
							<div class="span3 flip-back ha"
								style="margin-top: 0px; -webkit-animation-play-state: running; -webkit-animation: flipback180 1000ms;">
								<img src="/static/metro/13.jpg" alt="1">
							</div>
						</li>
						<li class="ha">
							<div class="span3 flip-front ha"
								style="-webkit-animation-play-state: running; -webkit-animation: flipback180 1000ms;">
								<img src="/static/metro/17.jpg" alt="2">
							</div>
							<div class="span3 flip-back ha"
								style="margin-top: 0px; -webkit-animation-play-state: running; -webkit-animation: flipfront180 1000ms;">
								<img src="/static/metro/17.jpg" alt="2">
							</div>
						</li>
						<li class="ha">
							<div class="span3 flip-front ha"
								style="-webkit-animation-play-state: running; -webkit-animation: flipback180 1000ms;">
								<img src="/static/metro/17.jpg" alt="3">
							</div>
							<div class="span3 flip-back ha"
								style="margin-top: 0px; -webkit-animation-play-state: running; -webkit-animation: flipfront180 1000ms;">
								<img src="/static/metro/17.jpg" alt="3">
							</div>
						</li>
						<li data-direction="horizontal" class="ha">
							<div class="span3 flip-front ha"
								style="-webkit-animation-play-state: running; -webkit-animation: flipfrontY180 1000ms;">
								<img src="/static/metro/17.jpg" alt="4">
							</div>
							<div class="span3 flip-back ha"
								style="margin-top: 0px; -webkit-animation-play-state: running; -webkit-animation: flipbackY180 1000ms;">
								<img src="/static/metro/17.jpg" alt="4">
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(".live-tile, .flip-list").not(".exclude").liveTile();
</script>
</html>
