<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/common/css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" src="/common/jquery/jquery-1.4.2.min.js"></script>
<!-- ui datepicker -->
<link type="text/css" href="/common/jquery/themes/1.8.1/base/jquery.ui.all.css" rel="stylesheet" />
<script type="text/javascript" src="/common/jquery/ui/1.8.1/jquery.ui.core.js"></script>
<script type="text/javascript" src="/common/jquery/ui/1.8.1/jquery.ui.widget.js"></script>
<script type="text/javascript" src="/common/jquery/ui/1.8.1/jquery.ui.datepicker.js"></script>
<!-- ui datepicker -->

<title>Date</title>
<script type="text/javascript">
$(function(){
	$("#datepicker").datepicker({dateFormat: $.datepicker.W3C,changeMonth: true,changeYear: true,yearRange: 'c-20:c+20'});
	$("#datepicker-simple").datepicker({dateFormat: $.datepicker.W3C}).attr("readonly", "readonly");
	$('#table-stripe tr:odd').addClass('odd');
	$('#table-stripe tr:even').addClass('even');
		
	$("#table-stripe tr").mouseover(function(){  
        //如果鼠标移到class为stripe的表格的tr上时，执行函数
		$(this).addClass("over");}).mouseout(function(){ 
        //给这行添加class值为over，并且当鼠标一出该行时执行函数
        $(this).removeClass("over");
	})  //移除该行的class
})
</script>
</head>
<body>
<input type="text" id="datepicker" >
<input type="text" id="datepicker-simple" >
<table id="table-stripe" border="0" cellpadding="0" cellspacing="1" class="stripe" align="center" width="98%">
	<tr>
		<th>A</th>
		<th>B</th>
		<th>C</th>
		<th>D</th>
	</tr>
	<tr>
		<td>a</td>
		<td>b</td>
		<td>c</td>
		<td>d</td>
	</tr>
	<tr>
		<td>a</td>
		<td>b</td>
		<td>c</td>
		<td>d</td>
	</tr>
	<tr>
		<td>a</td>
		<td>b</td>
		<td>c</td>
		<td>d</td>
	</tr>
	<tr>
		<td>a</td>
		<td>b</td>
		<td>c</td>
		<td>d</td>
	</tr>
</table>
</body>
</html>