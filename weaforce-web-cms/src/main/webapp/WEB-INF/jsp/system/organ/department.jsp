<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>部门管理</title>
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
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;部门字典域</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="department!list.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="center">
		<td>名称:</td>
		<td><input name="departmentQueryName" value="<s:property value="departmentQueryName" />" class="text"></td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','department!list.action')"></td>
		<security:authorize ifAnyGranted="ROLE_CONE_CRUD_USER">
		<td><input type="button" id="create" name="create" value="create" onClick="javascript:doSubmit('listPage','department!input.action')"></td>
		</security:authorize>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th height="23" width="23"></th>
		<th>代码</th>
		<th>名称</th>
		<th>有效</th>
		<th width="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr>
			<td align="center"><security:authorize ifAnyGranted="ROLE_CONE_CRUD_USER"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','department!input.action?departmentId=<s:property  value='departmentId' />');"></security:authorize></td>
			<td><s:property value="departmentCode" /></td>
			<td><s:property value="departmentName" /></td>
			<td align="center"><s:if test="%{departmentIsActive == 1}">
				<input type="checkbox" checked="checked" readonly="readonly">
			</s:if><s:else>
				<input type="checkbox" readonly="readonly">
			</s:else></td>
			<td align="center"><security:authorize ifAnyGranted="ROLE_CONE_CRUD_USER"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('是否要删除当前部门?')) doSubmit('listPage','department!delete.action?departmentId=<s:property  value='departmentId' />');"></security:authorize></td>
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