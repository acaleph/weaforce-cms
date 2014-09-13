<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Message input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#messageForm').validate();

	var oFCKeditor = new FCKeditor("messageRemark","100%","238","Basic") ;
	oFCKeditor.ReplaceTextarea() ;
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="message.action">信息管理</a>-&gt;信息维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="messageForm" name="messageForm" action="message!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp;信息内容</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="channelId" name="channelId" value="<s:property value='channelId' />" ></td>
				<td><input type="hidden" id="categoryId" name="categoryId" value="<s:property value='categoryId' />" ></td>
				<td><input type="hidden" id="adsId" name="adsId" value="<s:property value='adsId' />" ></td>
				<td><input type="hidden" id="messageId" name="messageId" value="<s:property value='messageId' />" ></td>
			</tr>
			<tr>
				<td height="30">标题:</td>
				<td><input id="messageTitle" name="messageTitle" value="<s:property value='messageTitle' />" size="78" class="required" ></td>
			</tr>
			<tr>
				<td height="30">手机:</td>
				<td><input id="messageMobile" name="messageMobile" value="<s:property value='messageMobile' />" size="28" class="required" ></td>
			</tr>
			<tr>
				<td height="30">电话:</td>
				<td><input id="messagePhone" name="messagePhone" value="<s:property value='messagePhone' />" size="28" class="required" ></td>
			</tr>
			<tr>
				<td height="30">创建时间:</td>
				<td><input id="date" name="date" value="<s:property value='date' />" size="28" readonly="readonly" class="required" ></td>
			</tr>
			<tr>
				<td height="30">描述:</td>
				<td><textarea id="messageRemark" name="messageRemark" rows="5" cols="88"><s:property value="messageRemark" /></textarea></td>
			</tr>
			<tr><td height="30" colspan="2"><hr size="1"></td></tr>
			<tr>
				<td colspan="2" height="23">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('messageForm','message!save.action')"
							type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('messageForm','message!delete.action')"
							type="button" id="delete" name="delete" value="Delete" class="button"></td>
						<td><input onClick="javascript:doSubmit('messageForm','message!lock.action')"
							type="button" id="lock" name="lock" value="Ok" class="button"></td>
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