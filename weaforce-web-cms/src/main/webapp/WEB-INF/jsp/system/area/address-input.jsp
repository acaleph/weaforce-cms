<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#addressForm').validate();
});
</script>
<title>Address Input</title>
</head>
<body>
<table border="0" align="center" width="98%" class="stripe">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6">&nbsp;系统导航-&gt;<a href="address.action">地址字典域</a>-&gt;创建/维护新的字典</td>
	</tr>
</table>
<form id="addressForm" name="addressForm" action="address!save.action"
	method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="599" colspan="2">&nbsp; 字典信息</td>
	</tr>
	<tr>
		<td align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="stripe">
			<tr>
				<td height="30"><input id="addressId" name="addressId" value="<s:property value='addressId' />"></td>
			</tr>
			<tr>
				<td height="30">国家:</td>
				<td><s:select name="addressCountry.countryId"
					value="%{addressCountry.countryId}" list="countryList"
					listKey="countryId" listValue="countryNameCn" required="true" /></td>
				<td>省份:</td>
				<td><s:select name="addressProvince.provinceId"
					value="%{addressProvince.provinceId}" list="provinceList"
					listKey="provinceId" listValue="provinceNameCn" required="true" /></td>
				<td>城市:</td>
				<td><s:select name="addressCity.cityId"
					value="%{addressCity.cityId}" list="cityList" listKey="cityId"
					listValue="cityNameCn" required="true" /></td>
			</tr>
			<tr>
				<td height="30">地址:</td>
				<td colspan="6"><input id="addressLocation" name="addressLocation"
					value="<s:property value='addressLocation' />" size="98"></td>
			</tr>
			<tr>
				<td height="30" align="left" width="100%" colspan="8"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="8" height="33">
					<table>
						<tr>
							<td><input onClick="doSubmit(addressForm,'address!save.action');" type="button" id="save" name="save" value="Save" class="button"></td>
							<td><input onClick="doSubmit(addressForm,'address!doDelete.action');" type="button" id="delete" name="delete" value="Delete" class="button"></td>
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