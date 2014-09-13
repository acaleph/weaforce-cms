<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>像册</title>
<script type="text/javascript">
$(document).ready(function(){
	$("#queryDateFrom").datepicker({dateFormat: $.datepicker.W3C}).attr("readonly", "readonly");
	$("#queryDateTo").datepicker({dateFormat: $.datepicker.W3C}).attr("readonly", "readonly");
	//检索当前文章的关联文章'
	albumList($('#articleId').val(),$('#queryName').val(),$('#queryDateFrom').val(),$('#queryDateTo').val());
})
albumList = function (articleId,albumName,dateFrom,dateTo){
	$.ajax({
		type:"post",
		dataType:"json",
		url:"/cms/album!getAlbumListJSON.action",
		data:{'articleId':articleId,'queryName':albumName,'queryDateFrom':dateFrom,'queryDateTo':dateTo},
		success:function(data){
			$('tr.relation').remove();
			$('#template').removeClass('hidden');
			$.each(data,function(index,item){
				//alert(indexItem + ":   " + item['staffLogin']);
				var row = $("#template").clone();
				if (item['checked'] == 0)
					row.find("#albumId").html("<input type=\"checkbox\" id=\"checkedAlbumIds\" name=\"checkedArticleIds\" value=\"" + item['albumId'] + "\" onClick=\"saveAlbum(" +$('#articleId').val() + "," + item['albumId'] + ")\">");
				if (item['checked'] == 1)
					row.find("#albumId").html("<input type=\"checkbox\" id=\"checkedAlbumIds\" name=\"checkedArticleIds\" value=\"" + item['albumId'] + "\" onClick=\"saveAlbum(" +$('#articleId').val() + "," + item['albumId'] + ")\" checked=\"true\">");
				row.find("#albumName").text(item['albumName']);
				row.find("#createTime").text(item['createTime']);
				row.attr("id","ready" + "_" + item['albumId']).addClass("rowData");//改变绑定好数据的行的id
				row.appendTo("#table-stripe");//添加到模板的容器中;
				row.addClass("relation"); //预备删除
			})
			if (data){
				$('#table-stripe tr:not([th]):odd').addClass('odd');
				$('#table-stripe tr:not([th]):even').addClass('even');
				$("#table-stripe tr:not([th])").mouseover(function(){  
			        //如果鼠标移到class为stripe的表格的tr上时，执行函数
					$(this).addClass("over");}).mouseout(function(){ 
			        //给这行添加class值为over，并且当鼠标一出该行时执行函数
			        $(this).removeClass("over");
				})  //移除该行的class
			}
		},
		complete:function(){
			$('#template').addClass('hidden');
		}
	});
}
//保存关联文章
saveAlbum = function(articleId,albumId){
	//alert("primaryId: " + primaryId + " slaveId: " + slaveId);
	$.ajax({
		type:"post",
		dataType:"json",
		url:"/cms/article!saveAlbum.action",
		data:{'articleId':articleId,'albumId':albumId},
		success:function(data){
			$.each(data,function(index,item){
				alert(item['returnMsg']);
			})
		}
	});
}
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6">&nbsp;系统导航-&gt;相关文章&nbsp;<span><font color="red"><s:actionmessage /></font></span></td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="listPage" name="listPage" action="contact.action" method="post">
<table border="0" cellpadding="0" cellspacing="1" id="searchbg" align="center" width="98%">
	<tr align="center">
		<td>名称:</td>
		<td><input id="queryName" name="queryName" value="<s:property value='queryName'/>"></td>
		<td>开始:</td>
		<td><input id="queryDateFrom" name="queryDateFrom" value="<s:property value="queryDateFrom" />" size="10" class="text"></td>
		<td>终止:</td>
		<td><input id="queryDateTo" name="queryDateTo" value="<s:property value="queryDateTo" />" size="10" class="text"></td>
		<td><input type="button" id="search" name="search" value="Search" onClick="javascript:albumList($('#articleId').val(),$('#queryName').val(),$('#queryDateFrom').val(),$('#queryDateTo').val());"></td>
	</tr>
</table>
<div class="spacer-2"></div>
<table id="table-stripe" border="0" cellpadding="0"  cellspacing="1" class="stripe" align="center" width="98%">
	<tr align="center" class="table-head">
		<th height="23" width="23"></th><th>标题</th><th>创建日期</th>
	</tr>
	<tr>
		<td colspan="3">
			<input type="hidden" id="articleId" name="articleId" value="<s:property value='articleId' />" >
			<input type="hidden" id="articleAlbum" name="articleAlbum" value="<s:property value='articleAlbum' />" >
		</td>
	</tr>
	<tr id="template">
		<td id="albumId" height="23"></td>
		<td id="albumName"></td>
		<td id="createTime"></td>
	</tr>
</table>
</form>
</body>
</html>