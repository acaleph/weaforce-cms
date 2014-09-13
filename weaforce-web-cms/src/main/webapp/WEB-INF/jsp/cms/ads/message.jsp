<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Message</title>
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
	if ($('#isOk').val() == '1'){$('#setOk').attr('checked','true');}
	$('#channelId').change(function(){
		$('#categoryId').buildSelect($('#categoryId'),{selected:0,url:"/cms/ads/category!getCategoryDDL.action",data:{'channelId':$(this).val()}},function(){});
	})
	$('#categoryId').change(function(){
		$('#adsId').buildSelect($('#adsId'),{selected:0,url:"/cms/ads/ads!getAdsDDL.action",data:{'categoryId':$(this).val()}},function(){});
	})
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;信息管理</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="message!list.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr>
		<td>频道:</td>
		<td><s:select id="channelId" name="channelId" value="%{channelId}" list="channelList" listKey="channelId" listValue="channelName" /></td>
		<td>栏目:</td>
		<td><s:select id="categoryId" name="categoryId" value="%{categoryId}" list="categoryList" listKey="categoryId" listValue="categoryName" /></td>
		<td>广告:</td>
		<td><s:select id="adsId" name="adsId" value="%{adsId}" list="adsList" listKey="adsId" listValue="adsName" /></td>
	</tr>
	<tr>
		<td>标题:</td>
		<td><input id="queryTitle" name="queryTitle" value="<s:property value="queryTitle" />" class="text"></td>
		<td>手机:</td>
		<td><input id="queryMobile" name="queryMobile" value="<s:property value="queryMobile" />" class="text"></td>
		<td colspan="2">
			<table>
				<tr>
		<td>开始:</td>
		<td><input id="queryDateFrom" name="queryDateFrom" value="<s:property value="queryDateFrom" />" size="10" class="text"></td>
		<td>终止:</td>
		<td><input id="queryDateTo" name="queryDateTo" value="<s:property value="queryDateTo" />" size="10" class="text"></td>
		<td>完成:</td>
		<td><input type="hidden" id="isOk" name="isOk" size="1" value="<s:property value='isOk' />"> 
			<input type="checkbox" id="setOk" name="setOk" onclick="javascript:(this.checked)? document.listPage.isOk.value=1:document.listPage.isOk.value=0;"></td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','message!list.action')"></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>标题</th>
		<th>手机</th>
		<th>电话</th>
		<th width="23" height="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr>
			<td height="23" align="center"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','message!input.action?messageId=<s:property  value='messageId' />');"></td>
			<td><s:property value="messageTitle" /></td>
			<td><s:property value="messageMobile" /></td>
			<td><s:property value="messagePhone" /></td>
			<td align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('当前标签将被删除,是否继续?')) doSubmit('listPage','message!delete.action?messageId=<s:property  value='messageId' />');"></td>
		</tr>
	</s:iterator>
</table>
<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
	<tr valign="top"><td><y:pages value="pageInfo" beanName="pageInfo" formName="listPage" /></td></tr>
</TABLE>
</form>
</body>
</html>