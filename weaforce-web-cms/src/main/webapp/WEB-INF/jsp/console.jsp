<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" src="/common/jquery/jquery-1.7.1.min.js"></script>
<title>Weaforce Console</title>
<script type="text/javascript">
$(document).ready(function(){
	$.ajax({
        	cache:false,
        	url:"/system/user!menu.action",
            type:"post",
            data:{},
            dataType:"json",
            success:function(data){
                //菜单绘制
            	menuTree(data);
            }
	});
});
//生成菜单
function menuTree(menuItems){
	var html = "";
	$.each(menuItems,function(itemIndex,item){
		html = "<ul class='url-link'>";
		$.each(menuItems,function(childItemIndex,childItem){
        if(item['menuId'] == childItem['menuFid'])
            //判断是否有链接
        	if (childItem['menuUrl'] == '')
            	html += "<li id='" + childItem['menuId'] + "'>" + childItem['menuName'] + "</li>";
            else
            	html += "<li id='" + childItem['menuId'] + "'><a href='" + childItem['menuUrl'] +"' target='container' onMouseOver=\"window.status='" + childItem['menuName'] +"';return true\" >" + childItem['menuName'] + "</a></li>";
        })
        html+="</ul>";
		//alert("item: " + item['menuId'] + " " + html);
		if (item['menuId'] == 1)
			$('#tree-root').append(html);
		else
			if (html != "<ul class='url-link'></ul>")
				$("#" + item['menuId']).append(html);
	});
    $('li:has(ul)').click(function(event){
		if (this == event.target) {
			if ($(this).children().is(':hidden')) {
				$(this).css({'list-style-image':'url(/common/jquery/plugin/tree/image/minus.gif)'}).children().slideDown();
        	}else {
                $(this).css({'list-style-image':'url(/common/jquery/plugin/tree/image/plus.gif)'}).children().slideUp();
            }
		}
        return true;  //true:DOM element click event will be available.false: not available
	}).css({cursor:'pointer','list-style-image':'url(/common/jquery/plugin/tree/image/plus.gif)'}).children().hide();
	$('li:not(:has(ul))').css({cursor: 'default','list-style-image':'url(/common/jquery/plugin/tree/image/minus.gif)'});
	$('#splitter').click(function(event){
		if (this==event.target)
			if($('#console-left-menu').is(':hidden')){
				$(this).attr('src','/common/image/splitter_left.gif');
                $('#console-left-menu').show();
			}else{
            	$(this).attr('src','/common/image/splitter_right.gif');
                $('#console-left-menu').hide();
            }
	}).css({cursor:'pointer'});
}
</script>
<style type="text/css">
body {

    FONT-SIZE: 12px;
    margin: 0;
    min-height: 100%;
    font-family: "Arial", "Helvetica", "Verdana", "sans-serif", "宋体";
}
#console-banner {
	margin: 0;
    font-size: 8px;
    background-color: #222222;
    border-bottom: 4px solid #273f85;
	background-image: url(/common/image/banner-bgcolor.gif); 
	/*BORDER: #BBC5E9 1px solid;*/
	/*height:51px;*/
	/*margin-bottom: 1.0em;*/
	/*
    margin: 0;
    font-size: 8px;
    background-color: #222222;
    border-bottom: 4px solid #273f85;
    */
}
#console-banner h1 {
    padding: 0;
    font-size: 21px;
    color: #FFF;
}
/* Main Menu */
#console-content-menu-main {
    clear: both;
    float: right;
    width: 100%;
    font-size: 8px;
    margin-top:0;
    margin-right: 0;
    margin-left: 0;
}
#console-content-menu-main ul {
    margin: 0px 0px 0px 0px;
    padding: 0px 0px 0px 0px;
    list-style: none;
    background-color: #CCCCCC;
}

#console-content-menu-main li {
    padding: 0px 0px 0px 0px;
    display: inline;
    font-size: 11px;
}
#console-content-menu-main li a {
    display: block;
    padding: 4px 10px;
    margin: 1px;
    /*background-color: #CCCCCC;*/
    background-image:url(/common/image/menu.gif);
    border-bottom: 1px solid #EEE;
    text-decoration: none;
    color: #1d2f64;
}

#console-content-menu-main li a:hover {
    /*background-color: #999999;*/
    background-image: url(/common/image/menudowm.gif);
}
#console-content-menu-tree {
    clear: both;
    float: right;
    width: 100%;
    font-size: 12px;
    margin-left: 0;
    margin: 0px 0px 0px 0px;
}
#console-content-menu-main ul {
    margin: 0px 0px 0px 0px;
    padding: 0px 0px 0px 0px;
    list-style: none;
    /*background-color: #CCCCCC;*/
}

#console-content-menu-tree li{
    margin: 0px 0px 0px 0px;
    padding: 0px 0px 0px 0px;
}
#console-footer { /*margin-bottom: 1.0em;*/
    margin: 0;
    font-size: 8px;
    background-color: #222222;
    border-bottom: 4px solid #273f85;
}
.url-link{
    padding-left:8px;
    padding-top:3px;
    margin-left:10px;
}

#main-right{
	float:right;	
	background-image: url(/common/image/banner-right.gif);
	text-algin:center;
	font-family: Arial, Helvetica, sans-serif;
	font-size: 12px;
	font-weight: bold;
	color: #FFFFFF;
	width:360px;
	height:50;
}
.c{color:  #FFFFFF; padding:2px 0px 0px 10px; text-decoration: none;}
a.c :link {
	color:  #FFFFFF;	text-decoration: none;
}
a.c:visited {
	color: #FFFFFF;	text-decoration: none;
}
a.c:hover {
	color:  #FFFFFF;	text-decoration: none;
}
a.c:active {
	color:  #FFFFFF;	text-decoration: none;
}
</style>
</head>
<body>
<table id="console-wrapper" width="100%" height="100%" border="0"
	align="center" cellPadding="0" cellSpacing="0">
	<!-- Banner -->
	<tr>
		<td height="18" id="console-banner">
			<table>
				<tr>
	   				<td width="55%" valign="top"><img src="/common/image/banner-left.jpg" width="623" height="51"></td>
	   				<td width="45%" >
	   					<div id="main-right">
	    					<div align="center"><a href="http://www.weaforce.com" target="_blank">WeaForce</a>&nbsp; |<a href="/userfiles/ManualGuideOfCONE.pdf" title="User manual guide" target="_blank">&nbsp; Manual</a> &nbsp;|&nbsp;<a href="j_spring_security_logout" class="c"> Logout</a></div>
	    				</div>	
	    			</td>
	   			</tr>
			</table>
		</td>
	</tr>
	<!-- Content -->
	<tr>
		<td>
        	<table id="console-content"  cellspacing="0" cellpadding="0" width="100%" height="100%" border="0">
            	<tr>
                	<td id="console-left-menu" width="18%" id="console-content-menu">
                    	<table cellspacing="0" cellpadding="0" width="100%" height="100%" border="0" class="table-border">
                        	<tr>
                            	<td align="center" valign="top" height="15%">
                                	<div id="console-content-menu-main">
                                    	<ul>
                                            <li><a href="/system/base/board.action" title="Board" target="container">公告板</a></li>
                                        	<li><a href="/system/base/event.action" title="Event" target="container">备忘录</a></li>
                                            <li><a href="/system/user!password.action" title="User" target="container">当前用户</a></li>
                                        </ul>
                                     </div>
                                </td>
                            </tr>
                         	<tr>
                            	<td align="left" valign="top">
                                	<div id="console-content-menu-tree" align="left">
                                    	<ul class='url-link'><li id="tree-root">我的应用程序</li></ul>
                                    </div>
                                </td>
                            </tr>
                       </table>
                    </td>
                    <td width="1%" background="/common/image/spliter-up-down.gif">
                    	<table cellspacing="0" cellpadding="0" width="100%" border="0" class="table-border">
                        	<tr><td width="8" height="40%"></td></tr>
                            <tr>
                                <td height="71"><img id="splitter" src="/common/image/splitter_left.gif" width="8" height="71" alt=""></td>
                            </tr>
                            <tr><td height="40%"></td></tr>
                         </table>
                     </td>
                     <td width="78%" height="100%" align="left" valign="top">
                     	<table cellspacing="0" cellpadding="0" width="100%" height="100%" border="0" class="table-border">
                        	<tr><td><iframe marginwidth="0" marginheight="0" src="/system/base/board.action" frameborder="0" scrolling="auto" width="100%" height="100%" id="container" name="container"></iframe></td></tr>
                         </table>
                     </td>
                </tr>
        	</table>
		</td>
	</tr>
	<tr>
		<td id="console-footer" height="3%">
			<div>
            	<div class="bg"></div>
                <div class="inner">
                	<p><span class="first">Sponsored by: acaleph8@yahoo.com.cn In</span><span><a href="http://www.weaforce.com" target="_blank">Manfred</a></span></p>
                </div>
            </div>
		</td>
	</tr>
</table>
</body>
</html>