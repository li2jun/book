<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>尚硅谷会员注册页面</title>
		<%-- 静态包含 base标签、css样式、jQuery文件 --%>
		<%@include file="/pages/common/head.jsp"%>

		<script type="text/javascript">
			$(function () {
				var patt = /^\w{5,12}$/;
				var patt2 = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
				/*$("#sub_btn").attr("disabled",true);*/
				$("#username").blur(function () {

					$.getJSON("http://localhost:8080/book/userServlet","action=ajaxExistName&username="+this.value,function (data) {
						if(data.existUsernameMap){
							$(".errorMsg").html("该用户名已经存在");
						}else{
							$(".errorMsg").html("");
						}
					})

				});
				$("#password").change(function () {
					if(!patt.test($("#password").val())){
						$(".errorMsg").html("密码不合法");
					}else{
						$(".errorMsg").html("")
					}
				});
				$("#repwd").change(function () {
					if($("#repwd").val() != ($("#password").val())){
						$(".errorMsg").html("两次密码不相同");
					}else{
						$(".errorMsg").html("");
					}
				});
				$("#email").change(function () {
					if(!patt2.test($("#email").val())){
						$(".errorMsg").html("邮箱格式不合法");
					}else{
						$(".errorMsg").html("");
						/*$("#sub_btn").attr("disabled",false);*/
					}
				});
			});
		</script>
	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}

	</style>
	</head>
	<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>

			<div class="login_banner">

				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>

				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">${(empty requestScope.msg)?"":requestScope.msg}</span>
							</div>
							<div class="form">
								<form action="userServlet?action=regist" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1"  id="username"
											value="${(empty requestScope.username)?"":requestScope.username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email"
											value="${(empty requestScope.email)?"":requestScope.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" name="code" style="width: 150px;" id="code" >
									<img alt="" src="kaptchaServlet" style="float: right; margin-right: 40px" width="90" height="40">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
								</form>
							</div>

						</div>
					</div>
				</div>
			</div>
	<%@include file="/pages/common/footer.jsp"%>
	</body>
</html>