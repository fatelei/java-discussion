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
			    <table id="usertable" class="table table-hover">
			        <thead>
			            <tr>
			                <th>编号</th>
			                <th>用户名</th>
			                <th>操作</th>
			            </tr>
			        </thead>
			        <tbody>
			            <tr>
			                <td><a href="#">1</a></td>
			                <td><a href="#">test</a></td>
			                <td>
			                    <form method="POST" action="">
			                        <input type="hidden" name="userFunFlag" value="4"/>
			                        <input type="submit" class="btn btn-success" onclick="return check_delete();" value="删除"/> 
			                    </form>
			                </td>
			            </tr>
			        </tbody>
			    </table>
			</div>
		</div>
		<hr>
		<footer>
			&copy;Web诉讼系统 2012
		</footer>
		<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</body>
</html>
