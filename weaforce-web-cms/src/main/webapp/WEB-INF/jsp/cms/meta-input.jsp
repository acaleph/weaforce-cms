<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#metaForm').validate();
	if ($('#metaIsActive').val() == '1'){$('#setActive').attr('checked','true');}
})
</script>
<title>META Input</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="meta.action">Meta字典域</a>-&gt;维护Meta字典</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="metaForm" name="metaForm" action="meta!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp; 字典信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="metaId" name="metaId" value="<s:property value='metaId' />" ></td>
			</tr>
			<tr>
				<td height="30">名称:</td>
				<td><input id="metaName" name="metaName" value="<s:property value='metaName' />" size="38" class="required"></td>

			</tr>
			<tr>
				<td height="30">键:</td>
				<td><input id="metaKey" name="metaKey" value="<s:property value='metaKey' />" size="38" class="required"></td>
			</tr>
			<tr>
				<td height="30">值:</td>
				<td><input id="metaValue" name="metaValue" value="<s:property value='metaValue' />" size="38" class="required"></td>
			</tr>
			<tr>
				<td height="30">有效:</td>
				<td><input type="hidden" id="metaIsActive" name="metaIsActive" size="1" value="<s:property value='metaIsActive' />"> 
					<input type="checkbox" id="setActive" name="setActive" onclick="javascript:(this.checked)? document.metaForm.metaIsActive.value=1:document.metaForm.metaIsActive.value=0;">
				</td>
			</tr>
			<tr>
				<td height="30">描述:</td>
				<td><textarea id="metaDescription" name="metaDescription" rows="5" cols="88"><s:property value="metaDescription" /></textarea></td>
			</tr>
			<tr>
				<td height="30" colspan="2"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="2" height="33">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('metaForm','meta!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('metaForm','meta!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
						<td><input onClick="javascript:doSubmit('metaForm','meta!lock.action')" type="button" id="lock" name="lock" value="Lock" class="button"></td>
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