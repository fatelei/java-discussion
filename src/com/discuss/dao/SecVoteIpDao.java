package com.discuss.dao;
import com.discuss.bean.SecVoteIpBean;
import com.discuss.util.SqlControl;

public class SecVoteIpDao {
	private SqlControl sqlCtrl = new SqlControl();
	
	//add vote ip addr , userId and secDisId
	public boolean addIpAdd(SecVoteIpBean svi){
		String addIpStr = "insert into " + SecVoteIpBean.TaName + 
							 "(" +SecVoteIpBean.Addr + ", " + SecVoteIpBean.SecDis + ", " + SecVoteIpBean.User + 
								") values ('" + svi.getIpAdd() + "', '" + svi.getSecDisId() + "', '" + svi.getUserId() + "');"; 
//		System.out.println(addIpStr);
		if(sqlCtrl.update(addIpStr) == -1){
			return false;
		}
		return true;
	}
	
	//judge the ip address is voted or not
	public boolean isAreadyVoted(String ipAddr, int secObId){
		String judStr = "select count(*) from " + SecVoteIpBean.TaName + " where " 
												+ SecVoteIpBean.Addr + " = '" + ipAddr + "' and "
												+ SecVoteIpBean.SecDis + " = '" + secObId + "';";
//		System.out.println(judStr);
		if(sqlCtrl.getOneInt(judStr) > 0){
			return true;
		}
		return false;
	}
	
}
