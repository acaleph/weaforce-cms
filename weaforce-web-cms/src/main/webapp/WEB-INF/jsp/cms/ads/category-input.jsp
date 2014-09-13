<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Catalog input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#categoryForm').validate();
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="category.action">广告栏目字典域</a>-&gt;栏目维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="categoryForm" name="categoryForm" action="category!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1"
	class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp; 栏目信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td colspan="6"><input type="hidden" id="categoryId" name="categoryId" value="<s:property value='categoryId' />" ></td>
			</tr>
			<tr>
				<td height="30">名称:</td>
				<td><input id="categoryName" name="categoryName" value="<s:property value='categoryName' />" class="required"></td>
				<td>Parse路径:</td>
				<td colspan="3"><input id="categoryParsePath" name="categoryParsePath" value="<s:property value='categoryParsePath' />" size="58"></td>
				<td>CSS:</td>
				<td><input id="categoryCss" name="categoryCss" value="<s:property value='categoryCss' />"></td>
			</tr>
			<tr>
				<td height="30">排序:</td>
				<td>
					<table>
						<tr>
							<td><s:select id="categoryOrder" name="categoryOrder" value="%{categoryOrder}" list="{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18}" /></td>
							<td>频道:</td>
							<td><s:select id="channelId" name="channelId" value="%{categoryChannel.channelId}" list="channelList" listKey="channelId" listValue="channelName" /></td>
						</tr>
					</table>
				</td>
				<td>网站URL:</td>
				<td colspan="5"><input id="categoryUrl" name="categoryUrl" value="<s:property value='categoryUrl' />" size="78"></td>
			</tr>
			<tr>
				<td>价格:</td>
				<td><input id="categoryPrice" name="categoryPrice" value="<s:property value='categoryPrice' />" size="14" class="required"></td>
				<td>压金:</td>
				<td><input id="categoryTotalPay" name="categoryTotalPay" value="<s:property value='categoryTotalPay' />" size="14" class="required"></td>
				<td>制作费用:</td>
				<td><input id="categoryTotalAds" name="categoryTotalAds" value="<s:property value='categoryTotalAds' />" size="14" class="required"></td>
				<td>网站费用:</td>
				<td><input id="categoryTotalWeb" name="categoryTotalWeb" value="<s:property value='categoryTotalWeb' />" size="14" class="required"></td>
			</tr>
			<tr>
				<td height="30">模板:</td>
				<td><s:select id="templateId" name="templateId" value="%{categoryTemplate.templateId}" list="templateList" listKey="templateId" listValue="templateName" /></td>
				<td>单元:</td>
				<td><s:select id="templateAdsId" name="templateAdsId" value="%{categoryAdsTemplate.templateId}" list="templateList" listKey="templateId" listValue="templateName" /></td>
				<td>明星:</td>
				<td><s:select id="templateAdsStarId" name="templateAdsStarId" value="%{categoryAdsStarTemplate.templateId}" list="templateList" listKey="templateId" listValue="templateName" /></td>
				<td>网站:</td>
				<td><s:select id="templateSiteId" name="templateSiteId" value="%{categorySiteTemplate.templateId}" list="templateList" listKey="templateId" listValue="templateName" /></td>
			</tr>
			<tr>
				<td>贴文:</td>
				<td><s:select id="templateTipArticleId" name="templateTipArticleId" value="%{categoryTipArticleTemplate.templateId}" list="templateList" listKey="templateId" listValue="templateName" /></td>
				<td>贴列:</td>
				<td><s:select id="templateTipListId" name="templateTipListId" value="%{categoryTipListTemplate.templateId}" list="templateList" listKey="templateId" listValue="templateName" /></td>
				<td>折文:</td>
				<td><s:select id="templateDiscountArticleId" name="templateDiscountArticleId" value="%{categoryDiscountArticleTemplate.templateId}" list="templateList" listKey="templateId" listValue="templateName" /></td>
				<td>折列:</td>
				<td><s:select id="templateDiscountListId" name="templateDiscountListId" value="%{categoryDiscountListTemplate.templateId}" list="templateList" listKey="templateId" listValue="templateName" /></td>
			</tr>
			<tr>
				<td height="30">描述:</td>
				<td colspan="7"><textarea id="categoryDescription" name="categoryDescription" rows="5" cols="68"><s:property value="categoryDescription" /></textarea></td>
			</tr>
			<tr><td height="30" colspan="8"><hr size="1"></td></tr>
			<tr>
				<td colspan="8" height="23">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('categoryForm','category!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('categoryForm','category!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
						<td><input onClick="javascript:doSubmit('categoryForm','category!parse.action')" type="button" id="parse" name="parse" value="Parse" class="button"></td>
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