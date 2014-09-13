<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>联系人</title>
<script type="text/javascript">
$(document).ready(function(){
	staffList($('#departmentId').val(),$('#staffId').val());
	//template 存在弊端
	$('#departmentId').change(function(){
		$('tr.rowData').remove();
		staffList($('#departmentId').val(),$('#staffId').val());
		//doSubmit('listPage', 'contact.action');
	})
})
staffList = function (departmentId,staffId){
	$.ajax({
		type:"post",
		dataType:"json",
		url:"/system/organ/staff!getStaffListJSON.action",
		data:{'departmentId':departmentId,'staffId':staffId},
		success:function(data){
			$.each(data,function(indexItem,item){
				//alert(indexItem + ":   " + item['staffLogin']);
				var row = $("#template").clone();
				if (item['checked'] == 0)
					row.find("#contactId").html("<input type=\"checkbox\" id=\"checkedStaffIds\" name=\"checkedStaffIds\" value=\"" + item['staffId'] + "\" onClick=\"saveContact(" +$('#staffId').val() + "," + item['staffId'] + ")\" >");
				if (item['checked'] == 1)
					row.find("#contactId").html("<input type=\"checkbox\" id=\"checkedStaffIds\" name=\"checkedStaffIds\" value=\"" + item['staffId'] + "\" onClick=\"saveContact(" +$('#staffId').val() + "," + item['staffId'] + ")\" checked=\"true\">");
				row.find("#staffLogin").text(item['staffLogin']);
				row.find("#staffName").text(item['staffName']);
				row.attr("id","ready").addClass("rowData");//改变绑定好数据的行的id
				row.appendTo("#table-stripe");//添加到模板的容器中;
			})
			if (data){
				$('#table-stripe tr:not([th]):odd').addClass('odd');
				$('#table-stripe tr:not([th]):even').addClass('even');
				$("#table-stripe tr:not([th])").mouseover(function(){  
			        //如果鼠标移到class为stripe的表格的tr上时，执行函数
					$(this).addClass("over");}).mouseout(function(){ 
			        //给这行添加class值为over，并且当鼠标一出该行时执行函数
			        $(this).removeClass("over");
				})  //移除该行的class
			}
		}
	});
}
//保存联系人
saveContact = function(staffId,contactId){
	$.ajax({
		type:"post",
		dataType:"json",
		url:"/system/organ/staff!saveContact.action",
		data:{'staffId':staffId,'contactId':contactId},
		success:function(data){
			$.each(data,function(index,item){
				$("#returnMsg").html(item['returnMsg']);
			})
		}
	});
}
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6">&nbsp;系统导航-&gt;<a href="staff.action?departmentId=<s:property value='departmentId' />">成员字典域</a>-&gt;联系人&nbsp;<span><font color="red"><s:actionmessage /></font></span></td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="staff!contact.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr>
		<td>部门:</td>
		<td><s:select id="departmentId" name="departmentId" value="%{departmentId}" list="departmentList" listKey="departmentId" listValue="departmentName" cssClass="required" /></td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:doSubmit('listPage', 'staff!contact.action');"></td>
		<td><div id="returnMsg" fontcolor="red"></div></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0"  cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th height="23"></th>
		<th>姓名</th>
		<th>登录</th>
	</tr>
	<tr>
		<td colspan="3">
			<input type="hidden" id="staffId" name="staffId" value="<s:property value='staffId' />" >
		</td>
	</tr>
	<tr id="template">
		<td id="contactId"></td>
		<td id="staffName"></td>
		<td id="staffLogin"></td>
	</tr>
</table>
</form>
</body>
</html>