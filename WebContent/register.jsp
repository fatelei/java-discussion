<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
							<!--If not login-->
							<li><a href="login.jsp">登录</a></li>
							<li><a href="register.jsp">注册</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="loginform">
				<form class="form-horizontal" method="POST" action="">
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
					<div class="control-group">
						<label class="control-label">密码确认:</label>
						<div class="controls">
							<input type="password" name="repassword"/>
						</div>
					</div>
					<div class="form-action">
						<input type="submit" class="btn btn-primary pull-right" value="注册"/>
						<input type="reset" class="btn btn-prmary" value="重置"/>
					</div>
				</form>
			</div>
			<hr>
			<footer>
				@copy;Discussion 2012
			</footer>
		</div>
		<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
	</body>
</html>