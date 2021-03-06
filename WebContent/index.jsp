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
				<table id="disTable" class="table table-hover">
					<thead>
						<tr>
							<th>编号</th>
							<th>主题</th>
							<th><a href="#" onclick="sortBy(0);">发布时间</a></th>
							<th><a href="#" onclick="sortBy(1);">访问次数</a></th>
							<% 
								if (authed != null && authed.equals("true")) {
									if (Integer.parseInt((String)session.getAttribute("userRank")) == 1) {
							%>
							<th>操作</th>
							<%
									}
									
								}
							%>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div class="row">
				<div id="page" class="pagination pull-right">
				</div>
			</div>
			<hr>
			<footer>
				<p>&copy;Web诉讼系统 2012</p>
			</footer>
		</div>
		<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
		<script type="text/javascript" src="js/jquery.json-2.3.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/post.js"></script>
		<script type="text/javascript" src="js/page.js"></script>
		<script type="text/javascript">
			var nowPage;
			var totalPages;
			var orderBy = ["disobj_reltime", "disobj_looknum"];
			var orderByIndex = -1;
			var isAsc = 1;
			var range = [0, 0];
			var cursor;
			var id = -1;
			var url = "disobj";
			var funcFlag = "3";
			$(document).ready(function(){
				$.get(url,
					{
						"disFunFlag": funcFlag,
						"nowPage": "1",
						"orderBy": orderBy[0],
						"isAsc": isAsc
					},
					function(data) {
						data = $.evalJSON(data);
						nowPage = data.nowPage;
						totalPages = data.totalPages;
						<%
							if (authed != null && authed.equals("true")) {
						%>
						id = <%=Integer.parseInt((String)session.getAttribute("userRank"))%>;
						<%
							}
						%>
						$("#disTable tbody").html(build_dislist(data.disList, id));
						$("#page").html(make_page(cursor, nowPage, totalPages));
					}
				);
			});
		</script>
	</body>
</html>
