<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#registryForm').validate();
	if ($('#registryIsActive').val() == '1'){$('#setActive').attr('checked','true');}
})
</script>
<title>Registry Input</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="registry.action">注册管理</a>-&gt;维护注册</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="registryForm" name="registryForm" action="registry!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp; 注册信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="registryId" name="registryId" value="<s:property value='registryId' />" ></td>
			</tr>
			<tr>
				<td height="30">姓名:</td>
				<td><input id="registryName" name="registryName" value="<s:property value='registryName' />" size="38" class="required"></td>
			</tr>
			<tr>
				<td height="30">性别:</td>
				<td>
					<table>
						<tr>
							<td>
								<select id="registrySex" name="registrySex">
									<option value="FEMALE">女</option>
									<option value="MALE">男</option>
								</select>
								<script type="text/javascript">
									document.registryForm.registrySex.value=<s:property value="registrySex" />;
								</script>
							</td>
							<td height="30">有效:</td>
							<td><input type="hidden" id="registryIsActive" name="registryIsActive" size="1" value="<s:property value='registryIsActive' />"> 
								<input type="checkbox" id="setActive" name="setActive" onclick="javascript:(this.checked)? document.registryForm.registryIsActive.value=1:document.registryForm.registryIsActive.value=0;">
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="30">登录:</td>
				<td><input id="registryLogin" name="registryLogin" value="<s:property value='registryLogin' />" size="38" class="required" style="TEXT-TRANSFORM: uppercase;"></td>
			</tr>
			<tr>
				<td height="30">昵称:</td>
				<td><input id="registryNickname" name="registryNickname" value="<s:property value='registryNickname' />" size="38" class="required"></td>
			</tr>
			<tr>
				<td height="30">宣传:</td>
				<td><input id="registryShow" name="registryShow" value="<s:property value='registryShow' />" ></td>
			</tr>
			<tr>
				<td height="30">宣传URL:</td>
				<td><input id="registryShowUrl" name="registryShowUrl" value="<s:property value='registryShowUrl' />" size="38" style="TEXT-TRANSFORM: lowercase;"></td>
			</tr>
			<tr>
				<td height="30">地址:</td>
				<td><input id="registryAddress" name="registryAddress" value="<s:property value='registryAddress' />" size="38" style="TEXT-TRANSFORM: lowercase;"></td>
			</tr>
			<tr>
				<td height="30" colspan="2"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="2" height="33">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('registryForm','registry!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('registryForm','registry!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
						<td><input onClick="javascript:doSubmit('registryForm','registry!lock.action')" type="button" id="lock" name="lock" value="Lock" class="button"></td>
						<td><input onClick="javascript:window.history.back();" type="button" name="Return" value="Return" class="button"></td>
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