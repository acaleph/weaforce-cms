<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	if ($('#departmentIsActive').val() == '1'){$('#setActive').attr('checked','true');}
});
</script>
<title>Department input</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a
			href="department.action">部门字典域</a>-&gt;维护部门</td>
	</tr>
</table>
<form id="departmentForm" name="departmentForm" action="department!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp;<img src="/common/image/title-icon0.gif" width="16" height="13">&nbsp;部门信息</td>
	</tr>
	<tr>
		<td width="100%" align="center"><input type="hidden"
			id="departmentId" name="departmentId" value="<s:property value='departmentId' />" />
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="stripe">
			<tr>
				<td height="30" width="15%">代码:</td>
				<td><input id="departmentCode" name="departmentCode" value="<s:property value='departmentCode' />" class="required"></td>
				<td>帐套:</td>
				<td><input id="account" name="account" value="<s:property value='account' />" readonly="readonly" ></td>
			</tr>
			<tr>
				<td height="30">名称:</td>
				<td><input id="departmentName" name="departmentName" value="<s:property value='departmentName' />" class="required"></td>
				<td>有效:</td>
				<td>
					<input type="hidden" id="departmentIsActive" name="departmentIsActive" size="1" value="<s:property value='departmentIsActive' />"> 
					<input type="checkbox" id="setActive" name="setActive" onclick="javascript:(this.checked)? document.departmentForm.departmentIsActive.value=1:document.departmentForm.departmentIsActive.value=0;" />
				</td>
			</tr>
			<tr>
				<td height="30">位置:</td>
				<td colspan="3"><input id="departmentLocation" name="departmentLocation" value="<s:property value='departmentLocation' />" size="80" /></td>
			</tr>
			<tr>
				<td height="30">描述:</td>
				<td height="30" colspan="3"><textarea id="departmentDescription" name="departmentDescription" rows="5" cols="98"><s:property value="departmentDescription" /></textarea></td>
			</tr>
			<tr>
				<td height="30" colspan="4"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="4" height="33">
					<table>
						<tr>
							<security:authorize ifAnyGranted="ROLE_CONE_CRUD_USER">
							<td><input onClick="javascript:doSubmit('departmentForm','department!save.action')"
								type="button" id="save" name="save" value="Save" class="button">
							</td>
							<td><input onClick="javascript:doSubmit('departmentForm','department!delete.action')"
								type="button" id="delete" name="delete" value="Delete" class="button">
							</td>
							</security:authorize>
							<td><input onClick="javascript:window.history.back();"
								type="button" id="return" name="return" value="Return" class="button">
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>