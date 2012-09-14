<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf8">
		<title>Discussion</title>
		<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet"/>
		<link type="text/css" href="css/base.css" rel="stylesheet"/>
		<link type="text/css" href="css/login.css" rel="stylesheet"/>
	</head>
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container-fluid">
					<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</a>
					<a class="brand" href="#">Web诉讼系统</a>
					<div class="nav-collapse collapse">
						<ul class="nav">
							<li class="active"><a href="index.jsp">首页</a></li>
							<li><a href="login.jsp">登录</a></li>
							<li><a href="register.jsp">注册</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="loginform">
				<form class="form-horizontal" method="POST" action="user">
					<input type="hidden" name="userFunFlag" value="1"/>
					<%String msg=(String)request.getAttribute("loginSta");
						if (msg != null && msg.equals("false")) {
					%>
					<div class="alert alert-error">
						<p style="text-align:center;"><strong>用户名或密码错误</strong></p>
					</div>
					<%
						}
					%>
					<div class="control-group">
						<label class="control-label">用户名:</label>
						<div class="controls">
							<input type="text" name="username"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">密码:</label>
						<div class="controls">
							<input type="password" name="password"/>
						</div>
					</div>
					<div class="form-action">
						<input type="submit" class="btn btn-primary pull-right" onclick="return check_login();" value="登录"/>
						<input type="reset" class="btn" value="重置"/>
					</div>
				</form>
			</div>
			<hr>
			<footer>
				&copy;Web诉讼系统 2012
			</footer>
		</div>
		<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</body>
</html>