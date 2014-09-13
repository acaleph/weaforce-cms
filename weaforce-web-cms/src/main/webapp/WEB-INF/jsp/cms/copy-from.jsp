<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Copy From</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#table-stripe tr:odd').addClass('odd');
	$('#table-stripe tr:even').addClass('even');
		
	$("#table-stripe tr").mouseover(function(){  
        //如果鼠标移到class为stripe的表格的tr上时，执行函数
		$(this).addClass("over");}).mouseout(function(){ 
        //给这行添加class值为over，并且当鼠标一出该行时执行函数
        $(this).removeClass("over");
	})  //移除该行的class
});
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="stripe">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;文章作者</td>
	</tr>
</table>
<div class="spacer-2"></div>
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center">
		<td><input type="button" id="create" name="create" value="create" onClick="javascript:doSubmit('listPage','copy-from!input.action')"></td>
	</tr>
</table>
<form id="listPage" name="listPage" action="copy-from.action" method="post">
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th height="23"></th>
		<th>名称</th>
		<th>邮件</th>
		<th></th>
	</tr>
	<s:iterator id="copyFrom" value="pageInfo.result">
		<tr align="left">
			<td height="23"><img class="pointerImage" border="0"
				title="Edit" src="/common/image/edit_inline.gif"
				onClick="javascript:doSubmit('listPage','copy-from!input.action?fromId=<s:property  value='fromId' />');"></td>
			<td><s:property value="fromName" /></td>
			<td><s:property value="fromWeb" /></td>
			<td><img class="pointerImage" border="0" title="Delete"
				src="/common/image/delete_inline.gif"
				onClick="javascript:if (confirm('是否要删除当前内容?')) doSubmit('listPage','copy-from!delete.action?fromId=<s:property  value='fromId' />');"></td>
		</tr>
	</s:iterator>
</table>
</form>
</body>
</html>