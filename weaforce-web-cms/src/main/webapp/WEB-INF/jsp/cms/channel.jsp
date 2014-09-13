<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>频道维护</title>
<script type="text/javascript">
	$(function(){
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
	})
</script>
</head>
<body>
	<div class="span12">
		<ul class="nav nav-tabs" id="myTab">
			<li><a href="#list">列表</a></li>
			<li><a href="#edit">编辑</a></li>
		</ul>
		<div class="tab-content">
			<div class="tab-pane fade" id="list">
				<form id="searchForm" name="searchForm" class="breadcrumb form-search" action="${ctx}/cms/channel?page=${page.currentPage}&crud=l&channelId=${channel.channelId}" method="post">
					<label for="queryName" class="control-label">名称：</label>
					<input id="queryName" name="queryName" type="text" value="${param.queryName}" data-provide="typeahead" class="input-large">
					<button type="submit" class="btn btn-primary">查询</button>
					<div class="pull-right">
						<a href="${ctx}/cms/channel?page=${page.currentPage}&crud=c&queryName=${param.queryName}" class="btn btn-success">新增</a>
					</div>
				</form>
				<table id="contentTable"
					class="table table-striped table-bordered table-condensed table-hover">
					<thead>
						<tr>
							<th width="23"></th>
							<th>名称</th>
							<th>URL</th>
							<th>路径</th>
							<th>创建日期</th>
							<th>创建者</th>
							<th width="23"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="o" varStatus="status">
							<tr class="${status.index % 2 == 0? 'warning':'info'}">
								<td align="center"><a href="${ctx}/cms/channel?page=${page.currentPage}&crud=r&channelId=${o.channelId}&queryName=${param.queryName}"><i class="icon-edit"></i></a></td>
								<td>${o.channelName}</td>
								<td>${o.channelUrl}</td>
								<td>${o.channelPath}</td>
								<td>${o.date}</td>
								<td>${o.creator}</td>
								<td align="center"><a href="${ctx}/cms/channel?page=${page.currentPage}&crud=d&channelId=${o.channelId}&queryName=${param.queryName}"><i class="icon-remove"></i></a></td>
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
				<form id="editForm" name="editForm" action="${ctx}/cms/channel?page=${page.currentPage}&crud=u&channelId=${channel.channelId}&queryName=${param.queryName}" method="post" class="form-horizontal">
					<input type="hidden" id="channelId" name="channelId" value="${channel.channelId}" />
					<fieldset>
						<div class="control-group">
							<label for="channelName" class="control-label">名称:</label>
							<div class="controls">
								<input type="text" id="channelName" name="channelName" value="${channel.channelName}" class="input-large required" />
							</div>
						</div>
						<div class="control-group">
							<label for="channelUrl" class="control-label">URL:</label>
							<div class="controls">
								<input type="text" id="channelUrl" name="channelUrl" value="${channel.channelUrl}" class="input-large required" />
							</div>
						</div>
						<div class="control-group">
							<label for="channelPath" class="control-label">路径:</label>
							<div class="controls">
								<input type="text" id="channelPath" name="channelPath" value="${channel.channelPath}" class="input-large" />
							</div>
						</div>
						<div class="control-group">
							<label for="channelOrder" class="control-label">排序:</label>
							<div class="controls">
								<input type="text" id="channelOrder" name="channelOrder" value="${channel.channelOrder}" class="input-large required" />
							</div>
						</div>
						<div class="control-group">
							<label for="templateId" class="control-label">模板:</label>
							<div class="controls">
								<select id="templateId" name="templateId">
									<c:forEach items="${templates}" var="o">
										<option value="${o.templateId}" ${o.selected? 'selected':''}>${o.templateName}</option>
									</c:forEach>
								</select>
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