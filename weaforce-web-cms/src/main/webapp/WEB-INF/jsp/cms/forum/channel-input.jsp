<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#channelForm').validate();
	if ($('#channelIsActive').val() == '1'){$('#setActive').attr('checked','true');}
})
</script>
<title>Channel Input</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="channel.action">频道字典域</a>-&gt;维护频道字典</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="channelForm" name="channelForm" action="channel!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp; 字典信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="channelId" name="channelId" value="<s:property value='channelId' />" ></td>
			</tr>
			<tr>
				<td>城市:</td>
				<td><s:select id="cityId" name="cityId" value="%{channelCity.cityId}" list="cityList" listKey="cityId" listValue="cityNameCn" /></td>
			</tr>
			<tr>
				<td>模板:<font color="red">*</font>:</td>
				<td><s:select id="templateId" name="templateId" value="%{channelTemplate.templateId}" list="templateList" listKey="templateId" listValue="templateName" /></td>
			</tr>
			<tr>
				<td height="30">名称:</td>
				<td><input id="channelName" name="channelName" value="<s:property value='channelName' />" class="required"></td>

			</tr>
			<tr>
				<td height="30">Parse路径:</td>
				<td><input id="channelParsePath" name="channelParsePath" value="<s:property value='channelParsePath' />" size="68" class="required"></td>
			</tr>
			<tr>
				<td height="30">排序:</td>
				<td><input id="channelOrder" name="channelOrder" value="<s:property value='channelOrder' />" size="38" class="required"></td>
			</tr>
			<tr>
				<td height="30">描述:</td>
				<td><textarea id="channelDescription" name="channelDescription" rows="5" cols="98"><s:property value="channelDescription" /></textarea></td>
			</tr>
			<tr><td height="30" colspan="2"><hr size="1"></td></tr>
			<tr>
				<td colspan="2" height="33">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('channelForm','channel!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('channelForm','channel!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
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