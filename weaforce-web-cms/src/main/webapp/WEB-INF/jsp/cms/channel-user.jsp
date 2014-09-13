<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script src="/common/jquery/ui/1.8.16/jquery.ui.position.js"></script>
<script src="/common/jquery/ui/1.8.16/jquery.ui.autocomplete.js"></script>
<title>栏目用户管理</title>
</head>
<body>
	<table border="0" align="center" width="100%" class="navigator-title">
		<tr>
			<td valign="middle" height="29" align="left">&nbsp;<img
				src="/common/image/point.gif" width="5" height="6">&nbsp;系统导航-&gt;频道字典域
			</td>
		</tr>
	</table>
	<div class="spacer-2"></div>
	<form id="userLoginForm" name="userLoginForm" action="" method="post">
		<table border="0" cellpadding="0" cellspacing="1" id="searchbg"
			align="center" width="98%">
			<tr align="center">
				<td><div class="ui-widget">
						用户:<input id="userLogin" name="userLogin" value="" class="text"
							size="38">
					</div></td>
			</tr>
		</table>
	</form>
	<div class="spacer-2"></div>
	<table id="table-stripe" border="0" cellpadding="0" cellspacing="1"
		class="stripe" align="center" width="98%">
		<tr align="center" class="table-head">
			<th width="23" height="23"></th>
			<th>类型</th>
			<th>模板</th>
			<th>名称</th>
			<th>URL名称</th>
			<th>文件名</th>
			<th>创建日期</th>
			<th>创建者</th>
			<th width="23"></th>
		</tr>
		<s:iterator value="categoryList">
			<tr>
				<td height="23" align="center"><input type="checkbox"
					class="category" id="<s:property value='categoryId'/>"
					name="<s:property value='categoryId'/>"
					value="<s:property value='categoryId'/>"
					onclick="setCategoryToUser(this.value,$('#userLogin').val())"></td>
				<td><s:if test="%{categoryParseType==0}">频道</s:if> <s:if
						test="%{categoryParseType==1}">类频道</s:if> <s:if
						test="%{categoryParseType==2}">文章</s:if> <s:if
						test="%{categoryParseType==3}">列表文本</s:if> <s:if
						test="%{categoryParseType==4}">列表图片</s:if></td>
				<td><s:property value="categoryTemplate.templateName" /></td>
				<td><s:property value="categoryName" /></td>
				<td><a href="<s:property value='categoryUrl' />"
					target="_blank">View</a></td>
				<td><s:property value="categoryParseName" />.<s:property
						value="categoryParseNameExt" /></td>
				<td><s:property value="date" /></td>
				<td><s:property value="creator" /></td>
				<td align="center"></td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>
<script>
	$(function() {
		//$('#table-stripe > th').parent().addClass('table-head');
		$('#table-stripe tr:not([th]):odd').addClass('odd');
		$('#table-stripe tr:not([th]):even').addClass('even');

		$("#table-stripe tr:not([th])").mouseover(function() {
			//如果鼠标移到class为stripe的表格的tr上时，执行函数
			$(this).addClass("over");
		}).mouseout(function() {
			//给这行添加class值为over，并且当鼠标一出该行时执行函数
			$(this).removeClass("over");
		}) //移除该行的class

		//Autocomplete
		$.ajax({
			type : "post",
			dataType : "json",
			url : "/cms/channel!getUserLoginJSON.action",
			success : function(data) {
				$("#userLogin").autocomplete({
					source : data
				});
			}
		})

		//用户验证
		$("#userLoginForm")
				.validate(
						{
							rules : {
								userLogin : {
									email : true,
									required : true,
									remote : "/system/user!checkBeingUserLogin.action?userLogin="
											+ $('#userLogin').val()
								}
							},
							messages : {
								userLogin : {
									remote : "用户登录名不存在！"
								}
							}
						});

		//用户变更
		$("#userLogin").blur(function() {
			//Deselected all for category
			$("input:checkbox.category").attr("checked", false);
			//Select for category
			$.ajax({
				type : "post",
				dataType : "json",
				url : "/cms/channel!getCategoryJSONByUser.action",
				data : {
					"userLogin" : $("#userLogin").val()
				},
				success : function(data) {
					$.each(data, function(indexItem, item) {
						$("#" + item['value']).attr('checked', true);
					})
				}
			})
		})
	});

	//Saving or deleting category of user
	setCategoryToUser = function(categoryId, userLogin) {
		//Delete category from user by default
		var flag = 0;
		//Set save flag for category to user
		if ($('#' + categoryId).is(':checked'))
			flag = 1;
		//Set action for delete or save
		$.post("/cms/channel!setCategoryToUser.action", {
			"categoryId" : categoryId,
			"userLogin" : userLogin,
			"flag" : flag
		})
	}
</script>
