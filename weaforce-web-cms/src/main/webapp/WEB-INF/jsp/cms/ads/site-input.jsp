<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Discount input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#siteForm').validate();
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="catalog-site.action">网站管理</a>-&gt;网站维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="siteForm" name="siteForm" action="site!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp;网站信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="siteId" name="siteId" value="<s:property value='siteId' />" ></td>
				<td><input type="hidden" id="channelId" name="channelId" value="<s:property value='channelId' />" ></td>
			</tr>
			<tr>
				<td height="30">栏目:</td>
				<td><s:select id="categoryId" name="categoryId" value="%{siteCategory.categoryId}" list="categoryList" listKey="categoryId" listValue="categoryName" /></td>
			</tr>
			<tr>
				<td height="30">名称:</td>
				<td><input id="siteName" name="siteName" value="<s:property value='siteName' />" size="28" class="required"></td>
			</tr>
			<tr>
				<td height="30">网站:</td>
				<td><input id="siteUrl" name="siteUrl" value="<s:property value='siteUrl' />" size="38" class="required"></td>
			</tr>
			<tr><td height="30" colspan="2"><hr size="1"></td></tr>
			<tr>
				<td colspan="2" height="23">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('siteForm','site!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('siteForm','site!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
						<td><input onClick="javascript:window.history.back();" type="button" id="return" name="return" value="Return" class="button"></td>
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