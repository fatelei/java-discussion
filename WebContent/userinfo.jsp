<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String authed = (String)session.getAttribute("loginState");
	String path = request.getRequestURI();
	if (authed == null || authed.equals("false")) {
		response.sendRedirect("login.jsp?path=" + path);
	} else {
		String rank = (String)session.getAttribute("userRank");
		if (Integer.parseInt(rank) != 1) {
			response.sendRedirect("index.jsp");
		}
	}
%>
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
							<li><a href="user_manage.jsp">用户管理</a></li>
							<li><a href="#" onclick="logout();">注销</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<div style="width: 600px; margin: 0 auto 0 auto;">
					<form class="form-horizontal" method="POST" action='user?userid=<%=request.getParameter("userId")%>'>
						<input type="hidden" name="userFunFlag" value="3"/>
						<div class="control-group">
							<label class="control-label">用户名:</label>
							<div class="controls">
								<input type="text" name="username" disabled/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">密码:</label>
							<div class="controls">
								<input type="password" name="password" disabled/>
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">用户身份:</label>
							<div class="controls">
								<select name="userrank" disabled>
									<option value="1">系统管理员</option>
									<option value="2">部门管理员</option>
									<option value="3">市民</option>
								</select>
							</div>
						</div>
						<div class="form-action">
							<input type="button" class="btn" value="编辑" onclick="edit_user();"/>
							<input type="submit" class="btn btn-primary pull-right" value="保存" onclick="return check_modify_user();"/>
						</div>
					</form>
				</div>
			</div>
			<hr>
			<footer>
				&copy;Web诉讼系统 2012
			</footer>
		</div>
		<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
		<script type="text/javascript" src="js/jquery.json-2.3.min.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$.post("user",
					{
						"userFunFlag": "7",
						"userid": <%=request.getParameter("userId")%>
					},
					function(data) {
						data = $.evalJSON(data);
						var nameObj = document.getElementsByName("username");
						var passwordObj = document.getElementsByName("password");
						var rankObj = document.getElementsByName("userrank");
						nameObj[0].value = data.name;
						passwordObj[0].value = data.password;
						rankObj[0].value = data.rank;
					}
				);
			});
		</script>
	</body>
</html>