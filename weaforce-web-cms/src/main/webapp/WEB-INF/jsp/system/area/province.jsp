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
<table border="0" align="center" width="98%" class="stripe">
	<tr>
		<td valign="middle" height="29" align="left">系统导航-&gt;省（州）字典域</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="province!list.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center">
		<td>英文名称:<input name="provinceQueryNameEn"
			value="<s:property value="provinceQueryNameEn" />" class="text"></td>
		<td>中文名称:<input name="provinceQueryNameCn"
			value="<s:property value="provinceQueryNameCn" />" class="text"></td>
		<td><input type="submit" name="search" value="Search"
			onClick="javascript:doSubmit('listPage','province!list.action')"></td>
		<td><input type="submit" name="new" value="create"
			onClick="javascript:doSubmit('listPage','province!input.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th height="23">ID</th>
		<th>英文名称</th>
		<th>中文名称</th>
		<th>创建者</th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr align="left">
			<td height="23"><s:property value="provinceId" /></td>
			<td><a href="province!input.action?provinceId=<s:property value='provinceId' />"><s:property
				value="provinceNameEn" /></a></td>
			<td><s:property value="provinceNameCn" /></td>
			<td><s:property value="creator" /></td>
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