<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>模板维护</title>
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
				<form id="searchForm" name="searchForm" class="breadcrumb form-search" action="${ctx}/cms/template?page=${page.currentPage}&crud=l&templateId=${template.templateId}" method="post">
					<label for="queryName" class="control-label">名称：</label>
					<input id="queryName" name="queryName" type="text" value="${param.queryName}" data-provide="typeahead" class="input-large">
					<button type="submit" class="btn btn-primary">查询</button>
					<div class="pull-right">
						<a href="${ctx}/cms/template?page=${page.currentPage}&crud=c&queryName=${param.queryName}" class="btn btn-success">新增</a>
					</div>
				</form>
				<table id="contentTable"
					class="table table-striped table-bordered table-condensed table-hover">
					<thead>
						<tr>
							<th width="23"></th>
							<th>名称</th>
							<th>状态</th>
							<th>类型</th>
							<th>创建日期</th>
							<th>创建者</th>
							<th width="23"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="o" varStatus="status">
							<tr class="${status.index % 2 == 0? 'warning':'info'}">
								<td align="center"><a href="${ctx}/cms/template?page=${page.currentPage}&crud=r&templateId=${o.templateId}&queryName=${param.queryName}"><i class="icon-edit"></i></a></td>
								<td>${o.templateName}</td>
								<td><label class="checkbox"><input type="checkbox" class="disabled" ${o.templateIsActive == '1'? 'checked':''}></label></td>
								<td>
									<c:forEach items="${types}" var="t" varStatus="status">
										<c:if test="${t.key == o.templateType}">${t.value}</c:if>
									</c:forEach>
								</td>
								<td>${o.date}</td>
								<td>${o.creator}</td>
								<td align="center"><a href="${ctx}/cms/template?page=${page.currentPage}&crud=d&templateId=${o.templateId}&queryName=${param.queryName}"><i class="icon-remove"></i></a></td>
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
				<form id="editForm" name="editForm" action="${ctx}/cms/template?page=${page.currentPage}&crud=u&templateId=${template.templateId}&queryName=${param.queryName}" method="post" class="form-horizontal">
					<input type="hidden" id="templateId" name="templateId" value="${template.templateId}" />
					<fieldset>
					<div class="row-fluid">
							<div class="span6">
								<div class="control-group">
									<label for="templateName" class="control-label">名称:</label>
									<div class="controls">
										<input type="text" id="templateName" name="templateName" value="${template.templateName}" class="input-large required" />
									</div>
								</div>
								<div class="control-group">
									<label for="templateTitle" class="control-label">标题:</label>
									<div class="controls">
										<input type="text" id="templateTitle" name="templateTitle" value="${template.templateTitle}" class="input-large required" />
									</div>
								</div>
								<div class="control-group">
									<label for="typeId" class="control-label">类型:</label>
									<div class="controls">
										<select id="type" name="type" >
											<c:forEach items="${types}" var="o" varStatus="status">
												<option value="${o.key}"  <c:if test="${o.key == template.templateType}">selected</c:if>>${o.value}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="control-group">
									<label for="templateIsActive" class="control-label">状态:</label>
									<div class="controls">
										<input type="checkbox" class="disabled" ${template.templateIsActive == '1'? 'checked':''}>
									</div>
								</div>
							</div>
							<div class="span6">
								<div class="control-group">
									<label for="templateKeywords" class="control-label">关键字:</label>
									<div class="controls">
										<input type="text" id="templateKeywords" name="templateKeywords" value="${template.templateKeywords}" class="input-large required" />
									</div>
								</div>
								<div class="control-group">
									<label for="templateScript" class="control-label">脚本:</label>
									<div class="controls">
										<input type="text" id="templateScript" name="templateScript" value="${template.templateScript}" class="input-large required" />
									</div>
								</div>
								<div class="control-group">
									<label for="templateStyle" class="control-label">样式:</label>
									<div class="controls">
										<input type="text" id="templateStyle" name="templateStyle" value="${template.templateStyle}" class="input-large required" />
									</div>
								</div>
								<div class="control-group">
									<label for="templateFile" class="control-label">文件:</label>
									<div class="controls">
										<input type="text" id="templateFile" name="templateFile" value="${template.templateFile}" class="input-large required" />
									</div>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="control-group">
								<label for="templateContent" class="control-label">模板:</label>
								<div class="controls">
									<textarea id="templateContent" name="templateContent" class="input-xlarge" ><c:out value="${template.templateContent}" /></textarea>
								</div>
							</div>
							<div class="form-actions">
								<button id="save" name="save" class="btn btn-primary" type="submit" >保存</button>&nbsp; 
								<button id="cancel" name="cancel" class="btn" type="button" onclick="history.back()" >返回</button>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
	</div>
</body>
</html>