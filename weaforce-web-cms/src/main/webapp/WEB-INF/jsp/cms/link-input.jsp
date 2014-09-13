<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#linkForm').validate();
	if ($('#linkIsActive').val() == '1'){$('#setActive').attr('checked','true');}
})
</script>
<title>META Input</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="link.action">友情链接</a>-&gt;链接维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="linkForm" name="linkForm" action="link!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp; 字典信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="linkId" name="linkId" value="<s:property value='linkId' />" ></td>
			</tr>
			<tr>
				<td height="30">名称:</td>
				<td><input id="linkName" name="linkName" value="<s:property value='linkName' />" size="38" class="required"></td>
			</tr>
			<tr>
				<td height="30">URL:</td>
				<td><input id="linkUrl" name="linkUrl" value="<s:property value='linkUrl' />" size="38" class="required"></td>
			</tr>
			<tr>
				<td height="30">Logo:</td>
				<td><input id="linkLogo" name="linkLogo" value="<s:property value='linkLogo' />" size="38"></td>
			</tr>
			<tr>
				<td height="30" colspan="2"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="2" height="33">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('linkForm','link!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('linkForm','link!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
						<td><input onClick="javascript:doSubmit('linkForm','link!lock.action')" type="button" id="lock" name="lock" value="Lock" class="button"></td>
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