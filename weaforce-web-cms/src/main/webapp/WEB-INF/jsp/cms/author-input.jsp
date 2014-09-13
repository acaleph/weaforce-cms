<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Author input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#authorForm').validate();
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">系统导航-&gt;<a href="author.action">文章作者</a>-&gt;作者维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="authorForm" name="authorForm" action="author!save.action" method="POST">
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">文章作者</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="stripe">
			<tr>
				<td><input type="hidden" id="authorId" name="authorId" value="<s:property value='authorId' />"></td>
			</tr>
			<tr>
				<td height="30">名称:</td>
				<td height="30"><input id="authorName" name="authorName" value="<s:property value='authorName' />" class="required"></td>
			</tr>
			<tr>
				<td height="30">电子邮件:</td>
				<td height="30"><input id="authorEmail" name="authorEmail" value="<s:property value='authorEmail' />" size="38" class="required"></td>
			</tr>
			<tr>
				<td height="30" colspan="2"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="2" height="33">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('authorForm','author!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('authorForm','author!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
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