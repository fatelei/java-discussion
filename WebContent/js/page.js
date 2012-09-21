/*
 * 分页
 */
function make_page(cursor, page, totalpages) {
	var html = "";
	var pageAry = [];
	for (var i = 1; i <= totalPages; i++) {
		pageAry.push(i);
	}
	html += '<ul>';
	cursor = page;
	if (parseInt(page) == 1) {
		html += '<li class="disabled"><a href="#">«</a></li>';
	} else {
		html += '<li><a href="#" onclick=\'forward(' + cursor + ');\'>«</a></li>';
	}
	if (!range[0] && !range[1]) {
		if (totalPages <= 5) {
			for (var i = 1; i <= totalPages; i++) {
				if (i == page) {
					html += '<li class="active"><a href="#">' + i + '</a></li>';
				} else {
					html += '<li><a href="#" onclick=\'direct_to_page(' + i + ')\'>' + i + '</a></li>';
				}
				//console.log('dasd');
			}
			update_range(range, 1, totalPages);
		} else {
			if (page <= 5) {
				html += make_page_num(5, page);
				update_range(range, 1, 5);
			} else {
				html += make_page_num(page, page);
				update_range(range, page-4, page);
			}
		}
	} else if (range[0] <= page && page <= range[1]){
		html += make_page_num(range[1], page);
	} else if (range[0] > page) {
		html += make_page_num(page+4, page);
		update_range(range, page, page+4);
	} else if (page > range[1]) {
		html += make_page_num(page, page);
		update_range(range, page-4, page);
	}
	if (page == totalPages) {
		html += '<li class="disabled"><a href="#">»</a></li>';
	} else {
		html += '<li><a href="#" onclick=\'backward(' + cursor + ');\'>»</a></li>';
	}
	html += '</ul>';
	return html;
}

/*
 * 更新分页分区
 */
function update_range(range, start, end) {
	range[0] = start;
	range[1] = end;
}

/*
 * 生成页数
 */
function make_page_num(target, curPage) {
	var html = "";
	var start = 0;
	if (target - 4 <= 0) {
		start = 1;
	} else {
		start = target - 4;
	}
	for (var i = start; i <= target; i++) {
		if (i == curPage) {
			html += '<li class="active"><a href="#" onclick=\'direct_to_page(' + i + ')\'>' + i + '</a></li>';
		} else {
			html += '<li><a href="#" onclick=\'direct_to_page(' + i + ')\'>' + i + '</a></li>';
		}
	}
	return html;
}

/*
 * 跳转到指定页面
 */
function direct_to_page(page) {
	$.get("disobj",
		{
			"disFunFlag": "3",
			"nowPage": page,
			"orderBy": orderByIndex == -1?orderBy[0]:orderBy[orderByIndex],
			"isAsc": isAsc
		},
		function (data) {
			data = $.evalJSON(data);
			nowPage = data.nowPage;
			totalPages = data.totalPages;
			$("#disTable tbody").html(build_dislist(data.disList, id));
			$("#page").html(make_page(cursor, nowPage, totalPages));
		}
	);
	return false;
}

/*
 * 前进一页
 */
function forward(cursor) {
	var toPage = 0;
	if (cursor - 1 <= 0) {
		toPage = 1;
	} else {
		toPage = cursor - 1;
	}
	return direct_to_page(toPage);
}

/*
 * 后退一页
 */
function backward(cursor) {
	var toPage = 0;
	if (cursor + 1 > totalPages) {
		toPage = totalPages;
	} else {
		toPage = cursor + 1;
	}
	return direct_to_page(toPage);
}

/*
 * 讨论列表排序
 */
function sortBy(index) {
	orderByIndex = index;
	direct_to_page(1);
	isAsc^=1;
}