<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Member</title>
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
	if ($('#queryLogicLock').val() == '1'){$('#setLock').attr('checked','true');};
});
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;成员字典域</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="staff!list.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="center">
		<td height="30">部门:</td>
		<td><s:select id="departmentId" name="departmentId" value="%{departmentId}" list="departmentList" listKey="departmentId" listValue="departmentName" required="true" /></td>
		<td>姓名:</td>
		<td><input id="queryStaffName" name="queryStaffName" value="<s:property value="queryStaffName" />" class="text"></td>
		<td>登录ID:</td>
		<td><input id="queryStaffLogin" name="queryStaffLogin" value="<s:property value="queryStaffLogin" />" class="text"></td>
		<security:authorize ifAnyGranted="ROLE_DATA_ADMIN_USER">
			<td>加锁</td>
			<td>
				<input type="hidden" id="queryLogicLock" name="queryLogicLock" size="1" value="<s:property value='queryLogicLock' />"> 
				<input type="checkbox" id="setLock" name="setLock" onclick="javascript:(this.checked)? document.listPage.queryLogicLock.value=1:document.listPage.queryLogicLock.value=0;">
			</td>
		</security:authorize>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','staff!list.action')"></td>
		<td><input type="button" id="create" name="create" value="Create" onClick="javascript:doSubmit('listPage','staff!input.action')"></td>
	</tr>
</table>
<div class="spacer-10"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th height="23" width="23"></th>
		<th>部门</th>
		<th>姓名</th>
		<th>中文名称</th>
		<th>有效</th>
		<th width="33">联系人</th>
		<th width="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr align="left">
			<td height="23" align="center">
				<img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','staff!input.action?staffId=<s:property  value='staffId' />');">
			</td>
			<td><s:property value="staffDepartment.departmentName" /></td>
			<td><s:property value="staffName" /></td>
			<td><s:property value="staffLogin" /></td>
			<td align="center"><s:if test="%{staffIsActive == 1}">
				<input type="checkbox" checked="checked" readonly="readonly">
			</s:if><s:else>
				<input type="checkbox" readonly="readonly">
			</s:else></td>
			<td width="33" align="center">
				<img class="pointerImage" border="0" title="Contact" src="/common/image/user_inline.gif" onClick="javascript:doSubmit('listPage','staff!contact.action?staffId=<s:property  value='staffId' />');">
			</td>
			<td width="23" align="center">
				<img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('是否要删除当前部门成员?')) doSubmit('listPage','staff!delete.action?staffId=<s:property  value='staffId' />');">
			</td>
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