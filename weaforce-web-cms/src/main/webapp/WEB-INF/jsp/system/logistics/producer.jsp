<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Producer</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#theTable tr:odd').addClass('odd');
	$('#theTable tr:even').addClass('even');
		
	$("#theTable tr").mouseover(function(){
        //如果鼠标移到class为stripe的表格的tr上时，执行函数
		$(this).addClass("over");}).mouseout(function(){ 
        //给这行添加class值为over，并且当鼠标一出该行时执行函数
        $(this).removeClass("over");
	})  //移除该行的class
})
</script>
</head>
<body>
<table border="0" align="center" width="100%"  class="navigator-title">
	<tr>
		<td height="29">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;供应商信息</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="producer!list.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr>
		<td>名称:</td>
		<td><input id="queryName" name="queryName" value="<s:property value="queryName" />" class="text"></td>
		<td>代码:</td>
		<td><input id="queryCode" name="queryCode" value="<s:property value="queryCode" />" class="text"></td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','producer!list.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="theTable" border="0" cellpadding="0" cellspacing="1"
	class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>中文名称</th>
		<th>英文名称</th>
		<th>简称</th>
		<th>日期</th>
		<th>描述</th>
		<th width="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr align="left">
			<td height="23" align="center"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','producer!input.action?producerId=<s:property  value='producerId' />');"></td>
			<td><s:property value="producerAccount.accountNameCn" /></td>
			<td><s:property value="producerAccount.accountNameEn" /></td>
			<td><s:property value="producerShotName" /></td>
			<td><s:property value="date" /></td>
			<td><s:property value="producerDescription" /></td>
			<td align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('是否要删除当前帐套?')) doSubmit('listPage','producer!delete.action?producerId=<s:property  value='producerId' />');"></td>
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