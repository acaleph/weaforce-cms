<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色编辑</title>
</head>
<body>
	<div class="span12">
		<ul class="nav nav-tabs" id="myTab">
			<li><a href="${ctx}/admin/role">列表</a></li>
			<li class="active"><a href="#">编辑</a></li>
		</ul>
		<form id="inputForm" action="${ctx}/admin/role/save" method="post" class="form-horizontal">
			<input type="hidden" id="roleId" name="roleId" value="${role.roleId}" />
			<fieldset>
				<div class="control-group">
					<label for="roleName" class="control-label">名称:</label>
					<div class="controls">
						<input type="text" id="roleName" name="roleName"
							value="${role.roleName}" class="input-large required" />
					</div>
				</div>
				<div class="control-group">
					<label for="roleDescription" class="control-label">描述:</label>
					<div class="controls">
						<textarea id="roleDescription" name="roleDescription"
							class="input-large">${role.roleDescription}</textarea>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<c:forEach items="${authorities}" var="o">
							<label class="checkbox inline"> <input type="checkbox"
								id="authorityIds" name="authorityIds" value="${o.authorityId}"
								<c:if test="${o.checked}">checked="checked"</c:if>>${o.authorityName}
							</label>
						</c:forEach>
					</div>
				</div>
				<div class="form-actions">
					<input id="submit_btn" class="btn btn-primary" type="submit"
						value="提交" />&nbsp; <input id="cancel_btn" class="btn"
						type="button" value="返回" onclick="history.back()" />
				</div>
			</fieldset>
		</form>
	</div>
</body>
</html>