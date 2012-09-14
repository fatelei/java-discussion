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
	if (repassword[0].value.length == 0 || password != repassword) {
		alert('密码不一致!');
		return false;
	}
	return true;
}