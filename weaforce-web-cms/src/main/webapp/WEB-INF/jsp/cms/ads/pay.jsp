<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Ads Pay</title>
<script type="text/javascript">
$(document).ready(function(){
	$("#queryDateFrom").datepicker({dateFormat: $.datepicker.W3C}).attr("readonly", "readonly");
	$("#queryDateTo").datepicker({dateFormat: $.datepicker.W3C}).attr("readonly", "readonly");
	//$('#theTable > th').parent().addClass('table-head');
	$('#table-stripe tr:not([th]):odd').addClass('odd');
	$('#table-stripe tr:not([th]):even').addClass('even');
		
	$("#table-stripe tr:not([th])").mouseover(function(){  
        //如果鼠标移到class为stripe的表格的tr上时，执行函数
		$(this).addClass("over");}).mouseout(function(){ 
        //给这行添加class值为over，并且当鼠标一出该行时执行函数
        $(this).removeClass("over");
	})  //移除该行的class
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;付款管理</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="pay.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="center">
		<td>频道:</td>
		<td><s:select id="channelId" name="channelId" value="%{channelId}" list="channelList" listKey="channelId" listValue="channelName" /></td>
		<td>栏目:</td>
		<td><s:select id="categoryId" name="categoryId" value="%{categoryId}" list="categoryList" listKey="categoryId" listValue="categoryName" /></td>
		<td>名称:</td>
		<td><input id="queryTitle" name="queryTitle" value="<s:property value="queryTitle" />" class="text"></td>
		<td>开始:</td>
		<td><input id="queryDateFrom" name="queryDateFrom" value="<s:property value="queryDateFrom" />" size="10" class="text"></td>
		<td>终止:</td>
		<td><input id="queryDateTo" name="queryDateTo" value="<s:property value="queryDateTo" />" size="10" class="text"></td>
		<td><input type="button" name="search" value="Search" onClick="javascript:doSubmit('listPage','pay!list.action')"></td>
		<td><input type="button" name="create" value="create" onClick="javascript:doSubmit('listPage','pay!input.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<th width="23" height="23"></th>
		<th>标题</th>
		<th>费用</th>
		<th>日期</th>
		<th>创建者</th>
		<th width="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr>
			<td height="23" align="center"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','pay!input.action?payId=<s:property value='payId' />');"></td>
			<td><s:property value="payTitle" /></td>
			<td><s:property value="payPay" /></td>
			<td><s:property value="payDateDate" /></td>
			<td><s:property value="creator" /></td>
			<td align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('当前付款将被删除,是否继续?')) doSubmit('listPage','pay!delete.action?payId=<s:property  value='payId' />');"></td>
		</tr>
	</s:iterator>
</table>
<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
	<tr valign="top"><td><y:pages value="pageInfo" beanName="pageInfo" formName="listPage" /></td></tr>
</TABLE>
</form>
</body>
</html>