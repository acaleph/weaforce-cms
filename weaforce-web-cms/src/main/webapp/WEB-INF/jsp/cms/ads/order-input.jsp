<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Discount input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#orderForm').validate();
	var oFCKeditor = new FCKeditor("orderDescription","98%","238","Basic") ;
	oFCKeditor.ReplaceTextarea();
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="order.action?channelId=<s:property value='channelId' />&categoryId=<s:property value='categoryId' />">商家产品</a>-&gt;产品维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="orderForm" name="orderForm" action="order!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp;产品信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="dealId" name="dealId" value="<s:property value='dealId' />" ></td>
				<td><input type="hidden" id="orderId" name="orderId" value="<s:property value='orderId' />" ></td>
			</tr>
			<tr>
				<td height="30">数量:</td>
				<td><input id="orderCount" name="orderCount" value="<s:property value='orderCount' />" class="required"></td>
			</tr>
			<tr>
				<td>描述:</td>
				<td><textarea id="orderDescription" name="orderDescription"><s:property value="orderDescription" /></textarea></td>
			</tr>
			<tr><td height="30" colspan="4"><hr size="1"></td></tr>
			<tr>
				<td colspan="4" height="23">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('orderForm','order!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('orderForm','order!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
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