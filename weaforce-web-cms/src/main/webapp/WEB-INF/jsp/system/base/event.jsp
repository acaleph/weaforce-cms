<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript" src="/common/jquery/plugin/jquery.blockUI.js"></script>
<title>Calendar Event</title>
<script type="text/javascript">
var date = new Date();
var iToday = date.getDate();
$(document).ready(function(){
	calendar($('#year').val(),$('#month').val());
	$('#year').change(function(){
		$("tr[id='ready']").remove(); 
		$("#template").css("display","");
		calendar($(this).val(),$('#month').val());
	});
	$('#month').change(function(){
		$("tr[id='ready']").remove(); 
		$("#template").css("display","");
		calendar($('#year').val(),$(this).val());
	});
})
/*构建日历*/
function calendar(year,month){
	//alert("year: " + year + " month: " + month);
	$.ajax({
		cache:false,
		type:"post",
		dataType:"json",
		url:"/system/base/event!calendar.action",
		data:{'year':year,'month':month},
		success:function(data){
			//alert(data);
			$.each(data,function(indexItem,item){
				var row = $("#template").clone();
				if (item['sun'])
					$.each(item['sun'],function(indexEntry,entry){
						row.find("#sun").text(entry['day']).addClass(entry['class']).attr('id',entry['utc']).dblclick(function(){if ($(this).attr("id") != '*') addEvent($(this).attr("id"))}); //.click(function(){alert($(this).text())})
					});
				if (item['mon'])
					$.each(item['mon'],function(indexEntry,entry){
						row.find("#mon").text(entry['day']).addClass(entry['class']).attr('id',entry['utc']).dblclick(function(){if ($(this).attr("id") != '*') addEvent($(this).attr("id"))}); //.dblclick(function(){alert($(this).text())})
					});
				if (item['tue'])
					$.each(item['tue'],function(indexEntry,entry){
						row.find("#tue").text(entry['day']).addClass(entry['class']).attr('id',entry['utc']).dblclick(function(){if ($(this).attr("id") != '*') addEvent($(this).attr("id"))});
					});
				if (item['wed'])
					$.each(item['wed'],function(indexEntry,entry){
						row.find("#wed").text(entry['day']).addClass(entry['class']).attr('id',entry['utc']).dblclick(function(){if ($(this).attr("id") != '*') addEvent($(this).attr("id"))});
					});
				if (item['thu'])
					$.each(item['thu'],function(indexEntry,entry){
						row.find("#thu").text(entry['day']).addClass(entry['class']).attr('id',entry['utc']).dblclick(function(){if ($(this).attr("id") != '*') addEvent($(this).attr("id"))});
					});
				if (item['fri'])
					$.each(item['fri'],function(indexEntry,entry){
						row.find("#fri").text(entry['day']).addClass(entry['class']).attr('id',entry['utc']).dblclick(function(){if ($(this).attr("id") != '*') addEvent($(this).attr("id"))});
					});
				if (item['sat'])
					$.each(item['sat'],function(indexEntry,entry){
						row.find("#sat").text(entry['day']).addClass(entry['class']).attr('id',entry['utc']).dblclick(function(){if ($(this).attr("id") != '*') addEvent($(this).attr("id"))});
					});
					row.attr("id","ready");//改变绑定好数据的行的id
					row.appendTo("#datas");//添加到模板的容器中);
					//$("td.mydate_day:contains('" + iToday +"')").removeClass('mydate_day').addClass('mydate_aday');
			});
			$("#template").css("display","none");
		}
	});	
}
function getLastMonth(){
	var iYear = parseInt($('#year').val());
	var iMonth = parseInt($('#month').val());
	iMonth = iMonth - 1;
	if (iMonth == 0) {
		iMonth = 12;
		if (iYear > 2009)
			iYear = iYear - 1;
		else
			iYear = 2009;
	}
	$('#year').attr('value',iYear);
	$('#month').attr('value',iMonth);
	$("tr[id='ready']").remove(); 
	$("#template").css("display","");
	calendar($('#year').val(),$('#month').val());
}
function getNextMonth(){
	var iYear = parseInt($('#year').val());
	var iMonth = parseInt($('#month').val());
	iMonth = iMonth + 1;
	if (iMonth > 12){
		iMonth = 1;
		if (iYear < 2011)
			iYear = iYear + 1;
		else
			iYear = 2011;
	}
	$('#year').attr('value',iYear);
	$('#month').attr('value',iMonth);
	$("tr[id='ready']").remove();
	$("#template").css("display","");
	calendar($('#year').val(),$('#month').val());
}
function addEvent(eventTime){
	$('#event').load('/system/base/event!input.action',{"eventTime":eventTime},function(){
		//验证
		$('#eventForm').validate();
		//绑定保存&取消事件
		var options = {
	                    target: "#returnMessage",
	                    url: "/system/base/event!save.action",
	                    beforeSubmit: showRequest,
	                    success:      showResponse
	    }
		$('#save').click(function(){
	    	$('#eventForm').ajaxSubmit(options);
	    })
	    $('#cancel').click(function(){
	    	$.unblockUI();
	    })
	    function showRequest(formData, jqForm, options) {/*$('#projectAttchment').empty()*/;return true;}
	    function showResponse(responseText, statusText)  {$.unblockUI();}
		$.blockUI( {message:$('#event')}, {width:'1080px', height:'580px'});
		return false;		
	});
}
</script>
</head>
<body>
<table border="0" align="center" width="100%" class="navigator-title">
	<tr>
		<td valign="middle" height="29" align="left">&nbsp;<img
			src="/common/image/point.gif" width="5" height="6">&nbsp;我的日历（备忘录）&nbsp;<font color="red"><span id="returnMessage"></span></font></td>
	</tr>
</table>
<div class="spacer-10"></div>
<table border="0" cellpadding="0" cellspacing="1" class="module_content stripe" align="center" width="495">
	<tr bgcolor="#EBF1FD">
		<td align="center" class="mydate_title">
			<a href="#" class="calendar_next" onclick="getLastMonth();">&laquo;</a>&nbsp;
			<b><s:select id="year" name="year" value="%{year}" list="{2009,2010,2011}" /></b>&nbsp;
			<b><s:select id="month" name="month" value="%{month}" list="{1,2,3,4,5,6,7,8,9,10,11,12}" required="true" /></b>&nbsp;
			<a href="#" class="calendar_next" onclick="getNextMonth();">&raquo;</a>
		</td>
	</tr>
	<tr>
		<td id="mydate" >
		<table border="0" cellpadding="0" cellspacing="1" id="datas" align="center" width="98%" class="stripe">
			<tr bgcolor="#EBF1FD">
				<th><span class="mydate_calendar">日</span></th>
				<th><span class="mydate_calendar">一</span></th>
				<th><span class="mydate_calendar">二</span></th>
				<th><span class="mydate_calendar">三</span></th>
				<th><span class="mydate_calendar">四</span></th>
				<th><span class="mydate_calendar">五</span></th>
				<th><span class="mydate_calendar">六</span></th>
			</tr>
			<tr><td><div class="spacer-2"></div></td></tr>
			<tr id="template">
				<td id="sun"></td>
				<td id="mon"></td>
				<td id="tue"></td>
				<td id="wed"></td>
				<td id="thu"></td>
				<td id="fri"></td>
				<td id="sat"></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<div id="event" style="display: none; cursor: default;">
</div>
</body>
</html>