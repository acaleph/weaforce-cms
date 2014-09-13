<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Resource Input</title>
</head>
<body>
<table border="0" align="center" width="100%" class="stripe">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="resource.action">资源管理</a>-&gt;维护资源</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="resourceForm" name="resourceForm" action="resource!save.action" method="POST">
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="599" colspan="2">&nbsp; 资源信息</td>
	</tr>
	<tr>
		<td width="100%" align="center" colspan="2"></td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe" height="100%">
			<tr>
				<td>
					<input type="hidden" id="businessId" name="businessId" value="<s:property value='businessId' />" >
					<input type="hidden" id="resourceId" name="resourceId" value="<s:property value='resourceId' />" >
				</td>
			</tr>
			<tr>
				<td height="30">名称:</td>
				<td height="30"><input id="resourceName" name="resourceName" value="<s:property value='resourceName' />" size="38" class="required" /></td>
			</tr>
			<tr>
				<td height="30">值:</td>
				<td height="30"><input id="resourceValue" name="resourceValue" value="<s:property value='resourceValue' />" size="38" class="required" /></td>
			</tr>
			<tr>
				<td height="30">类型:</td>
				<td height="30"><input id="resourceType" name="resourceType"
					value="<s:property value='resourceType' />" class="required" /></td>
			</tr>
			<tr>
				<td height="30">排序:</td>
				<td height="30"><input id="resourceGroupOrder" name="resourceGroupOrder"
					value="<s:property value='resourceGroupOrder' />" class="required number" /></td>
			</tr>
			<tr>
				<td height="30">模块:</td>
				<td height="30">
					<s:select id="moduleId" name="moduleId" value="%{resourceModule.moduleId}" list="moduleList" listKey="moduleId" listValue="moduleNameCn" required="true" />
				</td>
			</tr>
			<tr>
				<td height="20" width="100%" colspan="2"><hr size="1"></td>
			</tr>
			<tr>
				<td><INPUT type="button" id="save" name="save" value="Save" width="15" onClick="javascript:doSubmit('resourceForm','resource!save.action')"></td>
				<td><INPUT type="button" id="return" name="return" value="Return" width="15" onClick="javascript:window.history.back();"></td>
			</tr>

		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>