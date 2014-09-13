<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$("#boardForm").validate();
	if ($('#boardIsActive').val() == '1'){$('#setActive').attr('checked','true');}
	if ($('#enableSave').val() == 0) $('#save').attr('disabled',true);
	$("#boardDateDate").datepicker({dateFormat: $.datepicker.W3C}).attr("readonly", "readonly");
	var oFCKeditor = new FCKeditor("boardContent","98%","238","Basic") ;
	oFCKeditor.ReplaceTextarea() ;
})
</script>
<title>Board Input</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6">&nbsp;系统导航-&gt;<a href="board.action">公告板</a>-&gt;维护公告</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="boardForm" name="boardForm" action="board!save.action"
	method="POST">
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23">&nbsp;<img src="/common/image/title-icon0.gif" width="16" height="13">&nbsp;公告板信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="stripe">
			<tr>
				<td><input type="hidden" id="boardId" name="boardId"
					value="<s:property value='boardId' />"></td>
				<td><input type="hidden" id="enableSave" name="enableSave"
					value="<s:property value='enableSave' />"></td>
				<td><input type="hidden" id="queryDateDate"
					name="queryDateDate" value="<s:property value='queryDateDate' />"></td>
			</tr>
			<tr>
				<td height="30" width="15%">标题:</td>
				<td height="30" colspan="3"><input id="boardTitle" name="boardTitle"
					value="<s:property value='boardTitle'/>" size="88" class="required"></td>
			</tr>
			<tr>
				<td height="30" width="15%" bgcolor="#F1F8FC">日期:</td>
				<td height="30" bgcolor="#F1F8FC"><input id="boardDateDate" name="boardDateDate"
					value="<s:property value='boardDateDate'/>" size="10" class="required"></td>
				<td bgcolor="#F1F8FC">活动</td>
				<td bgcolor="#F1F8FC"><input type="hidden" id="boardIsActive"
					name="boardIsActive" size="1" value="<s:property value='boardIsActive' />">
					<input type="checkbox" id="setActive" name="setActive" onClick="javascript:(this.checked)?document.boardForm.boardIsActive.value=1:document.boardForm.boardIsActive.value=0;"></td>
			</tr>
			<tr>
				<td>记录内容:</td>
				<td colspan="3"><textarea id="boardContent" name="boardContent"><s:property
					value='boardContent' /></textarea></td>
			</tr>
			<tr>
				<td height="20" width="100%" colspan="4">
				<hr size="1">
				</td>
			</tr>
			<tr>
				<td align="center" colspan="4">
				<table>
					<tr>
						<s:if test="%{currentLogin == creator || boardId == null}">
						<td><input type="button" id="save" name="save" value="Save" width="15" onClick="javascript:doSubmit('boardForm','board!save.action')"></td>
						<td><input type="button" id="delete" name="delete" value="Delete" width="15" onClick="javascript:doSubmit('boardForm','board!delete.action')"></td>
						</s:if>
						<td><input type="button" id="return" name="return" value="Return" width="15" onClick="javascript:window.history.back();"></td>
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