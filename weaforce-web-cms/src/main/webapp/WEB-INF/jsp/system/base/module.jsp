<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Module List</title>
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
<body bgcolor="white">
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;实体字典</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="module!list.action" method="post">
<table id="searchbg" border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="center">
		<td>系统:</td>
		<td><s:select id="businessId" name="businessId" value="%{businessId}" list="businessList" listKey="businessId" listValue="businessNameCn" /></td>
		<td>英文名称:</td>
		<td><input name="moduleQueryNameEn" value="<s:property value="moduleQueryNameEn" />" class="text"></td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','module!list.action')"></td>
		<td><input type="button" id="create"name="create" value="Create" onClick="javascript:doSubmit('listPage','module!input.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th height="23" width="23"></th>
		<th>中文名称</th>
		<th>英文名称</th>
		<th>步长</th>
		<th>下一值</th>
		<th width="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr align="left">
			<td height="23" align="center"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','module!input.action?moduleId=<s:property  value='moduleId' />');"></td>
			<td><s:property value="moduleNameCn" /></td>
			<td><s:property value="moduleNameEn" /></td>
			<td><s:property value="moduleStep" /></td>
			<td><s:property value="moduleNext" /></td>
			<td align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('是否要删除当前模块?')) doSubmit('listPage','module!delete.action?moduleId=<s:property  value='moduleId' />');"></td>
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
