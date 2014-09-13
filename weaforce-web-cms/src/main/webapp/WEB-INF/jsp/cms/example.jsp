<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	(function() {
		var bar = $('.bar');
		var percent = $('.percent');
		var status = $('#status');
		$('form').ajaxForm({
			beforeSend : function() {
				status.empty();
				var percentVal = '0%';
				bar.width(percentVal)
				percent.html(percentVal);
			},
			uploadProgress : function(event, position, total, percentComplete) {
				var percentVal = percentComplete + '%';
				bar.width(percentVal)
				percent.html(percentVal);
			},
			success : function() {
				var percentVal = '100%';
				bar.width(percentVal)
				percent.html(percentVal);
			},
			complete : function(xhr) {
				status.html(xhr.responseText);
			}
		});
	})();
</script>
</head>
<body>
	<div class="progress">
		<div class="bar" style="width: 100%;"></div>
		<div class="percent">100%</div>
	</div>
	<div id="status">
		<br>uploaded: XiongTianXia.pdf (52259 bytes)
	</div>
</body>
</html>