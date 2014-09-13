<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Category input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#serviceForm').validate();
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="category-service.action">广告服务</a>-&gt;服务维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="serviceForm" name="serviceForm" action="category-service!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp;服务信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td colspan="4">
					<input type="hidden" id="channelId" name="channelId" value="<s:property value='channelId' />" >
					<input type="hidden" id="serviceId" name="serviceId" value="<s:property value='serviceId' />" >
				</td>
			</tr>
			<tr>
				<td height="30">栏目:</td>
				<td><s:select id="categoryId" name="categoryId" value="%{serviceCategory.categoryId}" list="categoryList" listKey="categoryId" listValue="categoryName" /></td>
				<td>名称:</td>
				<td><input id="serviceName" name="serviceName" value="<s:property value='serviceName' />" class="required"></td>
				<td>排序:</td>
				<td><input id="serviceOrder" name="serviceOrder" value="<s:property value='serviceOrder' />" class="required"></td>
			</tr>
			<tr>
				<td height="30">模板:</td>
				<td><s:select id="templateId" name="templateId" value="%{serviceTemplate.templateId}" list="templateList" listKey="templateId" listValue="templateName" /></td>
				<td>文件名称:</td>
				<td colspan="3"><input id="serviceFile" name="serviceFileName" value="<s:property value='serviceFileName' />" class="required"></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td height="30">描述:</td>
				<td colspan="5"><textarea id="serviceDescription" name="serviceDescription" rows="5" cols="88"><s:property value="serviceDescription" /></textarea></td>
			</tr>
			<tr><td height="30" colspan="6"><hr size="1"></td></tr>
			<tr>
				<td colspan="6" height="23">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('serviceForm','category-service!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('categoryForm','category-service!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
						<td><input onClick="javascript:doSubmit('categoryForm','category-service!parse.action')" type="button" id="parse" name="parse" value="Parse" class="button"></td>
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