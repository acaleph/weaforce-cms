<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>像册管理</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#albumForm').validate();
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="album.action">像册管理</a>-&gt;像册维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="albumForm" name="albumForm" action="album!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp; 字典信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr><td><input type="hidden" id="albumId" name="albumId" value="<s:property value='albumId' />" ></td></tr>
			<tr>
				<td height="30">模板:<font color="red">*</font>:</td>
				<td><s:select id="templateId" name="templateId" value="%{albumTemplate.templateId}" list="templateList" listKey="templateId" listValue="templateName" /></td>
				<td>Parse类型:<font color="red">*</font>:</td>
				<td><select id="albumParseType" name="albumParseType">
						<option value="0">频道</option>
						<option value="1">类频道</option>
						<option value="2">列表图片</option>
					</select>
					<script type="text/javascript">
						document.albumForm.albumParseType.value=<s:property value="albumParseType" />;
					</script>
				</td>
				<td>父像册:</td>
				<td><s:select id="parentId" name="parentId" value="%{albumParent.albumId}" list="albumList" listKey="albumId" listValue="albumName" /></td>
				<td>文件名称:</td>
				<td><input id="albumFileName" name="albumFileName" value="<s:property value='albumFileName' />" size="28" class="required"></td>
			</tr>
			<tr>
				<td height="30">显示名称:</td>
				<td><input id="albumName" name="albumName" value="<s:property value='albumName' />" class="required"></td>
				<td>标签代码:</td>
				<td><input id="albumLabelCode" name="albumLabelCode" value="<s:property value='albumLabelCode' />" class="required"></td>
				<td>路径:</td>
				<td colspan="3">
					<table>
						<tr>
							<td><input id="albumPath" name="albumPath" value="<s:property value='albumPath' />" size="38" class="required"></td>
							<td >排序:</td>
							<td><input id="albumOrder" name="albumOrder" value="<s:property value='albumOrder' />" size="8" class="required"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="30">描述:</td>
				<td colspan="9"><textarea id="albumDescription" name="albumDescription" rows="5" cols="88"><s:property value="albumDescription" /></textarea></td>
			</tr>
			<tr><td height="30" colspan="8"><hr size="1"></td></tr>
			<tr>
				<td colspan="8" height="23">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('albumForm','album!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('albumForm','album!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
						<td><input onClick="javascript:doSubmit('albumForm','album!parse.action')" type="button" id="parse" name="parse" value="Parse" class="button"></td>
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