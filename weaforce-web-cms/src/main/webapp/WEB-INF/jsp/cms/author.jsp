<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Author</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#table-stripe tr:odd').addClass('odd');
	$('#table-stripe tr:even').addClass('even');
		
	$("#table-stripe tr").mouseover(function(){  
        //如果鼠标移到class为stripe的表格的tr上时，执行函数
		$(this).addClass("over");}).mouseout(function(){ 
        //给这行添加class值为over，并且当鼠标一出该行时执行函数
        $(this).removeClass("over");
	})  //移除该行的class
});
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;文章作者</td>
	</tr>
</table>
<div class="spacer-2"></div>
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="right">
		<td><input type="button" id="create" name="create" value="create" onClick="javascript:doSubmit('listPage','author!input.action')"></td>
	</tr>
</table>
<form id="listPage" name="listPage" action="author.action" method="post">
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" align="center" width="98%" class="stripe">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>名称</th>
		<th>邮件</th>
		<th width="23" height="23"></th>
	</tr>
	<s:iterator id="author" value="pageInfo.result">
		<tr>
			<td height="23" align="center"><img class="pointerImage" border="0"
				title="Edit" src="/common/image/edit_inline.gif"
				onClick="javascript:doSubmit('listPage','author!input.action?authorId=<s:property  value='authorId' />');"></td>
			<td><s:property value="authorName" /></td>
			<td><s:property value="authorEmail" /></td>
			<td align="center"><img class="pointerImage" border="0" title="Delete"
				src="/common/image/delete_inline.gif"
				onClick="javascript:if (confirm('是否要删除当前内容?')) doSubmit('listPage','author!delete.action?authorId=<s:property  value='authorId' />');"></td>
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