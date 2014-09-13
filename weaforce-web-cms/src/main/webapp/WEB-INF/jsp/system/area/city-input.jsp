<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#cityForm').validate();
});
</script>
</head>
<body>
<table border="0" align="center" width="98%" class="stripe">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6">&nbsp;系统导航-&gt;<a href="city.action">城市字典域</a>-&gt;创建/维护新的字典</td>
	</tr>
</table>
<form id="cityForm" name="cityForm" action="city!save.action"
	method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1"
	class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp; 字典信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="stripe">
			<tr>
				<td colspan="2"><input type="hidden" id="cityId" name="cityId" value="<s:property value='cityId' />" ></td>
			</tr>
			<tr>
				<td height="30" width="15%">英文名称:</td>
				<td height="30"><input id="cityNameEn" name="cityNameEn" value="<s:property value='cityNameEn' />" ></td>
			</tr>
			<tr>
				<td height="30" width="15%">中文名称:</td>
				<td height="30"><input id="cityNameCn" name="cityNameCn" value="<s:property value='cityNameCn' />" ></td>
			</tr>
			<tr>
				<td height="30" width="15%">省份:</td>
				<td height="30"><s:select name="cityProvince.provinceId" value="%{cityProvince.provinceId}" list="provinceList" listKey="provinceId" listValue="provinceNameCn" required="true" /></td>
			</tr>
			<tr>
				<td height="30" width="100%" colspan="2"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="2" height="33">
					<table>
						<tr>
							<td><input onClick="doSubmit('cityForm','city!save.action');" type="button" id="save" name="save" value="Save" class="button"></td>
							<td><input onClick="doSubmit('cityForm','city!doDelete.action');" type="button" id="delete" name="delete" value="Delete" class="button"></td>
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