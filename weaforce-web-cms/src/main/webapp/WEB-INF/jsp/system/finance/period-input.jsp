<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>账期维护</title>
<script type="text/javascript">
$(document).ready(function(){
	$("#periodStartDate").datepicker({dateFormat: $.datepicker.W3C}).attr("readonly", "readonly");
	$("#periodEndDate").datepicker({dateFormat: $.datepicker.W3C}).attr("readonly", "readonly");
	$("#periodForm").validate();
	if ($('#periodIsActive').val() == '1'){$('#setActive').attr('checked','true');}
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="period.action">系统账期</a>-&gt;账期维护</td>
	</tr>
</table>
<form id="periodForm" name="periodForm" action="period!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1"
	class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp;<img src="/common/image/title-icon0.gif" width="16" height="13">&nbsp;工作状态</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="periodId" name="periodId" value="<s:property value='periodId' />"></td>
				<td><input type="hidden" id="parentId" name="parentId" value="<s:property value='periodParent.periodId' />"></td>
			</tr>
			<tr>
				<td height="30">名称:</td>
				<td height="30"><input id="periodName" name="periodName" value="<s:property value='periodName' />" class="required"></td>
			</tr>
			<tr>
				<td height="30">年度:</td>
				<td height="30"><s:select id="periodYear" name="periodYear" value="%{periodYear}" list="{2009,2010,2011}" required="true" /></td>
			</tr>
			<tr>
				<td height="30">活动:</td>
				<td height="30">
					<input type="hidden" id="periodIsActive" name="periodIsActive" size="1" value="<s:property value='periodIsActive' />"> 
					<input type="checkbox" id="setActive" name="setActive" onclick="javascript:(this.checked)? document.periodForm.periodIsActive.value=1:document.periodForm.periodIsActive.value=0;">
				</td>
			</tr>
			<tr>
				<td height="30">起始时间:</td>
				<td height="30"><input id="periodStartDate" name="periodStartDate" size="10" value="<s:property value='periodStartDate' />" class="required" <s:if test="%{periodList.size() > 0}">readonly="readonly"</s:if>></td>
			</tr>
			<tr>
				<td height="30">结束时间:</td>
				<td height="30"><input id="periodEndDate" name="periodEndDate" size="10" value="<s:property value='periodEndDate' />" class="required"></td>
			</tr>
			<tr>
				<td height="30" colspan="2"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="2" height="33">
				<table>
					<tr>
						<security:authorize ifAnyGranted="ROLE_CONE_CRUD_USER">
						<td><input onClick="javascript:doSubmit('periodForm','period!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						</security:authorize>
						<security:authorize ifAnyGranted="ROLE_CONE_CRUD_USER">
						<td><input onClick="javascript:doSubmit('periodForm','period!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
						</security:authorize>
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