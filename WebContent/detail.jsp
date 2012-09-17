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
				<span><input type="button" class="btn" value="发表诉讼"/></span>
				<span><input type="button" class="btn" value="回复"/></span>
			</div>
			<div class="sep5"></div>
			<div class="row">
				<div class="box">
					<div class="topic-header">
						<h2>Test title</h2>
						<small style="color: #999;">
                			<span></span>fate 发表于 2012-9-17
                		</small>
                		<div style="float:right">
                			<a href="#"><i class="icon-thumbs-up"></i></a>100
							<a href="#"><i class="icon-thumbs-down"></i></a>200
						</div>
					</div>
					<div class="topic-inner">
						<div class="topic-content">
                			<p>fsfdfaffsafsdfsdfsd</p>
            			</div>
					</div>
				</div>
				<div class="sep20"></div>
				<div id="reply">
					<div class="cell">
						<table cepllpadding="0" cellspacing="0" border="0" width="100%">
							<tbody>
								<tr>
									<td width="10" valign="top"></td>
									<td width="auto" valign="top" align="left">
										<div class="fr">
											<span class="badge">1</span>
											<a href="#"><i class="icon-thumbs-up"></i></a>100
											<a href="#"><i class="icon-thumbs-down"></i></a>200
										</div>
										<div class="sep3"></div>
										<strong>test</strong> 回复：<span class="small">2012-9-17</span>
										<div class="sep5"></div>
										<div class="reply_content">
											csafdasdasda
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div id="post-modal" class="modal fade hide">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h3></h3>
			</div>
			<form method="POST" action="">
				<div class="modal-body">
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
				</div>
				<div class="modal-footer">
					<input type="reset" class="btn" value="重置"/>
					<input type="submit" class="btn btn-primary" onclick="return check_post();" value="发表"/>
				</div>
			</form>
		</div>
		<div id="replay-modal" class="modal fade hide">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h3></h3>
			</div>
			<form method="POST" action="">
				<div class="modal-body">
					<div class="control-group">
						<label class="control-label">回复内容:</label>
						<div class="controls">
							<textarea name="content" style="width:500px;" rows="5"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<input type="reset" class="btn" value="重置"/>
					<input type="submit" class="btn btn-primary" onclick="return check_post();" value="发表"/>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/jquery.json-2.3.min.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</body>
</html>