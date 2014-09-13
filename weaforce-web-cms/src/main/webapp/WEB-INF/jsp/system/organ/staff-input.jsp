<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Member Form</title>
<script type="text/javascript">
$(document).ready(function(){
	if ($('#staffIsActive').val() == '1'){$('#setActive').attr('checked','true');}
	$("#staffForm").validate({
		rules: {
			staffLogin: {
					email:true,
        			required: true, 
        			remote: "/system/organ/staff!checkStaffLogin.action"
    			}
    		},
    	messages: {
			staffLogin: {
				remote: "非法或者不存在的登录名"
			}
		}
	});
});

</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="staff.action">成员字典域</a>-&gt;部门成员</td>
	</tr>
</table>
<form id="staffForm" name="staffForm" action="staff!save.action"
	method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp;<img src="/common/image/title-icon0.gif" width="16" height="13">&nbsp;成员信息</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="accountId" name="accountId" value="<s:property value='accountId' />"></td>
				<td><input type="hidden" id="staffId" name="staffId" value="<s:property value='staffId' />"></td>
				<td><input type="hidden" id="account" name="account" value="<s:property value='account' />" readonly="readonly" ></td>
			</tr>
			<tr>
				<td height="30">部门:</td>
				<td><s:select id="departmentId" name="departmentId" value="%{staffDepartment.departmentId}" list="departmentList" listKey="departmentId" listValue="departmentName" required="true" /></td>
				<td>姓名:</td>
				<td><input id="staffName" name="staffName" value="<s:property value='staffName' />" class="required"></td>
			</tr>
			<tr>
				<td height="30">登录(Email):</td>
				<td><input id="staffLogin" name="staffLogin" value="<s:property value='staffLogin' />" style="TEXT-TRANSFORM: uppercase;" size="38"></td>
				<td>有效:</td>
				<td>
					<input type="hidden" id="staffIsActive" name="staffIsActive" size="1" value="<s:property value='staffIsActive' />"> 
					<input type="checkbox" id="setActive" name="setActive" onclick="javascript:(this.checked)? document.staffForm.staffIsActive.value=1:document.staffForm.staffIsActive.value=0;" />
				</td>
			</tr>
			<tr>
				<td height="30" colspan="4"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="4" height="33">
				<table>
					<tr>
						<td><input onClick="doSubmit('staffForm','staff!save.action');" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="doSubmit('staffForm','staff!delete.action');" type="button" id="delete" name="delete" value="Delete" class="button"></td>
						<td><input onClick="doSubmit('staffForm','staff!lock.action');" type="button" id="lock" name="lock" value="Lock" class="button"></td>
						<td><input onClick="javascript:window.history.back();" type="button" name="Return" value="Return" class="button"></td>
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