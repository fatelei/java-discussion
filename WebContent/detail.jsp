<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.discuss.bean.DisObjBean"%>
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
			<%
				String msg = (String)request.getSession().getAttribute("post");
				if (msg != null && msg.equals("true")) {
			%>
			<div class="row">
				<div class="alert alert-success">
					<p style="text-align:center;"><strong>发表诉讼成功!</strong></p>
				</div>
			</div>
			<%}request.getSession().removeAttribute("post");%>
			<div class="row">
				<span><input type="button" class="btn" onclick="show_post_modal();" value="发表诉讼"/></span>
				<%
					if (authed != null && authed.equals("true")) {
						int rank = (Integer)session.getAttribute("userRank");
						if (rank == 2) {
				%>
				<span><input type="button" class="btn" onclick="" value="发表回复"/></span>
				<%
						}
					}
				%>
				<span><input type="button" class="btn" onclick="show_reply_modal();" value="附议"/></span>
			</div>
			<div class="sep5"></div>
			<div class="row">
				<div class="box" id="topic">
				</div>
				<div class="sep20"></div>
				<div id="reply">
				</div>
				<div class="sep20"></div>
				<div id="additionComments">
				</div>
			</div>
		</div>
		<div id="post-modal" class="modal fade hide">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h3>发表诉讼</h3>
			</div>
			<form method="POST" action="disobj" class="form-horizontal">
				<input type="hidden" name="disFunFlag" value="1"/>
				<div class="modal-body">
					<div class="control-group">
						<label class="control-label">标题:</label>
						<div class="controls">
							<input type="text" name="title" class="input-xlarge"/>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">诉讼内容:</label>
						<div class="controls">
							<textarea name="content" style="width:300px;" rows="5"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<input type="reset" class="btn" value="重置"/>
					<input type="submit" class="btn btn-primary" onclick="return check_post();" value="发表"/>
				</div>
			</form>
		</div>
		<div id="reply-modal" class="modal fade hide">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h3>回复诉讼</h3>
			</div>
			<form method="POST" action="" class="form-horizontal">
				<div class="modal-body">
					<div class="control-group">
						<label class="control-label">回复内容:</label>
						<div class="controls">
							<textarea name="replyContent" style="width:300px;" rows="5"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<input type="reset" class="btn" value="重置"/>
					<input type="submit" class="btn btn-primary" onclick="return check_reply();" value="发表"/>
				</div>
			</form>
		</div>
		<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/jquery.json-2.3.min.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/post.js"></script>
		<script type="text/javascript">
			var postId = <%=request.getParameter("postId")%>;
			$(document).ready(function(){
				$.get("disobj",
					{
						"disFunFlag": "2",
						"postId": postId
					},
					function(data) {
						data = $.evalJSON(data);
						$("#topic").html(build_topic(data));
					}
				);
				$.get("rplobj",
					{
						"rplFunFlag": "1",
						"postId": postId,
						"nowPage": 1
					},
					function(data) {
						data = $.evalJSON(data);
						$("#additionComments").html(data.secList);
					}
				);
			});
		</script>
	</body>
</html>