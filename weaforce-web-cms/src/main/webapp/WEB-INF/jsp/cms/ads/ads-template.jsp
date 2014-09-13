<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>对模板中的有关ADS标签进行替换</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;模板广告标签</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="adsForm" name="adsForm" action="ads-template!parse.action" method="Post">
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr>
		<td align="left">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe" align="center">
			<tr class="table-head">
				<td height="23" width="269" colspan="8">广告内容</td>
			</tr>
			<tr>
				<td>模板:</td>
				<td colspan="2"><s:select id="templateId" name="templateId" value="%{templateId}" list="templateList" listKey="templateId" listValue="templateName" /></td>
			</tr>
			<tr>
				<td><input onClick="doSubmit('adsForm','ads-template!parse.action');" type="button" id="save" name="save" value="Save" class="button"></td>
			</tr>	
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>