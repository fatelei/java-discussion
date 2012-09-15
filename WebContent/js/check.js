/*
 * 用户登录
 */
function check_login() {
	var username = document.getElementsByName("username");
	if (username[0].value.length == 0) {
		alert('用户名不能为空!');
		return false;
	}
	var password = document.getElementsByName("password");
	if (password[0].value.length == 0) {
		alert('密码不能为空!');
		return false;
	}
	return true;
}

/*
 * 用户注册
 */
function check_register() {
	var username = document.getElementsByName("username");
	if (username[0].value.length == 0) {
		alert('用户名不能为空!');
		return false;
	}
	var password = document.getElementsByName("password");
	if (password[0].value.length == 0) {
		alert('密码不能为空!');
		return false;
	}
	var repassword = document.getElementsByName("repassword");
	if (repassword[0].value.length == 0 || password[0].value != repassword[0].value) {
		alert('密码不一致!');
		return false;
	}
	return true;
}

/*
 * 删除用户
 */
function check_delete() {
    var msg = "是否删除该用户?";
    if (confirm(msg)) {
        return true;
    }
    return false;
}

/*
 * 用户注销
 */
function logout() {
	$.post('user', 
			{
				"userFunFlag": "5"
			}, function(data) {
				window.location.href = "index.jsp";
			}
	);
}

/*
 * 编辑用户
 */
function edit_user() {
	var username = document.getElementsByName("username");
	var password = document.getElementsByName("password");
	var rank = document.getElementsByName("rank");
	username[0].disabled = false;
	password[0].disabled = false;
	rank[0].disabled = false;
}
