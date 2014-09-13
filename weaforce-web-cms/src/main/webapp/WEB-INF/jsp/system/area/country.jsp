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
		<td valign="middle" height="29" align="left">系统导航-&gt;国家字典域</td>
	</tr>
</table>
<form id="listPage" name="listPage" action="country!list.action"
	method="post">
<table border="0" cellpadding="0" cellspacing="1" class="stripe"
	align="center" width="98%">
	<tr align="center">
		<td>英文名称:<input name="countryQueryNameEn"
			value="<s:property value="countryQueryNameEn" />" class="text"></td>
		<td>中文名称:<input name="countryQueryNameCn"
			value="<s:property value="countryQueryNameCn" />" class="text"></td>
		<td><input type="submit" name="search" value="Search"
			onClick="javascript:doSubmit('listPage','country!list.action')"></td>
		<td><input type="submit" name="new" value="create"
			onClick="javascript:doSubmit('listPage','country!input.action')"></td>
	</tr>
</table>

<table id="theTable" border="0" cellpadding="0" cellspacing="1"
	class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th height="23">ID</th>
		<th>英文名称</th>
		<th>中文名称</th>
		<th>电话</th>
		<th>WEB</th>
		<th>EMAIL</th>
	</tr>
	<s:iterator value="pageInfo.result">
		<tr align="left">
			<td height="23"><s:property value="countryId" /></td>
			<td><a
				href="country!input.action?countryId=<s:property  value='countryId' />"><s:property
				value="countryNameEn" /></a></td>
			<td><s:property value="countryNameCn" /></td>
			<td><s:property value="countryPhone" /></td>
			<td><s:property value="countryDomain" /></td>
			<td><s:property value="countryZone" /></td>
		</tr>
	</s:iterator>
</table>

<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
	<tr valign="top">
		<td><y:pages value="pageInfo" beanName="pageInfo"
			formName="listPage" /></td>
	</tr>
</TABLE>
</form>
</body>
</html>