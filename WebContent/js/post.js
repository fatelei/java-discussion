/*
 * 生成诉讼列表
 */
function build_dislist(data, id) {
	var html = "";
	for (var i = 0; i < data.length; i++) {
		html += "<tr>";
		html += '<td><a href="detail.jsp?postId=' + data[i].postId + '">' + data[i].postId + '</a></td>';
		html += '<td><a href="detail.jsp?postId=' + data[i].postId + '">' + data[i].title + '</a></td>';
		html += '<td>' + data[i].postDate + '</td>';
		html += '<td>' + data[i].viewNum + '</td>';
		if (id == 1) {
			html += '<td><a href="#" onclick=\'return check_delete_dis("' + data[i].postId + '");\' class="btn btn-success">删除</a></td>';
		}
		html += '</tr>';
	}
	return html;
}

function show_post_modal() {
	$("#post-modal").modal("show");
}

function show_reply_modal() {
	$("#reply-modal").modal("show");
}

/*
 * 获得主题内容
 */
function build_topic(data) {
	var html = "";
	html += '<div class="topic-header"><h2>' + data.title + '</h2>';
	html += '<small type="color: #999"><span></span>' + data.author + '发表于' + data.time + '</small></div>';
	html += '<div class="topic-inner"><div class="topic-content"><p>' + data.content + '</p></div></div>';
	return html;
}

/*
 * 获得回复内容
 */
function build_reply(data) {
	
}

/*
 * 获得附议列表
 */
function build_additional_comments(data) {
	var html = "";
	for (var i = 0; i < data.length; i++) {
		console.log(data[i].secUser);
		html += '<div class="cell">';
		html += '<table cellpadding="0" cellspacing="0" border="0" width="100%">';
		html += '<tbody><tr><td width="10" valign="top"></td>';
		html += '<td width="auto" valign="top" align="left">';
		html += '<div class="fr"><span class="badge">' + i + '</span>';
		html += '<a href="#"><i class="icon-thumbs-up"></i></a>' + data[i].secAprNum;
		html += '<a href="#"><i class="icon-thumbs-down"></i></a>' + data[i].secOppNum + '</div>';
		html += '<div class="sep3"></div>';
		html += '<strong>' + data[i].secUser + '</strong> 回复:<span class="small">' + data[i].secTime + '</span>';
		html += '<div class="sep5"></div>';
		html += '<div class="reply_content">' + data[i].secContent + '</div></td></tr></tbody></table></div>';
	}
	console.log(html);
	return html;
}

/*
 *
 */
function get_discuss(page, orderBy, isAsc) {
	
}

/*
 * 发表回复
 */
function post_reply() {
	var rplCnt = document.getElementsByName("replyContent");
	$.post("rplobj",
			{
				"rplFunFlag": "3",
				"postId": postId,
				"replyContent": rplCnt[0].value
			},
			function(data) {
				data = $.evalJSON(data);
				if (data.rplSta == 'false') {
					alert("回复失败!");
				} else {
					alert("回复成功!");
					$("#reply-modal").modal("hide");
				}
			}
	);
	return false;
}