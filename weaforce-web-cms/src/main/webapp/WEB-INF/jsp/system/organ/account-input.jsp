<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Account Form</title>
<script type="text/javascript">
$(document).ready(function(){
	if ($('#accountIsActive').val() == '1'){$('#setActive').attr('checked','true');}
	if ($('#setCustomer').val() == '1'){$('#setCustomer').attr('checked','true');}
	$("#accountForm").validate({
		rules: {
			accountIdentity: {
    			required: true, 
    			remote: "/system/organ/account!checkAccountIdentity.action?accountId=" + $('#accountId').val()
			},
			accountCode: {
    			required: true, 
    			remote: "/system/organ/account!checkAccountCode.action?accountId=" + $('#accountId').val()
			}
		},
		messages: {
			accountIdentity: {
				remote: "帐套已经存在"
			},
			accountCode: {
				remote: "代码已经存在"
			}
		}
	});
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="account.action">系统帐号</a>-&gt;帐号维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="accountForm" name="accountForm" action="account!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp; 基本信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
				<tr>
					<td colspan="6"><input type="hidden" id="accountId" name="accountId" value="<s:property value='accountId' />"></td>
				</tr>
				<tr>
					<td height="30">中文名称:</td>
					<td  colspan="3"><input id="accountNameCn" name="accountNameCn" value="<s:property value='accountNameCn' />" size="58"></td>
					<td>英文名称:</td>
					<td colspan="3"><input id="accountNameEn" name="accountNameEn" value="<s:property value='accountNameEn' />" size="58"></td>
				</tr>
				<tr>
					<td>主页:</td>
					<td colspan="3"><input id="accountWeb" name="accountWeb" value="<s:property value='accountWeb' />" size="58"></td>
					<td>Email:</td>
					<td colspan="3"><input id="accountEmail" name="accountEmail" value="<s:property value='accountEmail' />" size="58"></td>
				</tr>
				<tr>
					<td height="30">简称:</td>
					<td><input id="accountShortName" name="accountShortName" value="<s:property value='accountShortName' />"></td>
					<td height="30">代码:</td>
					<td><input id="accountCode" name="accountCode" value="<s:property value='accountCode' />" ></td>
					<td height="30">身份:</td>
					<td><input id="accountIdentity" name="accountIdentity" value="<s:property value='accountIdentity' />" ></td>
					<td>邮编:</td>
					<td><input id="accountPostcode" name="accountPostcode" value="<s:property value='accountPostcode' />"></td>
				</tr>
				<tr>
					<td height="30">联系人:</td>
					<td><input id="accountDefaultContact" name="accountDefaultContact" value="<s:property value='accountDefaultContact' />"></td>
					<td>手机:</td>
					<td><input id="accountContactCellphone" name="accountContactCellphone" value="<s:property value='accountContactCellphone' />"></td>
					<td>电话:</td>
					<td><input id="accountPhone" name="accountPhone" value="<s:property value='accountPhone' />"></td>
					<td>传真:</td>
					<td><input id="accountFax" name="accountFax" value="<s:property value='accountFax' />"></td>
				</tr>
				<tr>
					<td height="30">地址:</td>
					<td colspan="5"><input id="accountAddress" name="accountAddress" value="<s:property value='accountAddress' />" size="68"></td>
					<td>有效:</td>
					<td>
						<input type="hidden" id="accountIsActive" name="accountIsActive" size="1" value="<s:property value='accountIsActive' />"> 
						<input type="checkbox" id="setActive" name="setActive" onclick="javascript:(this.checked)? document.accountForm.accountIsActive.value=1:document.accountForm.accountIsActive.value=0;" />
					</td>
				</tr>
				<tr><td height="30" colspan="8"><hr size="1"></td></tr>
				<tr>
					<td colspan="8" height="33">
						<table>
							<tr>
								<td><input onClick="doSubmit('accountForm','account!save.action');" type="button" id="save" name="save" value="Save" class="button"></td>
								<td><input onClick="doSubmit('accountForm','account!doDelete.action');" type="button" id="delete" name="delete" value="Delete" class="button"></td>
								<td><input onClick="doSubmit('accountForm','/system/user!input.action');" type="button" id="user" name="user" value="User" class="button" title="新增用户"></td>
								<td><input onClick="doSubmit('accountForm','department!input.action');" type="button" id="department" name="department" value="Department" class="button" title="新增部门"></td>
								<td><input onClick="doSubmit('accountForm','staff!input.action');" type="button" id="staff" name="staff" value="Staff" class="button" title="新增职员"></td>
								<td><input onClick="javascript:window.history.back();" type="button" id="return" name="return" value="Return" class="button"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>