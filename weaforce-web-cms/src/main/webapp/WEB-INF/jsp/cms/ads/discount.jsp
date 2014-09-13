<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	$('#channelId').change(function(){
		$('#categoryId').buildSelect($('#categoryId'),{selected:0,url:"/cms/ads/category!getCategoryDDL.action",data:{'channelId':$(this).val()}},function(){});
	})
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;广告打折信息</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="discount!list.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="center">
		<td>频道:</td>
		<td><s:select id="channelId" name="channelId" value="%{channelId}" list="channelList" listKey="channelId" listValue="channelName" /></td>
		<td>栏目:</td>
		<td><s:select id="categoryId" name="categoryId" value="%{categoryId}" list="categoryList" listKey="categoryId" listValue="categoryName" /></td>
		<td>标题:</td>
		<td><input id="queryTitle" name="queryTitle" value="<s:property value="queryTitle" />" class="text"></td>
		<td>商家名称:</td>
		<td><input id="queryName" name="queryName" value="<s:property value="queryName" />" class="text"></td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','discount.action')"></td>
		<td><input type="button" id="parse" name="parse" value="Parse" onClick="javascript:doSubmit('listPage','discount!parsePage.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>标题</th>
		<th>商家</th>
		<th>发布</th>
		<th>开始</th>
		<th>结束</th>
		<th>预览</th>
		<th width="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr>
			<td height="23" align="center"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','discount!input.action?discountId=<s:property  value='discountId' />');"></td>
			<td><s:property value="discountTitle" /></td>
			<td><s:property value="discountAds.adsName" /></td>
			<td align="center"><s:if test="%{discountIsParse == 1}"><input type="checkbox" checked="checked" readonly="readonly"></s:if><s:if test="%{discountIsParse == 0}"><input type="checkbox" readonly="readonly"></s:if></td>
			<td><s:property value="discountDateFromDate" /></td>
			<td><s:property value="discountDateToDate" /></td>
			<td><a href="<s:property value='discountUrl' />" target="_blank">View</a></td>
			<td align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('当前打折优惠将被删除,是否继续?')) doSubmit('listPage','discount!delete.action?discountId=<s:property  value='discountId' />');"></td>
		</tr>
	</s:iterator>
</table>
<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
	<tr valign="top"><td><y:pages value="pageInfo" beanName="pageInfo" formName="listPage" /></td></tr>
</TABLE>
</form>
</body>
</html>