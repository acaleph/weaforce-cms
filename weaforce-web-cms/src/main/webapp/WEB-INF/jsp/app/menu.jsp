<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${cxt}/static/jquery/plugin/ztree.css" type="text/css">
<script type="text/javascript" src="${cxt}/static/jquery/plugin/form.js"></script>
<script type="text/javascript" src="${cxt}/static/jquery/plugin/ztree.core.js"></script>
<script type="text/javascript" src="${cxt}/static/jquery/plugin/ztree.excheck.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统菜单</title>
<script type="text/javascript">
	//Initial data
	var userLoginJSON = ${userLoginJSON};
	var zNodes = ${menuTree};
	$(function() {
		//Menu tree
		$.fn.zTree.init($("#menuTree"), {check:{enable: true},view:{showIcon:false},callback: {onCheck: onCheck}}, zNodes);
		//Menu tree for modal
		$.fn.zTree.init($("#menuTreeModal"),{check:{enable:true,chkStyle:"radio",radioType:"all"},view:{showIcon:false}},zNodes);
		$('#selectParent').bind("click", function() {
			var zTree = $.fn.zTree.getZTreeObj("menuTreeModal");
			var selectedNode = zTree.getCheckedNodes(true);
			$("#parentId").attr("value", selectedNode[0].id)
			$("#menuParent").attr("value", selectedNode[0].name)
			$('#myModal').modal("hide");
		});
		//User login changed, Refreshing the menu tree
		$('#userLogin').bind("change",function(){
			$('#searchForm').submit();
		});
		//Save menu to server using ajax form
		var options = { 
	        dataType:  'json', 
	        beforeSubmit:  showRequest,  // pre-submit callback 
	        success:       showResponse  // post-submit callback 
		};
		$('#editForm').ajaxForm(options);
		
        $("#userLogin").typeahead({
			source : userLoginJSON
		});
        
		$('#myTab a').click(function(e) {
			e.preventDefault();
			$(this).tab('show');
		})
		//Default tab show
		var isActive=${isActive};
		if (isActive == '1')
			$('#myTab a[href="#edit"]').tab('show');
		else
			$('#myTab a[href="#list"]').tab('show');
	});
	//When user changed set up the menu selection
	$("#userLogin").blur(function() {
		//Deselected all menu
		$("input:checkbox.menu").attr("checked", false);
		//Select for category
		$.ajax({
			type : "get",
			dataType : "json",
			url : "${ctx}/cms/menu/getRoleMenu",
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
	//Menu tree is checed, definded in setting on zTree
	onCheck = function(e, treeId, treeNode){
		var userLogin = $("#userLogin").val();
		if (userLogin.length == 0) return false;
		setMenuToRole(treeNode.id,$("#userLogin").val());
		//alert(treeId + "   " + treeNode.id + "    " + treeNode.name);
	};
	//Set menu to role of the user
	setMenuToRole = function(menuId, userLogin) {
		if (userLogin.length == 0){
			alert("用户不用为空！")
			return false;
		};
		$.get("${ctx}/app/menu/toRole", {"menuId":menuId,"userLogin":userLogin},function(data){});
	};
	//Ajax form request
	showRequest = function(formData, jqForm, options){
		return true;
	};
	//Ajax form response
	showResponse = function(data, statusText, json){
		$("#menuId").attr("value", data.id);
		$("#message").empty();
		$("#message").append("<button data-dismiss=\"alert\" class=\"close\">×</button>" + data.message);
		if (!$("#message").hasClass("alert alert-success"))
			$("#message").addClass("alert alert-success");
		return true;
	};
	
</script>
</head>
<body>
	<div class="span2">
		<div class="accordion-group">
			<div class="accordion-heading">
				<a class="accordion-toggle" href="#">功能菜单</a>
			</div>
			<div class="accordion-body ">
				<div class="accordion-inner">
					<div style="margin-top: 5px;">
						<ul id="menuTree" class="ztree"></ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="span10">
		<ul class="nav nav-tabs" id="myTab">
			<li><a href="#list">列表</a></li>
			<li><a href="#edit">编辑</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane fade" id="list">
				<form id="searchForm" name="searchForm" class="breadcrumb form-search" action="#">
					<label for="userLogin" class="control-label">用户：</label>
					<input id="userLogin" name="userLogin" type="text" value="${userLogin}" data-provide="typeahead" class="input-xlarge">
					<label for="menuName" class="control-label">名称：</label>
					<input type="text" id="menuName" name="menuName" class="input-medium" value="${param.menuName}">
					<button type="submit" class="btn btn-primary">查询</button>
				</form>
				<table id="contentTable"
					class="table table-striped table-bordered table-condensed table-hover">
					<thead>
						<tr>
							<th></th>
							<th>Group Order</th>
							<th>Name</th>
							<th>Icon</th>
							<th>URL</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="menu" varStatus="status">
							<tr class="${status.index % 2 == 0? 'warning':'info'}">
								<td><label class="checkbox"><input type="checkbox" class="disabled" id="${menu.menuId}" name="${menu.menuId}" value="${menu.menuId}"></label></td>
								<td><a href="${ctx}/app/menu?page=${page.currentPage}&menuId=${menu.menuId}&menuName=${param.menuName}">${menu.menuGroupOrder}</a></td>
								<td><a href="${ctx}/app/menu/input/${menu.menuId}">${menu.menuNameCn}</a></td>
								<td>${menu.menuIcon}</td>
								<td><a href="${ctx}/app/menu/delete/${menu.menuId}">${menu.menuUrl}</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="pagination">${page.pagination}</div>
			</div>
			<div class="tab-pane fade" id="edit">
				<div id="message">
				</div>
				<form id="editForm" name="editForm" action="${ctx}/app/menu/save" method="post"	class="form-horizontal">
					<input type="hidden" id="menuId" name="menuId" value="${menu.menuId}" /> 
					<input type="hidden" id="parentId" name="parentId" value="${menu.menuParent.menuId}" />
					<fieldset>
						<div class="control-group">
							<label for="menuGroupOrder" class="control-label">排序:</label>
							<div class="controls">
								<input type="text" id="menuGroupOrder" name="menuGroupOrder" value="${menu.menuGroupOrder}" class="input-large required" />
							</div>
						</div>
						<div class="control-group">
							<label for="menuNameCn" class="control-label">名称:</label>
							<div class="controls">
								<input type="text" id="menuNameCn" name="menuNameCn" value="${menu.menuNameCn}" class="input-large required" />
							</div>
						</div>
						<div class="control-group">
							<label for="menuIcon" class="control-label">图标:</label>
							<div class="controls">
								<input type="text" id="menuIcon" name="menuIcon" value="${menu.menuIcon}" class="input-large" />
							</div>
						</div>
						<div class="control-group">
							<label for="menuUrl" class="control-label">链接:</label>
							<div class="controls">
								<input type="text" id="menuUrl" name="menuUrl"
									value="${menu.menuUrl}" class="input-large required" />
							</div>
						</div>
						<div class="control-group">
							<label for="menuFid" class="control-label">父菜单:</label>
							<div class="controls">
								<div class="input-append">
									<input id="menuParent" value="${menu.menuParent.menuNameCn}"
										class="input-large" readonly="readonly" type="text">
									<button class="btn" type="button" data-toggle="modal"
										data-target="#myModal">选择</button>
								</div>
							</div>
						</div>
						<div class="control-group">
							<label for="menuDescription" class="control-label">描述:</label>
							<div class="controls">
								<textarea id="menuDescription" name="menuDescription" class="input-large">${menu.menuDescription}</textarea>
							</div>
						</div>
						<div class="form-actions">
							<input id="save" name="save" class="btn btn-primary" type="submit" value="提交" />&nbsp; 
							<input id="cancel" name="cancel" class="btn" type="button" value="返回" onclick="history.back()" />
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
	<div id="myModal" class="modal hide fade">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h3>选择菜单</h3>
		</div>
		<!--Modal header-->
		<div class="modal-body">
			<div class="row">
				<div class="span1"></div>
				<div class="span4 ">
					<div class="zTreeDemoBackground left">
						<ul id="menuTreeModal" class="ztree"></ul>
					</div>
				</div>
				<div class="span4" id="ratingstar"></div>
				<div class="span1" id="target"></div>
			</div>
		</div>
		<!--Modal body-->
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
			<button class="btn btn-primary" id="selectParent">Select</button>
		</div>
		<!--Modal footer-->
	</div>
	<!--Modal-->	
</body>
</html>
