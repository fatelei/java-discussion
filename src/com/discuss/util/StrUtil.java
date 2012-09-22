package com.discuss.util;

import java.io.UnsupportedEncodingException;

public class StrUtil {
	
	public static String tranGBKToUTF(String str){
		if(str != null){
			return transStrEncode(str, "gbk", "utf-8");
		}
		return null;
	}
	
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
}
