<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<script src="/common/jquery/jquery-1.7.1.min.js"></script>
<script src="/common/jquery/plugin/tree/jquery.tree.js" type="text/javascript"></script>
<link href="/common/jquery/plugin/tree/css/jquery.tree.css" rel="stylesheet" type="text/css" media="screen" />
<title>Menu</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#container-tree-menu').treeCrud({root:1,script:'/system/menu!tree.action'},function(url){
		//url is the organId value
		if (url > 0)
			$('#container-menu').load('/system/menu!input.action',{'menuId':url});
	});
	
	$('#sameNew').bind('click',function(){
		//alert("menuFid: " + $('#menuFid').val());
		if ($('#menuFid').val() > 0)
			$('#container-menu').load('/system/menu!input.action',{'menuFid':$('#menuFid').val()});
	});
	$('#nextNew').bind('click',function(){
		//alert("menuId: " + $('input[@id="menuId"]').val());
		if ($('#menuId').val() > 0)
			$('#container-menu').load('/system/menu!input.action',{'menuFid':$('#menuId').val()});
	});
});
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">系统导航-&gt;系统菜单</td>
	</tr>
</table>
<div class="spacer-2"></div>
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%" height="50%">
	<tr class="table-head">
		<td height="23" width="98%" colspan="2">系统菜单</td>
	</tr>
	<tr>
		<td width="30%">
		<table border="0" cellpadding="0" cellspacing="1" align="center"
			width="100%" height="90%" class="stripe">
			<tr>
				<td valign="top"><div id="container-tree-menu"></div></td>
			</tr>
		</table>
		</td>
		<td width="70%">
		<table border="0" cellpadding="0" cellspacing="1" align="center" width="100%" height="90%" class="stripe">
			<tr>
				<td><input type="button" id="sameNew" name="sameNew" value="新增同级" class="button"></td>
				<td><input type="button" id="nextNew" name="nextNew" value="新增下级" class="button"></td>
			</tr>
			<tr>
				<td width="100%" colspan="2"><div id="container-menu"></div></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>