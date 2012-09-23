package com.discuss.bean;

public class SecVoteIpBean {
	public static String TaName = "secvoip";	
	public static String ID = "ip_id";
	private int id;
	
	public static String Addr = "ip_ad";
	private String ipAdd;
	
	public static String SecDis = "ip_sedis";
	private int secDisId;
	private SecDisBean secDisBe;
	
	public static String User = "ip_user";
	private int userId;
	private UserBean userBe;
	
	public SecVoteIpBean(){}
	
	public SecVoteIpBean(String addr, int user, int secDisId){
		this.ipAdd = addr;
		this.userId = user;
		this.secDisId = secDisId;
	}
	
	public SecVoteIpBean(int id,String addr, int user, int secDisId){
		this.id = id;
		this.ipAdd = addr;
		this.userId = user;
		this.secDisId = secDisId;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIpAdd() {
		return ipAdd;
	}
	public void setIpAdd(String ipAdd) {
		this.ipAdd = ipAdd;
	}
	public int getSecDisId() {
		return secDisId;
	}
	public void setSecDisId(int secDisId) {
		this.secDisId = secDisId;
	}
	public SecDisBean getSecDisBe() {
		return secDisBe;
	}
	public void setSecDisBe(SecDisBean secDisBe) {
		this.secDisBe = secDisBe;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public UserBean getUserBe() {
		return userBe;
	}
	public void setUserBe(UserBean userBe) {
		this.userBe = userBe;
	}
}
