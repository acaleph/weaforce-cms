<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Currency</title>
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
});
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">系统导航-&gt;货币字典域</td>
	</tr>
</table>
<div class="spacer-10"></div>
<form id="listPage" name="listPage" action="currency!list.action"
	method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg"
	align="center" width="98%">
	<tr align="center">
		<td>代码:<input id="queryCurrencyCode" name="queryCurrencyCode"
			value="<s:property value="queryCurrencyCode" />" class="text"></td>
		<td>名称:<input id="queryCurrencyName" name="queryCurrencyName"
			value="<s:property value="queryCurrencyName" />" class="text"></td>
		<td><input type="submit" id="search" name="search" value="Search"
			onClick="javascript:doSubmit('listPage','currency!list.action')"></td>
		<td><input type="submit" id="create" name="create" value="create"
			onClick="javascript:doSubmit('listPage','currency!input.action')"></td>
	</tr>
</table>
<div class="spacer-10"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1"
	class="stripe" align="center" width="98%" bgcolor="#FFFFFF">
	<tr align="center" class="table-head">
		<th height="23"></th>
		<th>代码</th>
		<th>名称</th>
		<th>有效</th>
		<th></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr align="left">
			<td height="23"><security:authorize ifAnyGranted="ROLE_CONE_CRUD_USER"><img class="pointerImage" border="0"
				title="Edit" src="/common/image/edit_inline.gif"
				onClick="javascript:doSubmit('listPage','currency!input.action?currencyId=<s:property  value='currencyId' />');"></security:authorize></td>
			<td><s:property value="currencyCode" /></td>
			<td><s:property value="currencyName" /></td>
			<td align="center"><s:if test="%{currencyIsActive == 1}">
				<input type="checkbox" checked="checked" readonly="readonly">
			</s:if><s:else>
				<input type="checkbox" readonly="readonly">
			</s:else></td>
			<td><security:authorize ifAnyGranted="ROLE_CONE_CRUD_USER"><img class="pointerImage" border="0" title="Delete"
				src="/common/image/delete_inline.gif"
				onClick="javascript:if (confirm('是否要删除当前币种?')) doSubmit('listPage','currency!delete.action?currencyId=<s:property  value='currencyId' />');"></security:authorize></td>
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