<%@page contentType="text/html; charset=UTF-8" errorPage=""%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#table-stripe tr:not([th]):odd').addClass('odd');
	$('#table-stripe tr:not([th]):even').addClass('even');
		
	$("#table-stripe tr:not([th])").mouseover(function(){  
        //如果鼠标移到class为stripe的表格的tr上时，执行函数
		$(this).addClass("over");}).mouseout(function(){ 
        //给这行添加class值为over，并且当鼠标一出该行时执行函数
        $(this).removeClass("over");
	})  //移除该行的class
	$('#cityId').change(function(){
		$('#zoneId').buildSelect($('#zoneId'),{selected:0,url:"/system/area/zone!getZoneDDL.action",data:{'cityId':$(this).val()}},function(){});
	})
	$('#channelId').change(function(){
		$('#categoryId').buildSelect($('#categoryId'),{selected:0,url:"/cms/ads/category!getCategoryDDL.action",data:{'channelId':$(this).val()}},function(){});
	})
})
</script>
<title>文章管理</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;广告管理</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="ads!list.action" method="POST">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="center">
		<td>城市:</td>
		<td><s:select id="cityId" name="cityId" value="%{cityId}" list="cityList" listKey="cityId" listValue="cityNameCn" /></td>
		<td>城区:</td>
		<td><s:select id="zoneId" name="zoneId" value="%{zoneId}" list="zoneList" listKey="zoneId" listValue="zoneName" /></td>
		<td>频道:</td>
		<td><s:select id="channelId" name="channelId" value="%{channelId}" list="channelList" listKey="channelId" listValue="channelName" /></td>
		<td>栏目:</td>
		<td><s:select id="categoryId" name="categoryId" value="%{categoryId}" list="categoryList" listKey="categoryId" listValue="categoryName" /></td>
		<td>标题:</td>
		<td><input id="queryName" name="queryName" value="<s:property value='queryName'/>"></td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','ads!list.action')"></td>
		<td><input type="button" id="create" name="create" value="Create" onClick="javascript:doSubmit('listPage','ads!input.action')"></td>
		<td><input type="button" id="parse" name="parse" value="Parse" onClick="javascript:doSubmit('listPage','ads!parseAll.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>标题</th>
		<th>网站</th>
		<th>发布</th>
		<th>打折</th>
		<th>广告目录类别</th>
		<th>创建日期</th>
		<th width="23" height="23"></th>
	</tr>
	<s:iterator id="ads" value="pageInfo.result">
		<tr>
			<td height="23" align="center"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','ads!input.action?adsId=<s:property  value='adsId' />');"></td>
			<td><s:property value="adsName" /></td>
			<td><s:property value="adsWeb" /></td>
			<td align="center"><img class="pointerImage" border="0" title="page" src="/common/image/reset_inline.gif" onClick="javascript:if (confirm('是否要转发栏目至“首页”?')) doSubmit('listPage','page-link!ads.action?adsId=<s:property  value='adsId' />');">
			<s:if test="%{adsIsParse == 1}">
				<input type="checkbox" checked="checked" readonly="readonly">
				<img class="pointerImage" border="0" title="unparse" src="/common/image/reset_inline.gif" onClick="javascript:if (confirm('是否要解除发布文章?')) doSubmit('listPage','ads!unparse.action?adsId=<s:property  value='adsId' />');">
			</s:if><s:if test="%{adsIsParse == 0}">
				<input type="checkbox" readonly="readonly">
				<img class="pointerImage" border="0" title="parse" src="/common/image/reset_inline.gif" onClick="javascript:if (confirm('是否要发布(parse)文章?')) doSubmit('listPage','ads!parse.action?adsId=<s:property  value='adsId' />');">
			</s:if></td>
			<td><a href="discount!input.action?adsId=<s:property value='adsId' />&channelId=<s:property value='channelId' />&categoryId=<s:property value='categoryId' />">新建</a></td>
			<td><s:iterator value="adsService"><a href="ads-service!input.action?adsId=<s:property value='adsId' />&serviceId=<s:property value='serviceId' />"><s:property value="serviceName" /></a>&nbsp;&nbsp;</s:iterator></td>
			<td><s:property value="date" /></td>
			<td align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('当前广告将被删除,是否继续?')) doSubmit('listPage','ads!delete.action?adsId=<s:property  value='adsId' />');"></td>
		</tr>
	</s:iterator>
</table>
<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
	<tr valign="top">
		<td><y:pages value="pageInfo" beanName="pageInfo" formName="listPage" /></td>
	</tr>
</TABLE>
</form>
</body>
</html>