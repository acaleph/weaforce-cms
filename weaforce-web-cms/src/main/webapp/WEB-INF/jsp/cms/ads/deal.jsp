<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Discount</title>
<script type="text/javascript">
$(document).ready(function(){
	//$('#theTable > th').parent().addClass('table-head');
	$('#table-stripe tr:not([th]):odd').addClass('odd');
	$('#table-stripe tr:not([th]):even').addClass('even');
		
	$("#table-stripe tr:not([th])").mouseover(function(){  
        //如果鼠标移到class为stripe的表格的tr上时，执行函数
		$(this).addClass("over");}).mouseout(function(){ 
        //给这行添加class值为over，并且当鼠标一出该行时执行函数
        $(this).removeClass("over");
	})  //移除该行的class
	$("#dateFrom").datepicker({dateFormat: $.datepicker.W3C}).attr("readonly", "readonly");
	$("#dateTo").datepicker({dateFormat: $.datepicker.W3C}).attr("readonly", "readonly");
	$('#channelId').change(function(){
		$('#categoryId').buildSelect($('#categoryId'),{selected:0,url:"/cms/ads/category!getCategoryDDL.action",data:{'channelId':$(this).val()}},function(){});
	})
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;产品交易</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="deal.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="center">
		<td>起始日期:</td>
		<td><input id="dateFrom" name="dateFrom" value="<s:property value="dateFrom" />" class="text"></td>
		<td>终止日期:</td>
		<td><input id="dateTo" name="dateTo" value="<s:property value="dateTo" />" class="text"></td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','deal.action')"></td>
		<td><input type="button" id="create" name="create" value="Create" onClick="javascript:doSubmit('listPage','deal!input.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>商家</th>
		<th>开始</th>
		<th>结束</th>
		<th>原价</th>
		<th>打折</th>
		<th>现价</th>
		<th>创建</th>
		<th width="23" height="23"></th>
	</tr>
	<tr><td><input type="hidden" id="productId" name="productId" value="<s:property value='productId' />" ></td></tr>
	<s:iterator value="pageInfo.result">
		<tr>
			<td height="23" align="center"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','deal!input.action?dealId=<s:property  value='dealId' />');"></td>
			<td><s:property value="dealProduct.productAds.adsName" /></td>
			<td><s:property value="dealDateStartDate" /></td>
			<td><s:property value="dealDateEndDate" /></td>
			<td><s:property value="dealPrice" /></td>
			<td><s:property value="dealDiscount" /></td>
			<td><s:property value="dealPriceDiscount" /></td>
			<td><s:property value="createTimeDate" /></td>
			<td align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('当前打折优惠将被删除,是否继续?')) doSubmit('listPage','deal!delete.action?dealId=<s:property  value='dealId' />');"></td>
		</tr>
	</s:iterator>
</table>
<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
	<tr valign="top"><td><y:pages value="pageInfo" beanName="pageInfo" formName="listPage" /></td></tr>
</TABLE>
</form>
</body>
</html>