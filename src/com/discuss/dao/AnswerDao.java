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

	//add new	answer
	//include	the content of answer content , userId , ObjId and now time 
	public boolean addAns(AnswerBean disObj){
		//Don't	find the same topic
		String addAnsSql = "insert into  " + AnswerBean.AnsTable + 
				" (" + AnswerBean.AnsContent + ", " + AnswerBean.AnsObjID +", " 
								+ AnswerBean.AnsUserID + ", "  + AnswerBean.AnsObjRelTime + ") " +
				"values ('" + disObj.getAnsContent() + "', '" + disObj.getAnsObjID() +"', '" 
								+ disObj.getAnsUserID() + "', '" + TimeUtil.getNowTime() + "')";
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
	
	public boolean delAnsByObj(int objId){
		String delAnsSql = "delete from " + AnswerBean.AnsTable + " where " 
				+ AnswerBean.AnsObjID + " = '" + objId + "';";
		System.out.println(delAnsSql);
		if(sqlCtrl.update(delAnsSql) == -1){
			return false;
		}
		return true;
	}
	
	//count all
 	public int countAns(){
		String countSql = "select count(*) from " + AnswerBean.AnsTable + " ;";
		return sqlCtrl.count(countSql);
	}
	/*
	//query	split records	by	pages
	public	ArrayList<AnswerBean> querySecDisc(int nowPage, int pageCount, String orderBy, Boolean isAsc){
		String splitSql = null;
		int statrCount = (nowPage - 1) * pageCount;
		//middle page
		if(isAsc){
			splitSql = "select * from " + AnswerBean.SecDisTable+ " order by " + orderBy + " asc limit " + statrCount + " , " 
					+ pageCount + ";";
		}else{
			splitSql = "select * from " + AnswerBean.SecDisTable+ " order by " + orderBy + " desc limit " + statrCount + " , " 
					+ pageCount + ";";
		}
//		System.out.println(splitSql);
		return findSecDiscList(splitSql);
	} 
	*/
	
	//find obj's list
	public ArrayList<AnswerBean> findSecDiscList(String sql){
		ArrayList<AnswerBean> ansList = new ArrayList<AnswerBean>();
		res = sqlCtrl.queryResultSet(sql);
		try {
			while(res.next()){
				AnswerBean ans = new AnswerBean();
				ans.setAnsID(res.getInt(AnswerBean.AnsID));
				ans.setAnsContent(res.getString(AnswerBean.AnsContent));
				ans.setAnsObjID(res.getInt(AnswerBean.AnsObjID));
				ans.setAnsUserID(res.getInt(AnswerBean.AnsUserID));
				ans.setAnsObjRelTime(res.getString(AnswerBean.AnsObjRelTime));
				
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
