<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#buildingForm').validate();
	$('#zoneId').change(function(){
		$('#adsId').buildSelect($('#adsId'),{selected:0,url:"/cms/rent/building!getAdsDDL.action",data:{'zoneId':$(this).val()}},function(){});
	})
})
</script>
<title>Channel Input</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="building.action">栏目字典域</a>-&gt;维护栏目</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="buildingForm" name="buildingForm" action="building!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1"
	class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">栏目信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="buildingId" name="buildingId" value="<s:property value='buildingId' />"></td>
			</tr>
			<tr>
				<td>所在区:<font color="red">*</font>:</td>
				<td><s:select id="zoneId" name="zoneId" value="%{buildingZone.zoneId}" list="zoneList" listKey="zoneId" listValue="zoneName" cssClass="required" /></td>
			</tr>
			<tr>
				<td>广告商家<font color="red">*</font>:</td>
				<td><s:select id="adsId" name="adsId" value="%{buildingAds.adsId}" list="adsList" listKey="adsId" listValue="adsName" /></td>
			</tr>
			<tr>
				<td>名称<font color="red">*</font>:</td>
				<td><input id="buildingName" name="buildingName" value="<s:property value='buildingName'/>" class="required"></td>
			</tr>
			<tr>
				<td height="30" colspan="2"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="2" height="33">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('buildingForm','building!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('buildingForm','building!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
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