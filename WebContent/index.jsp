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
										<li><a href="user_manage.jsp">用户管理</a></li>
							<%		}	%>
									<li><a href="post.jsp">发表诉讼</a></li>
									<li><a href="#" onclick="logout();">注销</a></li>
							<%	}	%>
						</ul>
						<%
							if (authed != null && authed.equals("true")) {
						%>
						<div class="btn-group pull-right">
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                            	<i class="icon-user"></i>
                            	<%=(String)request.getSession().getAttribute("username")%>
                        	</a>
						</div>
						<%
							}
						%>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<span><a href="post.jsp" class="btn">发表诉讼</a></span>
			</div>
			<div class="row">
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
							<td><a href="detail.jsp?postId=1">1</a></td>
							<td><a href="detail.jsp?postId=1">1233123</a></td>
							<td>123123</td>
							<td>1312323123</td>
						</tr>
					</tbody>
				</table>
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
