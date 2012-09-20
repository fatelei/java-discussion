package com.discuss.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.discuss.bean.AnswerBean;
import com.discuss.bean.DisObjBean;
import com.discuss.bean.SecDisBean;
import com.discuss.bean.UserBean;
import com.discuss.util.SqlControl;

public class UserDao {
	private SqlControl sqlCtrl = new SqlControl();
	private ResultSet res = null;

	//login	is	ok	?
	public UserBean loginOk(String userName, String password){
		String findUserSql = "select * from " + UserBean.UserTable + 
										" where " + UserBean.UserName +" = '" + userName + "';";
		ArrayList<UserBean> userList = findUserList(findUserSql);
		if(userList.size() != 0){
			if(userList.get(0).getUserPassword().equals(password)){
				return userList.get(0);
			}else{
				return null;
			}			
		}else{
			return null;
		}
	}
	
	//add new user
	public boolean addUsr(UserBean user){
		//having the same user ?
		String coutUserSql = "select count(*) from " + UserBean.UserTable + " where " 
						+ UserBean.UserName + " = '" + user.getUserName() + "';";
		if(sqlCtrl.getOneInt(coutUserSql) > 0){
			//have
			return false;
		}else{
			//no	add
			String addUserSql = "insert into  " + UserBean.UserTable + " (" + UserBean.UserName +", " + UserBean.UserPassword + ", " + UserBean.UserRank + ") " +
					"values ('" + user.getUserName()+"', '" + user.getUserPassword() +"', '" + String.valueOf(user.getUserRank()) + "')";
			System.out.println(addUserSql);
			if(sqlCtrl.update(addUserSql)== -1){
				return false;
			}
		}
		return true;
	}
	
	//delete user by user's id
	public	boolean delUser(int userId ){
		//delete the user's answers
		String delAnsSql = "delete from " + AnswerBean.AnsTable + " where " 
				+ AnswerBean.AnsUserID + " = '" + userId + "';";
		System.out.println(delAnsSql);
		if(sqlCtrl.update(delAnsSql) == -1){
			return false;
		}
		
		//delete the user's secondDiscuss
		String delSecDisSql = "delete from " + SecDisBean.SecDisTable + " where " 
				+ SecDisBean.SecDisUserId + " = '" + userId + "';";
		System.out.println(delSecDisSql);
		if(sqlCtrl.update(delSecDisSql) == -1){
			return false;
		}
		
		//delete the user's Object
		String delObjSql = "delete from " + SecDisBean.SecDisTable + " where " 
				+ DisObjBean.DisObjUserID + " = '" + userId + "';";
		System.out.println(delObjSql);
		if(sqlCtrl.update(delObjSql) == -1){
			return false;
		}
		
		//delete the user's info		
		String delUserSql = "delete from " + UserBean.UserTable + " where " 
				+ UserBean.UserID + " = '" + userId + "';";
		if(sqlCtrl.update(delUserSql) == -1){
			return false;
		}
		return true;
	} 
	
	//modify user
	public boolean modifyUser(UserBean user){
		String modifSql = "update " + UserBean.UserTable +" set " + UserBean.UserName +" = '" + user.getUserName() +"', "
																  + UserBean.UserPassword + " = '" + user.getUserPassword() + "', "
																  + UserBean.UserRank + " = '" + user.getUserRank() + "' where "
																  + UserBean.UserID + " = '" + user.getUserId() + "';";
		if(sqlCtrl.update(modifSql) == -1){
			return false;
		}		
		return true;
	}
	
	//query	split records	by	pages
	public	ArrayList<UserBean> queryUser(int nowPage, int pageCount){
		String splitSql = null;
		int statrCount = (nowPage - 1) * pageCount;
		//middle page
		splitSql = "select * from " + UserBean.UserTable + " limit " + statrCount + " , " 
																			+ pageCount + " ;";
		return findUserList(splitSql);
	} 
	
	//count all user's num
	public int countUser(){
		String countSql = "select count(*) from " + UserBean.UserTable + " ;";
		return sqlCtrl.getOneInt(countSql);
	}
	
	//find user by name
	public int findUserByName(String name){
		String findSql = "select * from "  + UserBean.UserTable + " where " + UserBean.UserName + " = '" + name + "' ;";
		return findUserList(findSql).get(0).getUserId();
	}
	
	public UserBean findUserById(int userId) {
		String findSql = "select * from "  + UserBean.UserTable + " where " + UserBean.UserID + " = '" + userId + "' ;";
		return findUserList(findSql).get(0);
	}
	//find user's list
	public ArrayList<UserBean> findUserList(String sql){
		ArrayList<UserBean> userList = new ArrayList<UserBean>();
		res = sqlCtrl.queryResultSet(sql);
		try {
			while(res.next()){
				UserBean ub = new UserBean();
				ub.setUserId(res.getInt(UserBean.UserID));
				ub.setUserName(res.getString(UserBean.UserName));
				ub.setUserPassword(res.getString(UserBean.UserPassword));
				ub.setUserRank(res.getInt(UserBean.UserRank));
				userList.add(ub);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		sqlCtrl.closeCon();
		return userList;
	}
}
