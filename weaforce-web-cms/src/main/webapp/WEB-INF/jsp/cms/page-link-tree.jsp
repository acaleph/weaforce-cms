<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Page link tree</title>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
		cache:false,
		url:"/cms/page-link!tree.action",
		type:"get",
		data:{'parentId':$('#parentId').val()},
		dataType:"json",
		success:function(data){
			 var html = "<ul class='url-link'>";
			 $.each(data,function(index,item){
			 	html += "<li id='" + item['linkId'] + "'>" + item['linkName'] + "</li>";
			 	var childHtml = "<ul class='url-link'>";
			 	$.each(data,function(childIndex,childItem){
			 		 if(item['linkId'] == childItem['linkFid'])
			 		 	 childHtml += "<li class='editable' id='" + childItem['linkId'] + "'>" + childItem['linkName'] + "</li>";
			 	})
			 	childHtml+= "</ul>";
			 	html += "</ul>";
			 	if (item['linkFid'] == "")
			 		$('#container-tree-link').append(html);
			 	if (childHtml != "<ul class='url-link'></ul>")
			 		$("#" + item['linkId']).append(childHtml);
			 })
		},
		complete:function(){
			$('li:has(ul)').click(function(event){
            	if (this == event.target) {
                	if ($(this).children().is(':hidden')){
                    	$(this).css({'list-style-image':'url(/common/jquery/plugin/tree/image/minus.gif)'}).children().slideDown();
                    }else {
                    	$(this).css({'list-style-image':'url(/common/jquery/plugin/tree/image/plus.gif)'}).children().slideUp();
                    }
                }
                return true;  //true:DOM element click event will be available.false: not available
            }).css({cursor:'pointer','list-style-image':'url(/common/jquery/plugin/tree/image/plus.gif)'}).children().hide();
            $('li:not(:has(ul))').css({cursor: 'pointer','list-style-image':'url(/common/jquery/plugin/tree/image/minus.gif)'});
            //加载工程项目
            $('li.editable').click(function(){
            	//$('#link').attr('value',$(this).attr('id'));
                $('#container-form-link').load("/cms/page-link!input.action",{"linkId":$(this).attr('id')});
                return false;
            })
		}
	})
	//同级树枝
	$('#sameNew').bind('click',function(){
		alert(" parentId: " + $('input[id="parentId"]').val());
		if ($('#parentId').val() != undefined)
			if ($('input[id="parentId"]').val() > 0)
				$('#container-form-link').load('/cms/page-link!input.action',{'parentId':$('input[id="parentId"]').val()});
	});
	//下一级树枝
	$('#nextNew').bind('click',function(){
		if ($('#linkId').val() != undefined){
			if ($('input[id="linkId"]').val() > 0)
				$('#container-form-link').load('/cms/page-link!input.action',{'parentId':$('input[id="linkId"]').val()});
		}else{
			$('#container-form-link').load('/cms/page-link!input.action',{'parentId':$('#parentId').val()});
		}
	});	
})
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="page-link.action">制定页面</a>-&gt;页面结构</td>
	</tr>
</table>
<form id="treePage" name="treePage" action="" method="post">
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%" height="50%">
	<tr class="table-head">
		<td height="23" width="98%" colspan="2">页面结构</td>
	</tr>
	<tr>
		<td>
			<input id="parentId" name="parentId" type="hidden" value="<%=request.getParameter("linkId") %>">
		</td>
	</tr>
	<tr>
		<td>
		<table border="0" cellpadding="0" cellspacing="1" align="center" width="100%" height="100%" class="stripe">
			<tr>
				<td valign="top"><div id="container-tree-link"></div></td>
				<td width="68%"><div id="container-form-link"></div></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table border="0" cellpadding="0" cellspacing="1" align="center" width="100%" height="100%" class="stripe">
			<tr height="23">
				<td align="center">
				<table>
					<tr>
						<security:authorize ifAnyGranted="ROLE_CMS_CRUD_USER">
						<td><input type="button" id="sameNew" name="sameNew" value="新增同级" class="button"></td>
						<td><input type="button" id="nextNew" name="nextNew" value="新增下级" class="button"></td>
						</security:authorize>
					</tr>
				</table>
				</td>
			</tr>
			<tr><td colspan="2"><hr color="red"></td></tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>