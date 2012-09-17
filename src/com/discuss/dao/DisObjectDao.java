package com.discuss.dao;

import java.sql.ResultSet;

import com.discuss.bean.DisObjBean;
import com.discuss.util.SqlControl;
import com.discuss.util.TimeUtil;

public class DisObjectDao {
	private SqlControl sqlCtrl = new SqlControl();
	private ResultSet res = null;
	
	//add new	disObject
	//include	userid topic content time	
	public boolean addObject(DisObjBean disObj){
		//Don't	find the same topic
		String addUserSql = "insert into  " + DisObjBean.DisTableName + 
				" (" + DisObjBean.DisObjUserID + ", " + DisObjBean.DisObjTopic +", " 
								+ DisObjBean.DisObjContent + ", "  + DisObjBean.DisObjRelTime + ") " +
				"values ('" + disObj.getDisObjUserID() + "', '" + disObj.getDisObjTopic() +"', '" 
								+ disObj.getDisObjContent() + "', '" + TimeUtil.getNowTime() + "')";
		System.out.println(addUserSql);
		if(sqlCtrl.update(addUserSql)== -1){
			return false;
		}
		return false;
	}
}
