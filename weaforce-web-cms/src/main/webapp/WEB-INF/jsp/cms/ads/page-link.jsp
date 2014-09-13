<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Page link</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#table-stripe tr:odd').addClass('odd');
	$('#table-stripe tr:even').addClass('even');
		
	$("#table-stripe tr").mouseover(function(){  
        //如果鼠标移到class为stripe的表格的tr上时，执行函数
		$(this).addClass("over");}).mouseout(function(){ 
        //给这行添加class值为over，并且当鼠标一出该行时执行函数
        $(this).removeClass("over");
	})  //移除该行的class
	if ($('#queryLogicLock').val() == '1'){$('#setLock').attr('checked','true');};
	
});
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;定制页面&nbsp;<span><font color="red"><s:actionmessage /></font></span></td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="page-link!list.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="left">
		<td>标题:</td>
		<td><input id="queryTitle" name="queryTitle" value="<s:property value="queryTitle" />" class="text"></td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','page-link!list.action')"></td>
		<td><input type="button" id="create" name="create" value="Create" onClick="javascript:doSubmit('listPage','page-link!input.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>名称</th>
		<th width="23" height="23">结构</th>
		<th width="23" height="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr>
			<td width="23" height="23" align="center"><security:authorize ifAnyGranted="ROLE_CMS_CRUD_USER"> <img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','page-link!input.action?linkId=<s:property value='linkId' />');"></security:authorize></td>
			<td><s:property value="linkTitle" /></td>
			<td width="33" align="center">
				<s:if test="%{linkParent == null}">
					<img class="pointerImage" border="0" title="Tree" src="/common/image/tree_inline.gif" onClick="javascript:doSubmit('listPage','page-link-tree.action?linkId=<s:property value='linkId' />');">
				</s:if>
				</td>
			<td width="23" align="center"><security:authorize ifAnyGranted="ROLE_CMS_CRUD_USER"> <img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('是否要删除当前工程?')) doSubmit('listPage','page-link!delete.action?linkId=<s:property value='linkId' />');"></security:authorize></td>
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