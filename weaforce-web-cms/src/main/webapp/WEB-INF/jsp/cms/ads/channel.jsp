<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
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
<title>Channel</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;广告频道字典域</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="channel!list.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr>
		<td>城市:</td>
		<td><s:select id="cityId" name="cityId" value="%{cityId}" list="cityList" listKey="cityId" listValue="cityNameCn" /></td>
		<td>名称:</td>
		<td><input id="queryName" name="queryName" value="<s:property value="queryName" />" class="text"></td>
		<td><input type="button" name="search" value="Search" onClick="javascript:doSubmit('listPage','channel!list.action')"></td>
		<td><input type="button" name="create" value="create" onClick="javascript:doSubmit('listPage','channel!input.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th height="23" width="23"></th>
		<th>名称</th>
		<th>排序</th>
		<th>栏目</th>
		<th>首页</th>
		<th height="23" width="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr>
			<td height="23" width="23" align="center"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','channel!input.action?channelId=<s:property  value='channelId' />');"></td>
			<td><s:property value="channelName" /></td>
			<td><s:property value="channelOrder" /></td>
			<td><s:property value="channelCategoryNames" /></td>
			<td width="23" align="center"><img class="pointerImage" border="0" title="page" src="/common/image/reset_inline.gif" onClick="javascript:if (confirm('是否要转发频道至“首页”?')) doSubmit('listPage','page-link!channel.action?channelId=<s:property  value='channelId' />');"></td>
			<td width="23" align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('当前频道将被删除,是否继续?')) doSubmit('listPage','channel!delete.action?channelId=<s:property  value='channelId' />');"></td>
		</tr>
	</s:iterator>
</table>
<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
	<tr valign="top"><td><y:pages value="pageInfo" beanName="pageInfo" formName="listPage" /></td></tr>
</TABLE>
</form>
</body>
</html>