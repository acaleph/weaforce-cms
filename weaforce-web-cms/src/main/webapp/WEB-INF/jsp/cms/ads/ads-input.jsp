<%@page contentType="text/html; charset=UTF-8" errorPage=""%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<title>Ads Input</title>
<script type="text/javascript">
$(document).ready(function(){
	$('#adsForm').validate();
	if ($('#adsIsParse').val() == '1'){$('#setParse').attr('checked','true');}
	
	//var oFCKeditor = new FCKeditor("adsContentHtml","100%","238","Basic") ;
	//oFCKeditor.ReplaceTextarea() ;
	//广告风格
	$('#addStyleItem').click(function(){
		var row = $("#template").clone();
		row.show();
		row.appendTo("#styleItems");//添加到模板的容器中;
	})
});
function OnUploadCompleted(imageUrl,imageUrlResize,imageFile,imageFileResize,maxWidth,maxHeight){
	document.all.adsPictureUrl.value=imageUrl;
	//document.all.articleImageUrlResize.value=imageUrlResize;
	//document.all.articleImageFile.value=imageFile;
	//document.all.articleImageFileResize.value=imageFileResize;
	//document.all.articleImageWidth.value=maxWidth;
	//document.all.articleImageHeight.value=maxHeight;
}
//function OnUploadCompleted(fileUrl){
//	document.all.adsPictureUrl.value=fileUrl;
//}
/* 为上传窗口提供图片URL */
function getPic(){
	return document.all.adsPictureUrl.value;
}
/* 为上传窗口提供图片宽度 */
function getWidth(){
	return 150;// document.all.articleImageWidth.value;
}
/* 为上传窗口提供图片高度 */
function getHeight(){
	return 150;// document.all.articleImageHeight.value;
}
function openwinx(url,name,w,h) { 
    window.open(url,name,"top=100,left=400,width=" + w + ",height=" + h + ",toolbar=no,menubar=no,scrollbars=yes,resizable=no,location=no,status=no")
}
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img src="/common/image/point.gif" width="5" height="6" >&nbsp;系统导航-&gt;<a href="ads.action">广告管理</a>-&gt;广告维护</td>
	</tr>
</table>
<div class="spacer-2"></div>
<form id="adsForm" name="adsForm" action="ads!save.action" method="Post">
<table border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr>
		<td align="left">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="stripe" align="center">
			<tr class="table-head">
				<td height="23" width="269" colspan="8">广告内容</td>
			</tr>
			<tr>
				<td>
					<input type="hidden" id="cityId" name="cityId" value="<s:property value='cityId' />">
					<input type="hidden" id="adsId" name="adsId" value="<s:property value='adsId' />">
					<input type="hidden" id="channelId" name="channelId" value="<s:property value='channelId' />">
				</td>
			</tr>
			<tr>
				<td>广告目录:</td>
				<td>
					<table>
						<tr>
							<td><s:select id="categoryId" name="categoryId" value="%{adsCategory.categoryId}" list="categoryList" listKey="categoryId" listValue="categoryName" cssClass="required" /></td>
							<td>标签:</td>
							<td><s:select id="tagId" name="tagId" value="%{adsTag.tagId}" list="tagList" listKey="tagId" listValue="tagTag" cssClass="required" /></td>
							<td>所在区域:</td>
							<td><s:select id="zoneId" name="zoneId" value="%{adsZone.zoneId}" list="zoneList" listKey="zoneId" listValue="zoneName" cssClass="required" /></td>
							<td>发布:</td>
							<td><input type="hidden" id="adsIsParse" name="adsIsParse" size="1" value="<s:property value='adsIsParse' />">
								<input type="checkbox" id="setParse" name="setParse" onclick="javascript:(this.checked)? document.adsForm.adsIsParse.value=1:document.adsForm.adsIsParse.value=0;"></td>
						</tr>
					</table>
				</td>
				<td rowspan="6"  align="center">
					<table id="styleItems" class="stripe" height="100%"  width="100%">
						<tr class="table-head"><td height="23">风格特点</td><td align="right"><img id="addStyleItem" class="append" src="/common/image/iconclose.gif"></td></tr>
						<tr id="template" style="display:none;">
							<td>风格:</td>
							<td><input id="styleItem" name="styleItem" value="">&nbsp;<input id="styleId" name="styleId" type="hidden"></td>
						</tr>
						<s:iterator value="adsStyle">
						<tr>
							<td>风格:</td>
							<td><input id="styleItem" name="styleItem" value="<s:property value='styleItem'/>">&nbsp;<input id="styleId" name="styleId" value="<s:property value='styleId'/>" type="hidden"></td>
						</tr>
						</s:iterator>
					</table>
				</td>
			</tr>
			<tr>
				<td>名称:</td>
				<td colspan="2"><input id="adsName" name="adsName" value="<s:property value='adsName'/>" size="68" class="required"></td>
			</tr>
			<tr>
				<td>简介:</td>
				<td colspan="2"><input id="adsIntro" name="adsIntro" value="<s:property value='adsIntro'/>" size="68" class="required"></td>
			</tr>
			<tr>
				<td>网站:</td>
				<td colspan="2"><input id="adsWeb" name="adsWeb" value="<s:property value='adsWeb'/>" size="68"></td>
			</tr>
			<tr>
				<td>欢迎信息:</td>
				<td colspan="2"><input id="adsWelcome" name="adsWelcome" value="<s:property value='adsWelcome'/>" size="68"></td>
			</tr>
			<tr>
				<td>上联:</td>
				<td colspan="2"><input id="adsInfoLeft" name="adsInfoLeft" value="<s:property value='adsInfoLeft'/>" size="68"></td>
			</tr>
			<tr>
				<td>下联:</td>
				<td colspan="2"><input id="adsInfoRight" name="adsInfoRight" value="<s:property value='adsInfoRight'/>" size="68"></td>
			</tr>
			<tr>
				<td>图片:</td>
				<td  colspan="2">
					<input id="adsPictureUrl" name="adsPictureUrl" size="52" value="<s:property value='adsPictureUrl' />">
					<input type="button" value="上传图片" onclick="javascript:openwinx('/common/jsp/upload-image.jsp','upload_image_servlet','350','350')">
				</td>
			</tr>
			<tr>
				<td>地址:</td>
				<td><input id="adsAddress" name="adsAddress" value="<s:property value='adsAddress'/>" size="68" class="required"></td>
				<td>
					<table>
						<tr>
							<td>坐标X:</td>
							<td><input id="adsMapX" name="adsMapX" value="<s:property value='adsMapX'/>" size="10" ></td>
							<td>坐标Y:</td>
							<td><input id="adsMapY" name="adsMapY" value="<s:property value='adsMapY'/>" size="10" ></td>
							<td>热点级别:</td>
							<td><s:select id="adsHotLevel" name="adsHotLevel" value="%{adsHotLevel}" list="{1,2,3,4,5}" /></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>电话:</td>
				<td colspan="2">
					<table>
						<tr>
							<td><input id="adsPhone" name="adsPhone" value="<s:property value='adsPhone'/>" size="22" class="required"></td>
							<td>时间:</td>
							<td><input id="adsPhoneTime" name="adsPhoneTime" value="<s:property value='adsPhoneTime'/>" size="28" class="required"></td>
							<td>日期:</td>
							<td><input id="adsPhoneDay" name="adsPhoneDay" value="<s:property value='adsPhoneDay'/>" size="28" class="required"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td background="/common/image/cyrybg.gif">服务:</td>
				<td background="/common/image/cyrybg.gif" colspan="2"><div style="overflow: auto; width: 100%;" class="fontcolor"><s:checkboxlist name="checkedServiceIds" list="serviceList" listKey="serviceId" listValue="serviceName" /></div></td>
			</tr>
			<!-- 
			<tr>
				<td>文章内容：</td>
				<td colspan="2"><textarea id="adsContentHtml" name="adsContentHtml"><s:property value="adsContentHtml" /></textarea></td>
			</tr>
			 -->
			<tr><td height="3" colspan="3"><hr size="1"></td></tr>
			<tr>
				<td colspan="3" height="28">
				<table>
					<tr>
						<td><input onClick="doSubmit('adsForm','ads!save.action');" type="button" id="save" name="save" value="Save" class="button"></td>
						<td><input onClick="doSubmit('adsForm','ads!parse.action');" type="button" id="parse" name="parse" value="Parse" class="button"></td>
						<td><input onClick="doSubmit('adsForm','ads!delete.action');" type="button" id="delete" name="delete" value="Delete" class="button"></td>
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
