<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Insert title here</title>
</head>
<body>
<table border="0" align="center" width="100%">
	<tr>
		<td valign="middle" width="79%" height="29" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		系统导航 - &gt; <a href="address.action">地址字典域</a> - &gt;创建/维护新的字典</td>
	</tr>
</table>
<table id="theTable" border="0" cellpadding="0" cellspacing="1"
	class="stripe" align="center" width="96%">
	<tr class="table-head">
		<td height="23" width="599" colspan="2">&nbsp; 字典信息</td>
	</tr>
	<tr>
		<td width="598" align="center" colspan="2"></td>
	</tr>
	<tr>
		<td width="198" align="center"><img border="0"
			src="/common/image/main/CreateArea.jpg" width="131" height="155"
			alt=""></td>
		<td width="511" align="center"><s:form
			action="person!save.action" method="POST">
			<s:hidden name="person.personId" value="%{person.personId}" />
			<table width="480" border="0" cellpadding="0" cellspacing="0"
				class="stripe" bgcolor="#FFFFFF">
				<tr bgcolor="#FFFFFF">
					<td height="30" width="15%">姓名:</td>
					<td height="30"><s:textfield name="person.personName"
						value="%{person.personName}" /></td>
					<td height="30" width="15%">性别:</td>
					<td height="30"><s:textfield name="person.personSex"
						value="%{person.personSex}" /></td>
					<td height="30" width="15%">有效:</td>
					<td height="30"><s:checkbox name="person.personIsActive"
						value="%{person.personIsActive}" /></td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td height="30" width="15%">身份证:</td>
					<td height="30" colspan="6"><s:textfield
						name="person.personIdentity" value="%{person.personIdentity}" /></td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td height="30" width="15%">地址:</td>
					<td height="30" colspan="6"><s:select
						name="person.personAddress.addressId"
						value="%{person.personAddress.addressId}" list="personAddress"
						listKey="addressId" listValue="addressLocation" required="true" /></td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td height="30" align="left" width="20%" colspan="2">
					<hr size="1">
					</td>
				</tr>
				<tr bgcolor="#FFFFFF">
					<td colspan="8" height="33">
					<div align="right"><input
						onClick="doSimpleSubmit('0','person!save.action');"
						type="button" name="Save" value="Save" class="button">
					&nbsp;&nbsp;<input
						onClick="doSimpleSubmit('0','person!doDelete.action');"
						type="button" name="Delete" value="Delete" class="button">
					&nbsp;&nbsp;<input onClick="javascript:window.history.back();"
						type="button" name="Return" value="Return" class="button"></div>
					</td>
				</tr>
			</table>
		</s:form></td>
	</tr>
</table>
</body>
</html>