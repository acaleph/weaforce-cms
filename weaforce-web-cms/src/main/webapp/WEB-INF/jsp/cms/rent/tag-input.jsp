<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Tag input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#tagForm').validate();
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="tag.action">租金标签</a>-&gt;标签维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="tagForm" name="tagForm" action="tag!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp;标签信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="tagId" name="tagId" value="<s:property value='tagId' />" ></td>
				<td><input type="hidden" id="channelId" name="channelId" value="<s:property value='channelId' />" ></td>
			</tr>
			<tr>
				<td>名称:</td>
				<td><input id="tagName" name="tagName" value="<s:property value='tagName' />" class="required"></td>
				<td>标签:</td>
				<td><input id="tagTag" name="tagTag" value="<s:property value='tagTag' />" class="required"></td>
				<td>排序:</td>
				<td><s:select id="tagOrder" name="tagOrder" value="%{tagOrder}" list="{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20}" cssClass="required" /></td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td height="30">描述:</td>
				<td colspan="6"><textarea id="tagDescription" name="tagDescription" rows="5" cols="88"><s:property value="tagDescription" /></textarea></td>
			</tr>
			<tr><td height="30" colspan="6"><hr size="1"></td></tr>
			<tr>
				<td colspan="6" height="23">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('tagForm','tag!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('tagForm','tag!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
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