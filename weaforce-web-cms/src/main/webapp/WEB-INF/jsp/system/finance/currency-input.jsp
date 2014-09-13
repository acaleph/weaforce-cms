<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Currency Maintain</title>
<script type="text/javascript">
$(document).ready(function(){
	if ($('#currencyIsActive').val() == '1'){
		$('#setActive').attr('checked','true');
	}
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a
			href="currency.action">货币字典域</a>-&gt;货币信息</td>
	</tr>
</table>
<form id="currencyForm" name="currencyForm"
	action="currency!save.action" method="POST">
<table border="0" cellpadding="0" cellspacing="1" class="stripe"
	align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="599" colspan="2">&nbsp;<img src="/common/image/title-icon0.gif" width="16" height="13">&nbsp;货币信息</td>
	</tr>
	<tr>
		<td width="100%" align="center" colspan="2"></td>
	</tr>
	<tr>
		<td width="100%" align="center"><input type="hidden"
			id="currencyId" name="currencyId"
			value="<s:property value='currencyId' />" />
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="stripe" bgcolor="#FFFFFF" height="100%">
			<tr bgcolor="#FFFFFF">
				<td height="30">货币代码:</td>
				<td height="30"><input id="currencyCode" name="currencyCode"
					value="<s:property value='currencyCode' />" class="required" /></td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td height="30">货币名称:</td>
				<td height="30"><input id="currencyName" name="currencyName"
					value="<s:property value='currencyName' />" class="required" /></td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td height="30">有效:</td>
				<td height="30"><input type="hidden" id="currencyIsActive"
					name="currencyIsActive" size="1"
					value="<s:property value='currencyIsActive' />"> <input
					type="checkbox" id="setActive" name="setActive"
					value="<s:property value='currencyIsActive' />"
					onclick="javascript:(this.checked)? document.currencyForm.currencyIsActive.value=1:document.currencyForm.currencyIsActive.value=0;" />
				</td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td height="20" width="100%" colspan="2">
				<hr size="1">
				</td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td align="center" colspan="2">
					<table>
						<tr>
							<security:authorize ifAnyGranted="ROLE_CMS_CRUD_USER">
							<td><INPUT type="button" id="save" name="save" value="save" width="15" onClick="javascript:doSubmit('currencyForm','currency!save.action')"></td>
							</security:authorize>
							<td><INPUT type="button" name="CANCEL" value="CANCEL" width="15" onClick="javascript:window.history.back();"></td>
						</tr>
					</table>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>