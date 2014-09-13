<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Calendar input</title>
<script type="text/javascript">
$(document).ready(function(){
	$("#calendarForm").validate();
	if ($('#calendarIsActive').val() == '1'){$('#setActive').attr('checked','true');}
	$("#calendarDateDate").datepicker({dateFormat: $.datepicker.W3C}).attr("readonly", "readonly");
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img
			src="/common/image/point.gif" width="5" height="6">&nbsp;系统导航-&gt;<a href="calendar.action">工厂日历</a>-&gt;维护日历</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="calendarForm" name="calendarForm" action="calendar!save.action"
	method="POST">
<table border="0" cellpadding="0" cellspacing="1" class="stripe"
	align="center" width="98%">
	<tr class="table-head">
		<td height="23">&nbsp;<img src="/common/image/title-icon0.gif" width="16" height="13">&nbsp;日历信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="calendarId" name="calendarId"
					value="<s:property value='calendarId' />"></td>
			</tr>
			<tr>
				<td height="30" width="15%">标题:</td>
				<td height="30" ><input id="calendarTitle" name="calendarTitle"
					value="<s:property value='calendarTitle'/>" size="88" class="required"></td>
			</tr>
			<tr>
				<td height="30" width="15%" bgcolor="#F1F8FC">日期:</td>
				<td height="30" bgcolor="#F1F8FC"><input id="calendarDateDate" name="calendarDateDate"
					value="<s:property value='calendarDateDate'/>" size="10" class="required"></td>
			</tr>
			<tr>
				<td height="20" width="100%" colspan="2"><hr size="1"></td>
			</tr>
			<tr>
				<td align="center" colspan="2">
				<table>
					<tr>
						<td><input type="button" id="save" name="save" value="Save"
							width="15" onClick="javascript:doSubmit('calendarForm','calendar!save.action')"></td>
						<td><input type="button" id="delete" name="delete" value="Delete"
							width="15" onClick="javascript:doSubmit('calendarForm','calendar!delete.action')"></td>
						<td><input type="button" id="return" name="return" value="Return" width="15"
							onClick="javascript:window.history.back();"></td>
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