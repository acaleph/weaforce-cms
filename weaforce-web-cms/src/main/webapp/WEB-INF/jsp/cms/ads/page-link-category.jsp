<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Page link input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#linkForm').validate();
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="page-link.action">广告页面定义</a>-&gt;维护页面</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="linkForm" name="linkForm" action="page-link!save.action" method="Post">
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23">&nbsp;<img src="/common/image/title-icon1.gif" width="16" height="13">&nbsp;页面元素</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="linkId" name="linkId" value="<s:property value='linkId' />"></td>
				<td><input type="hidden" id="categoryId" name="categoryId" value="<s:property value='categoryId' />"></td>
			</tr>
			<tr>
				<td height="30">父链接:<font color="red">*</font>:</td>
				<td><s:select id="parentId" name="parentId" value="%{linkParent.linkId}" list="linkList" listKey="linkId" listValue="linkTitle" /></td>
			</tr>
			<tr>
				<td>排序:</td>
				<td><s:select id="linkOrder" name="linkOrder" value="%{linkOrder}" list="{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18}" /></td>
			</tr>
			<tr>
				<td height="16" width="100%" colspan="2"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="2" height="33">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('linkForm','page-link!save.action');" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('linkForm','page-link!delete.action');" type="button" id="delete" name="delete" value="Delete" class="button"></td>
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