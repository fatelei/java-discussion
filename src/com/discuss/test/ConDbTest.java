package com.discuss.test;

import com.discuss.util.SqlControl;

public class ConDbTest {
	public static void main(String[] args) {
		SqlControl sql = new SqlControl();
		if(sql.getDbConnetion() != null){
			System.out.println("ok!");
		}
	}

}
