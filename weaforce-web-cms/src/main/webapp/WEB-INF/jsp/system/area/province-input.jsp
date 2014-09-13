<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Province Input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#provinceForm').validate();
});
</script>
</head>
<body>
<table border="0" align="center" width="98%">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6">&nbsp;系统导航-&gt;<a href="province.action">省（州）字典域</a>-&gt;维护省（州）</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="provinceForm" name="provinceForm" action="province!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp; 字典信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="480" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td colspan="2"><input type="hidden" id="provinceId" name="provinceId" value="<s:property value='provinceId' />" ></td>
			</tr>
			<tr>
				<td height="30">英文名称:</td>
				<td><input id="provinceNameEn" name="provinceNameEn" value="<s:property value='provinceNameEn' />" class="required"></td>
			</tr>
			<tr>
				<td height="30">中文名称:</td>
				<td><input id="provinceNameCn" name="provinceNameCn" value="<s:property value='provinceNameCn' />" class="required"></td>
			</tr>
			<tr>
				<td height="30" align="left" width="20%" colspan="2"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="2" height="33">
					<table>
						<tr>
							<td><input onClick="doSubmit('provinceForm','province!save.action');"
								type="button" id="save" name="save" value="Save" class="button"></td>
							<td><input onClick="doSubmit('provinceForm','province!doDelete.action');"
								type="button" id="delete" name="delete" value="Delete" class="button">
							</td>
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