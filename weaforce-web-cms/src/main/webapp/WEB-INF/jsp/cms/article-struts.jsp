<%@page contentType="text/html; charset=UTF-8" errorPage=""%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$("#queryDateFrom").datepicker({dateFormat: $.datepicker.W3C}).attr("readonly", "readonly");
	$("#queryDateTo").datepicker({dateFormat: $.datepicker.W3C}).attr("readonly", "readonly");
	$('#table-stripe tr:not([th]):odd').addClass('odd');
	$('#table-stripe tr:not([th]):even').addClass('even');
		
	$("#table-stripe tr:not([th])").mouseover(function(){  
        //如果鼠标移到class为stripe的表格的tr上时，执行函数
		$(this).addClass("over");}).mouseout(function(){ 
        //给这行添加class值为over，并且当鼠标一出该行时执行函数
        $(this).removeClass("over");
	})  //移除该行的class
	$('#channelId').change(function(){
		$('#categoryId').buildSelect($('#categoryId'),{selected:0,url:"/cms/category!getCategoryDDL.action",data:{'channelId':$(this).val()}},function(){});
	})
})
</script>
<title>文章管理</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;文章管理</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="article!list.action" method="POST">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="center">
		<td>频道:</td>
		<td><s:select id="channelId" name="channelId" value="%{channelId}" list="channelList" listKey="channelId" listValue="channelName" /></td>
		<td>栏目:</td>
		<td><s:select id="categoryId" name="categoryId" value="%{categoryId}" list="categoryList" listKey="categoryId" listValue="categoryName" /></td>
		<td>标题:</td>
		<td><input id="queryTitle" name="queryTitle" value="<s:property value='queryTitle'/>"></td>
		<td>开始:</td>
		<td><input id="queryDateFrom" name="queryDateFrom" value="<s:property value="queryDateFrom" />" size="10" class="text"></td>
		<td>终止:</td>
		<td><input id="queryDateTo" name="queryDateTo" value="<s:property value="queryDateTo" />" size="10" class="text"></td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','article!list.action')"></td>
		<td><input type="button" id="create" name="create" value="Create" onClick="javascript:doSubmit('listPage','article!input.action')"></td>
		<td><input type="button" id="parse" name="parse" value="Parse" onClick="javascript:doSubmit('listPage','article!parseAll.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>标题</th>
		<th>创建时间</th>
		<th>发布</th>
		<th>创建人</th>
		<th>查看</th>
		<th>关联</th>
		<th>像册</th>
		<th width="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr>
			<td height="23" align="center"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','article!input.action?articleId=<s:property  value='articleId' />');"></td>
			<td align="left"><s:property value="articleTitle" /></td>
			<td align="left"><s:property value="date" /></td>
			<td align="center"><img class="pointerImage" border="0" title="自定义页" src="/common/image/reset_inline.gif" onClick="javascript:if (confirm('是否要转发(page-link)文章?')) doSubmit('listPage','page-link!article.action?articleId=<s:property  value='articleId' />');">
			<s:if test="%{articleIsParse == 1}">
				<input type="checkbox" checked="checked" readonly="readonly">
				<img class="pointerImage" border="0" title="取消发布" src="/common/image/reset_inline.gif" onClick="javascript:if (confirm('是否要解除发布文章?')) doSubmit('listPage','article!unparse.action?articleId=<s:property  value='articleId' />');">
			</s:if><s:if test="%{articleIsParse == 0}">
				<input type="checkbox" readonly="readonly">
				<img class="pointerImage" border="0" title="发布" src="/common/image/reset_inline.gif" onClick="javascript:if (confirm('是否要发布(parse)文章?')) doSubmit('listPage','article!parse.action?articleId=<s:property  value='articleId' />');">
			</s:if></td>
			<td align="left"><s:property value="creator" /></td>
			<td><a href="<s:property value="articleUrl" />" target="_blank">预览</a></td>
			<td align="center"><img class="pointerImage" border="0" title="Relation" src="/common/image/zoom-in.gif" onClick="javascript:doSubmit('listPage','article!relation.action?articleId=<s:property  value='articleId' />');"></td>
			<td align="center"><img class="pointerImage" border="0" title="Relation" src="/common/image/zoom-in.gif" onClick="javascript:doSubmit('listPage','article!album.action?articleId=<s:property  value='articleId' />');"></td>
			<td align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('当前文章将被删除,是否继续?')) doSubmit('listPage','article!delete.action?articleId=<s:property  value='articleId' />');"></td>
		</tr>
	</s:iterator>
</table>
<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
	<tr valign="top">
		<td><y:pages value="pageInfo" beanName="pageInfo" formName="listPage" /></td>
	</tr>
</TABLE>
</form>
</body>
</html>