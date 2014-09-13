<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>栏目维护</title>
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
				<form id="searchForm" name="searchForm" class="breadcrumb form-search" action="${ctx}/cms/category?page=${page.currentPage}&crud=l&categoryId=${category.categoryId}" method="post">
					<label for="queryName" class="control-label">名称：</label>
					<input id="queryName" name="queryName" type="text" value="${param.queryName}" data-provide="typeahead" class="input-large">
					<label for="queryChannelId" class="control-label">频道:</label>
					<select id="queryChannelId" name="queryChannelId">
						<c:forEach items="${channels}" var="o">
							<option value="${o.channelId}" ${o.channelId==param.queryChannelId? 'selected':''}>${o.channelName}</option>
						</c:forEach>
					</select>
					<button type="submit" class="btn btn-primary">查询</button>
					<div class="pull-right">
						<a href="${ctx}/cms/category?page=${page.currentPage}&crud=c&queryChannelId=${param.queryChannelId}&queryName=${param.queryName}" class="btn btn-success">新增</a>
					</div>
				</form>
				<table id="contentTable"
					class="table table-striped table-bordered table-condensed table-hover">
					<thead>
						<tr>
							<th width="23"></th>
							<th>名称</th>
							<th>状态</th>
							<th>创建日期</th>
							<th>创建者</th>
							<th>发布</th>
							<th width="23"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="o" varStatus="status">
							<tr class="${status.index % 2 == 0? 'warning':'info'}">
								<td align="center"><a href="${ctx}/cms/category?page=${page.currentPage}&crud=r&categoryId=${o.categoryId}&queryChannelId=${param.queryChannelId}&queryName=${param.queryName}"><i class="icon-edit"></i></a></td>
								<td>${o.categoryName}</td>
								<td><label class="checkbox"><input type="checkbox" class="disabled" ${o.categoryIsActive == '1'? 'checked':''}></label></td>
								<td>${o.date}</td>
								<td>${o.creator}</td>
								<td><a href="${ctx}/cms/category?page=${page.currentPage}&crud=p&categoryId=${o.categoryId}&queryChannelId=${param.queryChannelId}&queryName=${param.queryName}" class="btn btn-success">发布</a></td>
								<td align="center"><a href="${ctx}/cms/category?page=${page.currentPage}&crud=d&categoryId=${o.categoryId}&queryChannelId=${param.queryChannelId}&queryName=${param.queryName}"><i class="icon-remove"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="pagination">${page.pagination}</div>
			</div>
			<div class="tab-pane fade" id="edit">
				<c:if test="${not empty message}">
					<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
				</c:if>
				<form id="editForm" name="editForm" action="${ctx}/cms/category?page=${page.currentPage}&crud=u&queryChannelId=${param.queryChannelId}&queryName=${param.queryName}" method="post" class="form-horizontal">
					<input type="hidden" id="categoryId" name="categoryId" value="${category.categoryId}" />
					<fieldset>
						<div class="control-group">
							<label for="categoryName" class="control-label">名称:</label>
							<div class="controls">
								<input type="text" id="categoryName" name="categoryName" value="${category.categoryName}" class="input-large required" />
							</div>
						</div>
						<div class="control-group">
							<label for="categoryParseName" class="control-label">文件名称:</label>
							<div class="controls">
								<input type="text" id="categoryParseName" name="categoryParseName" value="${category.categoryParseName}" class="input-large required" />
							</div>
						</div>
						<div class="control-group">
							<label for=channelId class="control-label">频道:</label>
							<div class="controls">
								<select id="channelId" name="channelId">
									<c:forEach items="${channels}" var="o">
										<option value="${o.channelId}" ${o.selected? 'selected':''}>${o.channelName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label for=templateId class="control-label">模板:</label>
							<div class="controls">
								<select id="templateId" name="templateId">
									<c:forEach items="${templates}" var="o">
										<option value="${o.templateId}" ${o.selected? 'selected':''}>${o.templateName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="control-group">
							<label for="categoryIsActive" class="control-label">状态:</label>
							<div class="controls">
								<input type="checkbox" class="disabled" ${category.categoryIsActive == '1'? 'checked':''}>
							</div>
						</div>
						<div class="control-group">
							<label for="categoryDescription" class="control-label">描述:</label>
							<div class="controls">
								<textarea id="categoryDescription" name="categoryDescription" class="input-xlarge" >${category.categoryDescription}</textarea>
							</div>
						</div>
						<div class="control-group">
							<label for="categoryDescription" class="control-label">单元模板:</label>
							<div class="controls">
								<textarea id="categoryCellTemplate" name="categoryCellTemplate" class="input-xlarge" >${category.categoryCellTemplate}</textarea>
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