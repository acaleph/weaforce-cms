<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Help</title>
<script type="text/javascript">
	$(document).ready(function(){
		$('#listForm').ajaxForm({
			target: '#helpForm',
			success: function() {
				$('#helpForm').fadeIn('slow');
			}
		});
		
		$("#checkoutAsGuest").click(function() {
			$('#helpForm').load('help!input.action');
			return false;
		});
	});
</script>
</head>
<body>
<table border="0" align="center" width="100%">
	<tr>
		<td valign="middle" width="79%" height="29" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		系统导航 - &gt; 帮助信息</td>
		<td><a href="help!input.action">创建新的帮助</a>&nbsp;&nbsp;</td>
		<td>
		<div class="buttons"><input class="button" type="submit"
			name="submitBottom" id="submitBottom"
			value="compare selected products" /><a class="button"
			href="help!input.action" id="checkoutAsGuest">check out as
		guest</a></div>
		</td>
	</tr>
</table>
<div id="helpForm"></div>
<s:form id="listForm" action="help!input.action" method="post">
	<table border="0" cellpadding="0" cellspacing="1" class="stripe"
		align="center" width="96%">
		<tr align="center">
			<td>英文名称:<input name="helpQueryNameEn"
				value="<s:property value="helpQueryNameEn" />" class="text"></td>
			<td>中文名称:<input name="helpQueryNameCn"
				value="<s:property value="helpQueryNameCn" />" class="text"></td>
			<td><input type="submit" name="search" value="Search"></td>
		</tr>
	</table>

	<table id="theTable" border="0" cellpadding="0" cellspacing="1"
		class="stripe" align="center" width="96%">
		<tr align="center" class="table-head">
			<td height="23">ID</td>
			<td>英文名称</td>
			<td>中文名称</td>
			<td>电话</td>
			<td>WEB</td>
			<td>EMAIL</td>
		</tr>
		<s:iterator value="pageInfo.result">
			<tr align="left" onmouseover="selectbar(this)"
				onmouseout="unselectbar(this)">
				<td height="23"><s:property value="helpId" /></td>
				<td><a
					href="help!input.action?helpId=<s:property  value='helpId' />" id="editHelp"><s:property
					value="helpTitle" /></a></td>
				<td><s:property value="helpPicture" /></td>
				<td><s:property value="helpInfo" /></td>
				<td><s:property value="date" /></td>
				<td><s:property value="creator" /></td>
			</tr>
		</s:iterator>
	</table>

	<TABLE cellSpacing="1" cellPadding="2" width="100%" border="0">
		<tr valign="top">
			<td><y:pages value="pageInfo" beanName="pageInfo"
				formName="forms(0)" /></td>
		</tr>
	</TABLE>
</s:form>
</body>
</html>