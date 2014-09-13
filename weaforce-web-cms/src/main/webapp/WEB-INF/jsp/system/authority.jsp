<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
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
});
function authority(flag,authorityId){
	//alert(flag + " resourceId: " + resourceId);
	if (flag == 1)
		$.post("/system/authority!checked.action",{"authorityId":authorityId,"roleId":$('#roleId').val()});
	if (flag == 0)
		$.post("/system/authority!uncheck.action",{"authorityId":authorityId,"roleId":$('#roleId').val()});
}
</script>
<title>Authority</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;授权管理</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="authority.action" method="post">
<table id="searchbg" border="0" cellpadding="0" cellspacing="1" align="center" width="98%">
	<tr align="center">
		<td>系统:</td>
		<td>
			<s:select id="businessId" name="businessId" list="businessList" listKey="businessId" listValue="businessNameCn"  />
		</td>
		<td>角色:</td>
		<td>
			<s:select id="roleId" name="roleId" list="roleList" listKey="roleId" listValue="roleName"  />
		</td>
		<td>代码:<input id="queryAuthorityCode" name="queryAuthorityCode"
			value="<s:property value='queryAuthorityCode' />" class="text"></td>
		<td><input type="button" name="search" value="Search" onClick="javascript:doSubmit('listPage','authority!list.action')"></td>
		<td><input type="button" id="create" name="create" value="Create" onClick="javascript:doSubmit('listPage','authority!input.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1"  align="center" width="98%" class="stripe">
	<tr align="center" class="table-head">
		<th height="23"></th>
		<th>角色</th>
		<th>名称</th>
		<th>代码</th>
		<th>描述</th>
		<th></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr align="left">
			<td height="23"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','authority!input.action?authorityId=<s:property  value='authorityId' />');"></td>
			<td>
				<s:if test="%{checked == 1}">
					<input type="checkbox" id="<s:property value='authorityId' />" value="<s:property value='authorityId' />" checked="checked" readonly="readonly" onclick="javascript:(this.checked)? authority(1,this.value):authority(0,this.value)">
				</s:if><s:if test="%{checked == 0}">
					<input id="<s:property value='authorityId' />" value="<s:property value='authorityId' />" type="checkbox" readonly="readonly" onclick="javascript:(this.checked)? authority(1,this.value):authority(0,this.value)">
				</s:if>
			</td>
			<td><s:property value="authorityName" /></td>
			<td><s:property value="authorityCode" /></td>
			<td><s:property value="authorityDescription" /></td>
			<td><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('当前授权将被删除,是否继续?')) doSubmit('listPage','authority!delete.action?authorityId=<s:property  value='authorityId' />');"></td>
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