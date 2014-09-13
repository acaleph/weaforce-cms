<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script src="/common/jquery/plugin/tree/jquery.tree.js"
	type="text/javascript"></script>
<script src="/common/jquery/plugin/tree/jquery.easing.js"
	type="text/javascript"></script>
<link href="/common/jquery/plugin/tree/css/jquery.tree.css"
	rel="stylesheet" type="text/css" media="screen" />
<title>Organ Definition</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#container-tree-organ').treeCrud({root:1,script:'/system/organ/organ!doOrganTree.action'},function(url){
		//url is the organId value
		if (url > 0){
			//$('#nextNew').attr('disabled','true');
			//$('#sameNew').attr('disabled','true');
			$('#container-organ').load('/system/organ/organ!input.action',{'organId':url});
			$('#organForm').validate();
		}
	});
	//$('#nextNew').attr('disabled','true');
	//$('#sameNew').attr('disabled','true');
	
	$('#sameNew').bind('click',function(){
		//alert("organFid: " + $('#organFid').val());
		if ($('input[@id="organFid"]').val() > 0)
			$('#container-organ').load('/system/organ/organ!input.action',{'organFid':$('input[@id="organFid"]').val()});
	});
	$('#nextNew').bind('click',function(){
		//alert("organId: " + $('input[@id="organId"]').val());
		if ($('input[@id="organId"]').val() > 0)
			$('#container-organ').load('/system/organ/organ!input.action',{'organFid':$('input[@id="organId"]').val()});
	});
});
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" width="79%" height="29" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		系统导航 - &gt;组织机构</td>
	</tr>
</table>
<table border="0" cellpadding="0" cellspacing="1" class="stripe"
	align="center" width="98%" height="50%" bgcolor="#FFFFFF">
	<tr class="table-head">
		<td height="23" width="98%" colspan="2">&nbsp; 组织机构</td>
	</tr>
	<tr>
		<td width="98%" align="center" colspan="2"></td>
	</tr>
	<tr>
		<td width="30%">
		<table border="0" cellpadding="0" cellspacing="1" align="center"
			width="100%" height="90%" class="stripe">
			<tr>
				<td valign="top">
				<div id="container-tree-organ"></div>
				</td>
			</tr>
		</table>
		</td>
		<td width="80%">
		<table border="0" cellpadding="0" cellspacing="1" align="center"
			width="100%" height="90%" class="stripe">
			<tr>
				<td align="center" valign="top"><input type="button" id="sameNew"
					name="sameNew" value="新增同级" class="button">&nbsp;&nbsp;<input
					type="button" id="nextNew" name="nextNew" value="新增下级"
					class="button"></td>
			</tr>
			<tr>
				<td>
				<form id="organForm" name="organForm" action="" method="post">
				<div id="container-organ"></div>
				</form>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>