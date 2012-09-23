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
		html += '<div class="reply_content">';
		if (data[i].rplContent.match("quote") != null) {
			html += generate_quote(data[i].rplContent);
		} else {
			html += data[i].rplContent;
		}
		html += '</div></td></tr></tbody></table></div>';
		
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
		html += '<span class="span10"><i class="icon-comment"></i><a href="#" onclick="quote_addition_comments(this);">引用</a></span>';
		html += '<div class="sep5"></div>';
		html += '<div class="reply_content">';
		if (data[i].secContent.match("quote") != null) {
			html += generate_quote(data[i].secContent);
		} else {
			html += data[i].secContent;
		}
		html += '</div></td></tr></tbody></table></div>';
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
					if ("errmsg" in data) {
						alert(data.errmsg);
					} else {
						alert("附议失败!");
					}
					$("#addition-modal").modal("hide");
				} else {
					alert("附议成功!");
					window.location.reload();
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
					if ("errmsg" in data) {
						alert(data.errmsg);
					} else {
						alert("回复失败!");
					}
					$("#reply-modal").modal("hide");
				} else {
					alert("回复成功!");
					window.location.reload();
				}
			}
	);
	return false;
}

/*
 * 引用附议
 */
function quote_addition_comments(ele) {
	var parent = $(ele).parent().parent();
	var author = $(parent).find("strong")[0].innerText;
	var postTime = $(parent).find(".small")[0].innerText;
	var context = $(parent).find(".reply_content")[0].innerText;
	var quote_msg = "";
	quote_msg += '<quote><author>' + author + '</author>';
	quote_msg += '<time>' + postTime + '</time>';
	quote_msg += '<content>' + context + '</content></quote>';
	switch (identify) {
	case 2:
		var rplObj = document.getElementsByName("replyContent");
		rplObj[0].value = quote_msg;
		show_reply_modal();
		break;
	case 3:
		var addCmtObj = document.getElementsByName("additionContent");
		addCmtObj[0].value = quote_msg;
		show_addition_modal();
		break;
	}
	return false;
}

/*
 * 生成引用内容
 */
function generate_quote(data) {
	var html = "";
	var authorPattern = /<author>.*<\/author>/;
	var timePattern = /<time>.*<\/time>/;
	var contentPattern = /<content>.*<\/content>/;
	//console.log(target);
	var author = authorPattern.exec(data)[0];
	author = author.replace(/<[^>]*>/g, "");
	var time = timePattern.exec(data)[0];
	time = time.replace(/<[^>]*>/g, "");
	var content = contentPattern.exec(data)[0];
	content = content.replace(/<[^>]*>/g, "");
	html += '<div class="cell quote">';
	html += '<table cellpadding="0" cellspacing="0" border="0" width="100%">';
	html += '<tbody><tr><td width="10" valign="top"></td>';
	html += '<td width="auto" valign="top" align="left">';
	html += '<div class="sep3"></div>';
	html += '<strong>' + author + '</strong> 回复:<span class="small">' + time + '</span>';
	html += '<div class="sep5"></div>';
	html += '<div class="reply_content">' + content + '</div></td></tr></tbody></table></div>';
	html += data.replace(/<quote>.*<\/quote>/g, "");
	return html;
}