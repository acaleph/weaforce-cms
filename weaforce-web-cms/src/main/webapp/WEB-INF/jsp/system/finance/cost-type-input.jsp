<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Insert title here</title>
<script type="text/javascript"> 
$(document).ready(function(){
	$('#typeForm').validate();
	if ($('#typeIsActive').val() == '1'){$('#setActive').attr('checked','true');}
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a
			href="cost-type.action">费用字典域</a>-&gt;费用维护</td>
	</tr>
</table>
<form id="typeForm" name="typeForm" action="cost-type!save.action"
	method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1"
	class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp;<img src="/common/image/title-icon0.gif" width="16" height="13">&nbsp;类型信息</td>
	</tr>
	<tr>
		<td width="100%" align="center"><input type="hidden" id="typeId"
			name="typeId" value="<s:property value='typeId' />" />
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="stripe" bgcolor="#FFFFFF">
			<tr bgcolor="#FFFFFF">
				<td height="30" width="15%">名称:</td>
				<td height="30"><input id="typeName" name="typeName"
					value="<s:property value='typeName' />" class="required"></td>
				<td height="30">有效:</td>
				<td height="30"><input type="hidden" id="typeIsActive"
					name="typeIsActive" size="1"
					value="<s:property value='typeIsActive' />"> <input
					type="checkbox" id="setActive" name="setActive"
					onclick="javascript:(this.checked)? document.typeForm.typeIsActive.value=1:document.typeForm.typeIsActive.value=0;">
				</td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td height="30">描述:</td>
				<td height="30" colspan="3"><textarea id="typeDescription"
					name="typeDescription" rows="5" cols="98"><s:property
					value="typeDescription" /></textarea></td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td height="30" colspan="4">
				<hr size="1">
				</td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td colspan="4" height="33">
				<table>
					<tr>
						<security:authorize ifAnyGranted="ROLE_CMS_CRUD_USER">
						<td><input
							onClick="javascript:doSubmit('typeForm','cost-type!save.action')"
							type="button" id="save" name="save" value="Save" class="button"></td>
						</security:authorize>
						<security:authorize ifAnyGranted="ROLE_CONE_CRUD_USER">
						<td><input
							onClick="javascript:doSubmit('typeForm','cost-type!delete.action')"
							type="button" id="delete" name="delete" value="Delete" class="button"></td>
						</security:authorize>
						<td><input onClick="javascript:window.history.back();"
							type="button" name="Return" value="Return" class="button"></td>
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