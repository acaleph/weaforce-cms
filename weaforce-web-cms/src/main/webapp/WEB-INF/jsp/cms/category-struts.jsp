<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	//$('#table-stripe > th').parent().addClass('table-head');
	$('#table-stripe tr:not([th]):odd').addClass('odd');
	$('#table-stripe tr:not([th]):even').addClass('even');
		
	$("#table-stripe tr:not([th])").mouseover(function(){  
        //如果鼠标移到class为stripe的表格的tr上时，执行函数
		$(this).addClass("over");}).mouseout(function(){ 
        //给这行添加class值为over，并且当鼠标一出该行时执行函数
        $(this).removeClass("over");
	})  //移除该行的class
})
</script>
<title>Category</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;栏目字典域</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="category!list.action"
	method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="center">
		<td>频道:</td>
		<td><s:select id="channelId" name="channelId" value="%{channelId}" list="channelList" listKey="channelId" listValue="channelName" /></td>
		<td>名称:<input id="queryName" name="queryName" value="<s:property value="queryName" />" class="text"></td>
		<td><input type="button" name="search" value="Search" onClick="javascript:doSubmit('listPage','category!list.action')"></td>
		<td><input type="button" name="create" value="create" onClick="javascript:doSubmit('listPage','category!input.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>类型</th>
		<th>发布</th>
		<th>模板</th>
		<th>名称</th>
		<th>URL名称</th>
		<th>文件名</th>
		<th>创建日期</th>
		<th>创建者</th>
		<th width="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr>
			<td height="23" align="center"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','category!input.action?categoryId=<s:property  value='categoryId' />');"></td>
			<td><s:if test="%{categoryParseType==0}">频道</s:if>
				<s:if test="%{categoryParseType==1}">类频道</s:if>
				<s:if test="%{categoryParseType==2}">文章</s:if>
				<s:if test="%{categoryParseType==3}">列表文本</s:if>
				<s:if test="%{categoryParseType==4}">列表图片</s:if>
			</td>
			<td><img class="pointerImage" border="0" title="发布" src="/common/image/reset_inline.gif" onClick="javascript:if (confirm('是否要发布(parse)文章?')) doSubmit('listPage','category!parse.action?categoryId=<s:property  value='categoryId' />');"></td>
			<td><s:property value="categoryTemplate.templateName" /></td>
			<td><s:property value="categoryName" /></td>
			<td><a href="<s:property value='categoryUrl' />" target="_blank">View</a></td>
			<td><s:property value="categoryParseName" />.<s:property value="categoryParseNameExt" /></td>
			<td><s:property value="date" /></td>
			<td><s:property value="creator" /></td>
			<td align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('当前栏目将被删除,是否继续?')) doSubmit('listPage','category!delete.action?categoryId=<s:property  value='categoryId' />');"></td>
		</tr>
	</s:iterator>
</table>

<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
	<tr valign="top"><td><y:pages value="pageInfo" beanName="pageInfo" formName="listPage" /></td></tr>
</TABLE>
</form>
</body>
</html>