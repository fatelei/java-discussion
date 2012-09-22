package com.discuss.util;

import java.io.UnsupportedEncodingException;

public class StringUtil {
	//transform string encoding
	public static String transStrEncode(String srcStr, String oldEnc, String newEnc){
			try {
				return new String(srcStr.getBytes("iso8859-1"), "gb2312");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} 
			return null;
	}
}
