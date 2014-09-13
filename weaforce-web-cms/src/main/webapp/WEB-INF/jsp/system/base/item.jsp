<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Base Item</title>
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
});
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;通用字典域</td>
	</tr>
</table>
<form id="listPage" name="listPage" action="item!list.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr>
		<td>字典:</td>
		<td><s:select name="dictionaryId" value="%{dictionaryId}" list="dictionaryList" listKey="dictionaryId" listValue="dictionaryName" /></td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','item!list.action')"></td>
		<td><input type="button" id="create" name="create" value="Create" onClick="javascript:doSubmit('listPage','item!input.action')"></td>
	</tr>
</table>

<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>排序</th>
		<th>名称</th>
		<th>中文说明A</th>
		<th>中文说明B</th>
		<th>中文说明C</th>
		<th>中文说明D</th>
		<th>创建时间</th>
		<th>有效</th>
		<th>锁住</th>
		<th width="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr align="left">
			<td height="23" align="center"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','item!input.action?itemId=<s:property  value='itemId' />');"></td>
			<td height="23"><s:property value="itemGroupOrder" /></td>
			<td height="23"><s:property value="itemName" /></td>
			<td><s:property value="itemAnnexa" /></td>
			<td><s:property value="itemAnnexb" /></td>
			<td><s:property value="itemAnnexc" /></td>
			<td><s:property value="itemAnnexd" /></td>
			<td><s:property value="date" /></td>
			<td><s:property value="itemIsActive" /></td>
			<td><s:property value="logicLock" /></td>
			<td align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('当前项目将被删除,是否继续?')) doSubmit('listPage','item!delete.action?itemId=<s:property  value='itemId' />');"></td>
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
