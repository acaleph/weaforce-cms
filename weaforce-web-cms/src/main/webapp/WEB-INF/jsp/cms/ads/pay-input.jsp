<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Ads Pay input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#payForm').validate({
		rules:{
		payPay: {
				required: true,
				number: true,
				range:[1000,5000]
		}
	}
	});
	$("#payDateDate").datepicker({dateFormat: $.datepicker.W3C}).attr("readonly", "readonly");
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="pay.action">付款管理</a>-&gt;付款维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="payForm" name="payForm" action="pay!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp; 付款信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td>
					<input type="hidden" id="payId" name="payId" value="<s:property value='payId' />" >
					<input type="hidden" id="payPayBefore" name="payPayBefore" value="<s:property value='payPayBefore' />">
				</td>
			</tr>
			<tr>
				<td  height="30">广告商家:</td>
				<td><s:select id="adsId" name="adsId" value="%{payAds.adsId}" list="adsList" listKey="adsId" listValue="adsName" cssClass="required" /></td>
				<td>标题:</td>
				<td><input id="payTitle" name="payTitle" value="<s:property value='payTitle' />" size="48" class="required"></td>
			</tr>
			<tr>
				<td  height="30">费用:</td>
				<td><input id="payPay" name="payPay" value="<s:property value='payPay' />"  size="8" class="required"></td>
				<td>日期:</td>
				<td><input id="payDateDate" name="payDateDate" value="<s:property value='payDateDate' />"  size="10" class="required"></td>
			</tr>
			<tr>
				<td height="30">内容:</td>
				<td colspan="3"><textarea id="payContent" name="payContent" cols="78" rows="3"><s:property value='payContent' /></textarea></td>
			</tr>
			<tr><td height="30" colspan="4"><hr size="1"></td></tr>
			<tr>
				<td colspan="3" height="33">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('payForm','pay!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('payForm','pay!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
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