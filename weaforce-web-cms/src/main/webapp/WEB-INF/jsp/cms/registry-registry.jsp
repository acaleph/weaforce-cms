<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/taglibs.jsp"%>
<html>
<head>
<%@ include file="/common/jsp/meta.jsp"%>
<script type="text/javascript">
$(document).ready(function(){
	$("#userForm").validate({
		rules: {
			userLogin: {
				email:true,
    			required: true, 
    			remote: "/cms/registry!checkUserLogin.action?userLogin=" + $('#userLogin').val()
			},
			userPassword: "required",
	    	passwordConfirm: {
	      		equalTo: "#userPassword"
			}
		},
		messages: {
			userLogin: {
				remote: "用户登录名已经存在！"
			}
		}
	});
});
</script>
<title>用户注册</title>
</head>
<body>
<div id="content">
	<div class="top"></div>
	<div class="body" style="background: rgb(255, 255, 255) none repeat scroll 0% 0%; -moz-background-clip: border; -moz-background-origin: padding; -moz-background-inline-policy: continuous; position: relative;">
		<div style="float: left; width: 500px;">
			<p style="margin: 20px 0pt 5px 10px; color: rgb(255, 0, 0); font-size: 12px; font-weight: bold; line-height: 20px;">立即注册会员，即可下载会员独享的优惠券！</p>
			<form id="userForm" method="post" name="userForm" action="/cms/registry!save.action">
				<div class="reg" style="border: medium none ;">
					<h3>注册</h3>
					<table cellspacing="0" cellpadding="0" border="0">
						<tr><td class="th">邮箱：</td><td><input id="userLogin" type="text" value="" name="userLogin" class="email required"></td></tr>
						<tr><td class="th">密码：</td><td><input id="userPassword" type="password" value="" name="userPassword"></td></tr>
						<tr><td class="th">确认密码：</td><td><input id="passwordConfirm" type="password" value="" name="passwordConfirm"></td></tr>
						<tr>
							<td></td>
							<td>
								<p>《会员注册服务协议条款》</p>
								<textarea style="margin: 5px 0pt; padding: 5px; height: 80px; line-height: 18px;" rows="5" cols="80"><s:property value="agreement" /></textarea>
								<p>
									<input id="check" type="checkbox" style="margin: 0pt 5px 0pt 0pt; width: 14px; height: 14px;" checked="checked" name="check"/>
									<label for="check">我已经看过并同意</label>
								</p>
							</td>
						</tr>
						<tr><td></td><td><a class="btn" href="javascript:;">注册</a></td></tr>
					</table>
					<a style="margin: 10px 0pt 0pt; position: absolute; top: 48px; left: 110px; color: rgb(255, 0, 0); font-size: 12px; line-height: 30px;" href="/jcnLogin.htm?method=login&gourl=">如果您已经是会员，请登录&gt;&gt;</a>
				</div>
			</form>
		</div>
		<div style="margin: 50px 10px 0pt 0pt; float: right; line-height: 18px;">
			<h3 style="margin: 0pt 0pt 5px; font-size: 14px;">会员享有更多实惠：</h3>
			<p>收藏商家、小贴士、打折信息，便于记录和管理；</p>
			<p>手机免费下载打折、返现优惠券，享受真正的实惠。</p>
		</div>
		<div id="footer">
			<ul>
				<li><a target="_blank" href="/about">关于adniu.com</a></li>
				<li><a target="_blank" href="/about/faq">常见问题</a></li>
				<li><a target="_blank" href="/about/duty">权责声明</a></li>
				<li><a target="_blank" href="/about/feedback">用户反馈</a></li>
				<li><a target="_blank" href="/about/link">友情链接</a></li>
				<li><a target="_blank" href="/about/contact">联系我们</a></li>
				<li><a target="_blank" href="http://www.weaforce.com">维福斯精准营销平台</a></li>
				<li><a target="_blank" href="http://www.weaforce.com/hr.html">招聘</a></li>
			</ul>
			<p>粤ICP证090172号    www.adniu.com版权所有</p>
		</div>
	</div>
</div>
</body>
</html>