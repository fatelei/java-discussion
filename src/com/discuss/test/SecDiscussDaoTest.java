package com.discuss.test;

import com.discuss.bean.SecDisBean;
import com.discuss.dao.SecDiscussDao;

public class SecDiscussDaoTest {
	public static void main(String [] args){
		SecDiscussDao secDis = new SecDiscussDao();
		//add test
//		SecDisBean sec= new SecDisBean("小日本滚出钓鱼岛！", 12, 2);
//		secDis.addSecDisc(sec);
		//delete test
		//secDis.delSecDisc(2);
		
		//query	list
//		String findSql = "select * from discussobject where secDis_id = '2';";
//		System.out.println(secDisDao.findObjList(findSql).get(0).getsecDisTopic());
		
		//count test
//		System.out.println(secDis.countSecDisc());
		
		//query test
		System.out.println(secDis.querySecDisc(1, 10, SecDisBean.SecDisId, true).get(0).getSecDisContent());
	}
}
