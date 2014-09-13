<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	//$('#theTable > th').parent().addClass('table-head');
	$('#theTable tr:not([th]):odd').addClass('odd');
	$('#theTable tr:not([th]):even').addClass('even');
		
	$("#theTable tr:not([th])").mouseover(function(){  
        //如果鼠标移到class为stripe的表格的tr上时，执行函数
		$(this).addClass("over");}).mouseout(function(){ 
        //给这行添加class值为over，并且当鼠标一出该行时执行函数
        $(this).removeClass("over");
	})  //移除该行的class
})
</script>
<title>Meta</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;Meta字典域</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="meta.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="center">
		<td>名称:</td>
		<td><input id="queryMetaKey" name="queryMetaKey" value="<s:property value="queryMetaKey" />" class="text"></td>
		<td>值:</td>
		<td><input id="queryMetaValue" name="queryMetaValue" value="<s:property value="queryMetaValue" />" class="text"></td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','meta.action')"></td>
		<td><input type="button" id="create" name="create" value="create" onClick="javascript:doSubmit('listPage','meta!input.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>名称</th>
		<th>键</th>
		<th>值</th>
		<th>创建日期</th>
		<th>创建者</th>
		<th width="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr align="left">
			<td height="23"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','meta!input.action?metaId=<s:property  value='metaId' />');"></td>
			<td><s:property value="metaName" /></td>
			<td><s:property value="metaKey" /></td>
			<td><s:property value="metaValue" /></td>
			<td><s:property value="date" /></td>
			<td><s:property value="creator" /></td>
			<td><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('当前Meta将被删除,是否继续?')) doSubmit('listPage','meta!delete.action?metaId=<s:property  value='metaId' />');"></td>
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