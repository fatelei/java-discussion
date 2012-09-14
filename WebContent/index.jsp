<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf8">
		<title>Discussion</title>
		<link type="text/css" href="css/bootstrap.min.css" rel="stylesheet"/>
		<link type="text/css" href="css/base.css" rel="stylesheet"/>
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
							<li class="active"><a href="#">首页</a></li>
							<!--If not login-->
							<%
							    String authed = (String)session.getAttribute("loginState");
								if (authed == null || authed.equals("false")) {
							%>
									<li><a href="login.jsp">登录</a></li>
									<li><a href="register.jsp">注册</a></li>
							<%	} else {%>
							<% 
									String level = (String)session.getAttribute("userRank");
									if (Integer.parseInt(level) == 1) {
							%>
										<li><a href="#">用户管理</a></li>
							<%		}	%>
									<li><a href="#" onclick="logout();">注销</a></li>
							<%	}	%>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span3">
					<div class="well sidebar-nav">
						<ul>
							<%
								if (authed == null || authed.equals("false")) {
							%>
									<li><a href="register.jsp" class="btn btn-info">现在注册</a></li>
									<li>已注册用户，请<a href="login.jsp">登录</a></li>
							<%	}else{	%>
									<li>你好!<%=session.getAttribute("username")%></li>
							<%  } %>
						</ul>
					</div>
				</div>
				<div class="span9">
					<div class="row-fluid">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>编号</th>
									<th>主题</th>
									<th><a href="#">发布时间</a></th>
									<th><a href="#">访问次数</a></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td>1233123</td>
									<td>123123</td>
									<td>1312323123</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<hr>
			<footer>
				<p>&copy;Web诉讼系统 2012</p>
			</footer>
		</div>
		<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</body>
</html>
