<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String loginSta = (String)request.getSession().getAttribute("loginState");
	if (loginSta != null && loginSta.equals("false")) {
		response.sendRedirect("login.jsp?path=" + request.getRequestURI());
	}
%>
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
							<% 
								String rank = (String)request.getSession().getAttribute("userRank");
								if (Integer.parseInt(rank) == 1) {
							%>
							<li><a href="user_manage.jsp">用户管理</a></li>
							<%
								}
							%>
							<li><a href="post.jsp">发表诉讼</a></li>
							<li><a href="#" onclick="logout();">注销</a></li>
						</ul>
						<div class="btn-group pull-right">
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                            	<i class="icon-user"></i>
                            	<%=(String)request.getSession().getAttribute("username")%>
                        	</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div style="width:800px; margin:0 auto 0 auto;">
				<form class="form-horizontal" method="POST" action="disobj">
					<input type="hidden" name="disFunFlag" value="1"/>
					<%
						String msg=(String)request.getSession().getAttribute("post");
						if (msg != null && msg.equals("false")) {
					%>
					<div class="alert alert-error">
						<p style="text-align:center;"><strong>发表诉讼失败!</strong></p>
					</div>
					<%
						}
						request.getSession().removeAttribute("post");
					%>
					<div class="control-group">
						<label class="control-label">标题:</label>
						<div class="controls">
							<input type="text" name="title" class="input-xxlarge"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">诉讼内容:</label>
						<div class="controls">
							<textarea name="content" style="width:500px;" rows="5"></textarea>
						</div>
					</div>
					<div class="form-action">
						<input type="submit" class="btn btn-primary pull-right" onclick="return check_post();" value="发表"/>
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