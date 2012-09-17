package com.discuss.test;

import com.discuss.bean.DisObjBean;
import com.discuss.dao.DisObjectDao;

public class DisObjDaoTest {
	public static void main(String [] args){
		DisObjectDao disObjDao = new DisObjectDao();
		
		DisObjBean disObj = new DisObjBean();
		disObj.setDisObjContent("打倒日本帝国主义！");
		disObj.setDisObjTopic("Fuck Japanese");
		disObj.setDisObjUserID(12);
		
		disObjDao.addObject(disObj);
	}
}
