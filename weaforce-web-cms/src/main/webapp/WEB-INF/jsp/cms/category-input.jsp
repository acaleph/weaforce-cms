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
	if ($('#categoryWebOrMobile').val() == '1'){$('#setWebOrMobile').attr('checked','true');}
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
<table id="theTable" border="0" cellpadding="0" cellspacing="1"
	class="stripe" align="center" width="98%">
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
				<td>频道<font color="red">*</font>:</td>
				<td><s:select id="channelId" name="channelId" value="%{categoryChannel.channelId}" list="channelList" listKey="channelId" listValue="channelName" cssClass="required" /></td>
				<td>栏目模板<font color="red">*</font>:</td>
				<td><s:select id="templateId" name="templateId" value="%{categoryTemplate.templateId}" list="templateList" listKey="templateId" listValue="templateName" cssClass="required" /></td>
				<td>文章模板<font color="red">*</font>:</td>
				<td><s:select id="articleTemplateId" name="articleTemplateId" value="%{categoryArticleTemplate.templateId}" list="templateList" listKey="templateId" listValue="templateName" cssClass="required" /></td>
				<td>Parse类型<font color="red">*</font>:</td>
				<td><select id="categoryParseType" name="categoryParseType">
						<option value="0">频道</option>
						<option value="1">类频道</option>
						<option value="2">文章</option>
						<option value="3">列表文本</option>
						<option value="4">列表图片</option>
					</select>
					<script type="text/javascript">
						document.categoryForm.categoryParseType.value=<s:property value="categoryParseType" />;
					</script>
				</td>
			</tr>
			<tr>
				<td>显示名称<font color="red">*</font>:</td>
				<td><input id="categoryName" name="categoryName" value="<s:property value='categoryName'/>" class="required"></td>
				<td>文件名称<font color="red">*</font>:</td>
				<td><input id="categoryParseName" name="categoryParseName"
					value="<s:property value='categoryParseName'/>" class="required"></td>
				<td>最大文章数<font color="red">*</font>:</td>
				<td><input id="categoryMaxPerPage" name="categoryMaxPerPage" value="<s:property value='categoryMaxPerPage'/>" size="8" class="required number"></td>
				<td>排序:</td>
				<td><input id="categoryOrder" name="categoryOrder" value="<s:property value='categoryOrder' />" size="8" class="required"></td>
			</tr>
			<tr>
				<td>URL<font color="red">*</font>:</td>
				<td><input id="categoryUrl" name="categoryUrl" value="<s:property value='categoryUrl'/>" size="48" class="required"></td>
				<td>打开方式:</td>
				<td><select id="categoryArticleTarget" name="categoryArticleTarget">
						<option value="_blank">新页</option>
						<option value="_self">当前页</option>
					</select>
					<script type="text/javascript">
						document.categoryForm.categoryParseType.value=<s:property value="categoryArticleTarget" />;
					</script>
				</td>
				<td height="30">有效:</td>
				<td>
					<input type="hidden" id="categoryIsActive" name="categoryIsActive" size="1" value="<s:property value='categoryIsActive' />"> 
					<input type="checkbox" id="setActive" name="setActive" onclick="javascript:(this.checked)? document.categoryForm.categoryIsActive.value=1:document.categoryForm.categoryIsActive.value=0;">
				</td>
				<td>是否移动:</td>
				<td>
					<input type="hidden" id="categoryWebOrMobile" name="categoryWebOrMobile" size="1" value="<s:property value='categoryWebOrMobile' />"> 
					<input type="checkbox" id="setWebOrMobile" name="setWebOrMobile" onclick="javascript:(this.checked)? document.templateForm.categoryWebOrMobile.value=1:document.templateForm.categoryWebOrMobile.value=0;">
				</td>
			</tr>
			<tr>
				<td>描述<font color=red>*</font>:</td>
				<td colspan="7"><textarea rows="5" cols="88" name="categoryDescription" id="categoryDescription" class="common"><s:property value='categoryDescription' /></textarea></td>
			</tr>
			<tr>
				<td>单元板板<font color=red>*</font>:</td>
				<td colspan="7"><textarea rows="5" cols="88" name="categoryCellTemplate" id="categoryCellTemplate" class="common"><s:property value='categoryCellTemplate' /></textarea></td>
			</tr>
			<tr>
				<td height="30" colspan="8"><hr size="1"></td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td colspan="8" height="33">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('categoryForm','category!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('categoryForm','category!parse.action')" type="button" id="parse" name="parse" value="Parse" class="button"></td>
						<td><input onClick="javascript:doSubmit('categoryForm','category!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
						<td><input onClick="javascript:doSubmit('categoryForm','category!lock.action')" type="button" id="lock" name="lock" value="Lock" class="button"></td>
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