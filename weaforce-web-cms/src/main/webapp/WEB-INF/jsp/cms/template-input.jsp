<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript"> 
$(document).ready(function(){
	if ($('#templateIsActive').val() == '1'){$('#setActive').attr('checked','true');}
})
</script>
<title>Template Input</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="template.action">模板字典域</a>-&gt;维护模板</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="templateForm" name="templateForm" action="template!save.action" method="POST">
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr height="20">
		<td class="table-head" colspan="8">模板信息</td>
	</tr>
	<tr>
		<td><input type="hidden" id="templateId" name="templateId" value="<s:property value='templateId'/>"></td>
	</tr>
	<tr>
		<td height="30">名称:<font color=red>*</font>:</td>
		<td><input id="templateName" name="templateName" value="<s:property value='templateName'/>" size="38" ></td>
		<td>关键字:<font color=red>*</font>:</td>
		<td><input id="templateKey" name="templateKey" value="<s:property value='templateKey'/>" ></td>
		<td>有效:</td>
		<td>
			<input type="hidden" id="templateIsActive" name="templateIsActive" size="1" value="<s:property value='templateIsActive' />"> 
			<input type="checkbox" id="setActive" name="setActive" onclick="javascript:(this.checked)? document.templateForm.templateIsActive.value=1:document.templateForm.templateIsActive.value=0;">
		</td>
		<td></td>
		<td>
		</td>
	</tr>
	<tr>
		<td>说明:<font color="red">*</font>:</td>
		<td colspan="7"><textarea id="templateDescription" name="templateDescription" cols="98" rows="3"><s:property value='templateDescription' /></textarea></td>
	</tr>

	<tr>
		<td>内容:</td>
		<td colspan="7"><textarea rows="23" cols="98" name="templateContent" id="templateContent" ><s:property value='templateContent' /></textarea></td>
	</tr>
	<tr><td height="16" colspan="8"><hr size="1"></td></tr>
	<tr>
		<td colspan="8" height="28">
		<table>
			<tr>
				<td><input onClick="javascript:doSubmit('templateForm','template!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
				<td><input onClick="javascript:doSubmit('templateForm','template!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
				<td><input onClick="javascript:doSubmit('templateForm','template!lock.action')" type="button" id="lock" name="lock" value="Lock" class="button"></td>
				<td><input onClick="javascript:window.history.back();" type="button" id="return" name="return" value="Return" class="button"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>