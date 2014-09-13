<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Discount input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#productForm').validate();
	var oFCKeditor = new FCKeditor("productDescription","98%","238","Basic") ;
	oFCKeditor.ReplaceTextarea();
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="product.action?channelId=<s:property value='channelId' />&categoryId=<s:property value='categoryId' />">商家产品</a>-&gt;产品维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="productForm" name="productForm" action="product!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp;产品信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="channelId" name="channelId" value="<s:property value='channelId' />" ></td>
				<td><input type="hidden" id="categoryId" name="categoryId" value="<s:property value='categoryId' />" ></td>
				<td><input type="hidden" id="productId" name="productId" value="<s:property value='productId' />" ></td>
			</tr>
			<tr>
				<td height="30">广告商家:</td>
				<td><s:select id="adsId" name="adsId" value="%{adsId}" list="adsList" listKey="adsId" listValue="adsName" /></td>
			</tr>
			<tr>
				<td height="30">模板:</td>
				<td><s:select id="templateId" name="templateId" value="%{productTemplate.templateId}" list="templateList" listKey="templateId" listValue="templateName" /></td>
			</tr>
			<tr>
				<td height="30">名称:</td>
				<td><input id="productName" name="productName" value="<s:property value='productName' />" size="78" class="required"></td>
			</tr>
			<tr>
				<td>描述:</td>
				<td><textarea id="productDescription" name="productDescription"><s:property value="productDescription" /></textarea></td>
			</tr>
			<tr><td height="30" colspan="4"><hr size="1"></td></tr>
			<tr>
				<td colspan="4" height="23">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('productForm','product!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('productForm','product!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
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