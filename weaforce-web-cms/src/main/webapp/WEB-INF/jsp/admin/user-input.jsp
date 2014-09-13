<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
</head>
<body>
	<div class="span12">
		<ul class="nav nav-tabs" id="myTab">
			<li><a href="${ctx}/admin/user">列表</a></li>
			<li class="active"><a href="#">编辑</a></li>
		</ul>
		<form id="inputForm" action="${ctx}/admin/user/save" method="post" class="form-horizontal">
			<input type="hidden" id="userId" name="userId" value="${user.userId}" />
			<fieldset>
				<div class="row-fluid">
					<div class="span6">
						<div class="control-group">
							<label for="userName" class="control-label">中文姓名:</label>
							<div class="controls">
								<input type="text" id="userNameCn" name="userNameCn" value="${user.userNameCn}" class="input-large required" />
							</div>
						</div>
						<div class="control-group">
							<label for="userName" class="control-label">英文姓名:</label>
							<div class="controls">
								<input type="text" id="userNameEn" name="userNameEn" value="${user.userNameEn}" class="input-large required" />
							</div>
						</div>
					</div>
					<div class="span6">
						<div class="control-group">
							<label for="userLogin" class="control-label">登录:</label>
							<div class="controls">
								<input type="text" id="userLogin" name="userLogin" value="${user.userLogin}" class="input-large required" />
							</div>
						</div>
						<div class="control-group">
							<label for="defaultUserRoleId" class="control-label">角色:</label>
							<div class="controls">
								<select name="defaultUserRoleId">
									<c:forEach items="${roles}" var="o">
										<option value="${o.roleId}" ${o.selected? 'selected':''}>${o.roleName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="row-fluid">
					<div class="control-group">
						<div class="controls">
							<c:forEach items="${roles}" var="o">
								<label class="checkbox inline"> 
									<input type="checkbox" id="roleIds" name="roleIds" value="${o.roleId}" ${o.checked? 'checked':''}>${o.roleName}
								</label>
							</c:forEach>
						</div>
					</div>
					<div class="form-actions">
						<input id="submit_btn" class="btn btn-primary" type="submit" value="保存" />&nbsp;
						<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</body>
</html>