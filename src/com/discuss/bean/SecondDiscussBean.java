package com.discuss.bean;

public class SecondDiscussBean {
	public static final String SecDisId = "secdis_id";
	private int secDisId = 0;
	
	public static final String SecDisContent = "secdis_content";
	private String secDisContent = null;
	
	//支持人数
	public static final String SecDisSupNum = "secdis_supnum";
	private int secDisSupNum = 0;
	
	//反对人数
	public static final String SecDisOppNum = "secdis_oppnum";
	private int secDisOppNum = 0;
	
	public static final String SecDisUserId = "secdis_user";
	private int secDisUserId = 0;
	private UserBean secDisUser = null;
	
	public static final String SecDisObjectId = "secdis_object";
	private int secDisObjectId = 0;
	private DiscussObjectBean secDisObject = null;
	
	public static final String SecDisRelTime = "secdis_relTime";
	private String secDisRelTime = null;
	
	public int getSecDisId() {
		return secDisId;
	}
	public void setSecDisId(int secDisId) {
		this.secDisId = secDisId;
	}
	
	public String getSecDisContent() {
		return secDisContent;
	}
	public void setSecDisContent(String secDisContent) {
		this.secDisContent = secDisContent;
	}
	
	public int getSecDisSupNum() {
		return secDisSupNum;
	}
	public void setSecDisSupNum(int secDisSupNum) {
		this.secDisSupNum = secDisSupNum;
	}
	
	public int getSecDisOppNum() {
		return secDisOppNum;
	}
	public void setSecDisOppNum(int secDisOppNum) {
		this.secDisOppNum = secDisOppNum;
	}
	
	public int getSecDisUserId() {
		return secDisUserId;
	}
	public void setSecDisUserId(int secDisUserId) {
		this.secDisUserId = secDisUserId;
	}
	public UserBean getSecDisUser() {
		return secDisUser;
	}
	public void setSecDisUser(UserBean secDisUser) {
		this.secDisUser = secDisUser;
	}
	
	public int getSecDisObjectId() {
		return secDisObjectId;
	}
	public void setSecDisObjectId(int secDisObjectId) {
		this.secDisObjectId = secDisObjectId;
	}
	public DiscussObjectBean getSecDisObject() {
		return secDisObject;
	}
	public void setSecDisObject(DiscussObjectBean secDisObject) {
		this.secDisObject = secDisObject;
	}
	
	public String getSecDisRelTime() {
		return secDisRelTime;
	}
	public void setSecDisRelTime(String secDisRelTime) {
		this.secDisRelTime = secDisRelTime;
	}
}
