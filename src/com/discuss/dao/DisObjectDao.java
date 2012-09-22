package com.discuss.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.discuss.bean.AnswerBean;
import com.discuss.bean.DisObjBean;
import com.discuss.bean.SecDisBean;
import com.discuss.util.SqlControl;
import com.discuss.util.TimeUtil;

public class DisObjectDao {
	private SqlControl sqlCtrl = new SqlControl();
	private ResultSet res = null;
	
	//new dao
	private UserDao userDao = new UserDao();
	private AnswerDao ansDao = new AnswerDao();
	private SecDiscussDao secDao = new SecDiscussDao();
	
	//add new	disObject
	//include	userid  topic  content time	
	public int addObject(DisObjBean disObj){
		//judge this repeat
		if(queryObjInDbByName(disObj.getDisObjTopic())){
			return -1;
		}
		
		//Don't	find the same topic
		String addObjSql = "insert into " + DisObjBean.DisTableName + 
				"(" + DisObjBean.DisObjUserID + ", " + DisObjBean.DisObjTopic +", " 
								+ DisObjBean.DisObjContent + ", "  + DisObjBean.DisObjRelTime + ") " +
				"values('" + disObj.getDisObjUserID() + "', '" + disObj.getDisObjTopic() +"', '" 
								+ disObj.getDisObjContent() + "', '" + TimeUtil.getNowTime() + "')";
//		System.out.println(addObjSql);
		if(sqlCtrl.update(addObjSql)== -1){
			return -1;
		}		
		
		//find new object's id		
		return queryObjIdByName(disObj.getDisObjTopic());
	}
	
	//delete 
	public boolean delObject(int objId){
		//delete the object's answer
		String delAnsSql = "delete from " + AnswerBean.AnsTable + " where " 
				+ AnswerBean.AnsObjID + " = '" + objId + "';";
		System.out.println(delAnsSql);
		if(sqlCtrl.update(delAnsSql) == -1){
			return false;
		}
		
		//delete the object's secdiscuss
		String delSecDisSql = "delete from " + SecDisBean.SecDisTable + " where " 
				+ SecDisBean.SecDisObjectId + " = '" + objId + "';";
		System.out.println(delSecDisSql);
		if(sqlCtrl.update(delSecDisSql) == -1){
			return false;
		}
		
		//delete this obj's info
		String delObjSql = "delete from " + DisObjBean.DisTableName + " where " 
				+ DisObjBean.DisObjID + " = '" + objId + "';";
		System.out.println(delObjSql);
		if(sqlCtrl.update(delObjSql) == -1){
			return false;
		}
		return true;
	}
		
	//count 
	public int countObj(){
		String countSql = "select count(*) from " + DisObjBean.DisTableName + " ;";
		return sqlCtrl.getOneInt(countSql);
	}
	
	//query	
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
		disDetail.setSecDisList(secDao.querySecDiscByObj(nowPage, pageCount, id, SecDisBean.SecDisRelTime, true));
		return disDetail;
	}
	
	public DisObjBean queryObjById(int id){
		String findSql = "select * from " + DisObjBean.DisTableName + " where " + DisObjBean.DisObjID + " = '" + id + "';";
		System.out.println(findSql);
		return findObjList(findSql).get(0);
	}
	
	public int queryObjIdByName(String topicName){
		String querySql = "select " + DisObjBean.DisObjID + " from " + DisObjBean.DisTableName + " where "
																		+ DisObjBean.DisObjTopic + " = '" + topicName + "' ;";
		return sqlCtrl.getOneInt(querySql);
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
				obj.setDisObjRelTime(TimeUtil.formatTime(res.getTimestamp(DisObjBean.DisObjRelTime).toString()));
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

	//judge this Object is in the db or not
	public boolean queryObjInDbByName(String objName){
		String queryString = "select * from " + DisObjBean.DisTableName + " where " + DisObjBean.DisObjTopic + " = '" + objName +"'; ";
//		System.out.println("judge reapt :" + queryString );
		if(findObjList(queryString).size() > 0){
			return true;
		}
		return false;
	}

	//update this object looknum
	public boolean updateTheLookNum(int objId, int oneSum){
		String findOldSql = "select " + DisObjBean.DisObjLookNum + " from " + DisObjBean.DisTableName + 
					" where " + DisObjBean.DisObjID + " = " + objId + " ;";
//		System.out.println(findOldSql);
		int num = sqlCtrl.getOneInt(findOldSql);
		num += oneSum;
		String updateSql = "update " + DisObjBean.DisTableName + " set " + DisObjBean.DisObjLookNum + " = "
											+ " '" + num +"' where " + DisObjBean.DisObjID + " = '" + objId +"';";
//		System.out.println(updateSql);
		if(sqlCtrl.update(updateSql) == -1){
			//sql error
			return false;
		}
		return true;
	}
	
	//query the look num of object
	public int findLookNumById(int objId){
		String findLookStr = "select " + DisObjBean.DisObjLookNum + " from " 
						+ DisObjBean.DisTableName + " where " + DisObjBean.DisObjID + " = " + objId + " ;";
		return sqlCtrl.getOneInt(findLookStr);				
	}
}
