<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Page link input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#linkForm').validate();
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="page-link.action">页面定义</a>-&gt;维护页面</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="linkForm" name="linkForm" action="page-link!save.action" method="Post">
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23">&nbsp;<img src="/common/image/title-icon1.gif" width="16" height="13">&nbsp;页面元素</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="linkId" name="linkId" value="<s:property value='linkId' />"></td>
				<td><input type="hidden" id="parentId" name="parentId" value="<s:property value='linkParent.linkId' />"></td>
			</tr>
			<tr>
				<td height="30" align="left">频道<font color="red">*</font>:</td>
				<td><s:select id="channelId" name="channelId" value="%{linkChannel.channelId}" list="channelList" listKey="channelId" listValue="channelName" /></td>
				<td align="left">栏目<font color="red">*</font>:</td>
				<td><s:select id="categoryId" name="categoryId" value="%{linkCategory.categoryId}" list="categoryList" listKey="categoryId" listValue="categoryName" /></td>
				<td align="left">像册<font color="red">*</font>:</td>
				<td><s:select id="categoryId" name="albumId" value="%{linkAlbum.albumId}" list="albumList" listKey="albumId" listValue="albumName" /></td>
				<td >标签:</td>
				<td><input id="linkLabelCode" name="linkLabelCode" value="<s:property value='linkLabelCode' />" class="required"></td>
			</tr>
			<tr>
				<td height="30" align="left" bgcolor="#F1F8FC">标题:</td>
				<td bgcolor="#F1F8FC"><input id="linkName" name="linkName" value="<s:property value='linkName' />" size="28"  class="required"></td>
				<td align="left" bgcolor="#F1F8FC">排序:</td>
				<td bgcolor="#F1F8FC"><input id="linkOrder" name="linkOrder" value="<s:property value='linkOrder' />" class="number required"></td>
				<td align="left" bgcolor="#F1F8FC" >链接:</td>
				<td bgcolor="#F1F8FC"><input id="linkUrl" name="linkUrl" value="<s:property value='linkUrl' />" ></td>
				<td align="left" bgcolor="#F1F8FC">图片:</td>
				<td bgcolor="#F1F8FC"><input id="linkPictureUrl" name="linkPictureUrl" value="<s:property value='linkPictureUrl' />" ></td>
			</tr>
			<tr>
				<td height="16" width="100%" colspan="8"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="8" height="33">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('linkForm','page-link!save.action');" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('linkForm','page-link!delete.action');" type="button" id="delete" name="delete" value="Delete" class="button"></td>
						<td><input onClick="javascript:doSubmit('linkForm','page-link!parse.action');" type="button" id="parse" name="parse" value="Parse" class="button"></td>
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