<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Authority input</title>
</head>
<body>
<table border="0" align="center" width="100%" class="stripe">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="authority.action">授权管理</a>-&gt;授权信息</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="authorityForm" name="authorityForm"
	action="authority!save.action" method="POST">
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="599" colspan="2">&nbsp; 授权信息</td>
	</tr>
	<tr>
		<td width="100%" align="center" colspan="2"></td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe" height="100%">
			<tr>
				<td>
					<input type="hidden" id="authorityId" name="authorityId" value="<s:property value='authorityId' />" >
				</td>
			</tr>
			<tr>
				<td height="30">系统</td>
				<td height="30"><s:select id="queryBusinessId" name="queryBusinessId"
					value="%{authorityBusiness.businessId}" list="businessList"
					listKey="businessId" listValue="businessNameCn"  /></td>
			</tr>
			<tr>
				<td height="30">授权名称:</td>
				<td height="30"><input id="authorityName" name="authorityName"
					value="<s:property value='authorityName' />" size="38" class="required" /></td>
			</tr>
			<tr>
				<td height="30">授权代码:</td>
				<td height="30"><input id="authorityCode" name="authorityCode"
					value="<s:property value='authorityCode' />" size="38" class="required" />
				</td>
			</tr>
			<tr>
				<td height="30">说明:</td>
				<td height="30"><textarea id="authorityDescription"
					name="authorityDescription" rows="3" cols="78" class="required"><s:property
					value='authorityDescription' /></textarea></td>
			</tr>
			<tr>
				<td height="20" width="100%" colspan="2">
				<hr size="1">
				</td>
			</tr>
			<tr>
				<td><INPUT type="button" id="save" name="save" value="Save" width="15" onClick="javascript:doSubmit('authorityForm','authority!save.action')"></td>
				<td><INPUT type="button" id="return" name="return" value="Return" width="15" onClick="javascript:window.history.back();"></td>
			</tr>

		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>