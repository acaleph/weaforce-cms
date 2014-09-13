<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Country input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#countryForm').validate();
});
</script>
</head>
<body>
<table border="0" align="center" width="100%">
	<tr>
		<td valign="middle" width="79%" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6">&nbsp;系统导航 - &gt; <a href="country.action">国家字典域</a> - &gt;维护国家</td>
	</tr>
</table>
<form id="countryForm" name="countryForm" action="country!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="96%">
	<tr class="table-head">
		<td height="23" width="599" colspan="2">&nbsp; 字典信息</td>
	</tr>
	<tr>
		<td width="598" align="center" colspan="2"></td>
	</tr>
	<tr>
		<td width="198" align="center"><img border="0"
			src="/common/image/main/CreateArea.jpg" width="131" height="155"
			alt=""></td>
		<td width="511" align="center">
			<input type="hidden" id="countryId" name="countryId" value="%{countryId}" >
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="stripe">
				<tr>
					<td height="30" width="18%">英文名称:</td>
					<td height="30" colspan="3"><input id="countryNameEn" name="countryNameEn" value="%{countryNameEn}" size="78" ></td>
				</tr>
				<tr>
					<td height="30">中文名称:</td>
					<td height="30" colspan="3"><input id="countryNameCn" name="countryNameCn" value="%{countryNameCn}" size="78" ></td>
				</tr>
				<tr>
					<td height="30">代码:</td>
					<td height="30"><input id="countryCode" name="countryCode" value="%{countryCode}" ></td>
					<td height="30">域名:</td>
					<td height="30"><input id="countryDomain" name="countryDomain" value="%{countryDomain}" ></td>

				</tr>
				<tr>
					<td height="30">电话:</td>
					<td height="30"><input id="countryPhone" name="countryPhone" value="%{countryPhone}" ></td>
					<td height="30">时差:</td>
					<td height="30"><input id="countryZone" name="countryZone" value="%{countryZone}" ></td>
				</tr>
				<tr>
					<td height="30" align="left" colspan="4"><hr size="1"></td>
				</tr>
				<tr>
					<td colspan="8" height="33">
						<table>
							<tr>
								<td><input onClick="doSubmit('country','country!save.action');" type="button" id="save" name="save" value="Save" class="button"></td>
								<td><input onClick="doSubmit('countryForm','country!doDelete.action');" type="button" id="delete" name="delete" value="Delete" class="button"></td>
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