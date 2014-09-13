<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Discount input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#tipForm').validate();
	if ($('#tipIsParse').val() == '1'){$('#setParse').attr('checked','true');}
	var oFCKeditor = new FCKeditor('tipContent') ;
	oFCKeditor.Height = 358;
	oFCKeditor.ReplaceTextarea() ;
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="tip.action">服务小贴士</a>-&gt;小贴士维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="tipForm" name="tipForm" action="tip!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp;小贴士信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="tipId" name="tipId" value="<s:property value='tipId' />" ></td>
				<td><input type="hidden" id="channelId" name="channelId" value="<s:property value='channelId' />" ></td>
			</tr>
			<tr>
				<td height="30">栏目:</td>
				<td><s:select id="categoryId" name="categoryId" value="%{tipCategory.categoryId}" list="categoryList" listKey="categoryId" listValue="categoryName" /></td>
				<td>来源：</td>
				<td><s:select id="fromId" name="fromId" value="%{tipFrom.fromId}" list="fromList" listKey="fromId" listValue="fromName" /></td>
				<td>发布：</td>
				<td><input type="hidden" id="tipIsParse" name="tipIsParse" size="1" value="<s:property value='tipIsParse' />">
					<input type="checkbox" id="setParse" name="setParse" onclick="javascript:(this.checked)? document.tipForm.tipIsParse.value=1:document.tipForm.tipIsParse.value=0;">
				</td>
			</tr>
			<tr>
				<td height="30">标题:</td>
				<td colspan="5"><input id="tipTitle" name="tipTitle" value="<s:property value='tipTitle' />" size="78" class="required"></td>
			</tr>
			<tr>
				<td height="30">描述:</td>
				<td colspan="5"><textarea id="tipContent" name="tipContent"><s:property value="tipContent" /></textarea></td>
			</tr>
			<tr><td height="30" colspan="6"><hr size="1"></td></tr>
			<tr>
				<td colspan="6" height="23">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('tipForm','tip!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('tipForm','tip!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
						<td><input onClick="javascript:doSubmit('tipForm','tip!parse.action')" type="button" id="parse" name="parse" value="Parse" class="button"></td>
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