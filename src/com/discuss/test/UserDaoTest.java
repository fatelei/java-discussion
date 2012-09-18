package com.discuss.test;

import com.discuss.bean.UserBean;
import com.discuss.dao.UserDao;

public class UserDaoTest {

	public static void main(String[] args) {
		UserDao userD = new UserDao();
		
		UserBean user = new UserBean();
		user.setUserName("fate");
		user.setUserPassword("fate");
		user.setUserRank(1);
		user.setUserId(11);
		
		//login user test
//		System.out.println(userD.loginOk("hai", "hai"));
		
//		System.out.println(new SqlControl().count("select count(*) from User;"));
		
		
		//add user	test

//		
//		System.out.println(userD.addUsr(user));
		
		//del user test
//		System.out.println("delete user \t" + userD.delUser(5));
		
		//modify user test
//		userD.modifyUser(user);
		
		//query	test
//		System.out.println(userD.queryUser(1, 10).size());
		
		//count user test
//		System.out.println(userD.countUser());
		
		//query user	json
//		ArrayList<UserBean> users = userD.queryUser(1, SysConfBean.UserListPageNum);
		
		//bulid	json oject
//		JSONObject json = new JSONObject();   
//		JSONArray useArray = new JSONArray();   
//        JSONObject oneTemp = null;   
//        for(UserBean oneUser : users){
//        	oneTemp = new JSONObject();   
//        	oneTemp.put("id", oneUser.getUserId());   
//        	oneTemp.put("name", oneUser.getUserName());   
//        	useArray.add(oneTemp); 
//        }     
//        json.put("totalPage", 1);   
//        json.put("users", useArray); 
//
//        System.out.println(json);
//        System.out.println(json.toString());
        
        //find user by name
        System.out.println(userD.findUserByName("hai"));
	}

}
