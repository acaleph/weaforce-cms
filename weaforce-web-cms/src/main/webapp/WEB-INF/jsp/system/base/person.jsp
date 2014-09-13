<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Insert title here</title>
</head>
<body onload="alternateRowColor('theTable',1);">
<table border="0" align="center" width="100%">
	<tr>
		<td valign="middle" width="79%" height="29" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		系统导航 - &gt; 地址字典域</td>
		<td><a href="address!input.action">创建新的地址字典</a></td>
	</tr>
</table>
<s:form action="address!list.action" method="post">
	<table border="0" cellpadding="0" cellspacing="1" class="stripe"
		align="center" width="96%">
		<tr align="center">
			<td>国家:<s:select name="personQueryAddressCountryId"
				value="%{personQueryAddressCountryId}" list="personAddressCountry"
				listKey="countryId" listValue="countryNameCn" required="true" /></td>
			<td>省份:<s:select name="personQueryAddressProvinceId"
				value="%{personQueryAddressProvinceId}" list="personAddressProvince"
				listKey="provinceId" listValue="provinceNameCn" required="true" /></td>
			<td>城市:<s:select name="personQueryAddressCityId"
				value="%{personQueryAddressCityId}" list="personAddressCity"
				listKey="cityId" listValue="cityNameCn" required="true" /></td>
			<td>姓名:<input name="personQueryName"
				value="<s:property value="personQueryName" />" class="text"></td>
			<td><input type="submit" name="search" value="Search"></td>
		</tr>
	</table>

	<table id="theTable" border="0" cellpadding="0" cellspacing="1"
		class="stripe" align="center" width="96%">
		<tr align="center" class="table-head">
			<td height="23">ID</td>
			<td>国家</td>
			<td>省份</td>
			<td>城市</td>
			<td>姓名</td>
		</tr>
		<s:iterator value="pageInfo.result">
			<tr align="left" onmouseover="selectbar(this)"
				onmouseout="unselectbar(this)">
				<td height="23"><s:property value="personId" /></td>
				<td><s:property
					value="person.personAddress.addressCountry.countryNameCn" /></td>
				<td><s:property
					value="person.personAddress.addressProvince.provinceNameCn" /></td>
				<td><s:property
					value="person.personAddress.addressCity.cityNameCn" /></td>
				<td><a
					href="address!input.action?addressId=<s:property  value='addressId' />"><s:property
					value="personName" /></a></td>
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