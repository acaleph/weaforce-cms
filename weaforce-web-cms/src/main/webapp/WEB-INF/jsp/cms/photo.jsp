<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Photo</title>
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
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;像册管理</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="photo!list.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="center">
		<td>像册<font color="red">*</font>:</td>
		<td><s:select id="albumId" name="albumId" value="%{albumId}" list="albumList" listKey="albumId" listValue="albumName" /></td>
		<td>名称:<input id="queryName" name="queryName" value="<s:property value="queryName" />" class="text"></td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','photo!list.action')"></td>
		<td><input type="button" id="upload" name="upload" value="Upload" onClick="javascript:if ($('#albumId').val() > 0) doSubmit('listPage','photo!photo.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>名称</th>
		<th>宽度</th>
		<th>高度</th>
		<th>创建日期</th>
		<th>URL</th>
		<th width="23"></th>
		<th width="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr>
			<td height="23" align="center"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','photo!input.action?photoId=<s:property  value='photoId' />');"></td>
			<td><s:property value="photoName" /></td>
			<td><s:property value="photoWidth" /></td>
			<td><s:property value="photoHeight" /></td>
			<td><s:property value="date" /></td>
			<td><s:property value="photoUrl" /></td>
			<td><img class="pointerImage" border="0" title="page" src="/common/image/reset_inline.gif" onClick="javascript:if (confirm('是否要转发(page-link)文章?')) doSubmit('listPage','page-link!photo.action?photoId=<s:property  value='photoId' />');"></td>
			<td align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('当前照片将被删除,是否继续?')) doSubmit('listPage','photo!delete.action?photoId=<s:property  value='photoId' />');"></td>
		</tr>
	</s:iterator>
</table>
<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
	<tr valign="top"><td><y:pages value="pageInfo" beanName="pageInfo" formName="listPage" /></td></tr>
</TABLE>
</form>
</body>
</html>