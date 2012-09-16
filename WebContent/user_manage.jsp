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
							<li><a href="#">发表诉讼</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="container">
			<div class="row">
				<%
					String modifySta = (String)request.getSession().getAttribute("modifySta");
					if (modifySta != null && modifySta.equals("false")) {
				%>
				<div class="alert alert-error">
					<p style="text-align:center"><strong>修改用户信息失败!</strong></p>
				</div>
				<%
					} else if (modifySta != null && modifySta.equals("true")){
				%>
				<div class="alert alert-success">
					<p style="text-align:center"><strong>修改用户信息成功!</strong></p>
				</div>
				<%
					}
					request.getSession().removeAttribute("modifySta");
				%>
			    <table id="usertable" class="table table-hover">
			        <thead>
			            <tr>
			                <th>编号</th>
			                <th>用户名</th>
			                <th>操作</th>
			            </tr>
			        </thead>
			        <tbody>
			        </tbody>
			    </table>
			</div>
		</div>
		<hr>
		<footer>
			&copy;Web诉讼系统 2012
		</footer>
		<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
		<script type="text/javascript" src="js/jquery.json-2.3.min.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/generate.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$.post("user",
					{"userFunFlag": "6",
					 "nowPage": "1"},
					function(data) {
						data = $.evalJSON(data);
						$("#usertable tbody").html(generate_userlist(data.users));
					 }
				);
			});
		</script>
	</body>
</html>
