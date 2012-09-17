package com.discuss.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	//get now times
	public static String getCurrentTime(){
		Calendar ca = Calendar.getInstance();
		Date date = ca.getTime();
		return date.toString();
	}
	
	//get now timestamp
	public static Timestamp getNowTime(){
		return new Timestamp(System.currentTimeMillis());		
	}
	
}
