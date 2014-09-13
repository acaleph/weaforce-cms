<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#categoryForm').validate();
	if ($('#categoryIsActive').val() == '1'){$('#setActive').attr('checked','true');}
	if ($('#categoryArticleList').val() == '1'){$('#setArticle').attr('checked','true');}
})
</script>
<title>Channel Input</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="category.action">栏目字典域</a>-&gt;维护栏目</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="categoryForm" name="categoryForm" action="category!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">栏目信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="categoryId" name="categoryId" value="<s:property value='categoryId' />"></td>
			</tr>
			<tr>
				<td>频道:<font color="red">*</font>:</td>
				<td><s:select id="channelId" name="channelId" value="%{categoryChannel.channelId}" list="channelList" listKey="channelId" listValue="channelName" cssClass="required" /></td>
				<td>模板:<font color="red">*</font>:</td>
				<td><s:select id="templateId" name="templateId" value="%{categoryTemplate.templateId}" list="templateList" listKey="templateId" listValue="templateName" /></td>
			</tr>
			<tr>
				<td>名称:<font color="red">*</font>:</td>
				<td><input id="categoryName" name="categoryName" value="<s:property value='categoryName'/>" class="required"></td>
				<td>Parse路径:</td>
				<td><input id="categoryParsePath" name="categoryParsePath" value="<s:property value='categoryParsePath' />" size="68" class="required"></td>
			</tr>
			<tr>
				<td>文件名称:<font color="red">*</font>:</td>
				<td><input id="categoryParseName" name="categoryParseName" value="<s:property value='categoryParseName'/>" class="required"></td>
				<td>扩展名:<font color="red">*</font>:</td>
				<td>
					<select id="categoryParseNameExt" name="categoryParseNameExt">
						<option value="html">html</option>
						<option value="jsp">jsp</option>
						<option value="php">php</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>URL:<font color="red">*</font>:</td>
				<td><input id="categoryUrl" name="categoryUrl" value="<s:property value='categoryUrl'/>" size="68" class="required"></td>
				<td>排序:</td>
				<td><input id="categoryOrder" name="categoryOrder" value="<s:property value='categoryOrder' />" size="8" class="required"></td>
			</tr>
			<tr>
				<td>描述:<font color=red>*</font>:</td>
				<td colspan="3"><textarea rows="5" cols="88" name="categoryDescription" id="categoryDescription" class="common"><s:property value='categoryDescription' /></textarea></td>
			</tr>
			<tr>
				<td height="30" colspan="4"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="4" height="33">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('categoryForm','category!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('categoryForm','category!parse.action')" type="button" id="parse" name="parse" value="Parse" class="button"></td>
						<td><input onClick="javascript:doSubmit('categoryForm','category!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
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