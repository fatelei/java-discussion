package com.discuss.test;

import com.discuss.dao.UserDao;

public class UserDaoTest {

	public static void main(String[] args) {
		UserDao userD = new UserDao();
		//login user test
		System.out.println(userD.loginOk("hai", "hai"));
		
//		System.out.println(new SqlControl().count("select count(*) from User;"));
		
		
		//add user	test
//		UserBean user = new UserBean();
//		user.setUserName("fate");
//		user.setUserPassword("fate");
//		user.setUserRank(1);
//		
//		System.out.println(userD.addUsr(user));
		
		//del user test
//		System.out.println("delete user \t" + userD.delUser(5));
		
		//modify user test
		
		
	}

}
