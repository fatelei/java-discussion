package com.discuss.util;

import java.io.UnsupportedEncodingException;

public class StrUtil {
	
	public static String tranISOToUTF(String str){
		if(str != null){
			return transStrEncode(str, "iso8859-1", "utf-8");
		}
		return null;
	}
	
	//transform string encoding
	public static String transStrEncode(String srcStr, String oldEnc, String newEnc){
			try {
				return new String(srcStr.getBytes(oldEnc), newEnc);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} 
			return null;
	}
	
	//format ip to normal
	public String formatIpAddr(String ipAdd){
		String [] listStr = ipAdd.split(".");
		String res = null;
		for(String str : listStr){
			while(str.length() < 3){
				str = "0" + str;
			} 
			res += str + ".";
		}
		return res.substring(0, res.length() - 1);
	}
	
}
