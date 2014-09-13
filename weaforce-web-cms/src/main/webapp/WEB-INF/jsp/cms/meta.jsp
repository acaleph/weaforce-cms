<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>META维护</title>
<script type="text/javascript">
	$(function(){
		$('#mytab a').click(function(e) {
			e.preventDefault();
			$(this).tab('show');
		})
		//Default tab show
		var isActive=${isActive};
		if (isActive == '1')
			$('#mytab a[href="#edit"]').tab('show');
		else
			$('#mytab a[href="#list"]').tab('show');
	})
</script>
</head>
<body>
	<div class="span12">
		<ul class="nav nav-tabs" id="mytab">
			<li><a href="#list">列表</a></li>
			<li><a href="#edit">编辑</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane fade" id="list">
				<form id="searchForm" name="searchForm" class="breadcrumb form-search" action="${ctx}/cms/meta?page=${page.currentPage}&crud=l&metaId=${meta.metaId}" method="post">
					<label for="queryKey" class="control-label">键：</label>
					<input id="queryKey" name="queryKey" type="text" value="${param.queryKey}" data-provide="typeahead" class="input-large">
					<label for="queryValue" class="control-label">值：</label>
					<input id="queryValue" name="queryValue" type="text" value="${param.queryValue}" data-provide="typeahead" class="input-large">
					<button type="submit" class="btn btn-primary">查询</button>
					<div class="pull-right">
						<a href="${ctx}/cms/meta?page=${page.currentPage}&crud=c&queryName=${param.queryName}" class="btn btn-success">新增</a>
					</div>
				</form>
				<table id="contentTable"
					class="table table-striped table-bordered table-condensed table-hover">
					<thead>
						<tr>
							<th width="23"></th>
							<th>名称</th>
							<th>键</th>
							<th>值</th>
							<th>创建日期</th>
							<th>创建者</th>
							<th width="23"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="o" varStatus="status">
							<tr class="${status.index % 2 == 0? 'warning':'info'}">
								<td align="center"><a href="${ctx}/cms/meta?page=${page.currentPage}&crud=r&metaId=${o.metaId}&queryName=${param.queryName}"><i class="icon-edit"></i></a></td>
								<td>${o.metaName}</td>
								<td>${o.metaKey}</td>
								<td>${o.metaValue}</td>
								<td>${o.date}</td>
								<td>${o.creator}</td>
								<td align="center"><a href="${ctx}/cms/meta?page=${page.currentPage}&crud=d&metaId=${o.metaId}&queryName=${param.queryName}"><i class="icon-remove"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="pagination">${page.pagination}</div>
			</div>
			<div class="tab-pane fade" id="edit">
				<c:if test="${not empty message}">
					<div id="message" class="alert alert-success">
						<button data-dismiss="alert" class="close">×</button>
						${message}
					</div>
				</c:if>
				<form id="editForm" name="editForm" action="${ctx}/cms/meta?page=${page.currentPage}&crud=u&metaId=${meta.metaId}&queryName=${param.queryName}" method="post" class="form-horizontal">
					<input type="hidden" id="metaId" name="metaId" value="${meta.metaId}" />
					<fieldset>
						<div class="control-group">
							<label for="metaName" class="control-label">名称:</label>
							<div class="controls">
								<input type="text" id="metaName" name="metaName" value="${meta.metaName}" class="input-large required" />
							</div>
						</div>
						<div class="control-group">
							<label for="metaKey" class="control-label">名称:</label>
							<div class="controls">
								<input type="text" id="metaKey" name="metaKey" value="${meta.metaKey}" class="input-large required" />
							</div>
						</div>
						<div class="control-group">
							<label for="metaValue" class="control-label">名称:</label>
							<div class="controls">
								<input type="text" id="metaValue" name="metaValue" value="${meta.metaValue}" class="input-large required" />
							</div>
						</div>
						<div class="form-actions">
							<button id="save" name="save" class="btn btn-primary" type="submit" >保存</button>&nbsp; 
							<button id="cancel" name="cancel" class="btn" type="button" onclick="history.back()" >返回</button>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>