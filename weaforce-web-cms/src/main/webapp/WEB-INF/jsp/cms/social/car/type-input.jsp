<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Car share type</title>
<script type="text/javascript"> 
$(document).ready(function(){
	$('#typeForm').validate();
})
</script>
</head>
<body>
<table border="0" align="center" width="98%" class="stripe">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="type.action">类型字典域</a>-&gt;类型维护</td>
	</tr>
</table>
<form id="typeForm" name="typeForm" action="type!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp; 字典信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="typeId" name="typeId" value="<s:property value='typeId' />"></td>
			</tr>
			<tr>
				<td height="30">名称:</td>
				<td><input id="typeName" name="typeName" value="<s:property value='typeName' />" class="required"></td>
			</tr>
			<tr>
				<td height="30" colspan="4"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="4" height="33">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('typeForm','type!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('typeForm','type!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
						<td><input onClick="javascript:window.history.back();" type="button" id="return" name="return" value="Return" class="button"></td>
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