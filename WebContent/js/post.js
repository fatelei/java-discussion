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