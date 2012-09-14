package com.discuss.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.discuss.bean.UserBean;
import com.discuss.util.SqlControl;

public class UserDao {
	private SqlControl sqlCtrl = new SqlControl();
	private ResultSet res = null;
	
	//��¼�ж�
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
	
	//����û�
	public boolean addUsr(UserBean user){
		String addUserSql = "insert into  " + UserBean.UserTable + " (" + UserBean.UserName +", " + UserBean.UserPassword + ", " + UserBean.UserRank + ") " +
							"values ('" + user.getUserName()+"', '" + user.getUserPassword() +"', '" + String.valueOf(2) + "')";
		System.out.println(addUserSql);
		System.out.println(sqlCtrl.update(addUserSql));
		
		return true;
	}
		
	//��ȡ�û��б�
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
