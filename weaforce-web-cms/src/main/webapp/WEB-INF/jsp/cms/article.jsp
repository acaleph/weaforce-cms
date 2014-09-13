<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- JQ UI -->
<script src="${ctx}/static/jquery/ui.core.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery/ui.datepicker.js" type="text/javascript"></script>
<!-- zTree -->
<script src="${cxt}/static/jquery/plugin/ztree.core.js" type="text/javascript"></script>
<script src="${cxt}/static/jquery/plugin/ztree.excheck.js" type="text/javascript"></script>
<!-- KindEditor -->
<script src="${cxt}/static/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/static/kindeditor/lang/zh_CN.js" charset="utf-8"></script>

<!-- form -->
<script type="text/javascript" src="${cxt}/static/jquery/plugin/form.js"></script>
<!-- zTree -->
<link href="${cxt}/static/jquery/plugin/ztree.css" type="text/css" rel="stylesheet">
<!-- Jquery UI with datepicker -->
<link href="${ctx}/static/jquery/jquery-ui.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/jquery/ui.core.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/jquery/ui.datepicker.css" type="text/css" rel="stylesheet" />
<!-- KindEditor -->
<link href="${ctx}/static/kindeditor/themes/default/default.css"  rel="stylesheet" />

<title>舞文弄墨</title>
<script type="text/javascript">
	var zNodes = ${sessionScope.category}
	$(function() {
		$("#queryDateFrom").datepicker({dateFormat: $.datepicker.W3C,changeMonth: true,changeYear: true}).attr("readonly", "readonly");
		$("#queryDateTo").datepicker({dateFormat: $.datepicker.W3C,changeMonth: true,changeYear: true}).attr("readonly", "readonly");
		
		//Menu tree for modal
		$.fn.zTree.init($("#categoryModal"),{check:{enable:true,chkStyle:"radio",radioType:"all"},view:{showIcon:false}},zNodes);
		$('#selectCategory').bind("click", function() {
			var zTree = $.fn.zTree.getZTreeObj("categoryModal");
			var selectedNode = zTree.getCheckedNodes(true);
			$("#categoryId").attr("value", selectedNode[0].id);
			$("#categoryName").attr("value", selectedNode[0].name);
			$('#myCategory').modal("hide");
		});

		$('#mytab a').click(function(e) {
			e.preventDefault();
			$(this).tab('show');
		})
		//Default tab show
		var isActive = ${isActive};
		if (isActive == '1')
			$('#mytab a[href="#edit"]').tab('show');
		else
			$('#mytab a[href="#list"]').tab('show');
		
		//Upload file progress
		var bar = $('.bar');
		var status = $('#status');
		$('#uploadForm').ajaxForm({
			beforeSend : function() {
				status.empty();
				var percentVal = '0%';
				bar.css({"width":percentVal});
			},
			uploadProgress : function(event, position, total, percentComplete) {
				var percentVal = percentComplete + '%';
				bar.css({"width":percentVal})
			},
			success : function() {
				var percentVal = '100%';
				bar.css({"width":percentVal});
			},
			complete : function() {
				//status.html("上传成功！");
				$("#status").empty();
				$("#status").append("<button data-dismiss=\"alert\" class=\"close\">×</button>" + "上传成功");
				if (!$("#status").hasClass("alert alert-success"))
					$("#status").addClass("alert alert-success");
				return true;
			}
		});
		//Upload the file
		$('#upload').click(function(){
			document.uploadForm.action="${ctx}/cms/upload_image_servlet?subpath=${sessionScope.loginUser.userId}&maxWidth=" + $('#maxWidth').val() + "&maxHeight=" + $('#maxHeight').val();
			$('#uploadForm').submit();
		})
		//Channel changed
		$('#queryChannelId').change(function(){$('#searchForm').submit();})
	})
	//KindEditor
	KindEditor.ready(function(K) {
		var articleContent = K.create('#articleContent',{
			langType : 'en',
			allowFileManager : true,
			items:[
			        'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
			        'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
			        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
			        'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
			        'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
			        'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
			        'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
			        'anchor', 'link', 'unlink', '|', 'about'
			]
		});
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
				<form id="searchForm" name="searchForm" class="breadcrumb form-search" action="${ctx}/cms/article?page=${page.currentPage}&crud=l&articleId=${article.articleId}" method="post">
					<label for="queryTitle" class="control-label">名称:</label>
					<input id="queryTitle" name="queryTitle" type="text" value="${param.queryTitle}" data-provide="typeahead" class="input-medium">
					<label for="queryChannelId" class="control-label">频道:</label>
					<select id="queryChannelId" name="queryChannelId" class="input-medium">
						<c:forEach items="${channels}" var="o">
							<option value="${o.channelId}" ${o.channelId==param.queryChannelId? 'selected':''}>${o.channelName}</option>
						</c:forEach>
					</select>
					<label for="queryCategoryId" class="control-label">栏目:</label>
					<select id="queryCategoryId" name="queryCategoryId" class="input-medium">
						<c:forEach items="${categories}" var="o">
							<option value="${o.categoryId}" ${o.categoryId==param.queryCategoryId? 'selected':''}>${o.categoryName}</option>
						</c:forEach>
					</select>
					<label for="queryDateFrom" class="control-label">起始:</label>
					<input id="queryDateFrom" name="queryDateFrom" type="text" value="${param.queryDateFrom}" data-provide="typeahead" class="input-small">
					<label for="queryDateTo" class="control-label">终止:</label>
					<input id="queryDateTo" name="queryDateTo" type="text" value="${param.queryDateTo}" data-provide="typeahead" class="input-small">
					<button type="submit" class="btn btn-primary">查询</button>
					<div class="pull-right">
						<a href="${ctx}/cms/article?page=${page.currentPage}&crud=c&queryCategoryId=${param.queryCategoryId}&queryName=${param.queryName}" class="btn btn-success">新增</a>
					</div>
				</form>
				<table id="contentTable"
					class="table table-striped table-bordered table-condensed table-hover">
					<thead>
						<tr>
							<th width="23"></th>
							<th>名称</th>
							<th>发布</th>
							<th>创建日期</th>
							<th>创建者</th>
							<th width="23"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${page.content}" var="o" varStatus="status">
							<tr class="${status.index % 2 == 0? 'warning':'info'}">
								<td align="center"><a href="${ctx}/cms/article?page=${page.currentPage}&crud=r&articleId=${o.articleId}&queryName=${param.queryName}"><i class="icon-edit"></i></a></td>
								<td>${o.articleTitle}</td>
								<td><label class="checkbox"><input type="checkbox" disabled="disabled" ${o.articleIsParse == '1'? 'checked':''}></label></td>
								<td>${o.date}</td>
								<td>${o.creator}</td>
								<td align="center"><a href="${ctx}/cms/article?page=${page.currentPage}&crud=d&articleId=${o.articleId}&queryName=${param.queryName}"><i class="icon-remove"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="pagination">${page.pagination}</div>
			</div>
			<div class="tab-pane fade" id="edit">
				<c:if test="${not empty message}">
					<div id="message" class="alert alert-success">
						<button data-dismiss="alert" class="close">×</button>${message}
					</div>
				</c:if>
				<form id="editForm" name="editForm" action="${ctx}/cms/article?page=${page.currentPage}&crud=u&articleId=${article.articleId}&queryName=${param.queryName}" method="post" class="form-horizontal">
					<input type="hidden" id="articleId" name="articleId" value="${article.articleId}" />
					<input type="hidden" id="categoryId" name="categoryId" value="${article.articleCategory.categoryId}" />
					<input type="hidden" id="categoryId" name="categoryId" value="${article.articleCategory.categoryId}" />
					<input type="hidden" id="articleImageUrlResize" name="articleImageUrlResize" value="${article.articleImageUrlResize}"/>
					<fieldset>
						<div class="row-fluid">
							<div class="span6">
								<div class="control-group">
									<label for="articleTitle" class="control-label">标题:</label>
									<div class="controls">
										<input type="text" id="articleTitle" name="articleTitle" value="${article.articleTitle}" class="input-xlarge required" />
									</div>
								</div>
								<div class="control-group">
									<label for="articleImageUrl" class="control-label">图片:</label>
									<div class="controls">
										<div class="input-append">
											<input type="text" id="articleImageUrl" name="articleImageUrl" value="${article.articleImageUrl}" class="input-xlarge required" />
											<button class="btn" type="button" data-toggle="modal" data-target="#myImage">文件</button>
										</div>
									</div>
								</div>
							</div>
							<div class="span6">
								<div class="control-group">
									<label for="categoryName" class="control-label">栏目:</label>
									<div class="controls">
										<div class="input-append">
											<input id="categoryName" value="${article.articleCategory.categoryName}" class="input-large" readonly="readonly" type="text">
											<button class="btn" type="button" data-toggle="modal" data-target="#myCategory">选择</button>
										</div>
									</div>
								</div>
								<div class="control-group">
									<label for="articleIsActive" class="control-label">状态:</label>
									<div class="controls">
										<input type="checkbox" id="articleIsActive" name="articleIsActive" ${article.articleIsParse == '1'? 'checked':''}>
									</div>
								</div>
							</div>
						</div>
						<div class="row-fluid">
							<div class="control-group">
								<label for="articleIntro" class="control-label">简介:</label>
								<div class="controls">
									<textarea id="articleIntro" name="articleIntro" cols="80" rows="2" class="input-xxlarge" >${article.articleIntro}</textarea>
								</div>
							</div>
							<div class="control-group">
								<label for="articleContent" class="control-label">内容:</label>
								<div class="controls">
									<textarea id="articleContent" name="articleContent" cols="80" rows="10" class="input-xlarge" ><c:out value="${article.articleContent.contentContent}" /></textarea>
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
	<div id="myCategory" class="modal hide fade">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h3>选择栏目</h3>
		</div>
		<!--Modal header-->
		<div class="modal-body">
			<div class="row">
				<div class="span1"></div>
				<div class="span8 ">
					<div class="zTreeDemoBackground left">
						<ul id="categoryModal" class="ztree"></ul>
					</div>
				</div>
				<div class="span1" id="target"></div>
			</div>
		</div>
		<!--Modal body-->
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal">关闭</button>
			<button class="btn btn-primary" id="selectCategory">选择</button>
		</div>
		<!--Modal footer-->
	</div>
	<div id="myImage" class="modal hide fade">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal">&times;</button>
			<h3>上传文件</h3>
		</div>
		<!--Modal header-->
		<div class="modal-body">
			<div class="row-fluid">
				<div class="span1"></div>
				<div class="span8">
					<!-- File form -->
					<form id="uploadForm" name="uploadForm" action="${ctx}/cms/upload_image_servlet?subpath=${sessionScope.loginUser.userId}&maxWidth=150&maxHeight=150" method="post" enctype="multipart/form-data">
						<fieldset>
							<div class="row-fluid">
								<div class="span6">
									<div class="control-group">
										<label for="maxWidth" class="control-label">宽度:</label>
										<div class="controls">
											<input type="text" id="maxWidth" name="maxWidth" value="150" class="input-small required" />
										</div>
									</div>
								</div>
								<div class="span6">
									<div class="control-group">
										<label for="maxHeight" class="control-label">宽度:</label>
										<div class="controls">
											<input type="text" id="maxHeight" name="maxHeight" value="150" class="input-small required" />
										</div>
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="control-group">
									<label for="myfile" class="control-label">图片:</label>
									<div class="controls">
										<input type="file" id="myfile" name="myfile" class="input-xxlarge">
									</div>
								</div>
							</div>
							<div class="row-fluid">
								<div class="control-group">
									<div class="progress progress-striped active">
		              					<div class="bar" style="width: 0%"></div>
		            				</div>
									<div id="status"></div>
		            			</div>
		            		</div>
						</fieldset>
					</form>
				</div>
				<div class="span1" id="target"></div>
			</div>
		</div>
		<!--Modal body-->
		<div class="modal-footer">
			<button class="btn" data-dismiss="modal">关闭</button>
			<button class="btn btn-primary" id="upload">上传</button>
		</div>
		<!--Modal footer-->
	</div>
</body>
</html>