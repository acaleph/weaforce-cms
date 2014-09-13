<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>照片上传</title>
<script type="text/javascript">
$(document).ready(function(){$('#photoForm').validate();})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="photo.action">照片管理</a>-&gt;照片维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="photoForm" name="photoForm" action="album!save.action" method="POST" enctype="multipart/form-data">
<table border="0" cellspacing="0" cellpadding="0" align="center" class="stripe" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp;<img src="/common/image/title-icon0.gif" width="16" height="13">&nbsp;上传文件</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td colspan="8"><input type="hidden" id="albumId" name="albumId" value="<s:property value='albumId'/>"></td>
			</tr>
			<tr>
				<td>照片名称1:</td>
				<td><input id="photoNames" name="photoNames" size="18" class="required"></td>
				<td>文件1:</td>
				<td><input type="file" id="upload" name="upload" size="28"></td>
				<td>宽度1:</td>
				<td height="30"><s:select id="photoWidths" name="photoWidths" list="{150,300,450}" required="true" /></td>
				<td>高度1:</td>
				<td height="30"><s:select id="photoHeights" name="photoHeights" list="{150,300,450}" required="true" /></td>
				
			</tr>
			<tr>
				<td>照片名称2:</td>
				<td><input id="photoNames" name="photoNames" size="18" class="required"></td>
				<td>文件2:</td>
				<td><input type="file" id="upload" name="upload" size="28"></td>
				<td>宽度2:</td>
				<td height="30"><s:select id="photoWidths" name="photoWidths" list="{150,300,450}" required="true" /></td>
				<td>高度2:</td>
				<td height="30"><s:select id="photoHeights" name="photoHeights" list="{150,300,450}" required="true" /></td>
			</tr>
			<tr>
				<td>照片名称3:</td>
				<td><input id="photoNames" name="photoNames" size="18" class="required"></td>
				<td>文件3:</td>
				<td><input type="file" id="upload" name="upload" size="28"></td>
				<td>宽度3:</td>
				<td height="30"><s:select id="photoWidths" name="photoWidths" list="{150,300,450}" required="true" /></td>
				<td>高度3:</td>
				<td height="30"><s:select id="photoHeights" name="photoHeights" list="{150,300,450}" required="true" /></td>
			</tr>
			<tr>
				<td>照片名称4:</td>
				<td><input id="photoNames" name="photoNames" size="18" class="required"></td>
				<td>文件4:</td>
				<td><input type="file" id="upload" name="upload" size="28"></td>
				<td>宽度4:</td>
				<td height="30"><s:select id="photoWidths" name="photoWidths" list="{150,300,450}" required="true" /></td>
				<td>高度4:</td>
				<td height="30"><s:select id="photoHeights" name="photoHeights" list="{150,300,450}" required="true" /></td>
			</tr>
			<tr>
				<td>照片名称5:</td>
				<td><input id="photoNames" name="photoNames" size="18" class="required"></td>
				<td>文件5:</td>
				<td><input type="file" id="upload" name="upload" size="28"></td>
				<td>宽度5:</td>
				<td height="30"><s:select id="photoWidths" name="photoWidths" list="{150,300,450}" required="true" /></td>
				<td>高度5:</td>
				<td height="30"><s:select id="photoHeights" name="photoHeights" list="{150,300,450}" required="true" /></td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td height="30" colspan="8"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="6" valign="middle">&nbsp;&nbsp;<input onClick="doSubmit('photoForm','photo!upload.action');" type="button" id="uploadFile" name="uploadFile" value="Upload" class="button"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>