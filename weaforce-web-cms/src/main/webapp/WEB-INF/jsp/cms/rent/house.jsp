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
		document.all.buildingId.options.length=0;
	})
	$('#adsId').change(function(){
		if ($('#adsId').val() > 0)
			$('#buildingId').buildSelect($('#buildingId'),{selected:0,url:"/cms/rent/building!getBuildingDDL.action",data:{'zoneId':$('#zoneId').val(),'adsId':$(this).val()}},function(){});
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
<form id="listPage" name="listPage" action="house!list.action"
	method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="center">
		<td>所在区:</td>
		<td><s:select id="zoneId" name="zoneId" value="%{zoneId}" list="zoneList" listKey="zoneId" listValue="zoneName" /></td>
		<td>广告商家:</td>
		<td><s:select id="adsId" name="adsId" value="%{adsId}" list="adsList" listKey="adsId" listValue="adsName" /></td>
		<td>建筑:</td>
		<td><s:select id="buildingId" name="buildingId" value="%{buildingId}" list="buildingList" listKey="buildingId" listValue="buildingName" /></td>
		<td>名称:<input id="queryName" name="queryName" value="<s:property value="queryName" />" class="text"></td>
		<td><input type="button" name="search" value="Search" onClick="javascript:if ($('#adsId').val() > 0) doSubmit('listPage','house.action')"></td>
		<td><input type="button" name="create" value="Create" onClick="javascript:doSubmit('listPage','house!input.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1"
	class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>户型</th>
		<th>标题</th>
		<th>面积</th>
		<th>月租</th>
		<th>联系人</th>
		<th>手机</th>
		<th>电话</th>
		<th>照片</th>
		<th width="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr>
			<td height="23" align="center"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','house!input.action?houseId=<s:property  value='houseId' />');"></td>
			<td><s:property value="houseType.typeName" /></td>
			<td><s:property value="houseTitle" /></td>
			<td><s:property value="houseSquare" /></td>
			<td><s:property value="houseTag.tagTag" /></td>
			<td><s:property value="houseContact" /></td>
			<td><s:property value="houseContactMobile" /></td>
			<td><s:property value="houseContactPhone" /></td>
			<td><a href="photo!input.action?houseId=<s:property value='houseId'/>">上传</a>&nbsp;&nbsp;<a href="photo.action?houseId=<s:property value='houseId'/>">浏览</a></td>
			<td align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('当前栏目将被删除,是否继续?')) doSubmit('listPage','house!delete.action?houseId=<s:property  value='houseId' />');"></td>
		</tr>
	</s:iterator>
</table>

<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
	<tr valign="top"><td><y:pages value="pageInfo" beanName="pageInfo" formName="listPage" /></td></tr>
</TABLE>
</form>
</body>
</html>