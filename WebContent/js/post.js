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

function show_addition_modal() {
	$("#addition-modal").modal("show");
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
	var html = "";
	console.log(data.length);
	for (var i = 0; i < data.length; i++) {
		html += '<div class="cell replycolor">';
		html += '<table cellpadding="0" cellspacing="0" border="0" width="100%">';
		html += '<tbody><tr><td width="10" valign="top"></td>';
		html += '<td width="auto" valign="top" align="left">';
		html += '<div class="sep3"></div>';
		html += '<strong>' + data[i].rplUser + '</strong> 回复:<span class="small">' + data[i].rplTime + '</span>';
		html += '<div class="sep5"></div>';
		html += '<div class="reply_content">' + data[i].rplContent + '</div></td></tr></tbody></table></div>';
	}
	if (html.length == 0) {
		html += '<div class="cell replycolor">';
		html += '<div class="box transparent">';
		html += '<div class="topic-inner" style="text-align: center;"><span style="color: rgbd(0, 0, 0, 0.25);">没有回复</span></div>';
		html += '</div></div>';
	}
	return html;
}

/*
 * 获得附议列表
 */
function build_additional_comments(data) {
	var html = "";
	for (var i = 0; i < data.length; i++) {
		html += '<div class="cell additioncolor">';
		html += '<table cellpadding="0" cellspacing="0" border="0" width="100%">';
		html += '<tbody><tr><td width="10" valign="top"></td>';
		html += '<td width="auto" valign="top" align="left">';
		html += '<div class="fr">';
		html += '<a href="#" onclick=\'update_so(' + data[i].secId + ', 0);\'><i class="icon-thumbs-up"></i></a><i id="apr' + data[i].secId + '">' + data[i].secAprNum + '</i>';
		html += '<a href="#" onclick=\'update_so(' + data[i].secId + ', 1);\'><i class="icon-thumbs-down"></i></a><i id="opt' + data[i].secId + '">' + data[i].secOppNum + '</i></div>';
		html += '<div class="sep3"></div>';
		html += '<strong>' + data[i].secUser + '</strong> 回复:<span class="small">' + data[i].secTime + '</span>';
		html += '<div class="sep5"></div>';
		html += '<div class="reply_content">' + data[i].secContent + '</div></td></tr></tbody></table></div>';
	}
	if (html.length == 0) {
		html += '<div class="cell additioncolor">';
		html += '<div class="box transparent">';
		html += '<div class="topic-inner" style="text-align: center;"><span style="color: rgbd(0, 0, 0, 0.25);">没有附议</span></div>';
		html += '</div></div>';
	}
	return html;
}

/*
 *
 */
function update_so(secId, flag) {
	switch (flag) {
	case 0:
		$.post('rplobj', {
			   'rplFunFlag': "6",
			   'secId': secId
			}, function (data) {
				data = $.evalJSON(data);
				if (data.upSta == "true") {
					alert("支持成功!");
					console.log(data);
					$('#apr' + secId)[0].innerText = data.upNum;
				} else {
					alert("支持失败!");
				}
			}
		);
		break;
	case 1:
		$.post('rplobj', {
			   'rplFunFlag': "7",
			   'secId': secId
			}, function (data) {
				data = $.evalJSON(data);
				if (data.upSta == "true") {
					alert("反对成功!");
					$('#opt' + secId)[0].innerText = data.opNum;
				} else {
					alert("反对失败!");
				}
			}
		);
		break;
	}
	return false;
}

/*
 * 发表附议
 */
function post_addition() {
	var addCnt = document.getElementsByName("additionContent");
	console.log("post addition");
	$.post("rplobj",
			{
				"rplFunFlag": "3",
				"postId": postId,
				"additionContent": addCnt[0].value
			},
			function(data) {
				data = $.evalJSON(data);
				//console.log(data);
				if (data.adcSta == 'false') {
					alert("附议失败!");
				} else {
					alert("附议成功!");
					$("#addition-modal").modal("hide");
					direct_to_page(nowPage);
				}
			}
	);
	return false;
}

/*
 * 发表回复
 */
function post_reply() {
	var rplCnt = document.getElementsByName("replyContent");
	$.post('rplobj',
			{
				"rplFunFlag": "2",
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
					direct_to_page(nowPage);
				}
			}
	);
	return false;
}