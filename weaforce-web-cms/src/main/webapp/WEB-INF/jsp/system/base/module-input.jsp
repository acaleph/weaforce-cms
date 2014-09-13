<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#moduleForm').validate();
	if ($('#moduleDictionary').val() == '1'){$('#setDictionary').attr('checked','true');}
	if ($('#moduleAttachment').val() == '1'){$('#setAttachment').attr('checked','true');}
	if ($('#moduleIsResource').val() == '1'){$('#setResource').attr('checked','true');}
})
</script>
<title>Entity Maintenance Form</title>
</head>
<body>
<table border="0" align="center" width="98%">
	<tr>
		<td valign="middle" height="29" align="left" class="stripe">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="module.action">实体字典</a>-&gt;维护实体</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="moduleForm" name="moduleForm" action="module!save.action" method="POST">
<table border="0" cellpadding="0" cellspacing="1" class="stripe"
	align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp; 实体信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="moduleId" name="moduleId" value="<s:property value='moduleId' />"></td>
			</tr>
			<tr>
				<td height="30">系统</td>
				<td height="30"><s:select id="businessId" name="businessId" value="%{moduleBusiness.businessId}" list="businessList" listKey="businessId" listValue="businessNameCn"  /></td>
			</tr>
			<tr>
				<td>英文名称</td>
				<td><input id="moduleNameEn" name="moduleNameEn" value="<s:property value='moduleNameEn' />"></td>
			</tr>
			<tr>
				<td>中文名称</td>
				<td><input id="moduleNameCn" name="moduleNameCn" value="<s:property value='moduleNameCn' />"></td>
			</tr>
			<tr>
				<td>步长</td>
				<td><input id="moduleStep" name="moduleStep"
					value="<s:property value='moduleStep' />"></td>
			</tr>
			<tr>
				<td>下一值</td>
				<td><input id="moduleNext" name="moduleNext" value="<s:property value='moduleNext' />"></td>
			</tr>
			<tr>
				<td>说明</td>
				<td><textarea id="moduleDescription" name="moduleDescription" rows="5" cols="90"><s:property value="moduleDescription" /></textarea></td>
			</tr>
			<tr>
				<td>字典:</td>
				<td><input type="hidden" id="moduleDictionary" name="moduleDictionary" size="1" value="<s:property value='moduleDictionary' />"> 
					<input type="checkbox" id="setDictionary" name="setDictionary" onclick="javascript:(this.checked)? document.moduleForm.moduleDictionary.value=1:document.moduleForm.moduleDictionary.value=0;"></td>
			</tr>
			<tr>
				<td>附件:</td>
				<td><input type="hidden" id="moduleAttachment" name="moduleAttachment" size="1" value="<s:property value='moduleAttachment' />"> 
					<input type="checkbox" id="setAttachment" name="setAttachment" onclick="javascript:(this.checked)? document.moduleForm.moduleAttachment.value=1:document.moduleForm.moduleAttachment.value=0;"></td>
			</tr>
			<tr>
				<td>资源:</td>
				<td><input type="hidden" id="moduleIsResource" name="moduleIsResource" size="1" value="<s:property value='moduleIsResource' />"> 
					<input type="checkbox" id="setResource" name="setResource" onclick="javascript:(this.checked)? document.moduleForm.moduleIsResource.value=1:document.moduleForm.moduleIsResource.value=0;"></td>
			</tr>
			<tr>
				<td height="10" width="100%" colspan="2"><hr size="1"></td>
			</tr>
			<tr>
				<td align="center" colspan="2">
					<INPUT type="button" id="save" name="save" value="Save" width="15" onClick="javascript:doSubmit('moduleForm','module!save.action')">
					<INPUT type="button" id="return" name="return" value="Return" width="15" onClick="javascript:window.history.back();">
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>