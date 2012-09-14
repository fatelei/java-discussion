package com.discuss.bean;


//user bean
public class UserBean {
	public static final String UserTable = "user";
	
	public static final String UserID = "user_id";
	private  int  userId = 0;
	
	//user's	name
	public static final String UserName = "user_name";
	private  String  userName = null;
	
	public static final String UserPassword = "user_password";
	private  String  userPassword = null;
	
	//the rank of user,	1 administrator	; 2  
	public static final String UserRank = "user_rank";
	private  int  userRank = 0;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public int getUserRank() {
		return userRank;
	}
	public void setUserRank(int userRank) {
		this.userRank = userRank;
	}
}
