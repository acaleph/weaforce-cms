<%@page contentType="text/html; charset=UTF-8" errorPage=""%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Article input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#articleForm').validate();
	if ($('#articleIsParse').val() == '1'){$('#setParse').attr('checked','true');}
	
	var oFCKeditor = new FCKeditor('contentContent') ;
	oFCKeditor.Height = 358;
	oFCKeditor.ReplaceTextarea() ;
});
function OnUploadCompleted(imageUrl,imageUrlResize,imageFile,imageFileResize,maxWidth,maxHeight){
	document.all.articleImageUrl.value=imageUrl;
	document.all.articleImageUrlResize.value=imageUrlResize;
	document.all.articleImageFile.value=imageFile;
	document.all.articleImageFileResize.value=imageFileResize;
	document.all.articleImageWidth.value=maxWidth;
	document.all.articleImageHeight.value=maxHeight;
}
/* 为上传窗口提供图片URL */
function getPic(){
	return document.all.articleImageUrl.value;
}
/* 为上传窗口提供图片宽度 */
function getWidth(){
	return document.all.articleImageWidth.value;
}
/* 为上传窗口提供图片高度 */
function getHeight(){
	return document.all.articleImageHeight.value;
}
/*以创建时间作为图片文件名称*/
//function getCreateTime(){
//	return document.all.createTime.value;
//}
function openwinx(url,name,w,h) { 
    window.open(url,name,"top=100,left=400,width=" + w + ",height=" + h + ",toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no")
}
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="article.action">文章管理</a>-&gt;文章内容</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="articleForm" name="articleForm" action="article!save.action" method="Post">
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr>
		<td align="left">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe" align="center">
			<tr class="table-head">
				<td height="23" width="269" colspan="4">文章内容</td>
			</tr>
			<tr>
				<td>
					<input type="hidden" id="articleId" name="articleId" value="<s:property value='articleId' />">
					<input type="hidden" id="channelId" name="channelId" value="<s:property value='channelId' />">
					<input type="hidden" id="articleImageWidth" name="articleImageWidth" value="<s:property value='articleImageWidth' />">
					<input type="hidden" id="articleImageHeight" name="articleImageHeight" value="<s:property value='articleImageHeight' />">
					<input type="hidden" id="createTime" name="createTime" value="<s:property value='createTime' />">
					<input type="hidden" id="articleImageUrlResize" name="articleImageUrlResize" value="<s:property value='articleImageUrlResize' />">
					<input type="hidden" id="articleImageFile" name="articleImageFile" value="<s:property value='articleImageFile' />">
					<input type="hidden" id="articleImageFileResize" name="articleImageFileResize" value="<s:property value='articleImageFileResize' />">
				</td>
			</tr>
			<tr>
				<td>文章栏目:</td>
				<td colspan="3">
				<table>
					<tr>
						<td><s:select id="categoryId" name="categoryId" value="%{articleCategory.categoryId}" list="categoryList" listKey="categoryId" listValue="categoryName" cssClass="required" /></td>
						<td>标题：</td>
						<td><input id="articleTitle" name="articleTitle" value="<s:property value='articleTitle'/>" size="58" class="required"></td>
						<td>发布：</td>
						<td><input type="hidden" id="articleIsParse" name="articleIsParse" size="1" value="<s:property value='articleIsParse' />">
							<input type="checkbox" id="setParse" name="setParse" onclick="javascript:(this.checked)? document.articleForm.articleIsParse.value=1:document.articleForm.articleIsParse.value=0;">
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td>副标题：</td>
				<td><input id="articleTitleSub" name="articleTitleSub" value="<s:property value='articleTitleSub'/>" size="48"></td>
				<td>图片：</td>
				<td><input id="articleImageUrl" name="articleImageUrl" size="48" value="<s:property value='articleImageUrl' />">
					<input type="button" value="上传图片" onclick="javascript:openwinx('/common/jsp/upload-image.jsp','upload_image_servlet','350','350')"></td>
			</tr>
			<tr>
				<td>文章简介：</td>
				<td colspan="3">
					<table>
						<tr>
							<td><textarea id="articleIntro" name="articleIntro" rows="3" cols="66"><s:property value="articleIntro" /></textarea></td>
							<td>
								<table>
									<tr>
										<td>作者：</td>
										<td><s:select id="authorId" name="authorId" value="%{articleAuthor.authorId}" list="authorList" listKey="authorId" listValue="authorName" /></td>
									</tr>
									<tr>
										<td>来源：</td>
										<td><s:select id="fromId" name="fromId" value="%{articleFrom.fromId}" list="fromList" listKey="fromId" listValue="fromName" /></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>文章内容：</td>
				<td colspan="3"><textarea id="contentContent" name="contentContent"><s:property value="articleContent.contentContent" /></textarea></td>
			</tr>
			<tr><td height="3" colspan="4"><hr size="1"></td></tr>
			<tr>
				<td colspan="4" height="28">
				<table>
					<tr>
						<td><input onClick="doSubmit('articleForm','article!save.action');" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="doSubmit('articleForm','article!parse.action');" type="button" id="parse" name="parse" value="Parse" class="button"></td>
						<td><input onClick="doSubmit('articleForm','article!delete.action');" type="button" id="delete" name="delete" value="Delete" class="button"></td>
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
