<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>User password</title>
<script type="text/javascript">
	$(document).ready( function() {
		if ($('#userIsSystem').val() == 1)
			$("#save").attr("disabled", true);
		$("#userForm").validate({
			rules: {
		    	passwordNew: "required",
		    	passwordConfirm: {
		      		equalTo: "#passwordNew"
		    	}
		  	}
		});
		 var options = { 
			target:'#returnMessage',
			url:"user!changePassword.action",
			beforeSubmit:showRequest,
			success:showResponse
		 }
		$("#userForm").ajaxForm(options);
		function showRequest(formData, jqForm, options) {
			var queryString = $.param(formData);
			//alert('About to submit: \n\n' + queryString);
			return true;
		}
		function showResponse(responseText, statusText)  {
			//alert('status: ' + statusText + '\n\nresponseText: \n' + responseText + 
	        //'\n\nThe output div should have already been updated with the responseText.');
		}
	});
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6">&nbsp;当前用户</td>
	</tr>
</table>
<div class="spacer-10"></div>
<form id="userForm" name="userForm" action="user!changePassword.action"
	method="POST">
<table border="0" cellpadding="0" cellspacing="1" class="stripe"
	align="center" width="98%">
	<tr class="table-head">
		<td height="23">&nbsp;<img src="/common/image/title-icon0.gif" width="16" height="13">&nbsp;修改密碼&nbsp;<font color="red"><span id="returnMessage"></span></font></td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="stripe">
			<tr>
				<td><input type="hidden" id="userId" name="userId"
					value="<s:property value='userId' />"></td>
				<td><input type="hidden" id="userIsSystem" name="userIsSystem"
					value="<s:property value='userIsSystem' />"></td>
			</tr>
			<tr>
				<td height="30">帐套</td>
				<td height="30"><input id="accountName" name="accountName"
					value="<s:property value='accountName' />" size="30" readonly="readonly" ></td>
			</tr>
			<tr>
				<td height="30">角色</td>
				<td height="30"><input id="roleName" name="roleName"
					value="<s:property value='roleName' />" size="30" readonly="readonly" ></td>
			</tr>
			<tr>
				<td height="30">登录ID:</td>
				<td height="30"><input id="userLogin" name="userLogin"
					value="<s:property value='userLogin' />" size="30"
					style="TEXT-TRANSFORM: uppercase;" readonly="readonly"
					class="email required"></td>
			</tr>
			<tr>
				<td height="30">英文名称:</td>
				<td height="30"><input id="userNameEn" name="userNameEn"
					value="<s:property value='userNameEn' />" readonly="readonly" class="required"></td>
			</tr>
			<tr>
				<td height="30">中文名称:</td>
				<td height="30"><input id="userNameCn"
					name="userNameCn" value="<s:property value='userNameCn' />" readonly="readonly"
					class="required"></td>
			</tr>
			<tr>
				<td height="30">上一次登录:</td>
				<td height="30"><input id="userLastLoginDate"
					name="userLastLoginDate" value="<s:property value='userLastLoginDate' />" readonly="readonly" ></td>
			</tr>
			<tr>
				<td height="30">当前登录:</td>
				<td height="30"><input id="userCurrentLoginDate"
					name="userCurrentLoginDate" value="<s:property value='userCurrentLoginDate' />" readonly="readonly" ></td>
			</tr>
			<tr>
				<td height="30">密码明文:</td>
				<td height="30"><input id="passwordCurrent" name="passwordCurrent" class="required"></td>
			</tr>
			<tr>
				<td height="30">新密码:</td>
				<td height="30"><input id="passwordNew"
					name="passwordNew"></td>
			</tr>
			<tr>
				<td height="30">密码确认:</td>
				<td height="30"><input id="passwordConfirm"
					name="passwordConfirm"></td>
			</tr>
			<tr>
				<td height="20" width="100%" colspan="4">
				<hr size="1">
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">
				<table>
					<tr>
						<td><input type="button" id="save" name="save" value="Save"
							width="15" onClick="javascript:doSubmit('userForm','user!changePassword.action')"></td>
						<td><input type="button" id="return" name="return"
							value="Return" width="15"
							onClick="javascript:window.history.back();"></td>
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