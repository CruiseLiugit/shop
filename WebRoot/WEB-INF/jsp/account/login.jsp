<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<!--
		Charisma v1.0.0

		Copyright 2012 Muhammad Usman
		Licensed under the Apache License v2.0
		http://www.apache.org/licenses/LICENSE-2.0

		http://usman.it
		http://twitter.com/halalit_usman
	-->
	<meta charset="UTF-8">
	<title>留夫鸭核心管理系统</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="留夫鸭,订餐系统,CRM,会员管理,积分管理系统.">
	<meta name="author" content="刘立立,836131325@qq.com">

	<!-- The styles -->
	<!-- The styles -->
	<link id="bs-css" href="${base}/boot2/css/bootstrap-cerulean.css" rel="stylesheet">
	<link href="${base}/boot2/css/charisma-app.css" rel="stylesheet">
	<style type="text/css">
	  body {
		padding-bottom: 40px;
	  }
	  .sidebar-nav {
		padding: 9px 0;
	  }
	</style>
	
	<!-- messenger css... -->
	<link href="${base}/build/css/messenger.css" rel="stylesheet" type="text/css" >
	<link href="${base}/build/css/messenger-spinner.css" rel="stylesheet" type="text/css">
	<link href="${base}/build/css/messenger-theme-air.css" rel="stylesheet" type="text/css">

	<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
	<!--[if lt IE 9]>
	  <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->

	<!-- The fav icon -->
	<link rel="shortcut icon" href="${base}/img/favicon.ico">
		
</head>

<body>
		<div class="container-fluid">
		<div class="row-fluid">
		
			<div class="row-fluid">
				<div class="span12 center login-header">
					<h2>欢迎光临留夫鸭核心后台系统</h2>
				</div><!--/span-->
			</div><!--/row-->
			
			<div class="row-fluid">
				<div class="well span5 center login-box">
					<div class="alert alert-info">
						<c:choose>
								<c:when test="${error eq null}">
									请输入管理员用户名和密码进行登录.
								</c:when>
								<c:otherwise>
									${error}
								</c:otherwise>
							</c:choose>
					</div>
					<form class="form-horizontal" action="${base}/login" method="post">
						<fieldset>
							<div class="input-prepend" title="Username" data-rel="tooltip">
								<span class="add-on"><i class="icon-user"></i></span>
								<input autofocus class="input-large span10" name="loginName" id="login_name"  type="text" value="admin" />
							</div>
							<div class="clearfix"></div>

							<div class="input-prepend" title="Password" data-rel="tooltip">
								<span class="add-on"><i class="icon-lock"></i></span>
								<input class="input-large span10" name="logPwd" id="login_password" type="password" value="admin" />
							</div>
							<div class="clearfix"></div>
							<!--  
							<div class="input-prepend">
							<label class="remember" for="remember"><input type="checkbox" id="remember" />Remember me</label>
							</div>
							<div class="clearfix"></div>
							-->
							
							<p class="center span5">
							<button type="submit" id="login" class="btn btn-primary">登录系统</button>
							</p>
						</fieldset>
					</form>
				</div><!--/span-->
			</div><!--/row-->
				</div><!--/fluid-row-->
		
	</div><!--/.fluid-container-->

	<!-- external javascript
	================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->

	<!-- jQuery -->
	<script type="text/javascript" src="${base}/js/jquery.js"></script>
	
<!-- Message js lib -->	
<script type="text/javascript" src="${base}/build/js/messenger.js"></script>


<script type="text/javascript">
	$(document)
			.ready(
					function() {
						//选择好弹出框的样式
						$._messengerDefaults = {
							extraClasses : 'messenger-fixed messenger-on-bottom messenger-on-right',
							theme:'air'
						};

						//$.globalMessenger().post("请输入用户名和密码!");
						$.globalMessenger().post({
							message : '请输入用户名和密码!',
							type : 'error',
							hideAfter : 3,
							showCloseButton : true
						});

						$("#login").click(function() {
							var name = $("#login_name").val();
							var pwd = $("#login_password").val();
							if (name == "") {
								$.globalMessenger().post({
									message : "用户名不能为空!",
									hideAfter : 3,
									type : 'error',
								});
								$("#loginName").focus();
								return false;
							}
							if (pwd == "") {
								$.globalMessenger().post({
									message : "密码不能为空!",
									hideAfter : 3,
									type : 'error',
								});
								$("#login_password").focus();
								return false;
							}
							return true;
						});
					});
</script>
		
		
		
</body>
</html>

