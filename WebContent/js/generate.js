/*
 * 生成用户列表
 */
function generate_userlist(users) {
	var html = "";
	for (var i = 0; i < users.length; i++) {
		html += '<tr><td>';
		html += '<a href="userinfo.jsp?userId=' + users[i].id + '">' + users[i].id + '</a></td>';
		html += '<td><a href="userinfo.jsp?userId=' + users[i].id + '">' + users[i].name + '</a></td>';
		html += '<td>';
		html += '<input type="button" class="btn btn-success" onclick=\'return check_delete("' + users[i].id + '");\' value="删除"/></td>';
		html += '</tr>';
	}
	return html;
}