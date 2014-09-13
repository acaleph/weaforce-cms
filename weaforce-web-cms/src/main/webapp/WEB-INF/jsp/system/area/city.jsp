<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function(){
	//$('#theTable > th').parent().addClass('table-head');
	$('#theTable tr:not([th]):odd').addClass('odd');
	$('#theTable tr:not([th]):even').addClass('even');
		
	$("#theTable tr:not([th])").mouseover(function(){  
        //如果鼠标移到class为stripe的表格的tr上时，执行函数
		$(this).addClass("over");}).mouseout(function(){ 
        //给这行添加class值为over，并且当鼠标一出该行时执行函数
        $(this).removeClass("over");
	})  //移除该行的class
});
</script>
</head>
<body>
<table border="0" align="center" width="98%" class="stripe">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;城市字典域</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="city!list.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center">
		<td>英文名称:</td>
		<td><input name="cityQueryNameEn" value="<s:property value="cityQueryNameEn" />" class="text"></td>
		<td>中文名称:</td>
		<td><input name="cityQueryNameCn" value="<s:property value="cityQueryNameCn" />" class="text"></td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','city!list.action')"></td>
		<td><input type="button" id="create" name="create" value="Create" onClick="javascript:doSubmit('listPage','city!input.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>英文名称</th>
		<th>中文名称</th>
		<th>电话</th>
		<th>创建者</th>
		<th width="23" height="23"></th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr>
			<td height="23" align="center"><s:property value="cityId" /></td>
			<td><a href="city!input.action?cityId=<s:property  value='cityId' />"><s:property value="cityNameEn" /></a></td>
			<td><s:property value="cityNameCn" /></td>
			<td><s:property value="cityPhone" /></td>
			<td height="23" align="center"><s:property value="creator" /></td>
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