<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<link rel="stylesheet" type="text/css" href="/common/css/file.css">
<title>File management</title>
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
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;MINI网盘&nbsp;&nbsp;<span id="returnMessage"><font color="red"><s:actionmessage /></font></span></td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="file!list.action" method="POST">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="left">
		<td>系统模块:</td>
		<td><s:select id="queryModuleId" name="queryModuleId" value="%{queryModuleId}" list="moduleList" listKey="moduleId" listValue="moduleNameCn" required="true" /></td>
		<td>文件名称:</td>
		<td><input id="queryLocal" name="queryLocal" value="<s:property value="queryLocal" />" class="text"></td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','file!list.action')"></td>
		<td><input type="button" id="input" name="input" value="Upload" onClick="javascript:doSubmit('listPage','file!input.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1"
	class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th height="23" width="23"></th>
		<th>文件</th>
		<th>版本</th>
		<th width="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr align="left">
			<td height="23" align="center"><img src="<s:property value='iconPath'/>"></td>
			<td><a href="/system/download_servlet?fileServer=<s:property value='fileServer'/>&fileLocal=<s:property value="fileLocal" />"><s:property value="fileLocal" /></a></td>
			<td align="center"><s:property value="fileVersionMax" />-<s:property value="fileVersionMid" />-<s:property value="fileVersionMin" /></td>
			<td align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('是否要删除文件?')) doSubmit('listPage','file!delete.action?fileId=<s:property  value='fileId' />');"></td>
		</tr>
	</s:iterator>
</table>
<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
	<tr valign="top"><td><y:pages value="pageInfo" beanName="pageInfo" formName="listPage" /></td></tr>
</TABLE>
</form>
</body>
</html>