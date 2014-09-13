<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function(){
	$("#rateDateDate").datepicker({
		dateFormat: $.datepicker.W3C
	}).attr("readonly", "readonly");
	$('#rateForm').validate({
		rules:{
    		rateValue: {
      			required: true,
      			number: true
    		}
  		}
	})
	if ($('#rateIsActive').val() == '1'){$('#setActive').attr('checked','true');}
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a
			href="exchange-rate.action"> 汇率字典域</a>-&gt;维护汇率字典</td>
	</tr>
</table>
<form id="rateForm" name="rateForm" method="Post">
<table border="0" cellpadding="0" cellspacing="1" class="stripe"
	align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp;<img src="/common/image/title-icon0.gif" width="16" height="13">&nbsp;汇率信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="stripe">
			<tr>
				<td height="30" colspan="4"><input type="hidden" id="rateId"
					name="rateId" value="<s:property value='rateId' />"></td>
			</tr>
			<tr>
				<td height="30" align="left" width="20%">原币：</td>
				<td height="30"><s:select
					id="rateOriginId" name="rateOriginId"
					value="%{rateOriginCurrency.currencyId}" list="currencyList"
					listKey="currencyId" listValue="currencyCode" /></td>
				<td>目标币:</td>
				<td height="30"><s:select
					id="rateTargetId" name="rateTargetId"
					value="%{rateTargetCurrency.currencyId}" list="currencyList"
					listKey="currencyId" listValue="currencyCode" /></td>
			</tr>
			<tr>
				<td height="30" align="left" width="50">说明:</td>
				<td><textarea
					id="rateDescription" name="rateDescription" rows="3" cols="48"><s:property
					value="rateDescription" /></textarea></td>
				<td colspan="2">
					<table>
						<tr><td>汇率:</td><td><input id="rateValue" name="rateValue" value="<s:property value='rateValue'/>" size="8" ></td></tr>
						<tr><td>日期:</td><td><input id="rateDateDate" name="rateDateDate" value="<s:property value='rateDateDate' />" size="10"></td></tr>
						<tr><td>有效:</td>
							<td><input type="hidden" id="rateIsActive" name="rateIsActive"
								size="1" value="<s:property value='rateIsActive' />"> <input
								type="checkbox" id="setActive" name="setActive"
								onclick="javascript:(this.checked)? document.rateForm.rateIsActive.value=1:document.rateForm.rateIsActive.value=0;"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="16" width="100%" colspan="4">
				<hr size="1">
				</td>
			</tr>
			<tr>
				<td colspan="2" height="33">
				<table>
					<tr>
						<security:authorize ifAnyGranted="ROLE_CMS_CRUD_USER">
						<td><input
							onClick="doSubmit('rateForm','exchange-rate!save.action');"
							type="button" id="save" name="save" value="Save" class="button"></td>
						</security:authorize>
						<td><input
							onClick="doSubmit('rateForm','exchange-rate!delete.action');"
							type="button" id="delete" name="delete" value="Delete" class="button"></td>
						<td><input onClick="javascript:window.history.back();"
							type="button" name="Return" value="Return" class="button"></td>
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