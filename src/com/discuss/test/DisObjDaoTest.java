package com.discuss.test;

import com.discuss.dao.DisObjectDao;

public class DisObjDaoTest {
	public static void main(String [] args){
		DisObjectDao disObjDao = new DisObjectDao();
		
		//add test
//		DisObjBean disObj = new DisObjBean("Fuck Japanese", "hdfjiajeofjfojdi", 12);
//		System.out.println(disObjDao.addObject(disObj));
		
		//delete test
		//disObjDao.delObject(1);
		
		//query	list
//		String findSql = "select * from discussobject where disobj_id = '2';";
//		System.out.println(disObjDao.findObjList(findSql).get(0).getDisObjTopic());
		
		//count test
		//System.out.println(disObjDao.countObj());
		
		//query test
//		System.out.println(disObjDao.queryObj(1, 10, DisObjBean.DisObjContent, false).get(0).getDisObjID());
		
//		DisObjBean dis = disObjDao.queryObjByDetail(2, 1, 10);
//		System.out.println(dis.getDisObjContent());
//		System.out.println(dis.getAns().getAnsContent());
//		System.out.println(dis.getSecDisList().size());
		
		System.out.println(disObjDao.findLookNumById(2));
	}
}
