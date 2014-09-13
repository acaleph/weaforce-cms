<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#channelForm').validate();
})
</script>
<title>Channel Input</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="channel.action">广告频道字典域</a>-&gt;频道维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="channelForm" name="channelForm" action="channel!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp; 频道信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="channelId" name="channelId" value="<s:property value='channelId' />" ></td>
			</tr>
			<tr>
				<td height="30">显示名称:</td>
				<td><input id="channelName" name="channelName" value="<s:property value='channelName' />" class="required"></td>

			</tr>
			<tr>
				<td height="30">城市:</td>
				<td><s:select id="cityId" name="cityId" value="%{channelCity.cityId}" list="cityList" listKey="cityId" listValue="cityNameCn" /></td>
			</tr>
			<tr>
				<td height="30">排序:</td>
				<td><s:select id="channelOrder" name="channelOrder" value="%{channelOrder}" list="{1,2,3,4,5,6,7,8,9,10,11}" /></td>
			</tr>
			<tr>
				<td>描述:</td>
				<td><textarea id="channelDescription" name="channelDescription" rows="5" cols="98"><s:property value="channelDescription" /></textarea></td>
			</tr>
			<tr><td height="30" colspan="2"><hr size="1"></td></tr>
			<tr>
				<td colspan="2" height="33">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('channelForm','channel!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('channelForm','channel!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
						<td><input onClick="javascript:doSubmit('channelForm','channel!lock.action')" type="button" id="lock" name="lock" value="Lock" class="button"></td>
						<td><input onClick="javascript:doSubmit('channelForm','channel!parse.action')" type="button" id="parse" name="parse" value="Parse" class="button"></td>
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