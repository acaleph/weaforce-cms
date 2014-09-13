<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
</head>
<body>
	<div class="span12">
		<ul class="nav nav-tabs" id="myTab">
			<li class="active"><a href="#list">列表</a></li>
			<li><a href="${ctx}/admin/role/create">新增</a></li>
		</ul>
		<c:if test="${not empty message}">
			<div id="message" class="alert alert-success">
				<button data-dismiss="alert" class="close">×</button>
				${message}
			</div>
		</c:if>
		<form id="searchForm" name="searchForm" class="breadcrumb form-search" action="#">
			<label for="roleName" class="control-label">名称：</label> <input
				type="text" id="roleName" name="roleName" class="input-medium"
				value="${param.roleName}">
			<button type="submit" class="btn btn-primary">查询</button>
		</form>
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed table-hover">
			<thead>
				<tr>
					<th width="23" height="23"></th>
					<th>名称</th>
					<th>创建时间</th>
					<th width="23"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.content}" var="o" varStatus="status">
					<tr class="${status.index % 2 == 0? 'warning':'info'}">
						<td><a href="${ctx}/admin/role/edit/${o.roleId}">${status.index + 1}</a></td>
						<td>${o.roleName}</td>
						<td>${o.date}</td>
						<td></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="pagination">${page.pagination}</div>
	</div>
</body>
</html>