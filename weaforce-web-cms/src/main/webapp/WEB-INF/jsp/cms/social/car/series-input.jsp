<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#seriesForm').validate();
})
</script>
<title>Series Input</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="series.action">栏目字典域</a>-&gt;维护栏目</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="seriesForm" name="seriesForm" action="series!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1"
	class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">栏目信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="seriesId" name="seriesId" value="<s:property value='seriesId' />"></td>
			</tr>
			<tr>
				<td>品牌<font color="red">*</font>:</td>
				<td><s:select id="brandId" name="brandId" value="%{seriesBrand.brandId}" list="brandList" listKey="brandId" listValue="brandName" cssClass="required" /></td>
			</tr>
			<tr>
				<td>名称<font color="red">*</font>:</td>
				<td><input id="seriesName" name="seriesName" value="<s:property value='seriesName'/>" class="required"></td>
			</tr>
			<tr>
				<td height="30" colspan="2"><hr size="1"></td>
			</tr>
			<tr bgcolor="#FFFFFF">
				<td colspan="2" height="33">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('seriesForm','series!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('seriesForm','series!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
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