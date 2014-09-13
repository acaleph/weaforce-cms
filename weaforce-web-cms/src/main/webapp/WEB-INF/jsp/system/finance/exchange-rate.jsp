<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>汇率字典域</title>
<script type="text/javascript">
$(document).ready(function(){
	//$('#theTable > th').parent().addClass('table-head');
	$('#theTable tr:not([th]):odd').addClass('odd');
	$('#theTable tr:not([th]):even').addClass('even');
		
	$("#theTable tr:not([th])").mouseover(function(){  
        //如果鼠标移到class为stripe的表格的tr上时，执行函数
		$(this).addClass("over");}).mouseout(function(){ 
        //给这行添加class值为over，并且当鼠标一出该行时执行函数
        $(this).removeClass("over");
	})  //移除该行的class
});
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">系统导航-&gt;汇率字典域</td>
	</tr>
</table>
<div class="spacer-10"></div>
<form id="listPage" name="listPage" action="exchangeRate!list.action"
	method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg"
	align="center" width="98%">
	<tr align="center">
		<td>原币:</td>
		<td><s:select id="rateOriginId" name="rateOriginId"
			value="%{rateOriginId}" list="currencyList" listKey="currencyId"
			listValue="currencyCode" /></td>
		<td>目标币:</td>
		<td><s:select id="rateTargetId" name="rateTargetId"
			value="%{rateTargetId}" list="currencyList" listKey="currencyId"
			listValue="currencyCode" /></td>
		<td><input type="button" id="search" name="search" value="Search"
			onClick="javascript:doSubmit('listPage','exchange-rate!list.action')"></td>
		<td><input type="button" id="create" name="create" value="create"
			onClick="javascript:doSubmit('listPage','exchange-rate!input.action')"></td>
	</tr>
</table>
<div class="spacer-10"></div>
<table id="theTable" border="0" cellpadding="0" cellspacing="1"
	class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th height="23"></th>
		<th>原币</th>
		<th>目标币</th>
		<th>汇率</th>
		<th>有效</th>
		<th>有效时间</th>
		<th>创建人</th>
		<th></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr align="left">
			<td height="23"><security:authorize ifAnyGranted="ROLE_CONE_CRUD_USER"><img class="pointerImage" border="0"
				title="Edit" src="/common/image/edit_inline.gif"
				onClick="javascript:doSubmit('listPage','exchange-rate!input.action?rateId=<s:property  value='rateId' />');"></security:authorize></td>
			<td><s:property value="rateOriginCurrency.currencyCode" /></td>
			<td><s:property value="rateTargetCurrency.currencyCode" /></td>
			<td><s:property value="rateValue" /></td>
			<td align="center"><s:if test="%{rateIsActive == 1}">
				<input type="checkbox" checked="checked" readonly="readonly">
			</s:if><s:if test="%{rateIsActive == 0}">
				<input type="checkbox" readonly="readonly">
			</s:if></td>
			<td><s:property value="rateDateDate" /></td>
			<td><s:property value="creator" /></td>
			<td><security:authorize ifAnyGranted="ROLE_CONE_CRUD_USER"><img class="pointerImage" border="0" title="Delete"
				src="/common/image/delete_inline.gif"
				onClick="javascript:if (confirm('是否要将当前汇率变为无效状态?')) doSubmit('listPage','exchange-rate!delete.action?rateId=<s:property  value='rateId' />');"></security:authorize></td>
		</tr>
	</s:iterator>
</table>

<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
	<tr valign="top">
		<td><y:pages value="pageInfo" beanName="pageInfo"
			formName="listPage" /></td>
	</tr>
</TABLE>
</form>
</body>
</html>