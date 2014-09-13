<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Photo input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#photoForm').validate();
})
function OnUploadCompleted(fileUrlSmall,fileUrl){
	document.all.photoUrlSmall.value=fileUrlSmall;
	document.all.photoUrl.value=fileUrl;
}
function getPic(){
	return document.all.photoUrlSmall.value;
}
function openwinx(url,name,w,h) {
    window.open(url,name,"top=100,left=400,width=" + w + ",height=" + h + ",toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no")
}
function getAlbum(){
	return document.all.albumId.value;
}
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="photo.action">照片管理</a>-&gt;照片维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="photoForm" name="photoForm" action="album!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp; 照片信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="photoId" name="photoId" value="<s:property value='photoId' />" /></td>
			</tr>
			<tr>
				<td>像集:<font color="red">*</font>:</td>
				<td><s:select id="albumId" name="albumId" value="%{photoAlbum.albumId}" list="albumList" listKey="albumId" listValue="albumName" /></td>
				<td height="30">名称:</td>
				<td height="30"><input id="photoName" name="photoName" value="<s:property value='photoName' />" class="required"></td>
			</tr>
			<tr>
				<td height="30">小图:</td>
				<td><input id="photoUrlResize" name="photoUrlResize" value="<s:property value='photoUrlResize' />" size="38" class="required" readonly="readonly"></td>
				<td>图片:</td>
				<td><input id="photoUrl" name="photoUrl" value="<s:property value='photoUrl' />" size="38" class="required" readonly="readonly"></td>
			</tr>
			<tr>
				<td height="30">物理地址:</td>
				<td><input id="photoLocationResize" name="photoLocationResize" value="<s:property value='photoLocationResize' />" size="38" class="required" readonly="readonly"></td>
				<td>物理地址:</td>
				<td><input id="photoLocation" name="photoLocation" value="<s:property value='photoLocation' />" size="38" class="required" readonly="readonly"></td>
			</tr>
			<tr>
				<td height="30">描述:</td>
				<td colspan="3"><textarea id="photoDescription" name="photoDescription" rows="5" cols="78"><s:property value="photoDescription" /></textarea></td>
			</tr>
			<tr><td height="30" colspan="4"><hr size="1"></td></tr>
			<tr>
				<td colspan="4" height="33">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('photoForm','photo!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('photoForm','photo!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
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