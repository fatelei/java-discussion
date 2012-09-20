package com.discuss.test;

import com.discuss.util.TimeUtil;

public class UtilTest {
	public static void main(String [] args){
		System.out.println(TimeUtil.formatTime(TimeUtil.getNowTime().toString()));
	}
}
