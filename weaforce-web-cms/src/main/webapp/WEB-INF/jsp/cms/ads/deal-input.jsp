<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Discount input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#dealForm').validate({
		rules:{
			dealPrice: {
				required: true,
				number: true,
				range:[10,500]
			},
			dealDiscount: {
				required: true,
				number: true,
				range:[1,9.9]
			}
		}
	});
	$("#dealDateStartDate").datepicker({dateFormat: $.datepicker.W3C}).attr("readonly", "readonly");
	$("#dealDateEndDate").datepicker({dateFormat: $.datepicker.W3C}).attr("readonly", "readonly");
	var oFCKeditor = new FCKeditor("dealContent");
	oFCKeditor.Height = 358;
	oFCKeditor.ReplaceTextarea();
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="deal.action">产品交易</a>-&gt;交易维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="dealForm" name="dealForm" action="deal!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp;交易信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="productId" name="productId" value="<s:property value='productId' />" ></td>
				<td><input type="hidden" id="dealId" name="dealId" value="<s:property value='dealId' />" ></td>
			</tr>
			<tr>
				<td height="30">起始日期:</td>
				<td><input id="dealDateStartDate" name="dealDateStartDate" value="<s:property value='dealDateStartDate' />" size="10" class="required"></td>
				<td>终止日期:</td>
				<td><input id="dealDateEndDate" name="dealDateEndDate" value="<s:property value='dealDateEndDate' />" size="20" class="required"></td>
			</tr>
			<tr>
				<td height="30">原价:</td>
				<td><input id="dealPrice" name="dealPrice" value="<s:property value='dealPrice' />" class="required"></td>
				<td>打折:</td>
				<td><input id="dealDiscount" name="dealDiscount" value="<s:property value='dealDiscount' />" class="required"></td>
			</tr>
			<tr>
				<td>描述:</td>
				<td colspan="3"><textarea id="dealContent" name="dealContent"><s:property value="dealContent" /></textarea></td>
			</tr>
			<tr><td height="30" colspan="4"><hr size="1"></td></tr>
			<tr>
				<td colspan="4" height="23">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('dealForm','deal!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('dealForm','deal!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
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