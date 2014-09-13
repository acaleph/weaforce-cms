<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script src="/common/jquery/plugin/tree/jquery.tree.js" type="text/javascript"></script>
<script src="/common/jquery/plugin/tree/jquery.easing.js" type="text/javascript"></script>
<link href="/common/jquery/plugin/tree/css/jquery.tree.css" rel="stylesheet" type="text/css" media="screen" />
<title>Role Input</title>
<script type="text/javascript"> 
var roleMenuRank ="";
$(document).ready(function(){
	//if ($('#roleIsSystem').val() == 1)
	//	 $("#save").attr("disabled",true);
	
	$('#container-tree-menu').treeSimple({event:'dblclick',root:1,script:'/system/role!roleMenuHtml.action'},function(url){
		//alert(url);
	});
	$('#roleForm').validate(function(){});
	//Will be replaced by common function when page loading.
	if ($('#roleIsActive').val() == '1'){$('#setActive').attr('checked','true');}
	//Ready for function resetTree
	if ($('#menuRank').val() != ''){
		roleMenuRank = "," + $('#menuRank').val() + ",";
		document.roleForm.menuRank.value = roleMenuRank;
	}else
		roleMenuRank = ",";
});
function resetTree(chk){
	$.ajax({
		url:'/system/role!doChecked.action',
		data:{'menuValue':chk.value},
		type:'get',
		dataType:'json',
		success:function(data){
			$.each(data,function(index,entry){
				var menu = document.getElementById(entry['menuId']);
				if (menu != null){
					menu.checked = chk.checked;
					//Add(delete) parent(child) to #menuRank
					if (chk.checked){
						menu.value= entry['menuId'] + ':1';
						if (roleMenuRank.indexOf("," + entry['menuId'] + ",") == -1){
							document.roleForm.menuRank.value = roleMenuRank + entry['menuId'] + ",";
							//Must be added!
							roleMenuRank = $('#menuRank').val();
						}
					}else{
						menu.value= entry['menuId'] + ':0';
						if (roleMenuRank.indexOf("," + entry['menuId'] + ",") != -1){
							document.roleForm.menuRank.value = roleMenuRank.replace("," + entry['menuId'],"");
							//Must be added!
							roleMenuRank = $('#menuRank').val();
						}
					}
				}
			});
		}
	});
	//alert(chk.value + " name: " + chk.name + " checked " + chk.checked);
	var menuId = chk.value.substring(0,chk.value.length - 2);
	//alert(menuId);
	if (chk.checked){
		if (roleMenuRank.indexOf("," + menuId + ",") == -1)
			document.roleForm.menuRank.value = roleMenuRank + menuId + ",";
	}else{
		//Replace ",12," with ","
		document.roleForm.menuRank.value = roleMenuRank.replace("," + menuId + ",",",");
	}
	roleMenuRank = $('#menuRank').val();
}

</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="role.action">角色管理</a>-&gt;角色信息</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="roleForm" name="roleForm" action="role!save.action"
	method="POST">
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="599" colspan="2">&nbsp;<img src="/common/image/title-icon0.gif" width="16" height="13">&nbsp;角色信息</td>
	</tr>
	<tr>
		<td width="100%" align="center" colspan="2"></td>
	</tr>
	<tr>
		<td width="38%" align="center" rowspan="6">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe" bgcolor="#FFFFFF" height="100%">
			<tr>
				<td><input type="hidden" id="menuRank" name="menuRank" value="<s:property value='roleMenuRank' />" /></td>
			</tr>
			<!-- menu tree -->
			<tr>
				<td>
				<div id="container-tree">
				<div id="container-tree-menu"></div>
				</div>
				</td>
			</tr>
		</table>
		</td>
		<td width="62%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe" height="100%">
			<tr>
				<td><input type="hidden" id="roleId" name="roleId" value="<s:property value='roleId' />" ></td>
				<td><input type="hidden" id="roleIsSystem" name="roleIsSystem" value="<s:property value='roleIsSystem' />"></td>
			</tr>
			<tr>
				<td height="30">角色名称:</td>
				<td><input id="roleName" name="roleName" size="48" value="<s:property value='roleName' />" class="required" /></td>
			</tr>
			<tr>
				<td height="30">活动:</td>
				<td>
					<input type="hidden" id="roleIsActive" name="roleIsActive" size="1" value="<s:property value='roleIsActive' />"> 
					<input type="checkbox" id="setActive" name="setActive" onclick="javascript:(this.checked)? document.roleForm.roleIsActive.value=1:document.roleForm.roleIsActive.value=0;">
				</td>
			</tr>
			<tr>
				<td>说明:</td>
				<td><textarea id="roleDescription" name="roleDescription" rows="5" cols="55"><s:property value="roleDescription" /></textarea></td>
			</tr>
			<tr>
				<td height="30" colspan="2">
				<fieldset><legend>数据操作权限(注意授权类别):</legend>
				<div style="word-break: break-all; width: 100%; overflow: auto;">
				<s:checkboxlist name="checkedAuthorityIds" list="authoritieList"
					listKey="authorityId" listValue="authorityName" /></div>
				</fieldset>
				</td>
			</tr>
			<tr>
				<td height="20" width="100%" colspan="2"><hr size="1"></td>
			</tr>
			<tr>
				<td align="center"><input type="button" id="save" name="save" value="Save" width="15" onClick="javascript:doSubmit('roleForm','role!save.action')"></td>
				<td><input type="button" id="return" name="return" value="Return" width="15" onClick="javascript:window.history.back();"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>