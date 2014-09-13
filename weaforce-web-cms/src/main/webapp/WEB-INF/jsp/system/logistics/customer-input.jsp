<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<link rel="stylesheet" href="/common/themes/flora/flora.all.css" type="text/css" media="screen" title="Flora (Default)" />
<script language="javascript" src="/common/jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/common/jquery/ui/ui.core.js"></script>
<script type="text/javascript" src="/common/jquery/ui/ui.tabs.js"></script>
<title>Customer Input</title>
<script type="text/javascript">
	$(document).ready(function(){
		$("#customer-info > ul").tabs();
		if ($('#accountIsActive').val() == '1'){
			$('#setActive').attr('checked','true');
		}
	})
</script>
</head>
<body bgcolor="white">
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="customer.action">客户字典域</a>-&gt;客户维护</td>
	</tr>
</table>
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr>
		<td>
        <div id="customer-info">
            <ul style="height: 30px;">
                <li><a href="#account"><span>基本信息</span></a></li>
                <li><a href="#customer"><span>商务信息</span></a></li>
            </ul>
            <div id="account">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
				<tr>
					<td height="30">中文名称:</td>
					<td  colspan="3"><input id="accountNameCn" name="accountNameCn" value="<s:property value='customerAccount.accountNameCn' />" size="58"></td>
					<td>英文名称:</td>
					<td colspan="3"><input id="accountNameEn" name="accountNameEn" value="<s:property value='customerAccount.accountNameEn' />" size="58"></td>
				</tr>
				<tr>
					<td>主页:</td>
					<td colspan="3"><input id="accountWeb" name="accountWeb" value="<s:property value='customerAccount.accountWeb' />" size="58"></td>
					<td>Email:</td>
					<td colspan="3"><input id="accountEmail" name="accountEmail" value="<s:property value='customerAccount.accountEmail' />" size="58"></td>
				</tr>
				<tr>
					<td height="30">简称:</td>
					<td><input id="accountShortName" name="accountShortName" value="<s:property value='customerAccount.accountShortName' />"></td>
					<td height="30">代码:</td>
					<td><input id="accountCode" name="accountCode" value="<s:property value='customerAccount.accountCode' />"></td>
					<td height="30">身份:</td>
					<td><input id="accountIdentity" name="accountIdentity" value="<s:property value='customerAccount.accountIdentity' />"></td>
					<td>邮编:</td>
					<td><input id="accountPostcode" name="accountPostcode" value="<s:property value='customerAccount.accountPostcode' />"></td>
				</tr>
				<tr>
					<td height="30">联系人:</td>
					<td><input id="accountContact" name="accountContact" value="<s:property value='customerAccount.accountContact' />"></td>
					<td>电话:</td>
					<td><input id="accountPhone" name="accountPhone" value="<s:property value='customerAccount.accountPhone' />"></td>
					<td>传真:</td>
					<td><input id="accountFax" name="accountFax" value="<s:property value='customerAccount.accountFax' />"></td>
					<td>有效:</td>
					<td>
						<input type="hidden" id="accountIsActive" name="accountIsActive" size="1" value="<s:property value='customerAccount.accountIsActive' />"> 
						<input type="checkbox" id="setActive" name="setActive" onclick="javascript:(this.checked)? document.accountForm.accountIsActive.value=1:document.accountForm.accountIsActive.value=0;" />
					</td>
				</tr>
			</table>
			</div>
            <div id="customer">
            	<form id="customerForm" name="customerForm" action="customer!save.action" method="post">
               		 <table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="100%">
						<tr>
							<td><input type="hidden" id="accountId" name="accountId" value="<s:property value='customerAccount.accountId' />"></td>
							<td><input type="hidden" id="customerId" name="customerId" value="<s:property value='customerId' />"></td>
						</tr>
						<tr>
							<td height="30">代码:</td>
							<td><input id="customerCode" name="customerCode" value="<s:property value='customerCode' />"　></td>
							<td>月结:</td>
							<td>
								<select id="customerFinanceDay" name="customerFinanceDay">
									<option value="0">0</option>
									<option value="30">30</option>
									<option value="45">45</option>
									<option value="60">60</option>
									<option value="90">90</option>
								</select>天
								<script type="text/javascript">
									document.customerForm.customerFinanceDay.value=<s:property value="customerFinanceDay" />;
								</script>
							</td>
						</tr>
						<tr><td height="30" colspan="4"><hr size="1"></td></tr>
						<tr>
							<td colspan="4" height="33">
								<table>
									<tr>
										<td><input onClick="doSubmit('customerForm','customer!save.action');" type="button" id="save" name="save" value="Save" class="button"></td>
										<td><input onClick="doSubmit('customerForm','account!doDelete.action');" type="button" id="delete" name="delete" value="Delete" class="button"></td>
										<td><input onClick="javascript:window.history.back();" type="button" name="Return" value="Return" class="button"></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
                </form>
            </div>
        </div>
        </td>
	</tr>
</table>
</body>
</html>