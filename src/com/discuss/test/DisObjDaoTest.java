package com.discuss.test;

import com.discuss.bean.DisObjBean;
import com.discuss.dao.DisObjectDao;

public class DisObjDaoTest {
	public static void main(String [] args){
		DisObjectDao disObjDao = new DisObjectDao();
		
		//add test
//		DisObjBean disObj = new DisObjBean("Fuck Japanese", "hdfjiajeofjfojdi", 12);
//		disObjDao.addObject(disObj);
		
		//delete test
		//disObjDao.delObject(1);
		
		//query	list
//		String findSql = "select * from discussobject where disobj_id = '2';";
//		System.out.println(disObjDao.findObjList(findSql).get(0).getDisObjTopic());
		
		//count test
		//System.out.println(disObjDao.countObj());
		
		//query test
		System.out.println(disObjDao.queryObj(1, 10, DisObjBean.DisObjContent, false).get(0).getDisObjID());
	}
}
