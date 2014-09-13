<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Item</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="dictionary.action">通用字典域</a>-&gt;创建/维护通用字典</td>
	</tr>
</table>
<form id="dictionaryForm" name="dictionaryForm" action="dictionary!save.action" method="POST">
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23">&nbsp; 字典信息</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="dictionaryId" name="dictionaryId" value="<s:property value='dictionaryId' />"></td>
			</tr>
			<tr>
				<td height="30">名称:</td>
				<td><input type="text" id="dictionaryName" name="dictionaryName" value="<s:property value='dictionaryName' />"></td>
			</tr>
			<tr>
				<td height="10" width="100%" colspan="2"><hr size="1"></td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<table>
						<tr>
							<td><INPUT type="button" id="save" name="save" value="Save" width="15" onClick="javascript:doSubmit('dictionaryForm','dictionary!save.action')"></td>
							<td><INPUT type="button" id="return" name="return" value="Return" width="15" onClick="javascript:window.history.back();"></td>
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