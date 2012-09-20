package com.discuss.test;

import com.discuss.dao.AnswerDao;

public class AnsDaoTest {
	public static void main(String [] args){
		AnswerDao ansDao = new AnswerDao();
		
		//add test
//		AnswerBean ans = new AnswerBean("你说的真是太对了，支持你！", 12, 3);
//		System.out.println(ansDao.addAns(ans));
		
		//delete test
//		ansDao.delSecDisc(3);
		
		//count test
//		System.out.println(ansDao.countAns());
		
		//query	list
//		String findSql = "select * from answer where answer_id = '4';";
//		System.out.println(ansDao.findSecDiscList(findSql).get(0).getAnsContent());
		
		//query test
		System.out.println(ansDao.querySecDisc(4).getAnsContent());
//		System.out.println(ansDao.querySecDiscByObj(2).getAnsContent());
	}
}
