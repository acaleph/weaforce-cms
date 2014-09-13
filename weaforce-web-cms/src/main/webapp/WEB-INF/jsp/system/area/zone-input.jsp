<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Zone input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#zoneForm').validate();
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="zone.action">城区管理</a>-&gt;城区维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="zoneForm" name="zoneForm" action="zone!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp;城区信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
			<input type="hidden" id="zoneId" name="zoneId" value="<s:property value='zoneId' />" />
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td>城市:</td>
				<td><s:select id="cityId" name="cityId" value="%{zoneCity.cityId}" list="cityList" listKey="cityId" listValue="cityNameCn" /></td>
			</tr>
			<tr>
				<td height="30">名称:</td>
				<td><input id="zoneName" name="zoneName" value="<s:property value='zoneName' />" class="required"></td>
			</tr>
			<tr>
				<td height="30">排序:</td>
				<td><s:select id="zoneOrder" name="zoneOrder" value="%{zoneOrder}" list="{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28}" /></td>
			</tr>
			<tr><td height="30" colspan="2"><hr size="1"></td></tr>
			<tr>
				<td colspan="2" height="23">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('zoneForm','zone!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('zoneForm','zone!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
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