<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	//$('#theTable > th').parent().addClass('table-head');
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
<title>Topic</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;主题管理</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="post.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr>
		<td>论坛:</td>
		<td><s:select id="forumId" name="forumId" value="%{forumId}" list="forumList" listKey="forumId" listValue="forumName" /></td>
		<td>名称:</td>
		<td><input id="queryTitle" name="queryTitle" value="<s:property value="queryTitle" />" class="text"></td>
		<td><input type="button" name="search" value="Search" onClick="javascript:doSubmit('listPage','topic.action')"></td>
		<td><input type="button" name="create" value="Create" onClick="javascript:doSubmit('listPage','topic!input.action')"></td>
		<td><input type="button" name="parse" value="Parse" onClick="javascript:doSubmit('listPage','topic!parse.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th height="23" width="23"></th>
		<th>标题</th>
		<th>浏览</th>
		<th>回复</th>
		<th>贴子</th>
		<th height="23" width="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr>
			<td height="23" width="23" align="center"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','topic!input.action?topicId=<s:property  value='postId' />');"></td>
			<td><s:property value="postTitle" /></td>
			<td><s:property value="postTotalView" /></td>
			<td><s:property value="postTotalReply" /></td>
			<td><a href="post.action?topicId=<s:property value='postId' />">查询</a></td>
			<td width="23" align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('当前主题将被删除,是否继续?')) doSubmit('listPage','topic!delete.action?topicId=<s:property  value='postId' />');"></td>
		</tr>
	</s:iterator>
</table>
<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
	<tr valign="top"><td><y:pages value="pageInfo" beanName="pageInfo" formName="listPage" /></td></tr>
</TABLE>
</form>
</body>
</html>