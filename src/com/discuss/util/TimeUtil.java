package com.discuss.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
	
	//formate times
	public static String formatTime(String time){
		Timestamp resTime = Timestamp.valueOf(time);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		return df.format(resTime); 
	}
}
