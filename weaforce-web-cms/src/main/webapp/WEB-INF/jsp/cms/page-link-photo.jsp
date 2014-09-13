<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#linkForm').validate();
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="page-link.action">制定页面</a>-&gt;页面照片</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="linkForm" name="linkForm" action="page-link!save.action" method="Post">
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23">&nbsp;<img src="/common/image/title-icon1.gif" width="16" height="13">&nbsp;页面文章</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="stripe">
			<tr>
				<td><input type="hidden" id="linkId" name="linkId" value="<s:property value='linkId' />"></td>
				<td><input type="hidden" id="articleId" name="articleId" value="<s:property value='articleId' />"></td>
			</tr>
			<tr>
				<td height="30" align="left">父节点<font color="red">*</font>:</td>
				<td><s:select id="parentId" name="parentId" list="photoLinkList"
					listKey="linkId" listValue="linkName" cssClass="required" /></td>
				<td height="30" align="left" >标签:</td>
				<td><input id="linkLabelCode" name="linkLabelCode" value="<s:property value='linkLabelCode' />" size="28"  class="required"></td>
			</tr>
			<!-- 文章信息 -->
			<tr>
				<td height="30" align="left" bgcolor="#F1F8FC">标题:</td>
				<td bgcolor="#F1F8FC"><input id="linkName" name="linkName" value="<s:property value='linkArticle.articleTitle' />" size="28"  class="required"></td>
				<td height="30" align="left" bgcolor="#F1F8FC">排序:</td>
				<td bgcolor="#F1F8FC"><input id="linkOrder" name="linkOrder" value="<s:property value='linkOrder' />" class="number required"></td>
			</tr>
			<tr>
				<td height="30" align="left" >链接:</td>
				<td><input id="articleUrl" name="articleUrl" value="<s:property value='linkArticle.articleUrl' />" size="28" ></td>
				<td height="30" align="left">图片:</td>
				<td><input id="articlePictureUrl" name="articlePictureUrl" value="<s:property value='linkArticle.articlePictureUrl' />" size="28" ></td>
			</tr>
			<!-- 文章信息 -->
			<tr>
				<td height="16" width="100%" colspan="4"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="4" height="33">
				<table>
					<tr>
						<security:authorize ifAnyGranted="ROLE_CMS_CRUD_USER">
						<td><input onClick="javascript:doSubmit('linkForm','page-link!save.action');"
							type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('linkForm','page-link!delete.action');"
							type="button" id="delete" name="delete" value="Delete"
							class="button"></td>
						</security:authorize>
						<td><input onClick="javascript:window.history.back();"
							type="button" id="return" name="return" value="Return" class="button"></td>
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