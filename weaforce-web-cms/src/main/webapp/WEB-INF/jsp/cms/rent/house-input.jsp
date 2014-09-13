<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#houseForm').validate();
	if ($('#houseIsActive').val() == '1'){$('#setActive').attr('checked','true');}
})
</script>
<title>Channel Input</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="house.action">栏目字典域</a>-&gt;维护栏目</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="houseForm" name="houseForm" action="house!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">栏目信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="houseId" name="houseId" value="<s:property value='houseId' />"></td>
			</tr>
			<tr>
				<td>建筑:<font color="red">*</font>:</td>
				<td><s:select id="buildingId" name="buildingId" value="%{houseBuilding.buildingId}" list="buildingList" listKey="buildingId" listValue="buildingName" cssClass="required" /></td>
				<td>户型:<font color="red">*</font>:</td>
				<td><s:select id="typeId" name="typeId" value="%{houseType.typeId}" list="typeList" listKey="typeId" listValue="typeName" /></td>
				<td>月租:<font color="red">*</font>:</td>
				<td><s:select id="tagId" name="tagId" value="%{houseTag.tagId}" list="tagList" listKey="tagId" listValue="tagName" /></td>
			</tr>
			<tr>
				<td>标题:<font color="red">*</font>:</td>
				<td><input id="houseTitle" name="houseTitle" value="<s:property value='houseTitle'/>" size="48" class="required"></td>
				<td>面积:</td>
				<td><input id="houseSquare" name="houseSquare" value="<s:property value='houseSquare'/>" class="required"></td>
				<td>有效:</td>
				<td>
					<table>
						<tr>
							<td><input type="hidden" id="houseIsActive" name="houseIsActive" size="1" value="<s:property value='houseIsActive' />"></td>
							<td><input type="checkbox" id="setActive" name="setActive" onclick="javascript:(this.checked)? document.houseForm.houseIsActive.value=1:document.houseForm.houseIsActive.value=0;"></td>
							<td>楼层:</td>
							<td><s:select id="houseLayer" name="houseLayer" value="%{houseLayer}" list="{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28}" /></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>所有人:</td>
				<td><input id="houseOwnerName" name="houseOwnerName" value="<s:property value='houseOwnerName'/>" class="required"></td>
				<td>手机:<font color="red">*</font>:</td>
				<td><input id="houseOwnerMobile" name="houseOwnerMobile" value="<s:property value='houseOwnerMobile'/>"></td>
				<td>电话:<font color="red">*</font>:</td>
				<td><input id="houseOwnerPhone" name="houseOwnerPhone" value="<s:property value='houseOwnerPhone'/>"></td>
			</tr>
			<tr>
				<td>联系人:<font color="red">*</font>:</td>
				<td><input id="houseContact" name="houseContact" value="<s:property value='houseContact'/>" class="required"></td>
				<td>手机:<font color="red">*</font>:</td>
				<td><input id="houseContactMobile" name="houseContactMobile" value="<s:property value='houseContactMobile'/>"></td>
				<td>电话:<font color="red">*</font>:</td>
				<td><input id="houseContactPhone" name="houseContactPhone" value="<s:property value='houseContactPhone'/>"></td>
			</tr>
			<tr>
				<td>描述:<font color=red>*</font>:</td>
				<td colspan="5"><textarea rows="5" cols="88" name="houseDescription" id="houseDescription" class="common"><s:property value='houseDescription' /></textarea></td>
			</tr>
			<tr>
				<td height="30" colspan="6"><hr size="1"></td>
			</tr>
			<tr>
				<td colspan="6" height="33">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('houseForm','house!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('houseForm','house!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
						<td><input onClick="javascript:doSubmit('houseForm','house!update.action')" type="button" id="update" name="update" value="Update" class="button"></td>
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