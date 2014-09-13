<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Organ Form</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#organForm').validate();
});
</script>
</head>
<body>
<table>
	<tr>
		<td colspan="4"><input type="hidden" id="organId" name="organId"
			value="<s:property value='organId' />"> <input type="text"
			id="organFid" name="organFid"
			value="<s:property value='organ.organParent.organId' />"></td>
	</tr>
	<tr>
		<td>机构代码</td>
		<td><input type="text" id="organCode" name="organCode"
			value="<s:property value='organCode' />" class="required"></td>
		<td>全 称</td>
		<td><input type="text" id="organFullName" name="organFullName"
			value="<s:property value='organFullName' />" class="required"></td>
	</tr>
	<tr>
		<td>简 称</td>
		<td><input type="text" id="organName" name="organName"
			value="<s:property value='organName' />"></td>
		<td>负 责 人</td>
		<td><input type="text" id="organManager" name="organManager"
			value="<s:property value='organManager' />"></td>
	</tr>
	<tr>
		<td>联系电话(负责人)</td>
		<td><input type="text" id="organManagerPhone"
			name="organManagerPhone"
			value="<s:property value='organManagerPhone' />"></td>
		<td>手机号码(负责人)</td>
		<td><input type="text" id="organManagerCellphone"
			name="organManagerCellphone"
			value="<s:property value='organManagerCellphone' />"></td>
	</tr>
	<tr>
		<td>注册地址</td>
		<td><input type="text" id="organAddressRegistry"
			name="organAddressRegistry"
			value="<s:property value='organAddressRegistry' />"></td>
		<td>联系电话</td>
		<td><input type="text" id="organPhone" name="organPhone"
			value="<s:property value='organPhone' />"></td>
	</tr>
	<tr>
		<td>信函地址</td>
		<td><input type="text" id="organAddressMail"
			name="organAddressMail"
			value="<s:property value='organAddressMail' />"></td>
		<td>邮政编码</td>
		<td><input type="text" id="organPostcode" name="organPostcode"
			value="<s:property value='organPostcode' />"></td>
	</tr>
	<tr>
		<td>Email</td>
		<td><input type="text" id="organEmail" name="organEmail"
			value="<s:property value='organEmail' />"></td>
		<td>传 真</td>
		<td><input type="text" id="organFax" name="organFax"
			value="<s:property value='organFax' />"></td>
	</tr>
	<tr>
		<td>开 户 行</td>
		<td><input type="text" id="organBank" name="organBank"
			value="<s:property value='organBank' />"></td>
		<td>帐 号</td>
		<td><input type="text" id="organBankAccount"
			name="organBankAccount"
			value="<s:property value='organBankAccount' />"></td>
	</tr>
	<tr bgcolor="#FFFFFF">
		<td colspan="4" height="28">
		<table>
			<tr>
				<td><input type="button" id="save" name="save" value="Save"
					onClick="javascript:doSubmit('organForm','organ!save.action');"
					class="button"></td>
				<td><input type="button" id="delete" name="delete" value="Delete"
					onClick="javascript:doSubmit('organForm','organ!delete.action');"
					class="button"></td>
				<td><input onClick="javascript:window.history.back();"
					type="button" name="Return" value="Return" class="button"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>