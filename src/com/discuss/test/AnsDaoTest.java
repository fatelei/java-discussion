package com.discuss.test;

import com.discuss.bean.AnswerBean;
import com.discuss.dao.AnswerDao;

public class AnsDaoTest {
	public static void main(String [] args){
		AnswerDao ansDao = new AnswerDao();
		
		//add test
//		AnswerBean ans = new AnswerBean("你说的真是太对了，支持你！", 12, 2);
//		ansDao.addSecDisc(ans);
		
		//delete test
//		ansDao.delSecDisc(3);
		
		//count test
//		System.out.println(ansDao.countAns());
		
		//query	list
//		String findSql = "select * from discussobject where ans_id = '2';";
//		System.out.println(ansDao.findObjList(findSql).get(0).getansTopic());
		
		//query test
//		System.out.println(ansDao.queryObj(1, 10, AnswerBean.ansContent, false).get(0).getansID());
	}
}
