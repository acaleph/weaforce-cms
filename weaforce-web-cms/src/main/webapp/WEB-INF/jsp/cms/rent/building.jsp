<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	//$('#table-stripe > th').parent().addClass('table-head');
	$('#table-stripe tr:not([th]):odd').addClass('odd');
	$('#table-stripe tr:not([th]):even').addClass('even');
		
	$("#table-stripe tr:not([th])").mouseover(function(){  
        //如果鼠标移到class为stripe的表格的tr上时，执行函数
		$(this).addClass("over");}).mouseout(function(){ 
        //给这行添加class值为over，并且当鼠标一出该行时执行函数
        $(this).removeClass("over");
	})  //移除该行的class
	$('#zoneId').change(function(){
		$('#adsId').buildSelect($('#adsId'),{selected:0,url:"/cms/rent/building!getAdsDDL.action",data:{'zoneId':$(this).val()}},function(){});
	})
})
</script>
<title>Category</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;栏目字典域</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="building!list.action"
	method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="center">
		<td>所在区:</td>
		<td><s:select id="zoneId" name="zoneId" value="%{zoneId}" list="zoneList" listKey="zoneId" listValue="zoneName" /></td>
		<td>广告商家:</td>
		<td><s:select id="adsId" name="adsId" value="%{adsId}" list="adsList" listKey="adsId" listValue="adsName" /></td>
		<td>名称:<input id="queryName" name="queryName" value="<s:property value="queryName" />" class="text"></td>
		<td><input type="button" name="search" value="Search" onClick="javascript:doSubmit('listPage','building!list.action')"></td>
		<td><input type="button" name="create" value="create" onClick="javascript:doSubmit('listPage','building!input.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1"
	class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>所在区</th>
		<th>名称</th>
		<th>广告商家</th>
		<th width="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr>
			<td height="23" align="center"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','building!input.action?buildingId=<s:property  value='buildingId' />');"></td>
			<td><s:property value="buildingZone.zoneName" /></td>
			<td><s:property value="buildingName" /></td>
			<td><s:property value="buildingAds.adsName" /></td>
			<td align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('当前栏目将被删除,是否继续?')) doSubmit('listPage','building!delete.action?buildingId=<s:property  value='buildingId' />');"></td>
		</tr>
	</s:iterator>
</table>

<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
	<tr valign="top"><td><y:pages value="pageInfo" beanName="pageInfo" formName="listPage" /></td></tr>
</TABLE>
</form>
</body>
</html>