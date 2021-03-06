package com.discuss.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.discuss.bean.SecDisBean;
import com.discuss.bean.SecVoteIpBean;
import com.discuss.util.SqlControl;
import com.discuss.util.TimeUtil;

public class SecDiscussDao {
	private SqlControl sqlCtrl = new SqlControl();
	private ResultSet res = null;
	private UserDao userDao = new UserDao();
	private SecVoteIpDao svid = new SecVoteIpDao();; 
	
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
	
	//count all
	public int countSecDisc(int postId){
		String countSql = "select count(*) from " + SecDisBean.SecDisTable + " where " + SecDisBean.SecDisObjectId + "='" + postId + "';";
		return sqlCtrl.getOneInt(countSql);
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
				//System.out.println("id:\t"+ secDis.getSecDisId() +"\r\t\t回复内容是：\t" + secDis.getSecDisContent());
				secDisList.add(secDis);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		sqlCtrl.closeCon();
		return secDisList;
	}

	//update the support num
	public int updateTheSuptNum(int secDisId, int oneSum, String ip, int userId, int secId){
		//look had yet voted
		if(svid.isAreadyVoted(ip, secId)){
			return -2;
		}
		
		String findOldSql = "select " + SecDisBean.SecDisSupNum + " from " + SecDisBean.SecDisTable + 
					" where " + SecDisBean.SecDisId + " = " + secDisId + " ;";
		System.out.println(findOldSql);
		int num = sqlCtrl.getOneInt(findOldSql);
		num += oneSum;
		String updateSql = "update " + SecDisBean.SecDisTable + " set " + SecDisBean.SecDisSupNum + " = "
											+ " '" + num +"' where " + SecDisBean.SecDisId + " = '" + secDisId +"';";
//		System.out.println(updateSql);
		if(sqlCtrl.update(updateSql) == -1){
			//sql error
			return -1;
		}
		
		//put the suporter's ip in database
		svid.addIpAdd(new SecVoteIpBean(ip, userId, secId));
		
		return sqlCtrl.getOneInt(findOldSql);
	}
	
	//update the opposite num
	public int updateTheOppNum(int secDisId, int oneSum, String ip, int userId, int secId){
		
		//look had yet voted
		if(svid.isAreadyVoted(ip, secId)){
			return -2;
		}
		
		
		String findOldSql = "select " + SecDisBean.SecDisOppNum + " from " + SecDisBean.SecDisTable + 
					" where " + SecDisBean.SecDisId + " = " + secDisId + " ;";
		System.out.println(findOldSql);
		int num = sqlCtrl.getOneInt(findOldSql);
		num += oneSum;
		String updateSql = "update " + SecDisBean.SecDisTable + " set " + SecDisBean.SecDisOppNum + " = "
											+ " '" + num +"' where " + SecDisBean.SecDisId + " = '" + secDisId +"';";
		if(sqlCtrl.update(updateSql) == -1){
			return -1;
		}
		
		//put the suporter's ip in database
		svid.addIpAdd(new SecVoteIpBean(ip, userId, secId));
		
		return sqlCtrl.getOneInt(findOldSql);
	}
}
