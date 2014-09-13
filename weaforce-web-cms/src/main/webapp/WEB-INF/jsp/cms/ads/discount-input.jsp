<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Discount input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#discountForm').validate();
	$("#discountDateFromDate").datepicker({dateFormat: $.datepicker.W3C}).attr("readonly", "readonly");
	$("#discountDateToDate").datepicker({dateFormat: $.datepicker.W3C}).attr("readonly", "readonly");
	if ($('#discountIsParse').val() == '1'){$('#setParse').attr('checked','true');}
	var oFCKeditor = new FCKeditor('discountContent') ;
	oFCKeditor.Height = 358;
	oFCKeditor.ReplaceTextarea() ;
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="discount.action?channelId=<s:property value='channelId' />&categoryId=<s:property value='categoryId' />">广告打折信息</a>-&gt;打折维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="discountForm" name="discountForm" action="discount!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp;打折信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="channelId" name="channelId" value="<s:property value='channelId' />" ></td>
				<td><input type="hidden" id="categoryId" name="categoryId" value="<s:property value='categoryId' />" ></td>
				<td><input type="hidden" id="adsId" name="adsId" value="<s:property value='discountAds.adsId' />" ></td>
				<td><input type="hidden" id="discountId" name="discountId" value="<s:property value='discountId' />" ></td>
			</tr>
			<tr>
				<td height="30">标题:</td>
				<td colspan="2"><input id="discountTitle" name="discountTitle" value="<s:property value='discountTitle' />" size="78" class="required"></td>
				<td>发布：</td>
				<td><input type="hidden" id="discountIsParse" name="discountIsParse" size="1" value="<s:property value='discountIsParse' />">
					<input type="checkbox" id="setParse" name="setParse" onclick="javascript:(this.checked)? document.discountForm.discountIsParse.value=1:document.discountForm.discountIsParse.value=0;">
				</td>
			</tr>
			<tr>
				<td height="30">开始:</td>
				<td><input id="discountDateFromDate" name="discountDateFromDate" value="<s:property value='discountDateFromDate' />" size="10" class="required"></td>
				<td>结束:</td>
				<td><input id="discountDateToDate" name="discountDateToDate" value="<s:property value='discountDateToDate' />" size="10" class="required"></td>
			</tr>
			<tr>
				<td height="30">简介:</td>
				<td colspan="3"><textarea id="discountIntro" name="discountIntro" cols="78" rows="3"><s:property value="discountIntro" /></textarea></td>
			</tr>
			<tr>
				<td height="30">描述:</td>
				<td colspan="3"><textarea id="discountContent" name="discountContent"><s:property value="discountContent" /></textarea></td>
			</tr>
			<tr><td height="30" colspan="4"><hr size="1"></td></tr>
			<tr>
				<td colspan="4" height="23">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('discountForm','discount!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('discountForm','discount!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
						<td><input onClick="javascript:doSubmit('discountForm','discount!parse.action')" type="button" id="parse" name="parse" value="Parse" class="button"></td>
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