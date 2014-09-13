<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Resource</title>
<script type="text/javascript">
$(document).ready(function(){
	//$('#theTable > th').parent().addClass('table-head');
	$('#table-stripe tr:not([th]):odd').addClass('odd');
	$('#table-stripe tr:not([th]):even').addClass('even');
		
	$("#table-stripe tr:not([th])").mouseover(function(){
        //如果鼠标移到class为stripe的表格的tr上时，执行函数
		$(this).addClass("over");}).mouseout(function(){
        //给这行添加class值为over，并且当鼠标一出该行时执行函数
        $(this).removeClass("over");
	})  //移除该行的class
	//模块/授权数据
	$('#businessId').change(function(){
		$('#moduleId').buildSelect($('#moduleId'),{selected:0,url:"/system/base/module!getModuleDDL.action",data:{'businessId':$(this).val()}},function(){});
		$('#authorityId').buildSelect($('#authorityId'),{selected:0,url:"/system/authority!getAuthorityDDL.action",data:{'businessId':$(this).val()}},function(){});
	})
});
function authority(flag,resourceId){
	//alert(flag + " resourceId: " + resourceId);
	if (flag == 1)
		$.post("/system/resource!checked.action",{"resourceId":resourceId,"authorityId":$('#authorityId').val()});
	if (flag == 0)
		$.post("/system/resource!uncheck.action",{"resourceId":resourceId,"authorityId":$('#authorityId').val()});
}
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;资源管理&nbsp;&nbsp;<span id="returnMessage"><font color="red"><s:actionmessage /></font></span></td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="resource!list.action" method="POST">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="left">
		<td>系统:</td>
		<td><s:select id="businessId" name="businessId" list="businessList" listKey="businessId" listValue="businessNameCn"  /></td>
		<td>模块:</td>
		<td><s:select id="moduleId" name="moduleId" value="%{moduleId}" list="moduleList" listKey="moduleId" listValue="moduleNameCn" required="true" /></td>
		<td>授权:</td>
		<td><s:select id="authorityId" name="authorityId" value="%{authorityId}" list="authorityList" listKey="authorityId" listValue="authorityCode" required="true" /></td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage','resource!list.action')"></td>
		<td><input type="button" id="create" name="create" value="Create" onClick="javascript:doSubmit('listPage','resource!input.action')"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th width="23" height="23"></th>
		<th>授权</th>
		<th>名称</th>
		<th>值</th>
		<th>类型</th>
		<th width="23" height="23"></th>
	</tr>
	<s:iterator value="resourceList">
		<tr>
			<td height="23" align="center"><img class="pointerImage" border="0" title="Edit" src="/common/image/edit_inline.gif" onClick="javascript:doSubmit('listPage','resource!input.action?resourceId=<s:property  value='resourceId' />');"></td>
			<td>
				<s:if test="%{checked == 1}">
					<input type="checkbox" id="<s:property value='resourceId' />" value="<s:property value='resourceId' />" checked="checked" readonly="readonly" onclick="javascript:(this.checked)? authority(1,this.value):authority(0,this.value)">
				</s:if><s:if test="%{checked == 0}">
					<input id="<s:property value='resourceId' />" value="<s:property value='resourceId' />" type="checkbox" readonly="readonly" onclick="javascript:(this.checked)? authority(1,this.value):authority(0,this.value)">
				</s:if>
			</td>
			<td><s:property value="resourceName" /></td>
			<td><s:property value="resourceValue" /></td>
			<td><s:property value="resourceType" /></td>
			<td align="center"><img class="pointerImage" border="0" title="Delete" src="/common/image/delete_inline.gif" onClick="javascript:if (confirm('是否要删除当前内容?')) doSubmit('listPage','resource!delete.action?resourceId=<s:property  value='resourceId' />');"></td>
		</tr>
	</s:iterator>
</table>
</form>
</body>
</html>