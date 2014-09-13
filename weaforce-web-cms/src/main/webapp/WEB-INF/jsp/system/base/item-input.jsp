<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Item</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="item.action">通用字典域</a>-&gt;创建/维护通用字典</td>
	</tr>
</table>
<form id="itemForm" name="itemForm" action="item!save.action" method="POST">
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23">&nbsp; 字典信息</td>
	</tr>
	<tr>
		<td>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td colspan="6"><input type="hidden" id="itemId" name="itemId" value="<s:property value='itemId' />"></td>
			</tr>
			<tr>
				<td height="30">字典:</td>
				<td height="30"><s:select name="itemDictionaryId" value="%{itemDictionaryId}" list="dictionaryList" listKey="dictionaryId" listValue="dictionaryName" required="true" /></td>
			</tr>
			<tr>
				<td align="right">参数代码A:</td>
				<td><input type="text" id="itemName" name="itemName" value="<s:property value='itemName' />" style="TEXT-TRANSFORM: uppercase;"></td>
			</tr>
			<tr>
				<td align="right">排序:</td>
				<td><input type="text" id="itemGroupOrder" name="itemGroupOrder" value="<s:property value='itemGroupOrder' />"></td>
			</tr>
			<tr>
				<td align="right">有效:<font color=red>*</font></td>
				<td>
					<select name="itemIsActive"><option value="1">数据有效</option><option value="0">数据无效</option></select>
					<script type="text/javascript">
						document.itemForm.itemIsActive.value=<s:property value='itemIsActive' />;
					</script>
				</td>
			</tr>
			<tr>
				<td height="10" width="100%" colspan="6"><hr size="1"></td>
			</tr>
			<tr>
				<td align="center" colspan="6">
					<table>
						<tr>
							<td><INPUT type="button" id="save" name="save" value="Save" width="15" onClick="javascript:doSubmit('itemForm','item!save.action')"></td>
							<td><INPUT type="button" id="return" name="return" value="Return" width="15" onClick="javascript:window.history.back();"></td>
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