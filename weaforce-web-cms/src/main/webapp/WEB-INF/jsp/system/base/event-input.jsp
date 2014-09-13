<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript" src="/common/jquery/plugin/jquery.blockUI.js"></script>
<script type="text/javascript" src="/common/jquery/plugin/jquery.form.js"></script>
<title>Event input</title>
</head>
<body>
<form id="eventForm" name="eventForm" action="event!save.action">
<table>
	<tr class="table-head" height="29">
		<td height="30" colspan="2">备忘录</td>
	</tr>
	<tr>
		<td colspan="2"><input type="hidden" id="eventId" name="eventId" value="<s:property value='eventId'/>" readonly="readonly"></td>
	</tr>
	<tr>
		<td width="50">日期:</td>
		<td><input id="eventDate" name="eventDate" value="<s:property value='eventDate'/>" readonly="readonly"></td>
	</tr>
	<tr>
		<td>标题:</td>
		<td><input id="eventTitle" name="eventTitle" value="<s:property value='eventTitle'/>" size="33" class="required"></td>
	</tr>
	<tr>
		<td>内容:</td>
		<td><textarea id="eventContent" name="eventContent" rows="3" cols="28"><s:property value='eventContent'/></textarea></td>
	</tr>
	<tr>
		<td height="16" width="100%" colspan="2"><hr size="1"></td>
	</tr>
	<tr>
		<td colspan="2">
			<table><tr height="23">
			<td align="center" width="50%"><input type="button" id="save" name="save" value="Save"></td>
			<td align="center" width="50%"><input type="button" id="cancel" name="cancel" value="Cancel"></td>
			</tr></table>
		</td>
	</tr>
</table>
</form>
</body>
</html>