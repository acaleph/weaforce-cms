<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Account List</title>
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
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;系统帐号</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="account!list.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="center">
		<td>英文名称:<input name="queryAccountNameEn" value="<s:property value="queryAccountNameEn" />" class="text"></td>
		<td>中文名称:<input name="queryAccountNameCn" value="<s:property value="queryAccountNameCn" />" class="text"></td>
		<td>简称:<input name="queryAccountShortName" value="<s:property value="queryAccountShortName" />" class="text"></td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','account.action')"></td>
		<td><input type="button" id="create" name="create" value="Create" onClick="javascript:doSubmit('listPage','account!input.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>简称</th>
		<th>联系人</th>
		<th>电话</th>
		<th>WEB</th>
		<th>EMAIL</th>
		<th>C/P/R</th>
		<th>活动</th>
		<th width="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr>
			<td height="23" align="center"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','account!input.action?accountId=<s:property  value='accountId' />');"></td>
			<td><s:property value="accountShortName" /></td>
			<td><s:property value="accountDefaultContact" /></td>
			<td><s:property value="accountPhone" /></td>
			<td><s:property value="accountWeb" /></td>
			<td><s:property value="accountEmail" /></td>
			<td align="center">
			<img class="pointerImage" border="0" title="客户" src="/common/image/reset_inline.gif" onClick="javascript:if (confirm('是否要创建客户?')) doSubmit('listPage','/system/logistics/customer!input.action?accountId=<s:property  value='accountId' />');">
			<img class="pointerImage" border="0" title="供应商" src="/common/image/reset_inline.gif" onClick="javascript:if (confirm('是否要创建供应商?')) doSubmit('listPage','/system/logistics/provider!input.action?accountId=<s:property  value='accountId' />');">
			<img class="pointerImage" border="0" title="生产商" src="/common/image/reset_inline.gif" onClick="javascript:if (confirm('是否要创建供应商?')) doSubmit('listPage','/system/logistics/producer!input.action?accountId=<s:property  value='accountId' />');">
			</td>
			<td align="center">
			<s:if test="%{accountIsActive == 1}">
				<input type="checkbox" checked="checked" readonly="readonly">
			</s:if><s:if test="%{accountIsActive == 0}">
				<input type="checkbox" readonly="readonly">
			</s:if>
			</td>
			<td align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('是否要删除当前帐套?')) doSubmit('listPage','account!delete.action?accountId=<s:property  value='accountId' />');"></td>
		</tr>
	</s:iterator>
</table>
<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
	<tr valign="top"><td><y:pages value="pageInfo" beanName="pageInfo" formName="forms(0)" /></td></tr>
</TABLE>
</form>
</body>
</html>