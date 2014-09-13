<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>User input</title>
<script type="text/javascript">
$(document).ready(function(){
	//if ($('#userIsSystem').val() == 1)
	//	 $("#save").attr("disabled",true);
	$("#userForm").validate({
		rules: {
			userLogin: {
				email:true,
    			required: true, 
    			remote: "/system/user!checkUserLogin.action?userId=" + $('#userId').val()
			}
		},
		messages: {
			userLogin: {
				remote: "用户登录名已经存在"
			}
		}
	});
	if ($('#userIsActive').val() == '1') $('#setActive').attr('checked','true');
	if ($('#userXp').val() == '1') $('#setXp').attr('checked','true');
});
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="user.action">用户管理</a>-&gt;维护用户</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="userForm" name="userForm" action="user!save.action" method="POST">
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23">&nbsp;<img src="/common/image/title-icon0.gif" width="16" height="13">&nbsp;用户信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="userId" name="userId" value="<s:property value='userId' />"></td>
				<td><input type="hidden" id="userIsSystem" name="userIsSystem" value="<s:property value='userIsSystem' />" ></td>
			</tr>
			<tr>
				<td height="30" width="15%">菜单角色:</td>
				<td><s:select id="queryRoleId" name="queryRoleId" value="%{defaultUserRole.roleId}" list="roleList" listKey="roleId" listValue="roleName" required="true" /></td>
				<td>帐套代码</td>
				<td><input id="account" name="account" value="<s:property value='account' />" readonly="readonly" ></td>
			</tr>
			<tr>
				<td height="30">登录ID:</td>
				<td><input id="userLogin" name="userLogin" value="<s:property value='userLogin' />" size="30" style="TEXT-TRANSFORM: uppercase;" class="email required" ></td>
				<td>密码明文:</td>
				<td><input id="userPassword" name="userPassword" value="<s:property value='userPassword' />" ></td>
			</tr>
			<tr>
				<td height="30">英文名称:</td>
				<td><input id="userNameEn" name="userNameEn" value="<s:property value='userNameEn' />" class="required" ></td>
				<td>中文名称:</td>
				<td><input id="userNameCn" id="userNameCn" name="userNameCn" value="<s:property value='userNameCn' />" class="required" ></td>
			</tr>
			<tr>
				<td height="30">城市:</td>
				<td><s:select id="cityId" name="cityId" value="%{userCity.cityId}" list="cityList" listKey="cityId" listValue="cityNameCn" /></td>
				<td>区域:</td>
				<td><s:select id="zoneId" name="zoneId" value="%{userZone.zoneId}" list="zoneList" listKey="zoneId" listValue="zoneName" /></td>
			</tr>
			<tr>
				<td height="30">活动:</td>
				<td>
					<input type="hidden" id="userIsActive" name="userIsActive" size="1" value="<s:property value='userIsActive' />"> 
					<input type="checkbox" id="setActive" name="setActive" onclick="javascript:(this.checked)? document.userForm.userIsActive.value=1:document.userForm.userIsActive.value=0;">
				</td>
				<td height="30">体验:</td>
				<td><input type="hidden" id="userXp" name="userXp" size="1" value="<s:property value='userXp' />"> 
					<input type="checkbox" id="setXp" name="setXp" onclick="javascript:(this.checked)? document.userForm.userXp.value=1:document.userForm.userXp.value=0;"></td>
			</tr>
			<tr>
				<td height="30">选择角色:</td>
				<td height="30" colspan="3">
				<fieldset><legend>系统角色(将影响用户授权)</legend>
					<div style="word-break: break-all; width: 100%; overflow: auto;"><s:checkboxlist name="checkedRoleIds" list="roleList" listKey="roleId" listValue="roleName" /></div>
				</fieldset>
				</td>
			</tr>
			<tr>
				<td height="20" width="100%" colspan="4"><hr size="1"></td>
			</tr>
			<tr>
				<td align="center" colspan="4">
				<table>
					<tr>
						<td><input type="button" id="save" name="save" value="Save" width="15" onClick="javascript:doSubmit('userForm','user!save.action')" class="button"></td>
						<td><input type="button" id="lock" name="lock" value="Lock" onClick="javascript:doSubmit('userForm','user!lock.action');" class="button"></td>
						<td><input type="button" id="unlock" name="unlock" value="unlock" onClick="javascript:doSubmit('userForm','user!unlock.action');" class="button"></td>
						<td><input type="button" id="return" name="return" value="Return" width="15" onClick="javascript:window.history.back();" class="button"></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>