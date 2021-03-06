package com.discuss.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.discuss.bean.AnswerBean;
import com.discuss.util.SqlControl;
import com.discuss.util.TimeUtil;

public class AnswerDao {
	private SqlControl sqlCtrl = new SqlControl();
	private ResultSet res = null;
	private UserDao userDao = null;

	//add new	answer
	//include	the content of answer content , userId , ObjId and now time 
	public boolean addAns(AnswerBean ansObj){
		//have answered ?
		String isAnsedSql = "select count(*) from " + AnswerBean.AnsTable + " where " + AnswerBean.AnsObjID + " = '"+ ansObj.getAnsObjID() +"' ;"; 
//		System.out.println(isAnsedSql);
		if(sqlCtrl.getOneInt(isAnsedSql) > 0){
			//The user has already repeated for the object
			return false;
		}
		
		//Add the answer info to db;
		String addAnsSql = "insert into  " + AnswerBean.AnsTable + 
				" (" + AnswerBean.AnsContent + ", " + AnswerBean.AnsObjID +", " 
								+ AnswerBean.AnsUserID + ", "  + AnswerBean.AnsObjRelTime + ") " +
				"values ('" + ansObj.getAnsContent() + "', '" + ansObj.getAnsObjID() +"', '" 
								+ ansObj.getAnsUserID() + "', '" + TimeUtil.getNowTime() + "')";
		System.out.println(addAnsSql);
		if(sqlCtrl.update(addAnsSql)== -1){
			return false;
		}
		return true;
	}
	
	//delete 
	public boolean delAns(int ansId){
		String delAnsSql = "delete from " + AnswerBean.AnsTable + " where " 
				+ AnswerBean.AnsID + " = '" + ansId + "';";
		System.out.println(delAnsSql);
		if(sqlCtrl.update(delAnsSql) == -1){
			return false;
		}
		return true;
	}
	
	//count all
 	public int countAns(){
		String countSql = "select count(*) from " + AnswerBean.AnsTable + " ;";
		return sqlCtrl.getOneInt(countSql);
	}
	
	//query	split records	by	pages
	public	AnswerBean querySecDisc(int id){
			String findAnsSql = "select * from " + AnswerBean.AnsTable + " where " + AnswerBean.AnsID + " = '" + id+ "';";
		System.out.println(findAnsSql);
		return findSecDiscList(findAnsSql).get(0);
	} 
	
	public	AnswerBean querySecDiscByObj(int objId){
		String findAnsSql = "select * from " + AnswerBean.AnsTable + " where " + AnswerBean.AnsObjID + " = '" + objId+ "';";
		System.out.println(findAnsSql);
		ArrayList<AnswerBean> tmp = findSecDiscList(findAnsSql);
		if (tmp.size() > 0) {
			return tmp.get(0);
		} else {
			return null;
		}
	} 
	
	//find obj's list
	public ArrayList<AnswerBean> findSecDiscList(String sql){
		ArrayList<AnswerBean> ansList = new ArrayList<AnswerBean>();
		res = sqlCtrl.queryResultSet(sql);
		userDao = new UserDao();
		try {
			while(res.next()){
				AnswerBean ans = new AnswerBean();
				ans.setAnsID(res.getInt(AnswerBean.AnsID));
				ans.setAnsContent(res.getString(AnswerBean.AnsContent));
				ans.setAnsObjID(res.getInt(AnswerBean.AnsObjID));
				ans.setAnsUserID(res.getInt(AnswerBean.AnsUserID));
				ans.setAnsObjRelTime(TimeUtil.formatTime(res.getTimestamp(AnswerBean.AnsObjRelTime).toString()));
				ans.setAnsUser(userDao.findUserById(res.getInt(AnswerBean.AnsUserID)));
				ansList.add(ans);		
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		sqlCtrl.closeCon();
		return ansList;
	}
}
