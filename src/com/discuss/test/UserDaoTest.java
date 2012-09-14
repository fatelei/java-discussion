package com.discuss.test;

import com.discuss.bean.UserBean;
import com.discuss.dao.UserDao;

public class UserDaoTest {

	public static void main(String[] args) {
		UserDao userD = new UserDao();
		
		//add user
		UserBean user = new UserBean();
		user.setUserName("fate");
		user.setUserPassword("fate");
		user.setUserRank(1);
		
		System.out.println(userD.addUsr(user));
		
	}

}
