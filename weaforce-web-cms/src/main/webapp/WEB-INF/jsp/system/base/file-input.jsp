<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript"
	src="/common/jquery/plugin/jquery.blockUI.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	 var options = {
                    //target:'#uploadMsg',   // target element(s) to be updated with server response
                    url: "/system/base/file!upload.action",
                    //data:{"queryModuleId":$('#queryModuleId').val()},
                    beforeSubmit:showRequest,  // pre-submit callback
                    success:       showResponse  // post-submit callback
     };
     // bind form using 'ajaxForm'
    $('#fileForm').ajaxForm(options);
    function showRequest(formData, jqForm, options) {
    	$('#uploadMsg').empty();
        $.blockUI();
        return true;
    }
    // post-submit callback
   function showResponse(responseText, statusText)  {
   		$.unblockUI();
   		window.location="/system/base/file.action?queryModuleId=" + $('#queryModuleId').val();
   }
})
</script>
<title>文件管理</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="file.action">MINI网盘</a>-&gt;文件上载</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="fileForm" name="fileForm" action="file!upload.action" method="POST" enctype="multipart/form-data">
<table border="0" cellspacing="0" cellpadding="0" align="center" class="stripe" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp;<img src="/common/image/title-icon0.gif" width="16" height="13">&nbsp;上传文件</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td colspan="4"><input type="hidden" id="queryModuleId" name="queryModuleId" value="<s:property value='queryModuleId'/>">
				</td>
			</tr>
			<tr>
				<td>文件1:</td>
				<td><input type="file" id="upload" name="upload" size="28"></td>
				<td>版本:</td>
				<td><s:select name="fileVersionMaxs" list="versionList"
					listKey="id" listValue="name" required="true" />-<s:select
					name="fileVersionMids" list="versionList" listKey="id"
					listValue="name" required="true" />-<s:select
					name="fileVersionMins" list="versionList" listKey="id"
					listValue="name" required="true" /></td>
			</tr>
			<tr>
				<td>文件2:</td>
				<td><input type="file" id="upload" name="upload" size="28"></td>
				<td>版本:</td>
				<td><s:select name="fileVersionMaxs" list="versionList"
					listKey="id" listValue="name" required="true" />-<s:select
					name="fileVersionMids" list="versionList" listKey="id"
					listValue="name" required="true" />-<s:select
					name="fileVersionMins" list="versionList" listKey="id"
					listValue="name" required="true" /></td>
			</tr>
			<tr>
				<td>文件3:</td>
				<td><input type="file" id="upload" name="upload" size="28"></td>
				<td>版本:</td>
				<td><s:select name="fileVersionMaxs" list="versionList"
					listKey="id" listValue="name" required="true" />-<s:select
					name="fileVersionMids" list="versionList" listKey="id"
					listValue="name" required="true" />-<s:select
					name="fileVersionMins" list="versionList" listKey="id"
					listValue="name" required="true" /></td>
			</tr>
			<tr>
				<td>文件4:</td>
				<td><input type="file" id="upload" name="upload" size="28"></td>
				<td>版本:</td>
				<td><s:select name="fileVersionMaxs" list="versionList"
					listKey="id" listValue="name" required="true" />-<s:select
					name="fileVersionMids" list="versionList" listKey="id"
					listValue="name" required="true" />-<s:select
					name="fileVersionMins" list="versionList" listKey="id"
					listValue="name" required="true" /></td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td height="30" colspan="4"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="6" valign="middle">&nbsp;&nbsp;<input onClick="doSubmit('fileForm','file!upload.action');" type="button" id="uploadFile" name="uploadFile" value="Upload" class="button"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>