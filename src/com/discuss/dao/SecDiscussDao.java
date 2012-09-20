package com.discuss.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.discuss.bean.SecDisBean;
import com.discuss.util.SqlControl;
import com.discuss.util.TimeUtil;

public class SecDiscussDao {
	private SqlControl sqlCtrl = new SqlControl();
	private ResultSet res = null;
	private UserDao userDao = null;
	
	//add new	SecDiscuss
	//include	the content of seconded discuss , userId , ObjId and now time 
	public boolean addSecDisc(SecDisBean disObj){
		//Don't	find the same topic
		String addSecDisSql = "insert into  " + SecDisBean.SecDisTable + 
				" (" + SecDisBean.SecDisContent + ", " + SecDisBean.SecDisObjectId +", " 
								+ SecDisBean.SecDisUserId + ", "  + SecDisBean.SecDisRelTime + ") " +
				"values ('" + disObj.getSecDisContent() + "', '" + disObj.getSecDisObjectId() +"', '" 
								+ disObj.getSecDisUserId() + "', '" + TimeUtil.getNowTime() + "')";
		System.out.println(addSecDisSql);
		if(sqlCtrl.update(addSecDisSql)== -1){
			return false;
		}
		return true;
	}
	
	//delete 
	public boolean delSecDisc(int SecDisId){
		String delSecDisSql = "delete from " + SecDisBean.SecDisTable + " where " 
				+ SecDisBean.SecDisId + " = '" + SecDisId + "';";
		System.out.println(delSecDisSql);
		if(sqlCtrl.update(delSecDisSql) == -1){
			return false;
		}
		return true;
	}
	
	public boolean delSecDiscByObj(int objId){
		String delSecDisSql = "delete from " + SecDisBean.SecDisTable + " where " 
				+ SecDisBean.SecDisObjectId + " = '" + objId + "';";
		System.out.println(delSecDisSql);
		if(sqlCtrl.update(delSecDisSql) == -1){
			return false;
		}
		return true;
	}
	
	public boolean delSecDiscByUser(int userId){
		String delSecDisSql = "delete from " + SecDisBean.SecDisTable + " where " 
				+ SecDisBean.SecDisUserId + " = '" + userId + "';";
		System.out.println(delSecDisSql);
		if(sqlCtrl.update(delSecDisSql) == -1){
			return false;
		}
		return true;
	}
	
	//count all
	public int countSecDisc(){
		String countSql = "select count(*) from " + SecDisBean.SecDisTable + " ;";
		return sqlCtrl.count(countSql);
	}
	
	//query	split records	by	pages
	public	ArrayList<SecDisBean> querySecDisc(int nowPage, int pageCount, String orderBy, Boolean isAsc){
		String splitSql = null;
		int statrCount = (nowPage - 1) * pageCount;
		//middle page
		if(isAsc){
			splitSql = "select * from " + SecDisBean.SecDisTable+ " order by " + orderBy + " asc limit " + statrCount + " , " 
					+ pageCount + ";";
		}else{
			splitSql = "select * from " + SecDisBean.SecDisTable+ " order by " + orderBy + " desc limit " + statrCount + " , " 
					+ pageCount + ";";
		}
//		System.out.println(splitSql);
		return findSecDiscList(splitSql);
	} 
	
	//query	recods by Discuss object
	public	ArrayList<SecDisBean> querySecDiscByObj(int nowPage, int pageCount, int objId, String orderBy, Boolean isAsc){
		String splitSql = null;
		int statrCount = (nowPage - 1) * pageCount;
		//middle page
		if(isAsc){
			splitSql = "select * from " + SecDisBean.SecDisTable+ " where " + SecDisBean.SecDisObjectId +" = '" +objId 
					+ "' order by " + orderBy + " asc limit " + statrCount + " , " + pageCount + ";";
		}else{
			splitSql = "select * from " + SecDisBean.SecDisTable+ " where " + SecDisBean.SecDisObjectId +" = '" +objId 
					+ "' order by " + orderBy + " desc limit " + statrCount + " , " + pageCount + ";";
		}
//		System.out.println(splitSql);
		return findSecDiscList(splitSql);
	} 
	
	//find obj's list
	public ArrayList<SecDisBean> findSecDiscList(String sql){
		ArrayList<SecDisBean> secDisList = new ArrayList<SecDisBean>();
		res = sqlCtrl.queryResultSet(sql);
		try {
			while(res.next()){
				SecDisBean secDis = new SecDisBean();
				secDis.setSecDisId(res.getInt(SecDisBean.SecDisId));
				secDis.setSecDisContent(res.getString(SecDisBean.SecDisContent));
				secDis.setSecDisSupNum(res.getInt(SecDisBean.SecDisSupNum));
				secDis.setSecDisOppNum(res.getInt(SecDisBean.SecDisOppNum));
				secDis.setSecDisObjectId(res.getInt(SecDisBean.SecDisObjectId));
				secDis.setSecDisUserId(res.getInt(SecDisBean.SecDisUserId));
				secDis.setSecDisRelTime(TimeUtil.formatTime(res.getTimestamp(SecDisBean.SecDisRelTime).toString()));

				secDis.setSecDisUser(userDao.findUserById(res.getInt(SecDisBean.SecDisUserId)));
				secDisList.add(secDis);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		sqlCtrl.closeCon();
		return secDisList;
	}
}
