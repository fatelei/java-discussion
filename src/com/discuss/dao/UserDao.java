package com.discuss.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.discuss.bean.UserBean;
import com.discuss.util.SqlControl;

public class UserDao {
	private SqlControl sqlCtrl = new SqlControl();
	private ResultSet res = null;
	
	//登录判断
	public boolean loginOk(String userName, String password){
		String findUserSql = "select * from " + UserBean.UserTable + 
										" where " + UserBean.UserName +" = '" + userName + "';";
		ArrayList<UserBean> userList = findUserList(findUserSql);
		if(userList.size() != 0){
			if(userList.get(0).getUserPassword().equals(password)){
				return true;
			}else{
				return false;
			}			
		}else{
			return false;
		}
	}
	
	//添加用户
	public boolean addUsr(UserBean user){
		//判重复
		String coutUserSql = "select count(*) from " + UserBean.UserTable + " where " 
											+ UserBean.UserName + " = '" + user.getUserName() + "';";
		if(sqlCtrl.count(coutUserSql) > 0){
			//用户名重复
			return false;
		}else{
			//添加
			String addUserSql = "insert into  " + UserBean.UserTable + " (" + UserBean.UserName +", " + UserBean.UserPassword + ", " + UserBean.UserRank + ") " +
					"values ('" + user.getUserName()+"', '" + user.getUserPassword() +"', '" + String.valueOf(2) + "')";
			System.out.println(addUserSql);
			try {
				System.out.println(sqlCtrl.update(addUserSql));
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
		
	//获取用户列表
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
		}
		sqlCtrl.closeCon();
		return userList;
	}
}
