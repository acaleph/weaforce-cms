<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Board</title>
<script type="text/javascript">
$(document).ready(function(){
	$("#queryDate").datepicker({dateFormat: $.datepicker.W3C}).attr("readonly", "readonly");
	$('#table-stripe tr:not([th]):odd').addClass('odd');
	$('#table-stripe tr:not([th]):even').addClass('even');
		
	$("#table-stripe tr:not([th])").mouseover(function(){  
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
		<td valign="middle" height="29" align="left">&nbsp;<img
			src="/common/image/point.gif" width="5" height="6">&nbsp;系统导航-&gt;公告板</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="board!list.action"
	method="post" name="listPage">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg"
	align="center" width="98%">
	<tr align="center">
		<td>标题:</td>
		<td><input id="queryTitle" name="queryTitle"
			value="<s:property value="queryTitle" />" class="queryTitle"></td>
		<td>日期:</td>
		<td><input id="queryDate" name="queryDate"
			value="<s:property value="queryDate" />" class="queryDate"></td>
		<td><input type="button" id="search" name="search" value="Search"
			onClick="javascript:doSubmit('listPage','board!list.action')"></td>
		<td><input type="button" id="create" name="create" value="create"
			onClick="javascript:doSubmit('listPage','board!input.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1"
	class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>标题</th>
		<th>日期</th>
		<th>创建者</th>
		<th width="23" height="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr>
			<td align="center"><img class="pointerImage" border="0"
				title="Edit" src="/common/image/edit_inline.gif"
				onClick="javascript:doSubmit('listPage','board!input.action?boardId=<s:property  value='boardId' />');"></td>
			<td><s:property value="boardTitle" /></td>
			<td><s:property value="boardDateDate" /></td>
			<td><s:property value="creator" /></td>
			<td align="center"><s:if test="%{currentLogin == creator}"><img class="pointerImage" border="0" title="Delete"
				src="/common/image/delete_inline.gif"
				onClick="javascript:if (confirm('是否要删除当前公告?')) doSubmit('listPage','board!delete.action?boardId=<s:property  value='boardId' />');"></s:if></td>
		</tr>
	</s:iterator>
</table>
<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
	<tr valign="top"><td><y:pages value="pageInfo" beanName="pageInfo" formName="listPage" /></td></tr>
</TABLE>
</form>
</body>
</html>