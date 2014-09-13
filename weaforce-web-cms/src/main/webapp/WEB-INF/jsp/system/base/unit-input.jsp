<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Unit input</title>
<script type="text/javascript"> 
$(document).ready(function(){
	if ($('#unitIsActive').val() == '1'){$('#setActive').attr('checked','true');}
})
</script>
</head>
<body>
<table border="0" align="center" width="100%"class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="unit.action">单位字典域</a>-&gt;单位字典维护</td>
	</tr>
</table>
<form id="unitForm" name="unitForm" action="unit!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1"
	class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp; 字典信息</td>
	</tr>
	<tr>
		<td width="100%" align="center"><input type="hidden" id="unitId" name="unitId" value="<s:property value='unitId' />" />
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="stripe">
			<tr>
				<td height="30" width="15%">代码:</td>
				<td height="30"><input id="unitCode" name="unitCode" value="<s:property value='unitCode' />" class="required"></td>
			</tr>
			<tr>
				<td height="30">有效:</td>
				<td height="30">
					<input type="hidden" id="unitIsActive" name="unitIsActive" size="1" value="<s:property value='unitIsActive' />"> 
					<input type="checkbox" id="setActive" name="setActive" onclick="javascript:(this.checked)? document.unitForm.unitIsActive.value=1:document.unitForm.unitIsActive.value=0;" >
				</td>
			</tr>
			<tr>
				<td height="30">名称:</td>
				<td height="30" colspan="3"><input id="unitName" name="unitName" value="<s:property value='unitName' />" class="required"></td>

			</tr>
			<tr>
				<td height="30">描述:</td>
				<td height="30" colspan="3"><textarea id="unitDescription" name="unitDescription" rows="5" cols="98"><s:property value="unitDescription" /></textarea></td>
			</tr>
			<tr>
				<td height="30" colspan="2"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="2" height="33">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('unitForm','unit!save.action')" type="button" name="Save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('unitForm','unit!delete.action')" type="button" name="Delete" value="Delete" class="button"></td>
						<td><input onClick="javascript:window.history.back();" type="button" name="Return" value="Return" class="button"></td>
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