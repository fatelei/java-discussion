package com.discuss.bean;

public class DiscussObjectBean {
	public static final String DisObjID = "disobj_id";
	private int disObjID = 0;
	
	public static final String DisObjTopic = "disobj_topic";
	private String disObjTopic = null;
	
	public static final String DisObjContent = "disobj_content";
	private String disObjContent = null;
	
	public static final String DisObjUserID = "disobj_user";
	private int disObjUserID = 0;
	private UserBean DisObjUser = null;
	
	public static final String DisObjRelTime = "disobj_reltime";
	private String disObjRelTime = null;
	
	public static final String DisObjLookNum = "disobj_looknum";
	private int disObjLookNum = 0;
	
	public int getDisObjID() {
		return disObjID;
	}
	public void setDisObjID(int disObjID) {
		this.disObjID = disObjID;
	}
	
	public String getDisObjTopic() {
		return disObjTopic;
	}
	public void setDisObjTopic(String disObjTopic) {
		this.disObjTopic = disObjTopic;
	}
	
	public String getDisObjContent() {
		return disObjContent;
	}
	public void setDisObjContent(String disObjContent) {
		this.disObjContent = disObjContent;
	}
	
	public int getDisObjUserID() {
		return disObjUserID;
	}
	public void setDisObjUserID(int disObjUserID) {
		this.disObjUserID = disObjUserID;
	}
	public UserBean getDisObjUser() {
		return DisObjUser;
	}
	public void setDisObjUser(UserBean disObjUser) {
		DisObjUser = disObjUser;
	}
	
	public String getDisObjRelTime() {
		return disObjRelTime;
	}
	public void setDisObjRelTime(String disObjRelTime) {
		this.disObjRelTime = disObjRelTime;
	}
	
	public int getDisObjLookNum() {
		return disObjLookNum;
	}
	public void setDisObjLookNum(int disObjLookNum) {
		this.disObjLookNum = disObjLookNum;
	}
}
