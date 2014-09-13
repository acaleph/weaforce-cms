<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="keywords" content="人事考核|协同办公|项目管理|CMS系统">
<script src="${ctx}/static/jquery/jquery.min.js" type="text/javascript"></script>
<!-- validate -->
<script src="${ctx}/static/jquery/plugin/validate.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/plugin/validate_zh.js" type="text/javascript"></script>
<link href="${ctx}/static/jquery/plugin/validate.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<!-- validate -->
<title>维福斯软件 系统应用平台</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#loginform').validate();
})
</script>
<style type="text/css">
#container {
	width: 100%;
}
table.header {
	width:100%;
}
#menu_header {
	background:#666666 none repeat scroll 0 0;
	height:24px;
	margin-bottom:4px;
	white-space:nowrap;
}
#menu_header_menu {
	background:transparent url(${ctx}/common/css/image/mainmenu_button.png) repeat-x scroll 0 0;
	font-weight:bold;
	padding:0 10px;
	text-align:center;
}
#menu_header_shortcuts {
	background:#363636 none repeat scroll 0 0;
	font-weight:bold;
	padding-left:10px;
	padding-right:0;
	text-align:center;
}
table.menu_connection {
	border:0 none;
	height:25px;
	padding:0;
	white-space:nowrap;
}
table.menu_connection td.menu_connection_welcome {
	background:#363636 none repeat scroll 0 0;
	color:white;
	padding-left:10px;
	padding-right:18px;
}
table.menu_connection td.menu_connection_links {
	background:transparent url(${ctx}/common/css/image/mainmenu_button.png) repeat-x scroll 0 0;
	color:white;
	padding-left:10px;
	padding-right:10px;
}
.view { width:100%;}
div.welcome {
	color:#990033;
	font-size:16px;
	font-weight:bold;
	text-align:center;
}
div.box2 {
	margin:10px auto;
	padding:10px;
	width:450px;
}
div.box, div.box2 {
	background-color:#F6F6F6;
	border-bottom:1px solid #999999;
	border-top:1px solid #999999;
}
body {
	background:#FFFFFF none repeat scroll 0 0;
	color:#000000;
	margin:0;
	padding:0;
}
body, table {
	font-family:Verdana,Arial,sans-serif;
	font-size:12px;
}
td.label {
	padding:0 4px;
	text-align:right;
	white-space:nowrap;
}
#footer {
	border-top:1px solid #888888;
	clear:both;
	color:#777777;
	font-size:11px;
	line-height:20px;
	margin-top:25px;
	text-align:center;
}
</style>
</head>
<body>
<table id="container" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td>
			<table id="header" class="header" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td colspan="2">
						<table id="menu_header" width="100%" cellspacing="0" cellpadding="0">
							<tr>
								<td id="menu_header_menu" width="8%"><a href="http://www.weaforce.com">维福斯软件</a></td>
								<td id="menu_header_shortcuts" width="8%"><a href="javascript:;">用户注册</a></td>
								<td width="26" style="background: transparent url(${ctx}/common/css/image/diagonal_left.gif) no-repeat scroll left center; -moz-background-clip: -moz-initial; -moz-background-origin: -moz-initial; -moz-background-inline-policy: -moz-initial;"></td>
								<td></td>
								<td align="right"> </td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<div class="view">
				<br>
				<center>
					<img width="200" height="60" border="0" usemap="#devby_map" alt="Developped by Manfred" src="/common/css/image/weaforce.png"/>
						<map name="devby_map">
						<area target="_blank" coords="0,20,100,60" href="http://www.weaforce.com" shape="rect"/>
						<area target="_blank" coords="120,20,200,60" href="http://www.weaforce.com" shape="rect"/>
					</map>
					<br>
					<form id="loginform" name="loginform" method="post" action="/j_spring_security_check">
						<div class="box2 welcome">维福斯欢迎您</div>
						<div class="box2">
							<table cellspacing="2" border="0" align="center">
								<tr>
									<td class="label">用户:</td>
									<td><input  type="text" id="j_username" name="j_username" style="width: 300px;TEXT-TRANSFORM: uppercase;" class="email required"></td>
								</tr>
								<tr>
									<td class="label">密码:</td>
									<td><input type="password"  id="j_password" name="j_password" value="12345678" style="width: 300px;" class="required"></td>
								</tr>
								<tr>
									<td></td>
									<td align="right"><button id="login" name="login" type="submit" style="width: 80px; white-space: nowrap;">登录</button></td>
								</tr>
							</table>
						</div>
					</form>
				</center>
			</div>
		</td>
	</tr>
	<tr>
		<td>
			<div id="footer">
				Copyright © WEAFORCE. Ltd. All Rights Reserved. More Information on <a href="http://www.weaforce.com" target="_blank">www.weaforce.com</a>.<br>
			</div>
		</td>
	</tr>
</table>
</body>
</html>