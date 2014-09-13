<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Calendar</title>
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
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;系统账期</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="period!list.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="center">
		<td>账期年度:</td>
		<td><s:select id="periodYear" name="periodYear" value="%{periodYear}" list="{2009,2010,2011,2012}" required="true" /></td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','period!list.action')"></td>
		<td><input type="button" id="create" name="create" value="Create" onClick="javascript:doSubmit('listPage','period!input.action')">
		</td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>账期ID</th>
		<th>账期名称</th>
		<th>账期年度</th>
		<th>开始日期</th>
		<th>结束日期</th>
		<th>父账期ID</th>
		<th>父账期</th>
		<th width="23"></th>
	</tr>
	<s:iterator value="periodList">
		<tr>
			<td height="23" align="center"><security:authorize ifAnyGranted="ROLE_CONE_CRUD_USER"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','period!input.action?periodId=<s:property  value='periodId' />');"></security:authorize></td>
			<td><s:property value="periodId" /></td>
			<td><s:property value="periodName" /></td>
			<td><s:property value="periodYear" /></td>
			<td><s:property value="periodStartDate" /></td>
			<td><s:property value="periodEndDate" /></td>
			<td><s:property value="periodParent.periodId" /></td>
			<td><s:property value="periodParent.periodName" /></td>
			<td align="center"><security:authorize ifAnyGranted="ROLE_CONE_CRUD_USER"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('是否要删除当前帐期?')) doSubmit('listPage','period!delete.action?periodId=<s:property  value='periodId' />');"></security:authorize></td>
		</tr>
	</s:iterator>
</table>
</form>
</body>
</html>