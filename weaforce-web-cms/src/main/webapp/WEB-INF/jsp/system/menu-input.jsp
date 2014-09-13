<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Menu</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#menuForm').validate();
});
</script>
</head>
<body>
<form id="menuForm" name="menuForm" action="" method="post">
<table>
	<tr>
		<td colspan="4">
			<input type="hidden" id="menuId" name="menuId" value="<s:property value='menuId' />"> 
			<input type="hidden" id="menuFid" name="menuFid" value="<s:property value='menuParent.menuId' />">
		</td>
	</tr>
	<tr>
		<td>中文名称</td>
		<td><input type="text" id="menuNameCn" name="menuNameCn" value="<s:property value='menuNameCn' />" class="required"></td>
		<td>英文名称</td>
		<td><input type="text" id="menuNameEn" name="menuNameEn" value="<s:property value='menuNameEn' />"></td>
	</tr>
	<tr>
		<td>排 序</td>
		<td><input type="text" id="menuGroupOrder" name="menuGroupOrder" value="<s:property value='menuGroupOrder' />"></td>
		<td>链 接</td>
		<td><input type="text" id="menuUrl" name="menuUrl" value="<s:property value='menuUrl' />"></td>
	</tr>
	<tr>
		<td align="right">说明:<font color=red>*</font></td>
		<td colspan="3">
			<textarea rows="3" cols="58" name="menuDescription" class="common"><s:property value="menuDescription" /></textarea>
		</td>
	</tr>
	<tr>
		<td height="10" width="100%" colspan="4"><hr size="1"></td>
	</tr>
	<tr>
		<td colspan="4" height="28">
			<table>
				<tr>
					<td><input type="button" id="save" name="save" value="Save" onClick="javascript:doSubmit('menuForm','menu!save.action');" class="button"></td>
					<td><input type="button" id="delete" name="delete" value="Delete" onClick="javascript:doSubmit('menuForm','menu!delete.action');" class="button"></td>
					<td><input onClick="javascript:window.history.back();" type="button" id="return" name="return" value="Return" class="button"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>