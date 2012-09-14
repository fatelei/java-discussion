package com.discuss.bean;

public class AnswerBean {
	public static final String AnsID = "answer_id";
	private int ansID = 0;
	
	public static final String AnsContent = "answer_content";
	private String ansContent = null;
	
	public static final String AnsUserID = "answer_user";
	private int ansUserID = 0;
	private UserBean ansUser = null;
	
	public static final String AnsObjID = "answer_object";
	private int ansObjID = 0;
	private DiscussObjectBean ansObj = null;
	
	public static final String AnsObjRelTime = "answer_reltime";
	private String ansObjRelTime = null;
	
	public int getAnsID() {
		return ansID;
	}
	public void setAnsID(int ansID) {
		this.ansID = ansID;
	}
	
	public String getAnsContent() {
		return ansContent;
	}
	public void setAnsContent(String ansContent) {
		this.ansContent = ansContent;
	}
	
	public int getAnsUserID() {
		return ansUserID;
	}
	public void setAnsUserID(int ansUserID) {
		this.ansUserID = ansUserID;
	}
	
	public UserBean getAnsUser() {
		return ansUser;
	}
	public void setAnsUser(UserBean ansUser) {
		this.ansUser = ansUser;
	}
	
	public int getAnsObjID() {
		return ansObjID;
	}
	public void setAnsObjID(int ansObjID) {
		this.ansObjID = ansObjID;
	}
	public DiscussObjectBean getAnsObj() {
		return ansObj;
	}
	public void setAnsObj(DiscussObjectBean ansObj) {
		this.ansObj = ansObj;
	}

	
	public String getAnsObjRelTime() {
		return ansObjRelTime;
	}
	public void setAnsObjRelTime(String ansObjRelTime) {
		this.ansObjRelTime = ansObjRelTime;
	}
}