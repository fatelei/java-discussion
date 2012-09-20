package com.discuss.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.discuss.bean.DisObjBean;
import com.discuss.bean.SecDisBean;
import com.discuss.util.SqlControl;
import com.discuss.util.TimeUtil;

public class DisObjectDao {
	private SqlControl sqlCtrl = new SqlControl();
	private AnswerDao ansDao = new AnswerDao();
	private SecDiscussDao secDao = new SecDiscussDao();
	private UserDao userDao = new UserDao();
	private ResultSet res = null;
	
	//add new	disObject
	//include	userid  topic  content time	
	public boolean addObject(DisObjBean disObj){
		//Don't	find the same topic
		String addObjSql = "insert into " + DisObjBean.DisTableName + 
				"(" + DisObjBean.DisObjUserID + ", " + DisObjBean.DisObjTopic +", " 
								+ DisObjBean.DisObjContent + ", "  + DisObjBean.DisObjRelTime + ") " +
				"values('" + disObj.getDisObjUserID() + "', '" + disObj.getDisObjTopic() +"', '" 
								+ disObj.getDisObjContent() + "', '" + TimeUtil.getNowTime() + "')";
		System.out.println(addObjSql);
		if(sqlCtrl.update(addObjSql)== -1){
			return false;
		}
		return true;
	}
	
	//delete 
	public boolean delObject(int objId){
		if(!ansDao.delAnsByObj(objId) || !secDao.delSecDiscByObj(objId)){
			return false;
		}		
		
		String delObjSql = "delete from " + DisObjBean.DisTableName + " where " 
				+ DisObjBean.DisObjID + " = '" + objId + "';";
		System.out.println(delObjSql);
		if(sqlCtrl.update(delObjSql) == -1){
			return false;
		}
		return true;
	}
	
	//count all object's num
	public int countObj(){
		String countSql = "select count(*) from " + DisObjBean.DisTableName + " ;";
		return sqlCtrl.count(countSql);
	}
	
	//query	split records	by	pages
	public	ArrayList<DisObjBean> queryObj(int nowPage, int pageCount, String orderBy, Boolean isAsc){
		String splitSql = null;
		int statrCount = (nowPage - 1) * pageCount;
		//middle page
		if(isAsc){
			splitSql = "select * from " + DisObjBean.DisTableName+ " order by " + orderBy + " asc limit " + statrCount + " , " 
					+ pageCount + ";";
		}else{
			splitSql = "select * from " + DisObjBean.DisTableName+ " order by " + orderBy + " desc limit " + statrCount + " , " 
					+ pageCount + ";";
		}
		System.out.println(splitSql);
		return findObjList(splitSql);
	} 
	
	public DisObjBean queryObjByDetail(int id, int nowPage, int pageCount){
		DisObjBean disDetail = queryObjById(id);
		disDetail.setAns(ansDao.querySecDiscByObj(id));
		disDetail.setSecDisList(secDao.querySecDiscByObj(nowPage, pageCount, id, SecDisBean.SecDisRelTime, false));
		return disDetail;
	}
	
	public DisObjBean queryObjById(int id){
		String findSql = "select * from " + DisObjBean.DisTableName + " where " + DisObjBean.DisObjID + " = '" + id + "';";
		System.out.println(findSql);
		return findObjList(findSql).get(0);
	}
	
	//find obj's list
	public ArrayList<DisObjBean> findObjList(String sql){
		ArrayList<DisObjBean> objList = new ArrayList<DisObjBean>();
		res = sqlCtrl.queryResultSet(sql);
		try {
			while(res.next()){
				DisObjBean obj = new DisObjBean();
				obj.setDisObjID(res.getInt(DisObjBean.DisObjID));
				obj.setDisObjTopic(res.getString(DisObjBean.DisObjTopic));
				obj.setDisObjContent(res.getString(DisObjBean.DisObjContent));
				obj.setDisObjUserID(res.getInt(DisObjBean.DisObjUserID));
				obj.setDisObjRelTime(res.getTimestamp(DisObjBean.DisObjRelTime).toString());
				obj.setDisObjLookNum(res.getInt(DisObjBean.DisObjLookNum));
				obj.setDisObjUser(userDao.findUserById(res.getInt(DisObjBean.DisObjUserID)));
				objList.add(obj);			
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		sqlCtrl.closeCon();
		return objList;
	}
}
