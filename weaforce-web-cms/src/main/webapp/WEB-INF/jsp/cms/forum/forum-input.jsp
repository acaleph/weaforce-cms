<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#forumForm').validate();
})
</script>
<title>Channel Input</title>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="forum.action">论坛管理</a>-&gt;论坛维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="forumForm" name="forumForm" action="forum!save.action" method="POST">
<table id="theTable" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr class="table-head">
		<td height="23" width="100%" colspan="2">&nbsp; 论坛信息</td>
	</tr>
	<tr>
		<td width="100%" align="center">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe">
			<tr>
				<td><input type="hidden" id="forumId" name="forumId" value="<s:property value='forumId' />" ></td>
			</tr>
			<tr>
				<td height="30">栏目:</td>
				<td><s:select id="categoryId" name="categoryId" value="%{forumCategory.categoryId}" list="categoryList" listKey="categoryId" listValue="categoryName" /></td>
			</tr>
			<tr>
				<td height="30">模板:<font color="red">*</font>:</td>
				<td><s:select id="templateId" name="templateId" value="%{forumTemplate.templateId}" list="templateList" listKey="templateId" listValue="templateName" /></td>
			</tr>
			<tr>
				<td height="30">名称:</td>
				<td><input id="forumName" name="forumName" value="<s:property value='forumName' />" class="required"></td>
			</tr>
			<tr>
				<td height="30">文件:</td>
				<td><input id="forumFileName" name="forumFileName" value="<s:property value='forumFileName' />" class="required"></td>
			</tr>
			<tr>
				<td height="30">排序:</td>
				<td><s:select id="forumOrder" name="forumOrder" value="%{forumOrder}" list="{1,2,3,4,5,6,7,8,9,10,11}" /></td>
			</tr>
			<tr>
				<td>描述:</td>
				<td><textarea id="forumDescription" name="forumDescription" rows="5" cols="98"><s:property value="forumDescription" /></textarea></td>
			</tr>
			<tr><td height="30" colspan="2"><hr size="1"></td></tr>
			<tr>
				<td colspan="2" height="33">
				<table>
					<tr>
						<td><input onClick="javascript:doSubmit('forumForm','forum!save.action')" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="javascript:doSubmit('forumForm','forum!delete.action')" type="button" id="delete" name="delete" value="Delete" class="button"></td>
						<td><input onClick="javascript:doSubmit('forumForm','forum!parse.action')" type="button" id="parse" name="parse" value="Parse" class="button"></td>
						<td><input onClick="javascript:doSubmit('forumForm','forum!lock.action')" type="button" id="lock" name="lock" value="Lock" class="button"></td>
						<td><input onClick="javascript:window.history.back();" type="button" id="return" name="return" value="Return" class="button"></td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>