<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>User List</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#theTable tr:not([th]):odd').addClass('odd');
	$('#theTable tr:not([th]):even').addClass('even');
		
	$("#theTable tr:not([th])").mouseover(function(){  
        //如果鼠标移到class为stripe的表格的tr上时，执行函数
		$(this).addClass("over");}).mouseout(function(){ 
        //给这行添加class值为over，并且当鼠标一出该行时执行函数
        $(this).removeClass("over");
	})  //移除该行的class
	if ($('#queryLogicLock').val() == '1'){$('#setLock').attr('checked','true');};
});
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img
			src="/common/image/point.gif" width="5" height="6">&nbsp;系统导航-&gt;用户管理</td>
	</tr>
</table>
<div class="spacer-10"></div>
<form id="listPage" name="listPage" action="user!list.action"
	method="post" name="listPage">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg"
	align="center" width="98%">
	<tr align="center">
		<td height="30">角色:</td>
		<td><s:select name="queryRoleId" value="%{queryRoleId}" list="roleList" listKey="roleId" listValue="roleName" required="true" /></td>
		<td>英文名称:</td>
		<td><input id="userQueryNameEn" name="userQueryNameEn"
			value="<s:property value="userQueryNameEn" />" class="text"></td>
		<td>中文名称:</td>
		<td><input id="userQueryNameCn" name="userQueryNameCn" value="<s:property value="userQueryNameCn" />" class="text"></td>
		<security:authorize ifAnyGranted="ROLE_DATA_ADMIN_USER">
			<td>加锁</td>
			<td><input type="hidden" id="queryLogicLock"
				name="queryLogicLock" size="1"
				value="<s:property value='queryLogicLock' />"> <input
				type="checkbox" id="setLock" name="setLock"
				onclick="javascript:(this.checked)? document.listPage.queryLogicLock.value=1:document.listPage.queryLogicLock.value=0;"></td>
		</security:authorize>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','user!list.action')"></td>
		<td><input type="button" id="create" name="create" value="create" onClick="javascript:doSubmit('listPage','user!input.action')"></td>
	</tr>
</table>
<div class="spacer-10"></div>
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>中文名称</th>
		<th>英文名称</th>
		<th>登录ID</th>
		<th>系统用户</th>
		<th>角色</th>
		<th>默认角色</th>
		<th>创建日期</th>
		<th width="23" height="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr>
			<td height="23" align="center"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','user!input.action?userId=<s:property  value='userId' />');"></td>
			<td><s:property value="userNameCn" /></td>
			<td><s:property value="userNameEn" /></td>
			<td><s:property value="userLogin" /></td>
			<td align="center"><s:if test="%{userIsSystem==1}">Yes</s:if><s:else>No</s:else></td>
			<td><s:property value="userRoleNames" /></td>
			<td><s:property value="defaultUserRole.roleName" /></td>
			<td><s:property value="date" /></td>
			<td align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('是否要删除当前用户(此用户将被锁定，不允许登录系统)?')) doSubmit('listPage','user!delete.action?userId=<s:property  value='userId' />');"></td>
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