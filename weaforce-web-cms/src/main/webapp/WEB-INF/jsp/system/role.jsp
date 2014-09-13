<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>全局角色和本帐套角色管理</title>
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
	if ($('#queryActive').val() == '1'){$('#setActive').attr('checked','true');}
})
</script>
</head>
<body bgcolor="white">
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;角色管理</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="role!list.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg"
	align="center" width="98%">
	<tr align="center">
		<td>英文名称:<input id="queryName" name="queryName" value="<s:property value="queryName" />" class="text"></td>
		<td>活动：</td>
		<td>
			<input type="hidden" id="queryActive" name="queryActive" size="1" value="<s:property value='queryActive' />"> 
			<input type="checkbox" id="setActive" name="setActive" onclick="javascript:(this.checked)? document.listPage.queryActive.value=1:document.listPage.queryActive.value=0;">
		</td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','role!list.action')"></td>
		<security:authorize ifAnyGranted="ROLE_CONE_CRUD_USER">
		<td><input type="button" id="create" name="create" value="create" onClick="javascript:doSubmit('listPage','role!input.action')"></td>
		</security:authorize>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<th width="23" height="23"></th>
		<th>名称</th>
		<th>系统</th>
		<th>状态</th>
		<th>创建时间</th>
		<th width="23">重置</th>
		<th width="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr>
			<td height="23" align="center">
				<img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','role!input.action?roleId=<s:property value='roleId' />');">
			</td>
			<td><s:property value="roleName" /></td>
			<td align="center"><s:if test="%{roleIsSystem == 1}">Yes</s:if><s:else>No</s:else></td>
			<td align="center">
				<s:if test="%{roleIsActive == 1}">
					<input type="checkbox" checked="checked" readonly="readonly">
				</s:if><s:else>
					<input type="checkbox" readonly="readonly">
				</s:else>
			</td>
			<td><s:property value="date" /></td>
			<td width="46" align="center">
				<img class="pointerImage" border="0" title="Reset" src="/common/image/reset_inline.gif" onClick="javascript:if (confirm('当前角色菜单项将被重置,是否继续?')) doSubmit('listPage','role!reset.action?roleId=<s:property  value='roleId' />');">
			</td>
			<td align="center">
				<security:authorize ifAnyGranted="ROLE_CONE_CRUD_USER">
					<img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('当前角色将被设置为无效,不能被引用,是否继续?')) doSubmit('listPage','role!delete.action?roleId=<s:property  value='roleId' />');">
				</security:authorize>
			</td>
		</tr>
	</s:iterator>
</table>
<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
	<tr valign="top"><td><y:pages value="pageInfo" beanName="pageInfo" formName="listPage" /></td></tr>
</TABLE>
</form>
</body>
</html>