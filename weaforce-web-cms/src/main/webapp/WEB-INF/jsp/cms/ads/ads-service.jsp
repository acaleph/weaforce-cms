<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Service</title>
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
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;广告服务</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="ads-service!list.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="center">
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>栏目</th>
		<th>广告</th>
		<th>服务</th>
		<th>标题</th>
		<th>像册</th>
		<th width="23" height="23"></th>
	</tr>
	<s:iterator value="serviceList">
		<tr>
			<td height="23" align="center"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','ads-service!input.action?adsId=<s:property value='serviceAds.adsId' />&serviceId=<s:property value='serviceService.serviceId' />');"></td>
			<td><s:property value="serviceService.serviceCategory.categoryName" /></td>
			<td><s:property value="serviceAds.adsName" /></td>
			<td><s:property value='serviceService.serviceName' /></td>
			<td><s:property value="adsServiceTitle" /></td>
			<td height="23" align="center"><img class="pointerImage" border="0" title="Album" src="/common/image/zoom-in.gif" onClick="javascript:doSubmit('listPage','ads-service!album.action?adsId=<s:property value='serviceAds.adsId' />&serviceId=<s:property value='serviceService.serviceId' />');"></td>
			<td align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('当前服务将被删除,是否继续?')) doSubmit('listPage','ads-service!delete.action?adsId=<s:property value='serviceAds.adsId' />&serviceId=<s:property  value='serviceService.serviceId' />');"></td>
		</tr>
	</s:iterator>
</table>
<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
	<tr valign="top"><td><y:pages value="pageInfo" beanName="pageInfo" formName="listPage" /></td></tr>
</TABLE>
</form>
</body>
</html>