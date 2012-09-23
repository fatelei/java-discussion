package com.discuss.test;

import com.discuss.dao.SecVoteIpDao;

public class SecVoteIpDaoTest {
	public static void main(String[] args) {
		SecVoteIpDao svid = new SecVoteIpDao();
//		SecVoteIpBean svi = new SecVoteIpBean("127.0.0.1", 13, 1);
//		svid.addIpAdd(svi);
		
		
		System.out.println(svid.isAreadyVoted("127.0.0.1", 1));
	}
	
	

}
