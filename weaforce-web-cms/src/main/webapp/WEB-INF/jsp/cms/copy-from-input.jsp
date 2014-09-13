<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Copy From input</title>
</head>
<body>
<table border="0" align="center" width="100%" class="stripe">
	<tr>
		<td valign="middle" height="29" align="left">系统导航-&gt;<a href="copy-from.action">文章来源</a>-&gt;来源维护</td>
	</tr>
</table>
<form id="fromForm" name="fromForm" action="copy-from!save.action" method="POST">
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">来源信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="stripe">
			<tr>
				<td><input type="hidden" id="fromId" name="fromId" value="<s:property value='fromId' />"></td>
			</tr>
			<tr>
				<td height="30">名称:</td>
				<td height="30"><input id="fromName" name="fromName" value="<s:property value='fromName' />" class="required"></td>
			</tr>
			<tr>
				<td height="30">网站:</td>
				<td height="30"><input id="fromWeb" name="fromWeb" value="<s:property value='fromWeb' />" class="required"></td>
			</tr>
			<tr>
				<td height="30" colspan="2"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="2" height="33">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('fromForm','copy-from!save.action')"
							type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('fromForm','copy-from!delete.action')"
							type="button" id="delete" name="delete" value="Delete" class="button"></td>
						<td><input onClick="javascript:window.history.back();"
							type="button" id="return" name="return" value="Return" class="button"></td>
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